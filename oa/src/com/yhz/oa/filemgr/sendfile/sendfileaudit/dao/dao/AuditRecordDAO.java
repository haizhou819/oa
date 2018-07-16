package com.yhz.oa.filemgr.sendfile.sendfileaudit.dao.dao;

import com.yhz.framework.dao.dao.Dao;
import com.yhz.oa.filemgr.sendfile.sendfileaudit.vo.AuditRecordModel;
import com.yhz.oa.filemgr.sendfile.sendfileaudit.vo.AuditRecordQueryModel;

public interface AuditRecordDAO extends Dao<AuditRecordModel,AuditRecordQueryModel>{
	public void setStateToNo(String requestUuid);
}
