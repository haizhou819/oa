package com.yhz.oa.workflow.deploy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipInputStream;

import org.jbpm.api.ExecutionService;
import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.RepositoryService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.yhz.framework.uuidservice.Client;
import com.yhz.oa.usermgr.business.ebi.UserEbi;
import com.yhz.oa.usermgr.vo.UserModel;

public class DeployAction extends ActionSupport{
	public File myFile = null;
	public String myFileFileName = "";
	private String LoginUserId = "";
	private UserEbi userEbi = null;

	private RepositoryService rs = null;
	private ExecutionService es = null;
	
	public String msg = "";
	public void setRs(RepositoryService rs){
		this.rs = rs;
	}
	public void setEs(ExecutionService es){
		this.es = es;
	}
	public void setUserEbi(UserEbi userEbi) {
		this.userEbi = userEbi;
	}
	
	public String submitFlag = "deploy";
	public List<ProcessDefinition> listPD = new ArrayList<ProcessDefinition>();
	public List<ProcessInstance> listPI = new ArrayList<ProcessInstance>();
	public String execute(){
		System.out.println("submitFlag========="+submitFlag);
		if("deploy".equals(submitFlag)){
			LoginUserId = (String)ActionContext.getContext().getSession().get("LoginUserId");
			UserModel um = userEbi.getByUuid(LoginUserId);
			String authority = um.getAuthority();
			if(authority.equals("1")){
				return this.deploy();
			}else{
				return "noAuthority";
			}
			
		}else if("viewPD".equals(submitFlag)){
			return this.viewPD();
		}else if("viewPI".equals(submitFlag)){
			return this.viewPI();
		}
		return this.SUCCESS;
	}
	private String viewPD(){
		listPD = rs.createProcessDefinitionQuery().list();
		return "toPDList";
	}
	private String deploy(){
		//先保存文件
		String filePathName = "E:/upload/"+myFileFileName;
		this.saveFile(filePathName);
		//2：再发布文件
		//rs.createDeployment().addResourceFromFile(new File(filePathName)).deploy();
		ZipInputStream zis = new ZipInputStream(Client.class.getResourceAsStream("/SendFile.zip"));
		rs.createDeployment().addResourcesFromZipInputStream(zis).deploy();
		msg = "恭喜你，流程发布成功";
		return this.SUCCESS;
	}
	private String viewPI(){
		listPI= es.createProcessInstanceQuery().list();
		return "toPIList";
	}
	private void saveFile(String filePathName){
		//把上传过来的文件存放到e盘目录下，以真实的文件名作为名字
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
}
