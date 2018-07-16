package com.yhz.oa.filemgr.sendfile.sendfilerequest.business.ebi;

import java.util.List;

import com.yhz.oa.filemgr.sendfile.sendfilerequest.vo.SendFileRequestModel;
import com.yhz.oa.filemgr.sendfile.sendfilerequest.vo.SendFileRequestQueryModel;

public interface SendFileRequestEbi {
	public String create(SendFileRequestModel m);
	public void update(SendFileRequestModel m);
	public void delete(SendFileRequestModel m);
	
	public SendFileRequestModel getByUuid(String uuid);
	public List<SendFileRequestModel> getAll(boolean needPage,int fromNum,int pageNum);
	public List<SendFileRequestModel> getByCondition(SendFileRequestQueryModel qm,boolean needPage,int fromNum,int pageNum);
	public int getCount(SendFileRequestQueryModel qm);
	////////////////////////////////////
	public String draft(SendFileRequestModel m);
	public void toDepAudit(String requestUuid,String startUserId,String webctx);	
}
