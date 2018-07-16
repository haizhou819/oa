package com.yhz.oa.workflow.web.action;

import java.util.List;

import org.jbpm.api.TaskService;
import org.jbpm.api.task.Task;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.yhz.oa.workflow.web.vo.WorkFlowWebModel;

public class WorkFlowAction extends ActionSupport{
	private TaskService ts = null;
	public void setTs(TaskService ts){
		this.ts = ts;
	}
	public WorkFlowWebModel wm = new WorkFlowWebModel();
	@Override
	public String execute() throws Exception {
		if("viewMyWorkList".equals(wm.getSubmitFlag())){
			return this.viewMyWorkList();
		}
		return this.SUCCESS;
	}
	private String viewMyWorkList(){
		List<Task> list = ts.createTaskQuery()
		.assignee(""+ActionContext.getContext().getSession().get("LoginUserId"))
		.list();
		wm.setListTask(list);
		return "toMyWorkList";
	}
}
