package com.yhz.oa.usermgr.web.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.yhz.oa.usermgr.business.ebi.UserEbi;
import com.yhz.oa.usermgr.vo.UserModel;
import com.yhz.oa.usermgr.vo.UserQueryModel;
import com.yhz.oa.usermgr.web.vo.UserWebModel;

public class UserAction extends ActionSupport{
	private UserEbi userEbi = null;
	private String LoginUserId = "";
	private String password = "";

	public void setUserEbi(UserEbi userEbi) {
		this.userEbi = userEbi;
	}

	//是传递到后台，进行真正的业务处理和数据操作使用的
	public UserModel um = new UserModel();
	//用来封装查询条件的
	public UserQueryModel uqm = new UserQueryModel();
	//用来页面和action之间交互数据使用的
	public UserWebModel wm = new UserWebModel();
	public String msg = "";
	
	@Override
	public String execute() throws Exception {
		System.out.println("submitFlag========"+wm.getSubmitFlag());
		if("toAdd".equals(wm.getSubmitFlag())){
			LoginUserId = (String)ActionContext.getContext().getSession().get("LoginUserId");
			UserModel user = userEbi.getByUuid(LoginUserId);
			String authority = user.getAuthority();
			if(authority.equals("1")){
				return this.toAdd();
			}else{
				return "noAuthority";
			}
		}else if("add".equals(wm.getSubmitFlag())){
			return this.add();
		}else if("toList".equals(wm.getSubmitFlag())){
			return this.toList();
		}else if("toUserUpdate".equals(wm.getSubmitFlag())){
			LoginUserId = (String)ActionContext.getContext().getSession().get("LoginUserId");
			UserModel user = userEbi.getByUuid(LoginUserId);
			String authority = user.getAuthority();
			if(authority.equals("1")){
				return this.toUserUpdate();
			}else{
				return "noAuthority";
			}
		}else if("userUpdate".equals(wm.getSubmitFlag())){
			return this.userUpdate();
		}else if("userDelete".equals(wm.getSubmitFlag())){
			LoginUserId = (String)ActionContext.getContext().getSession().get("LoginUserId");
			UserModel user = userEbi.getByUuid(LoginUserId);
			String authority = user.getAuthority();
			if(authority.equals("1")){
				return this.userDelete();
			}else{
				return "noAuthority";
			}
		}else if("userQuery".equals(wm.getSubmitFlag())){
			return this.userQuery();
		}else if("userLogin".equals(wm.getSubmitFlag())){
			return this.userLogin();
		}
		return super.execute();
	}
	
	public String toAdd(){
		return "toAdd";
	}
	public String add(){
		this.userEbi.create(um);
		return toList();
	}
	public String toList(){
		List<UserModel> list = null;
		Object obj = ActionContext.getContext().getSession().get("isQuery");
		UserQueryModel qm = new UserQueryModel();
		if("true".equals(obj)){
			qm = (UserQueryModel)ActionContext.getContext().getSession().get("uQm");
			System.out.println("qm.uuid="+qm.getUuid());
		}
		list = this.userEbi.getByCondition(qm,true,wm.getFromNum(),wm.PAGE_SHOW);
		wm.setCount(this.userEbi.getCount(qm));
		wm.setListCol(list);
		return "toList";
	}
	public String toUserUpdate(){
		this.um = userEbi.getByUuid(um.getUuid());
		return "toUserUpdate";
	}
	public String userUpdate(){
		System.out.println(um.getPassword()+"------------");
		this.um.setUuid(um.getPassword());
		this.userEbi.update(um);
		msg="恭喜您，修改用户信息成功！";
		return this.SUCCESS;
	}
	public String userDelete(){
		this.userEbi.delete(um);
		msg = "恭喜您，删除用户成功！";
		return this.SUCCESS;
	}
	public String userQuery(){
		ActionContext.getContext().getSession().put("isQuery","true");
		ActionContext.getContext().getSession().put("uQm", this.uqm);
		return toList();
	}
	public String userLogin(){
		LoginUserId = (String)ActionContext.getContext().getSession().get("LoginUserId");
		password = (String)ActionContext.getContext().getSession().get("password");
		UserModel user = userEbi.getByUuid(LoginUserId);
		if(user != null && password.equals(user.getPassword())){
			return "loginSuccess";
		}
		msg = "用户名或密码错误，请重新登录！";
		return "loginFail";
	}
	private void clearSession(){
		ActionContext.getContext().getSession().remove("LoginUserId");
		ActionContext.getContext().getSession().remove("password");
	}
}
