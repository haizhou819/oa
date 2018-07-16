package com.yhz.oa.filemgr.sendfile.sendfilerequest.web.vo;

import java.util.ArrayList;
import java.util.List;

import com.yhz.framework.web.WebModel;
import com.yhz.oa.filemgr.sendfile.sendfilerequest.vo.SendFileRequestModel;

public class SendFileRequestWebModel extends WebModel{
	private List<SendFileRequestModel> listCol = new ArrayList<SendFileRequestModel>();
	private boolean needClearQuery = false;

	
	public boolean isNeedClearQuery() {
		return needClearQuery;
	}

	public void setNeedClearQuery(boolean needClearQuery) {
		this.needClearQuery = needClearQuery;
	}

	public List<SendFileRequestModel> getListCol() {
		return listCol;
	}

	public void setListCol(List<SendFileRequestModel> listCol) {
		this.listCol = listCol;
	}
}
