package com.yhz.oa.filemgr.sendfile.sendfilerequest;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yhz.oa.filemgr.sendfile.sendfilerequest.business.ebi.SendFileRequestEbi;
import com.yhz.oa.usermgr.dao.dao.UserDao;

public class Client {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "applicationContext.xml" });
		
		SendFileRequestEbi t = (SendFileRequestEbi)context.getBean("sfrEbi");
		
		List list = t.getAll(false,0,0);
		System.out.println("list"+list);
	}
	public static UserDao getUserDao(){
		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "applicationContext-user.xml" });
		
		return (UserDao)context.getBean("userDao");
	}
}
