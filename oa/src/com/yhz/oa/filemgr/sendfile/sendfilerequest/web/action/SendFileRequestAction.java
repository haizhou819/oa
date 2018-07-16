package com.yhz.oa.filemgr.sendfile.sendfilerequest.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.yhz.oa.filemgr.sendfile.attach.business.ebi.AttachEbi;
import com.yhz.oa.filemgr.sendfile.attach.vo.AttachModel;
import com.yhz.oa.filemgr.sendfile.sendfilerequest.business.ebi.SendFileRequestEbi;
import com.yhz.oa.filemgr.sendfile.sendfilerequest.vo.SendFileRequestModel;
import com.yhz.oa.filemgr.sendfile.sendfilerequest.vo.SendFileRequestQueryModel;
import com.yhz.oa.filemgr.sendfile.sendfilerequest.web.vo.SendFileRequestWebModel;

public class SendFileRequestAction extends ActionSupport{
	private SendFileRequestEbi ebi = null;
	private AttachEbi attachEbi = null;
	public void setAttachEbi(AttachEbi attachEbi){
		this.attachEbi = attachEbi;
	}
	public void setEbi(SendFileRequestEbi ebi){
		this.ebi = ebi;
	}
	
	//是传递到后台，进行真正的业务处理和数据操作使用的
	public SendFileRequestModel sfrm = new SendFileRequestModel();
	//用来封装查询条件的
	public SendFileRequestQueryModel sfrqm = new SendFileRequestQueryModel();
	//用来页面和action之间交互数据使用的
	public SendFileRequestWebModel wm = new SendFileRequestWebModel();
	
	public File myFile = null;
	public String myFileFileName = "";
	public List<AttachModel> listAttach = new ArrayList<AttachModel>();
	public String attachUuid = "";
	
	private String depAuditUser = "";
	private String depMangerAuditUser = "";
	private String checkUser = "";
	
