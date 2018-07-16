package com.yhz.oa.filemgr;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import org.jbpm.api.Configuration;
import org.jbpm.api.Deployment;
import org.jbpm.api.Execution;
import org.jbpm.api.ExecutionService;
import org.jbpm.api.HistoryService;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.RepositoryService;
import org.jbpm.api.TaskService;
import org.jbpm.api.history.HistoryProcessInstance;
import org.jbpm.api.task.Task;

public class Client {
	public static void main(String[] args) {
//		//1:发布和部署流程文件
		//deployPD();
		viewDeploryPD();
	}
	
	private static void deployPD(){
		//1:要得到引擎实例
		ProcessEngine engine = Configuration.getProcessEngine();
		//2：要得到RepositoryService
		RepositoryService rs = engine.getRepositoryService();
		//3：调用相应的api
		String deployId = rs.createDeployment().addResourceFromClasspath("com/yhz/oa/filemgr/sendfile/SendFile.jpdl.xml").deploy();
	}
	private static void viewDeploryPD(){
		//1:要得到引擎实例
		ProcessEngine engine = Configuration.getProcessEngine();
		//2：要得到RepositoryService
		RepositoryService rs = engine.getRepositoryService();
		
		List<Deployment> list = rs.createDeploymentQuery().list();
		for(Deployment d : list){
			System.out.println("id="+d.getId()+",name="+d.getName());
		}
	}
	private static void startPI(){
		//1:要得到引擎实例
		ProcessEngine engine = Configuration.getProcessEngine();
		//2:得到ExecutionService
		ExecutionService es = engine.getExecutionService();
		
		Map map = new HashMap();
		map.put("hqry", "aa,bb,cc,dd,ee");
		
		es.startProcessInstanceByKey("sign",map);		
	}
	private static void viewPI(){
		//1:要得到引擎实例
		ProcessEngine engine = Configuration.getProcessEngine();
		//2:得到ExecutionService
		ExecutionService es = engine.getExecutionService();
		
		List<ProcessInstance> list= es.createProcessInstanceQuery().list();
		for(ProcessInstance pi : list){
			System.out.println("id="+pi.getId()+",name="+pi.getName());
		}
	}
	private static void viewWorkList(){
		//1:要得到引擎实例
		ProcessEngine engine = Configuration.getProcessEngine();
		//2:得到TaskService
		TaskService ts = engine.getTaskService();
		
		List<Task> list= ts.createTaskQuery().list();
		for(Task t : list){
			System.out.println("id="+t.getId()+",name="+t.getName()+",activity="+t.getActivityName()+",user="+t.getAssignee());
			
		}
	}
	private static void completeWork(String piId,String nowUser,double score){
		//1:要得到引擎实例
		ProcessEngine engine = Configuration.getProcessEngine();
		//2:得到TaskService
		TaskService ts = engine.getTaskService();
		
		ExecutionService es = engine.getExecutionService();
		
		Task mainTask = ts.createTaskQuery().processInstanceId(piId).activityName("depSign").uniqueResult();
		if(mainTask==null){
			System.out.println("会签已经结束");
			return;
		}
		
		
		Map map = new HashMap();
		map.put("nowHqry", nowUser);
		map.put("hqResult", score);
		
		es.signalExecutionById(piId,map);
	}
	private static void viewHistory(){
		//1:要得到引擎实例
		ProcessEngine engine = Configuration.getProcessEngine();
		//2:得到HistoryService
		HistoryService hs = engine.getHistoryService();
		
		List<HistoryProcessInstance> list = hs.createHistoryProcessInstanceQuery().list();
		for(HistoryProcessInstance hpi : list){
			System.out.println("piId="+hpi.getProcessInstanceId()+",state="+hpi.getState());
		}
	}
}

