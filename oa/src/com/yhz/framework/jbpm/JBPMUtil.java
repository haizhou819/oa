package com.yhz.framework.jbpm;

import org.jbpm.api.Configuration;
import org.jbpm.api.ExecutionService;
import org.jbpm.api.HistoryService;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.RepositoryService;
import org.jbpm.api.TaskService;

public class JBPMUtil {
	private static ProcessEngine engine = null;
	static{
		engine = Configuration.getProcessEngine();
	}
	public static RepositoryService getRS(){
		return engine.getRepositoryService();
	}
	public static ExecutionService getES(){
		return engine.getExecutionService();
	}
	public static TaskService getTS(){
		return engine.getTaskService();
	}
	public static HistoryService getHS(){
		return engine.getHistoryService();
	}
}
