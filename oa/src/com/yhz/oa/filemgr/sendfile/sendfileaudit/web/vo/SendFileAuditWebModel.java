package com.yhz.oa.filemgr.sendfile.sendfileaudit.web.vo;

import java.util.ArrayList;
import java.util.List;

import com.yhz.framework.web.WebModel;
import com.yhz.oa.filemgr.sendfile.sendfileaudit.vo.AuditRecordModel;

public class SendFileAuditWebModel extends WebModel{
	private List<AuditRecordModel> listAR = new ArrayList<AuditRecordModel>();
	private boolean needDepMangerSign = false;
	private String depManagerSignUserIds = "";
	
	
	
	public String getDepManagerSignUserIds() {
		return depManagerSignUserIds;
	}

	public void setDepManagerSignUserIds(String depManagerSignUserIds) {
		this.depManagerSignUserIds = depManagerSignUserIds;
	}

	public boolean isNeedDepMangerSign() {
		return needDepMangerSign;
	}

	public void setNeedDepMangerSign(boolean needDepMangerSign) {
		this.needDepMangerSign = needDepMangerSign;
	}

	public List<AuditRecordModel> getListAR() {
		return listAR;
	}

	public void setListAR(List<AuditRecordModel> listAR) {
		this.listAR = listAR;
	}
}
