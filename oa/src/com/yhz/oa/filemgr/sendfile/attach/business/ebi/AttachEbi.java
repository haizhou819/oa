package com.yhz.oa.filemgr.sendfile.attach.business.ebi;

import java.util.List;

import com.yhz.oa.filemgr.sendfile.attach.vo.AttachModel;


public interface AttachEbi {
	public void saveAttach(AttachModel am);
	public void saveAttachs(List<AttachModel> list);
	
	public List<AttachModel> getAttachByRequestUuid(String requestUuid);
	public void updateRequestAttach(String requestUuid,List<AttachModel> list);
	
	public AttachModel getByUuid(String uuid);
}
