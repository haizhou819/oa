package com.yhz.framework.uuidservice;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yhz.framework.uuidservice.business.ebi.UuidServiceEbi;

public class Client {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "applicationContext.xml" });
		/*UuidServiceDao dao = (UuidServiceDao)context.getBean("uuidDao");
		UuidModel um = new UuidModel();
		um.setPreKey("b");
		um.setNum(13);
		dao.create(um);
		
		UuidModel um2 = dao.getByUuid(new UuidModel(), "a");
		System.out.println(um2.getNum());*/
		
		
		UuidServiceEbi u = (UuidServiceEbi)context.getBean("uuidEbi");
		
		System.out.println("========"+u.getNextUuid("test","某公司_第_#_号文件",5, 10));
		System.out.println("========"+u.getNextUuid("test","某公司_第_#_号文件",5, 10));
		System.out.println("========"+u.getNextUuid("test","某公司_第_#_号文件",5, 10));
		System.out.println("========"+u.getNextUuid("test","某公司_第_#_号文件",5, 10));
		System.out.println("========"+u.getNextUuid("test","某公司_第_#_号文件",5, 10));
	}
}
