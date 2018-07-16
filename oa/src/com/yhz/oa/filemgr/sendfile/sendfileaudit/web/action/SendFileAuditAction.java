package com.yhz.oa.filemgr.sendfile.sendfileaudit.web.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.yhz.oa.filemgr.sendfile.sendfileaudit.business.ebi.AuditRecordEbi;
import com.yhz.oa.filemgr.sendfile.sendfileaudit.vo.AuditRecordModel;
import com.yhz.oa.filemgr.sendfile.sendfileaudit.vo.AuditRecordQueryModel;
import com.yhz.oa.filemgr.sendfile.sendfileaudit.web.vo.SendFileAuditWebModel;
import com.yhz.oa.filemgr.sendfile.sendfilerequest.business.ebi.SendFileRequestEbi;
import com.yhz.oa.filemgr.sendfile.sendfilerequest.vo.SendFileRequestModel;

public class SendFileAuditAction extends ActionSupport{
	private SendFileRequestEbi sfrEbi = null;
	public void setSfrEbi(SendFileRequestEbi sfrEbi){
		this.sfrEbi = sfrEbi;
	}
	private AuditRecordEbi ebi = null;
	public void setEbi(AuditRecordEbi ebi){
		this.ebi = ebi;
	}
	
	
	public String requestUuid = "";
	public String taskId = "";
	
	
	public SendFileRequestModel sfrm = new SendFileRequestModel();
	public SendFileAuditWebModel wm = new SendFileAuditWebModel();
	public AuditRecordModel arm = new AuditRecordModel();
	
	
	
