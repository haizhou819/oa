package com.yhz.oa.filemgr.sendfile.sendfileaudit.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import com.yhz.framework.dao.impl.S3H3Template;
import com.yhz.oa.filemgr.sendfile.sendfileaudit.dao.dao.AuditRecordDAO;
import com.yhz.oa.filemgr.sendfile.sendfileaudit.vo.AuditRecordModel;
import com.yhz.oa.filemgr.sendfile.sendfileaudit.vo.AuditRecordQueryModel;

public class H3Impl extends S3H3Template<AuditRecordModel,AuditRecordQueryModel> implements AuditRecordDAO{

	@Override
	public void setStateToNo(String requestUuid) {
		String hql ="update AuditRecordModel o set o.state=:state where o.requestUuid=:reqUuid and o.state=:oldState";
		Map map = new HashMap();
		map.put("state", AuditRecordModel.STATE_NO);
		map.put("reqUuid", requestUuid);
		map.put("oldState", AuditRecordModel.STATE_OK);
		this.executeMyUpdate(hql, map);
	}

	@Override
	protected void setMyUpdateParams(Query q, Map map) {
		q.setString("state", ""+map.get("state"));
		q.setString("reqUuid", ""+map.get("reqUuid"));
		q.setString("oldState", ""+map.get("oldState"));
	}

	@Override
	public List<AuditRecordModel> getByCondition(AuditRecordQueryModel qm,
			boolean needPage, int fromNum, int pageNum) {
		List<String> list = new ArrayList<String>();
		list.add("auditType");
		return super.getByCondition(qm, needPage, fromNum, pageNum,list);
	}

	@Override
	protected String prepareSql(String sql, AuditRecordQueryModel qm,
			List<String> excludeName) throws SQLException {
		String str = super.prepareSql(sql, qm, excludeName);
		if(qm.getAuditType()!=null && qm.getAuditType().trim().length()>0){
			str +=" and o.auditType <= :auditType ";
		}
		return str; 
	}

	@Override
	protected void setValue(Query q, AuditRecordQueryModel qm,
			List<String> excludeName) throws SQLException {
		super.setValue(q, qm, excludeName);
		if(qm.getAuditType()!=null && qm.getAuditType().trim().length()>0){
			q.setString("auditType", qm.getAuditType());
		}
	}
	
}
