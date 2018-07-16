package com.yhz.oa.filemgr.sendfile.sendfilerequest.vo;

import com.yhz.framework.format.FormatUtil;


public class SendFileRequestModel {
	public transient final static String STATE_DRAFT = "草稿";
	public transient final static String STATE_WAIT_DEPAUDIT = "等待部门内审";
	public transient final static String STATE_WAIT_MODIFY = "等待修改";
	public transient final static String STATE_WAIT_DEPMANAGER_AUDIT = "等待部门领导审核";
	public transient final static String STATE_WAIT_CHECKFILE = "等待文书核稿";
	public transient final static String STATE_WAIT_COMPANYMANAGER_AUDIT = "等待公司领导审核";
	public transient final static String STATE_WAIT_FILENUM = "等待文书编号";
	public transient final static String STATE_WAIT_PRINTER = "等待打印";
	public transient final static String STATE_WAIT_SEND = "等待分发";
	public transient final static String STATE_WAIT_DISPOSED = "作废";
	public transient final static String STATE_WAIT_ENDED = "结束";
	
	private String uuid;
	private String reqUser;
	private long reqTime;
	private String draftUser;
	private String mainDep;
	private String urgency;
	private String securityLevel;
	private String draftType;
	private String title;
	private String contentDesc;
	private String fileNum;
	private int printNums;
	private String printer;
	private String auditor;
	private String state;
	private String piId;
	
	private transient String afterUpdate = "";
	private transient String draftUserName = "";
	private transient String reqTimeStr = "";
	
	public String getAfterUpdate() {
		return afterUpdate;
	}
	public void setAfterUpdate(String afterUpdate) {
		this.afterUpdate = afterUpdate;
	}
	public String getReqTimeStr(){
		String str = "";
		if(this.reqTime > 0){
			str = FormatUtil.formatDate(this.reqTime, "");
		}
		return str;
	}
	public void setReqTimeStr(String str){
		if(str!=null && str.trim().length()>0){
			this.reqTime = FormatUtil.formateDateStringToLong(str,"");
		}		
	}
	public String getDraftUserName() {
		return draftUserName;
	}
	public void setDraftUserName(String draftUserName) {
		this.draftUserName = draftUserName;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getReqUser() {
		return reqUser;
	}
	public void setReqUser(String reqUser) {
		this.reqUser = reqUser;
	}
	public long getReqTime() {
		return reqTime;
	}
	public void setReqTime(long reqTime) {
		this.reqTime = reqTime;
	}
	public String getDraftUser() {
		return draftUser;
	}
	public void setDraftUser(String draftUser) {
		this.draftUser = draftUser;
	}
	public String getMainDep() {
		return mainDep;
	}
	public void setMainDep(String mainDep) {
		this.mainDep = mainDep;
	}
	public String getUrgency() {
		return urgency;
	}
	public void setUrgency(String urgency) {
		this.urgency = urgency;
	}
	public String getSecurityLevel() {
		return securityLevel;
	}
	public void setSecurityLevel(String securityLevel) {
		this.securityLevel = securityLevel;
	}
	public String getDraftType() {
		return draftType;
	}
	public void setDraftType(String draftType) {
		this.draftType = draftType;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContentDesc() {
		return contentDesc;
	}
	public void setContentDesc(String contentDesc) {
		this.contentDesc = contentDesc;
	}
	public String getFileNum() {
		return fileNum;
	}
	public void setFileNum(String fileNum) {
		this.fileNum = fileNum;
	}
	public int getPrintNums() {
		return printNums;
	}
	public void setPrintNums(int printNums) {
		this.printNums = printNums;
	}
	public String getPrinter() {
		return printer;
	}
	public void setPrinter(String printer) {
		this.printer = printer;
	}
	public String getAuditor() {
		return auditor;
	}
	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPiId() {
		return piId;
	}
	public void setPiId(String piId) {
		this.piId = piId;
	}  
}