	@Override
	public String execute() throws Exception {
		System.out.println("submitFlag========"+wm.getSubmitFlag());
		if("toAdd".equals(wm.getSubmitFlag())){
			return this.toAdd();
		}else if("add".equals(wm.getSubmitFlag())){
			return this.add();
		}else if("toList".equals(wm.getSubmitFlag())){
			return this.toList();
		}else if("toUpdate".equals(wm.getSubmitFlag())){
			return this.toUpdate();
		}else if("update".equals(wm.getSubmitFlag())){
			return this.update();
		}/*else if("toDelete".equals(wm.getSubmitFlag())){
			return this.toDelete();
		}*/else if("delete".equals(wm.getSubmitFlag())){
			return this.delete();
		}else if("toQuery".equals(wm.getSubmitFlag())){
			return this.toQuery();
		}else if("query".equals(wm.getSubmitFlag())){
			return this.query();
		}else if("highQuery".equals(wm.getSubmitFlag())){
			return this.highQuery();
		}
		/////////////////////////////
		else if("toDraft".equals(wm.getSubmitFlag())){
			return this.toDraft();
		}else if("draft".equals(wm.getSubmitFlag())){
			return this.draft();
		}else if("toDraftList".equals(wm.getSubmitFlag())){
			return this.toDraftList();
		}else if("toDraftQuery".equals(wm.getSubmitFlag())){
			return this.toDraftQuery();
		}else if("draftQuery".equals(wm.getSubmitFlag())){
			return this.draftQuery();
		}else if("toDraftUpdate".equals(wm.getSubmitFlag())){
			return this.toDraftUpdate();
		}else if("draftUpdate".equals(wm.getSubmitFlag())){
			return this.draftUpdate();
		}else if("viewFile".equals(wm.getSubmitFlag())){
			return this.viewFile();
		}else if("toDepAudit".equals(wm.getSubmitFlag())){
			return this.toDepAudit();
		}
		
		///////////////////////////////
		else if("toUpdateList".equals(wm.getSubmitFlag())){
			return this.toUpdateList();
		}else if("toUpdateUpdate".equals(wm.getSubmitFlag())){
			return this.toUpdateUpdate();
		}else if("saveUpdateUpdate".equals(wm.getSubmitFlag())){
			return this.saveUpdateUpdate();
		}
		return this.SUCCESS;
	}
	private String toUpdateList(){
		List<SendFileRequestModel> list = null;
		SendFileRequestQueryModel qm = new SendFileRequestQueryModel();
		//添加一个状态
		qm.setState(qm.STATE_WAIT_MODIFY);
		list = this.ebi.getByCondition(qm,false,0,0);
		wm.setListCol(list);
		return "toUpdateList";
	}
	private String toUpdateUpdate(){
		//1：获取主记录
		this.sfrm = this.ebi.getByUuid(this.sfrm.getUuid());
		return "toUpdateUpdate";
	}
	private String saveUpdateUpdate(){
		//1:保存主记录
		this.sfrm.setState(sfrm.STATE_WAIT_MODIFY);
		this.ebi.update(this.sfrm);

		return this.toUpdateList();
	}
	private String toDepAudit(){
		//1:
		this.ebi.toDepAudit(this.sfrm.getUuid()
				,""+ActionContext.getContext().getSession().get("LoginUserId")
				,ServletActionContext.getServletContext().getContextPath());
		
		return this.SUCCESS;
	}
	private String viewFile(){
		return "viewFile";
	}
	private String draftUpdate(){
		//1:保存主记录
		this.sfrm.setState(sfrm.STATE_DRAFT);
		this.ebi.update(this.sfrm);
		//2:保存附件
		//2.1:先删除掉原来的数据
		//2.2:再增加新的数据
		System.out.println("myFile="+this.myFile+" , name=="+this.myFileFileName);
		if(this.myFile!=null && this.myFileFileName!=null && this.myFileFileName.trim().length()>0){
			System.out.println("now update attach=================");
			List<AttachModel> list = new ArrayList<AttachModel>();
			
			String filePath = "D:/workspace/oa/WebContent/upload/"+this.myFileFileName;
			this.saveFile(filePath);
			
			AttachModel am = new AttachModel();
			am.setContentDesc(sfrm.getContentDesc());
			am.setFileName(this.myFileFileName);
			am.setFilePath(filePath);
			am.setRequestUuid(this.sfrm.getUuid());
			
			list.add(am);
			
			this.attachEbi.updateRequestAttach(this.sfrm.getUuid(), list);
		}
		return this.toDraftList();
	}
	private String toDraftUpdate(){
		//1：获取主记录
		this.sfrm = this.ebi.getByUuid(this.sfrm.getUuid());
		System.out.println("=====now in draftUpdate====="+this.sfrm.getUuid());
		//2:获取子记录
		this.listAttach = this.attachEbi.getAttachByRequestUuid(this.sfrm.getUuid());
		return "toDraftUpdate";
	}
	
