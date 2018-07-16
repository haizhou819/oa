package com.yhz.oa.filemgr.sendfile.attach.business.ebo;

import java.util.List;

import com.yhz.framework.uuidservice.business.ebi.UuidServiceEbi;
import com.yhz.oa.filemgr.sendfile.attach.business.ebi.AttachEbi;
import com.yhz.oa.filemgr.sendfile.attach.dao.dao.AttachDAO;
import com.yhz.oa.filemgr.sendfile.attach.vo.AttachModel;
import com.yhz.oa.filemgr.sendfile.attach.vo.AttachQueryModel;


public class AttachEbo implements AttachEbi{
	private AttachDAO dao = null;
	private UuidServiceEbi uuidEbi = null;
	public void setUuidEbi(UuidServiceEbi uuidEbi){
		this.uuidEbi = uuidEbi;
	}
	public void setDao(AttachDAO dao){
		this.dao = dao;
	}
	
	
	@Override
	public void saveAttach(AttachModel am) {
		am.setUuid(this.uuidEbi.getNextUuid("Attach", "Attach_#", 5, 10));
		
		this.dao.create(am);
	}

	@Override
	public void saveAttachs(List<AttachModel> list) {
		for(AttachModel am : list){
			this.saveAttach(am);
		}
	}
	@Override
	public List<AttachModel> getAttachByRequestUuid(String requestUuid) {
		AttachQueryModel qm = new AttachQueryModel();
		qm.setRequestUuid(requestUuid);
				
		return this.dao.getByCondition(qm, false,0, 0);
	}
	@Override
	public void updateRequestAttach(String requestUuid, List<AttachModel> list) {
		this.dao.deleteAttachByRequestUuid(requestUuid);
		this.saveAttachs(list);
	}
	@Override
	public AttachModel getByUuid(String uuid) {
		return this.dao.getByUuid(new AttachModel(), uuid);
	}

}
