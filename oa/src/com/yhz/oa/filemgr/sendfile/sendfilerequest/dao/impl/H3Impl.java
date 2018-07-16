package com.yhz.oa.filemgr.sendfile.sendfilerequest.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import com.yhz.framework.dao.impl.S3H3Template;
import com.yhz.oa.filemgr.sendfile.sendfilerequest.dao.dao.SendFileRequestDao;
import com.yhz.oa.filemgr.sendfile.sendfilerequest.vo.SendFileRequestModel;
import com.yhz.oa.filemgr.sendfile.sendfilerequest.vo.SendFileRequestQueryModel;


public class H3Impl extends S3H3Template<SendFileRequestModel,SendFileRequestQueryModel> implements SendFileRequestDao{
	@Override
	public SendFileRequestModel getByUuid(SendFileRequestModel m, String uuid) {
		SendFileRequestQueryModel qm = new SendFileRequestQueryModel();
		qm.setUuid(uuid);
		List<SendFileRequestModel> list = this.getByCondition(qm, false,0,0);
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	protected String getQueryByConditionMainSqlSelectPart(
			SendFileRequestQueryModel m) throws SQLException {
		String str = super.getQueryByConditionMainSqlSelectPart(m);
		str +=" ,u.name ";
		return str;
	}

	@Override
	protected String getQueryByConditionMainSqlFormPart(
			SendFileRequestQueryModel m) throws SQLException {
		String str =super.getQueryByConditionMainSqlFormPart(m);
		str +=" , UserModel u ";
		return str;
	}

	@Override
	protected String getQueryByConditionMainSqlWherePart(
			SendFileRequestQueryModel m) throws SQLException {
		String str = super.getQueryByConditionMainSqlWherePart(m); 
		str += " and o.draftUser=u.uuid ";
		return str;
	}

	@Override
	public List<SendFileRequestModel> getByCondition(
			SendFileRequestQueryModel qm, boolean needPage, int fromNum,
			int pageNum, List<String> excludeName) {
		List<Object[]> list =this.getHibernateTemplate().execute(super.getByConditionCall(qm, needPage, fromNum, pageNum, excludeName));
		List<SendFileRequestModel> retList = new ArrayList<SendFileRequestModel>();
		for(Object[] os : list){
			SendFileRequestModel sfrm = (SendFileRequestModel)os[0];
			String draftUserName = ""+os[1];
			sfrm.setDraftUserName(draftUserName);
			
			retList.add(sfrm);
		}
		return retList; 
	}

	@Override
	public void updateState(String requestUuid, String newState) {
		System.out.println(requestUuid+"-------"+newState);
		String hql = "update SendFileRequestModel o set o.state=:state where o.uuid=:uuid";
		Map map = new HashMap();
		map.put("uuid", requestUuid);
		map.put("state", newState);
		this.executeMyUpdate(hql, map);		
	}
	
	@Override
	protected void setMyUpdateParams(Query q, Map map) {
		q.setString("state", ""+map.get("state"));
		q.setString("uuid", ""+map.get("uuid"));
	}
}
