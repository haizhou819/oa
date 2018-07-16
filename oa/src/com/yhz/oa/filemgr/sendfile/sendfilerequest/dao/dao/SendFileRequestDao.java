package com.yhz.oa.filemgr.sendfile.sendfilerequest.dao.dao;

import com.yhz.framework.dao.dao.Dao;
import com.yhz.oa.filemgr.sendfile.sendfilerequest.vo.SendFileRequestModel;
import com.yhz.oa.filemgr.sendfile.sendfilerequest.vo.SendFileRequestQueryModel;

public interface SendFileRequestDao extends Dao<SendFileRequestModel,SendFileRequestQueryModel>{
	public void updateState(String requestUuid, String newState);
}