	private String toDraft(){
		return "toDraft";
	}
	private String draft(){
		System.out.println("=============now in draft===========");
		//1:先增加draft的主记录
		this.sfrm.setDraftUser(""+ActionContext.getContext().getSession().get("LoginUserId"));
		String uuid = this.ebi.draft(sfrm);
		//2：保存附件
		//2.1先写文件
		String filePath = "E:/BaiduYunDownload/oa/WebContent/upload"+this.myFileFileName;
		this.saveFile(filePath);
		//2.2保存数据库
		AttachModel am = new AttachModel();
		am.setContentDesc(sfrm.getContentDesc());
		am.setFileName(this.myFileFileName);
		am.setFilePath(filePath);
		am.setRequestUuid(uuid);
		
		this.attachEbi.saveAttach(am);
		
		return this.toDraftList();
	}
	private void saveFile(String filePathName){
		//把上传过来的文件存放到e盘temp目录下，以真实的文件名作为名字
		OutputStream output = null;
		InputStream input = null;
		try{	
			output = new FileOutputStream(filePathName);
			//建立一个1k大小的缓冲区
			byte[] bs = new byte[1024];			
			//将上传过来的文件输出到output中
			input = new FileInputStream(myFile);
			int length = 0;
			//length=input.read(bs)这句话中，length=-1代表了读到文件结尾
			while ((length=input.read(bs))>0){
				output.write(bs, 0, length);
			}
		}catch(Exception err){
			err.printStackTrace();
		}finally{	
			try {
				input.close();
			} catch (Exception e) {
				e.printStackTrace();
			}	
			try {
				output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}	
	}
	private String toDraftList(){
		List<SendFileRequestModel> list = null;
		Object obj = ActionContext.getContext().getSession().get("isQuery");
		SendFileRequestQueryModel qm = new SendFileRequestQueryModel();
		if("true".equals(obj)){
			qm = (SendFileRequestQueryModel)ActionContext.getContext().getSession().get("draftQm");
		}
		
		//添加一个状态
		qm.setState(qm.STATE_DRAFT);
		
		list = this.ebi.getByCondition(qm,true,wm.getFromNum(),wm.PAGE_SHOW);
		
		wm.setCount(this.ebi.getCount(qm));
		wm.setListCol(list);
		
		
		return "toDraftList";
	}
	private String toDraftQuery(){
		return "toDraftQuery";
	}
	private String draftQuery(){
		ActionContext.getContext().getSession().put("isQuery","true");
		ActionContext.getContext().getSession().put("draftQm", this.sfrqm);
		return this.toDraftList();
	}
	public String getContentDisposition() throws UnsupportedEncodingException{
		AttachModel am = this.attachEbi.getByUuid(attachUuid);
		String s = am.getFileName();
		s = new String(s.getBytes("utf-8"),"ISO8859-1");
		return "attachment;filename=\""+s+"\"";
	}
	public InputStream getInputStream() throws Exception{
		AttachModel am = this.attachEbi.getByUuid(attachUuid);
		File file = new File(am.getFilePath());
		return new FileInputStream(file);
	}
	///////////////////////////////////////////////////
	public String toAdd(){
		return "toAdd";
	}
	public String add(){
		clearSession();
		this.ebi.create(sfrm);
		return toList();
	}
	public String toList(){
		if(wm.isNeedClearQuery()){
			this.clearSession();
		}
		
		List<SendFileRequestModel> list = null;
		Object obj = ActionContext.getContext().getSession().get("isQuery");
		Object obj2 = ActionContext.getContext().getSession().get("isHighQuery");
		SendFileRequestQueryModel qm = new SendFileRequestQueryModel();
		if("true".equals(obj) && "false".equals(obj2)){
			qm = (SendFileRequestQueryModel)ActionContext.getContext().getSession().get("qm");
			System.out.println("now query================"+qm+" , obj="+obj);
		}else if("false".equals(obj) && "true".equals(obj2)){
			qm = (SendFileRequestQueryModel)ActionContext.getContext().getSession().get("qm");
		}
		
		list = this.ebi.getByCondition(qm,true,wm.getFromNum(),wm.PAGE_SHOW);
		wm.setCount(this.ebi.getCount(qm));
		wm.setListCol(list);
		return "toList";
	}
	public String toUpdate(){
		this.sfrm = this.ebi.getByUuid(sfrm.getUuid());
		return "toUpdate";
	}
	public String update(){
		clearSession();
		this.ebi.update(this.sfrm);
		return toList();
	}
	/*public String toDelete(){
		this.sfrm = this.ebi.getByUuid(sfrm.getUuid());
		return "toDelete";
	}*/
	public String delete(){
		clearSession();
		this.sfrm = this.ebi.getByUuid(sfrm.getUuid());
		if(sfrm != null){
			this.ebi.delete(sfrm);
		}
		return toList();
	}
	public String toQuery(){
		return "toQuery";
	}
	public String query(){
		ActionContext.getContext().getSession().put("isQuery","true");
		ActionContext.getContext().getSession().put("isHighQuery","false");
		ActionContext.getContext().getSession().put("qm", this.sfrqm);
		return toList();
	}
	public String highQuery(){
		ActionContext.getContext().getSession().put("isQuery","false");
		ActionContext.getContext().getSession().put("isHighQuery","true");
		ActionContext.getContext().getSession().put("qm", this.sfrqm);
		return toList();
	}
	private void clearSession(){
		ActionContext.getContext().getSession().remove("isQuery");
		ActionContext.getContext().getSession().remove("qm");
	}
}