	public String msg = "";
	public String execute(){
		
		if("toDepAudit".equals(wm.getSubmitFlag())){
			return this.toDepAudit();
		}else if("saveDepAudit".equals(wm.getSubmitFlag())){
			return this.saveDepAudit();
		}else if("toMyUpdate".equals(wm.getSubmitFlag())){
			return this.toMyUpdate();
		}else if("saveMyUpdate".equals(wm.getSubmitFlag())){
			return this.saveMyUpdate();
		}else if("toDepManagerAudit".equals(wm.getSubmitFlag())){
			return this.toDepManagerAudit();
		}else if("saveDepManagerAudit".equals(wm.getSubmitFlag())){
			return this.saveDepManagerAudit();
		}else if("toDepManagerSign".equals(wm.getSubmitFlag())){
			return this.toDepManagerSign();
		}else if("saveDepManagerSign".equals(wm.getSubmitFlag())){
			return this.saveDepManagerSign();
		}else if("toCheck".equals(wm.getSubmitFlag())){
			return this.toCheck();
		}else if("saveCheck".equals(wm.getSubmitFlag())){
			return this.saveCheck();
		}else if("getHistoryAudit".equals(wm.getSubmitFlag())){
			return this.getHistoryAudit();
		}else if("getHistoryAudit2".equals(wm.getSubmitFlag())){
			return this.getHistoryAudit2();
		}else if("getHistoryAudit3".equals(wm.getSubmitFlag())){
			return this.getHistoryAudit3();
		}
		
		
		return this.SUCCESS;
	}
	private InputStream inputStream;
	public InputStream getInputStream() {
		return inputStream;
	}
	private String getHistoryAudit3(){
		//这里去查询数据
		AuditRecordQueryModel qm = new AuditRecordQueryModel();
		qm.setRequestUuid(this.requestUuid);
		qm.setAuditType("1");
		
		List<AuditRecordModel> list = this.ebi.getByCondition(qm, false, 0, 0);
		
		
		//JSON
		//[{a:'11',b:'22'},{a:'22',b:'333'}]
		String retStr = "[";
		for(AuditRecordModel arm : list){
			retStr +="{auditUser:'"+arm.getAuditUser()+"',auditTime:'"+arm.getAuditTime()+"'" +
					",auditResult:'"+arm.getAuditResult()+"',auditDesc:'"+arm.getAuditDesc()+"'},";
			
		}
		retStr = retStr.substring(0,retStr.length()-1);
		retStr +="]";
		
		try {
			inputStream = new ByteArrayInputStream(retStr.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}		

		return "toDepAuditAjax3";
		
	}

	private String getHistoryAudit2(){
		//这里去查询数据
		AuditRecordQueryModel qm = new AuditRecordQueryModel();
		qm.setRequestUuid(this.requestUuid);
		qm.setAuditType("1");
		
		List<AuditRecordModel> list = this.ebi.getByCondition(qm, false, 0, 0);
		
		String retStr = "";
		//JSON
		//[{a:'11',b:'22'},{a:'22',b:'333'}]
		
		for(AuditRecordModel arm : list){
			retStr +=arm.getAuditUser()+","+arm.getAuditTime()+","+arm.getAuditResult()+","+arm.getAuditDesc()+"|";
		}
		retStr = retStr.substring(0,retStr.length()-1);
		
		try {
			inputStream = new ByteArrayInputStream(retStr.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}		

		return "toDepAuditAjax2";
		
	}
	private String getHistoryAudit(){
		//这里去查询数据
		AuditRecordQueryModel qm = new AuditRecordQueryModel();
		qm.setRequestUuid(this.requestUuid);
		qm.setAuditType("1");
		
		List<AuditRecordModel> list = this.ebi.getByCondition(qm, false, 0, 0);
		
		wm.setListAR(list);

		return "toDepAuditAjax";
	}
	private String saveCheck(){
		arm.setAuditUser(""+ActionContext.getContext().getSession().get("LoginUserId"));
		this.ebi.saveCheck(arm,taskId);
		
		msg="恭喜您，保存文书核稿记录成功";
		return this.SUCCESS;
	}
	
	private String toCheck(){
		//1:获取要修改的单子的数据
		sfrm = this.sfrEbi.getByUuid(this.requestUuid);
		//2:获取到所有已经审核的审核数据
		AuditRecordQueryModel qm = new AuditRecordQueryModel();
		qm.setRequestUuid(this.requestUuid);
		qm.setAuditType(AuditRecordModel.AUDITTYPE_CHECK);
		
		List<AuditRecordModel> list = this.ebi.getByCondition(qm, false, 0, 0);
		
		wm.setListAR(list);
		return "toCheck";
	}

	private String saveDepManagerSign(){
		this.ebi.saveDepManagerSign(arm, ""+ActionContext.getContext().getSession().get("LoginUserId")
				);
		
		msg="恭喜您，保存会签记录成功";
		return this.SUCCESS;
	}
	
	private String toDepManagerSign(){
		//1:获取要修改的单子的数据
		sfrm = this.sfrEbi.getByUuid(this.requestUuid);
		//2:获取到所有已经审核的审核数据
		AuditRecordQueryModel qm = new AuditRecordQueryModel();
		qm.setRequestUuid(this.requestUuid);
		qm.setAuditType(AuditRecordModel.AUDITTYPE_DEPMANAGER);
		
		List<AuditRecordModel> list = this.ebi.getByCondition(qm, false, 0, 0);
		
		wm.setListAR(list);
		return "toDepManagerSign";
	}
	
	
	private String saveDepManagerAudit(){
		this.ebi.saveDepManagerAudit(arm, wm.isNeedDepMangerSign(),wm.getDepManagerSignUserIds(), taskId);		
		
		msg="恭喜您，保存修改记录成功";
		return this.SUCCESS;
	}
	private String toDepManagerAudit(){
		//1:获取要修改的单子的数据
		sfrm = this.sfrEbi.getByUuid(this.requestUuid);
		//2:获取到所有已经审核的审核数据
		AuditRecordQueryModel qm = new AuditRecordQueryModel();
		qm.setRequestUuid(this.requestUuid);
		qm.setAuditType(AuditRecordModel.AUDITTYPE_DEPMANAGER);
		
		List<AuditRecordModel> list = this.ebi.getByCondition(qm, false, 0, 0);
		
		wm.setListAR(list);
		return "toDepManagerAudit";
	}
	
	private String saveMyUpdate(){
		this.ebi.saveMyUpdate(sfrm, taskId);
		
		msg="恭喜您，保存修改记录成功";
		return this.SUCCESS;
	}
	private String toMyUpdate(){
		//1:获取要修改的单子的数据
		sfrm = this.sfrEbi.getByUuid(this.requestUuid);
		//2:获取到所有已经审核的审核数据
		AuditRecordQueryModel qm = new AuditRecordQueryModel();
		qm.setRequestUuid(this.requestUuid);
		
		List<AuditRecordModel> list = this.ebi.getByCondition(qm, false, 0, 0);
		
		wm.setListAR(list);
		return "toMyUpdate";
	}
	
	private String saveDepAudit(){
		arm.setAuditType(arm.AUDITTYPE_DEP);
		arm.setAuditUser(""+ActionContext.getContext().getSession().get("LoginUserId"));
		
		this.ebi.create(arm,true,true,taskId);
		
		msg="恭喜您，保存审核记录成功";
		return this.SUCCESS;
	}
	private String toDepAudit(){
		//1:获取要审核的单子的数据
		sfrm = this.sfrEbi.getByUuid(this.requestUuid);
//		//2:获取到所有已经审核的审核数据
//		AuditRecordQueryModel qm = new AuditRecordQueryModel();
//		qm.setRequestUuid(this.requestUuid);
//		qm.setAuditType("1");
//		
//		List<AuditRecordModel> list = this.ebi.getByCondition(qm, false, 0, 0);
//		
//		wm.setListAR(list);
		return "toDepAudit";
	}
}
