package com.yhz.oa.workflow.web.vo;

import java.util.ArrayList;
import java.util.List;

import org.jbpm.api.task.Task;

import com.yhz.framework.web.WebModel;

public class WorkFlowWebModel extends WebModel{
	private List<Task> listTask = new ArrayList<Task>();

	public List<Task> getListTask() {
		return listTask;
	}

	public void setListTask(List<Task> listTask) {
		this.listTask = listTask;
	}
	
}
