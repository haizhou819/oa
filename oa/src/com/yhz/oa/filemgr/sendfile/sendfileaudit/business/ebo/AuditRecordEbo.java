package com.yhz.oa.filemgr.sendfile.sendfileaudit.business.ebo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jbpm.api.ExecutionService;
import org.jbpm.api.TaskService;
import org.jbpm.api.task.Task;

import com.yhz.framework.uuidservice.business.ebi.UuidServiceEbi;
import com.yhz.oa.filemgr.sendfile.sendfileaudit.business.ebi.AuditRecordEbi;
import com.yhz.oa.filemgr.sendfile.sendfileaudit.dao.dao.AuditRecordDAO;
import com.yhz.oa.filemgr.sendfile.sendfileaudit.vo.AuditRecordModel;
import com.yhz.oa.filemgr.sendfile.sendfileaudit.vo.AuditRecordQueryModel;
import com.yhz.oa.filemgr.sendfile.sendfilerequest.business.ebi.SendFileRequestEbi;
import com.yhz.oa.filemgr.sendfile.sendfilerequest.vo.SendFileRequestModel;

public class AuditRecordEbo implements AuditRecordEbi{
	private SendFileRequestEbi sfrEbi = null;
	public void setSfrEbi(SendFileRequestEbi srfEbi){
		this.sfrEbi = srfEbi;
	}
	private ExecutionService es = null;
	public void setEs(ExecutionService es){
		this.es = es;
	}
	
	private TaskService ts = null;
	public void setTs(TaskService ts){
		this.ts = ts;
	}
	
	private AuditRecordDAO dao = null;
	private UuidServiceEbi uuidEbi = null;
	
	
	public void setDao(AuditRecordDAO dao) {
		this.dao = dao;
	}

	public void setUuidEbi(UuidServiceEbi uuidEbi) {
		this.uuidEbi = uuidEbi;
	}

	@Override
	public void create(AuditRecordModel m,boolean needSetNo,boolean needWorkFlow,String taskId) {
		//应该在新增之前把其他的关于这个申请单的审核，修改为无效状态
		if(needSetNo){
			this.dao.setStateToNo(m.getRequestUuid());
		}
		
		//新增
		m.setUuid(uuidEbi.getNextUuid("AuditRecord", "AuditRecord_#", 5, 10));
		m.setAuditTime(System.currentTimeMillis());
		m.setState(m.STATE_OK);
		
		this.dao.create(m);
		
		if(needWorkFlow){
			Map map = new HashMap();
			map.put("depAuditResult", m.getAuditResult());
			ts.completeTask(taskId,map);
		}
	}

	@Override
	public void update(AuditRecordModel m) {
		this.dao.update(m);
	}

	@Override
	public void delete(AuditRecordModel m) {
		this.dao.delete(m);
	}

	@Override
	public AuditRecordModel getByUuid(String uuid) {
		return dao.getByUuid(new AuditRecordModel(), uuid);
	}

	@Override
	public List<AuditRecordModel> getAll(boolean needPage, int fromNum,
			int pageNum) {
		return dao.getAll(new AuditRecordQueryModel(), needPage, fromNum, pageNum);
	}

	@Override
	public List<AuditRecordModel> getByCondition(AuditRecordQueryModel qm,
			boolean needPage, int fromNum, int pageNum) {
		return dao.getByCondition(qm, needPage, fromNum, pageNum);
	}

	@Override
	public int getCount(AuditRecordQueryModel qm) {
		return dao.getCount(qm);
	}

	@Override
	public void saveMyUpdate(SendFileRequestModel sfrm, String taskId) {
		//应该先判断后续的动作
		if("goon".equals(sfrm.getAfterUpdate())){
			//1:保存记录		
			//2:把记录的状态修改为等待修改状态
			sfrm.setState(sfrm.STATE_WAIT_MODIFY);
			this.sfrEbi.update(sfrm);
			
			//3：结束工作			
			ts.completeTask(taskId,"toDepAudit");
		}else if("dispose".equals(sfrm.getAfterUpdate())){
			//1：结束工作			
			ts.completeTask(taskId,"toDispose");
		}
	}

	@Override
	public void saveDepManagerAudit(AuditRecordModel arm,
			boolean needDepMangerSign, String depManagerSignUserIds,String taskId) {
		//1:看看是否需要会签
		if(needDepMangerSign){
			//3:如果需要会签，那么就启动会签的节点
			//3.1:结束自己的工作
			//3.2：启动会签
			arm.setAuditResult("toDepMangerSign");
			Map map = new HashMap();
			map.put("depManagerAuditResult", arm.getAuditResult());
			map.put("depManagerSignUserIds", depManagerSignUserIds);
			
			this.ts.completeTask(taskId,map);
			
			//为每个参与会签的人员预创建审核记录
//			int signNum = uuidEbi.getNextUuid("DepMangerSign", 10);
//			String ss[] = depManagerSignUserIds.split(",");
//			for(String user : ss){
//				AuditRecordModel tm = new AuditRecordModel();
//				tm.setAuditDesc("");
//				tm.setAuditResult("");
//				tm.setAuditTime(0L);
//				tm.setAuditType(tm.AUDITTYPE_DEPMANAGER);
//				tm.setAuditUser(user);
//				tm.setRequestUuid(arm.getRequestUuid());
//				tm.setSignNum(signNum);
//				tm.setState(tm.STATE_TEMP);
//				
//				this.create(tm, false, false, taskId);
//			}			
		}else{
			//2:如果不需要，那么就设置审核结果为true，驱动流程向下
			arm.setAuditResult("toCheck");
			arm.setAuditType(arm.AUDITTYPE_DEPMANAGER);
	
			this.create(arm, true, false, taskId);
			
			Map map = new HashMap();
			map.put("depManagerAuditResult", arm.getAuditResult());
			this.ts.completeTask(taskId,map);
		}		
	}
	
	public void saveDepManagerSign(AuditRecordModel arm,String nowUser){
		String piId = this.sfrEbi.getByUuid(arm.getRequestUuid()).getPiId();
		
		Task mainTask = ts.createTaskQuery().processInstanceId(piId).activityName("部门领导会签").uniqueResult();
	
		if(mainTask==null){
			System.out.println("会签已经结束");
			return;
		}
		//1:先把这个人的审核记录保存下来
		arm.setAuditUser(nowUser);
		arm.setAuditType(arm.AUDITTYPE_DEPMANAGER);
		
		this.create(arm, false, false, "");
		//2:signal自己的工作，由工作流去判断是否能够继续流程
		Map map = new HashMap();
		map.put("nowHqry", nowUser);
		map.put("hqResult", arm.getAuditResult());
		System.out.println("es======"+es);
		System.out.println(piId);
		es.signalExecutionById(piId,map);		
	}

	@Override
	public void saveCheck(AuditRecordModel arm, String taskId) {
		arm.setAuditType(arm.AUDITTYPE_CHECK);
		this.create(arm, true, false, taskId);
		
		this.ts.completeTask(taskId);
	}
}
