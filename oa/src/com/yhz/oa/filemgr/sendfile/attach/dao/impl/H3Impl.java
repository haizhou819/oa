package com.yhz.oa.filemgr.sendfile.attach.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.Query;

import com.yhz.framework.dao.impl.S3H3Template;
import com.yhz.oa.filemgr.sendfile.attach.dao.dao.AttachDAO;
import com.yhz.oa.filemgr.sendfile.attach.vo.AttachModel;
import com.yhz.oa.filemgr.sendfile.attach.vo.AttachQueryModel;



public class H3Impl extends S3H3Template<AttachModel,AttachQueryModel> implements AttachDAO{

	@Override
	public void deleteAttachByRequestUuid(String requestUuid) {
		System.out.println("now in deleteAttachByRequestUuid======"+requestUuid);
		String hql = "delete from AttachModel o where o.requestUuid=:requestUuid";
		Map map = new HashMap();
		map.put("p1", requestUuid);
		
		this.executeMyUpdate(hql,map);
	}
	protected void setMyUpdateParams(Query q,Map map){
		q.setString("requestUuid", ""+map.get("p1"));
		System.out.println("ffffffffff==="+map.get("p1"));
	}
}
