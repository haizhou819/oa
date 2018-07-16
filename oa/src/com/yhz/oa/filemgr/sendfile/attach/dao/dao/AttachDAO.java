package com.yhz.oa.filemgr.sendfile.attach.dao.dao;

import com.yhz.framework.dao.dao.Dao;
import com.yhz.oa.filemgr.sendfile.attach.vo.AttachModel;
import com.yhz.oa.filemgr.sendfile.attach.vo.AttachQueryModel;


public interface AttachDAO extends Dao<AttachModel,AttachQueryModel>{
	public void deleteAttachByRequestUuid(String requestUuid);
}
