package com.yhz.framework.login;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.yhz.oa.usermgr.dao.dao.UserDao;
import com.yhz.oa.usermgr.dao.impl.H3Impl;
import com.yhz.oa.usermgr.vo.UserModel;

public class LoginInterceptor implements Interceptor{
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Object obj = invocation.getInvocationContext().getSession().get("LoginUserId");
		if(obj==null || obj.toString().trim().length()==0){
			return "toLogin";
		}else {
			//拦截的action的名字
			//1:String action = invocation.getInvocationContext().getName();
			//2:String action = invocation.getProxy().getActionName()
			
			//执行目标方法 (调用下一个拦截器, 或执行Action) 
			return invocation.invoke();
		}
	}
}
