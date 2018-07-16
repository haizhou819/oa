package com.yhz.oa.filemgr.sendfile.sendfileaudit.business.ebi;

import java.util.List;

import com.yhz.oa.filemgr.sendfile.sendfileaudit.vo.AuditRecordModel;
import com.yhz.oa.filemgr.sendfile.sendfileaudit.vo.AuditRecordQueryModel;
import com.yhz.oa.filemgr.sendfile.sendfilerequest.vo.SendFileRequestModel;

public interface AuditRecordEbi {
	public void create(AuditRecordModel m,boolean needSetNo,boolean needWorkFlow,String taskId);
	public void update(AuditRecordModel m);
	public void delete(AuditRecordModel m);
	
	public AuditRecordModel getByUuid(String uuid);
	public List<AuditRecordModel> getAll(boolean needPage,int fromNum,int pageNum);
	public List<AuditRecordModel> getByCondition(AuditRecordQueryModel qm,boolean needPage,int fromNum,int pageNum);
	public int getCount(AuditRecordQueryModel qm);
	
	
	///////////////////////////////////////
	
	public void saveMyUpdate(SendFileRequestModel sfrm,String taskId);
	public void saveDepManagerAudit(AuditRecordModel arm,boolean needDepMangerSign, String depManagerSignUserIds,String taskId);
	public void saveDepManagerSign(AuditRecordModel arm,String nowUser);
	public void saveCheck(AuditRecordModel arm,String taskId);
}
