package com.yhz.oa.filemgr.sendfile.sendfilerequest.business.ebo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jbpm.api.ExecutionService;
import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.RepositoryService;

import com.yhz.framework.uuidservice.business.ebi.UuidServiceEbi;
import com.yhz.oa.filemgr.sendfile.sendfilerequest.business.ebi.SendFileRequestEbi;
import com.yhz.oa.filemgr.sendfile.sendfilerequest.dao.dao.SendFileRequestDao;
import com.yhz.oa.filemgr.sendfile.sendfilerequest.vo.SendFileRequestModel;
import com.yhz.oa.filemgr.sendfile.sendfilerequest.vo.SendFileRequestQueryModel;

public class SendFileRequestEbo implements SendFileRequestEbi{
	private SendFileRequestDao dao = null;
	private UuidServiceEbi uuidEbi = null;
	private RepositoryService rs = null;
	private ExecutionService es = null;
	public void setEs(ExecutionService es){
		this.es = es;
	}
	public void setRs(RepositoryService rs){
		this.rs = rs;
	}
	public void setDao(SendFileRequestDao dao) {
		this.dao = dao;
	}
	public void setUuidEbi(UuidServiceEbi uuidEbi) {
		this.uuidEbi = uuidEbi;
	}

	@Override
	public String create(SendFileRequestModel m) {
		//1:
		m.setUuid(uuidEbi.getNextUuid("SFRequest","SFRequest_#",5, 10));
		
		dao.create(m);
		return m.getUuid();
	}

	@Override
	public void update(SendFileRequestModel m) {
		dao.update(m);
	}

	@Override
	public void delete(SendFileRequestModel m) {
		dao.delete(m);
	}

	@Override
	public SendFileRequestModel getByUuid(String uuid) {
		return dao.getByUuid(new SendFileRequestModel(), uuid);
	}

	@Override
	public List<SendFileRequestModel> getAll(boolean needPage, int fromNum,
			int pageNum) {
		return dao.getAll(new SendFileRequestQueryModel(), needPage, fromNum, pageNum);
	}

	@Override
	public List<SendFileRequestModel> getByCondition(
			SendFileRequestQueryModel qm, boolean needPage, int fromNum,
			int pageNum) {
		return dao.getByCondition(qm, needPage, fromNum, pageNum);
	}

	@Override
	public int getCount(SendFileRequestQueryModel qm) {
		return dao.getCount(qm);
	}
	@Override
	public String draft(SendFileRequestModel m) {
		//添加默认数据
		m.setState(m.STATE_DRAFT);		
		return this.create(m);
	}
	@Override
	public void toDepAudit(String requestUuid,String startUserId,String webctx) {
		SendFileRequestModel srfm = this.getByUuid(requestUuid);
		srfm.setReqTime(System.currentTimeMillis());
		srfm.setReqUser(startUserId);
		srfm.setState(SendFileRequestModel.STATE_WAIT_DEPAUDIT);
		//this.dao.update(srfm);
		
//		this.dao.updateState(requestUuid, SendFileRequestModel.STATE_WAIT_DEPAUDIT);
		//2:要启动流程
		ProcessDefinition pd = rs.createProcessDefinitionQuery().processDefinitionKey("SendFile").uniqueResult();
		
		Map map = new HashMap();
		map.put("startUserId", startUserId);
		map.put("depAuditUser", "002");		
		map.put("webctx", webctx);
		map.put("requestUuid", requestUuid);
		map.put("depMangerAuditUser", "003");
		map.put("checkUser", "006");
		
		ProcessInstance pi = es.startProcessInstanceByKey(pd.getKey(),map);
		//把piid设置回到申请单里面
		srfm.setPiId(pi.getId());
		this.dao.update(srfm);		
	}
	
	/*public Map<String,String> geContextMap(){
		Map<String,String> map = new HashMap<String,String>();
		map.put("startUserId", startUserId);
		map.put("depAuditUser", "002");		
		map.put("webctx", webctx);
		map.put("requestUuid", requestUuid);
		map.put("depMangerAuditUser", "003");
		map.put("checkUser", "006");
		return map;
	}*/

}
