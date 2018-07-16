package com.yhz.oa.filemgr.sendfile.sendfileaudit.vo;

public class AuditRecordModel {
	public transient final static String STATE_OK = "有效";
	public transient final static String STATE_NO = "无效";
	public transient final static String STATE_TEMP = "临时暂存";
	
	public transient final static String AUDITTYPE_DEP = "1";//部门审核
	public transient final static String AUDITTYPE_DEPMANAGER = "2";//部门领导审核
	public transient final static String AUDITTYPE_CHECK = "3";//文书核稿
	public transient final static String AUDITTYPE_COMPANYMANAGER = "4";//公司领导审核
	
	private String uuid;
	private String auditUser;
	private long auditTime;
	private String requestUuid;
	private String auditResult;
	private String auditDesc;
	private String state;
	private int signNum;
	private String auditType;
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getAuditUser() {
		return auditUser;
	}
	public void setAuditUser(String auditUser) {
		this.auditUser = auditUser;
	}
	public long getAuditTime() {
		return auditTime;
	}
	public void setAuditTime(long auditTime) {
		this.auditTime = auditTime;
	}
	public String getRequestUuid() {
		return requestUuid;
	}
	public void setRequestUuid(String requestUuid) {
		this.requestUuid = requestUuid;
	}
	public String getAuditResult() {
		return auditResult;
	}
	public void setAuditResult(String auditResult) {
		this.auditResult = auditResult;
	}
	public String getAuditDesc() {
		return auditDesc;
	}
	public void setAuditDesc(String auditDesc) {
		this.auditDesc = auditDesc;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getSignNum() {
		return signNum;
	}
	public void setSignNum(int signNum) {
		this.signNum = signNum;
	}
	public String getAuditType() {
		return auditType;
	}
	public void setAuditType(String auditType) {
		this.auditType = auditType;
	}
	
	
}
