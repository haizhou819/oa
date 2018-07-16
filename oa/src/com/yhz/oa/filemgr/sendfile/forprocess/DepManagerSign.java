package com.yhz.oa.filemgr.sendfile.forprocess;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jbpm.api.Configuration;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.TaskService;
import org.jbpm.api.activity.ActivityExecution;
import org.jbpm.api.activity.ExternalActivityBehaviour;
import org.jbpm.api.model.Transition;
import org.jbpm.api.task.Task;
import org.jbpm.pvm.internal.env.EnvironmentImpl;
import org.jbpm.pvm.internal.history.HistoryEvent;
import org.jbpm.pvm.internal.history.events.TaskActivityStart;
import org.jbpm.pvm.internal.model.ExecutionImpl;
import org.jbpm.pvm.internal.session.DbSession;
import org.jbpm.pvm.internal.task.TaskImpl;

public class DepManagerSign implements ExternalActivityBehaviour{

	@Override
	public void execute(ActivityExecution aexe) throws Exception {
		//1:创建主task，目的是让流程停在这个地方
		//2:为每个参加会签的人员，创建一个子task
		//3:让流程停在这里
		
		//主要实现为参与会签的每个人创建一个工作
		ExecutionImpl exe = (ExecutionImpl)aexe;
		DbSession session = EnvironmentImpl.getFromCurrent(DbSession.class);
		
		//1：创建主task，目的是让这个流程停在customer活动，一般不用分配工作人员
		TaskImpl mainTask = session.createTask();
		mainTask.setAssignee(null);
		mainTask.setDescription("");
		mainTask.setName("DepManagerHQ");
		mainTask.setActivityName(exe.getActivityName());
		mainTask.setExecution(exe);
		mainTask.setExecutionDbid(exe.getDbid());
		mainTask.setProcessInstance(exe.getProcessInstance());
		mainTask.setSignalling(false);
		
		session.save(mainTask);
		
		HistoryEvent.fire(new TaskActivityStart(mainTask),exe);
		
		//2：创建子task，为每个参与会签的人员创建一个相应的task
		String hqry = (String)exe.getVariable("depManagerSignUserIds");
		System.out.println("参与会签人员："+hqry);
		List<String> listHqry = new ArrayList<String>();
		String [] hqrys = hqry.split(",");
		for(String s : hqrys){
			listHqry.add(s);
			
			TaskImpl  subTask = mainTask.createSubTask();
			subTask.setActivityName(exe.getActivityName());
			subTask.setAssignee(s);
			String tempStr = "请"+s+"对"+aexe.getVariable("startUserId")+"的发文申请进行 部门领导会签$$" +
				aexe.getVariable("webctx")
				+"/sendFileAuditAction.action?wm.submitFlag=toDepManagerSign&requestUuid="
				+aexe.getVariable("requestUuid");
	
			subTask.setDescription(tempStr);
			subTask.setExecutionDbid(exe.getDbid());
			subTask.setSignalling(false);
			
			session.save(subTask);
			
			HistoryEvent.fire(new TaskActivityStart(subTask),exe);
		}
		//把参与会签的人员保存下来
		exe.setVariable("listHqry", listHqry);
		
		//把ai历史数据保存下来
		exe.setVariable("HistoryAIID", exe.getHistoryActivityInstanceDbid());
		
		//3：让流程停留在这里，等待会签人员的响应
		exe.waitForSignal();		
	}

	@Override
	public void signal(ActivityExecution exe, String outComingName, Map<String, ?> params)
			throws Exception {
		
		//1:判断这个人是否应该参与会签
		String nowHqry = (String)params.get("nowHqry");
		List<String> listHqry = (List<String>)exe.getVariable("listHqry");
		if(!listHqry.contains(nowHqry)){
			System.out.println("对不起，你不能参与会签");
			//流程继续等待
			exe.waitForSignal();
			return;
		}
		//2:判断这个人是否会签过了
		//2.1：找主task
		ProcessEngine engine = Configuration.getProcessEngine();
		TaskService ts = engine.getTaskService();
		
		Task mainTask = ts.createTaskQuery().processInstanceId(exe.getProcessInstance().getId()).activityName("部门领导会签").uniqueResult();
		boolean hasSign = true;
		List<Task> list = ts.getSubTasks(mainTask.getId());
		for(Task subTask : list){
			if(nowHqry.equals(subTask.getAssignee())){
				hasSign = false;
				//3：如果这个人应该参与会签，而且还没有会签过，应该把这个人的task终止掉
				ts.completeTask(subTask.getId());
				break;
			}
		}
		
		if(hasSign){
			System.out.println("对不起，你已经会签过了，不能重复会签");
			//流程继续等待
			exe.waitForSignal();
			return;
		}
		
		//4:把这个人的会签结果保存下来
		Map<String,String> map = new HashMap<String,String>();
		Object obj = exe.getVariable("HQResult");
		if(obj!=null){
			map = (Map<String,String>)obj;
		}
		map.put(nowHqry, ""+params.get("hqResult"));
		exe.setVariable("HQResult", map);
		
		//5:业务规则
		//5.1判断是否所有的人都完成了
		if((listHqry.size()) > map.size()){
			//7:
			exe.waitForSignal();
			return ;
		}
		
		
		//6:
		//6:
		//6.1
		this.overTasks(ts, (ExecutionImpl)exe);
		//6.2:
		Transition t = exe.getActivity().getDefaultOutgoingTransition();
		
		//6.3
		((ExecutionImpl)exe).take(t);
	}
	
	private void overTasks(TaskService ts,ExecutionImpl exe){
		//1:终止所有未完成的task
		Task mainTask = ts.createTaskQuery().processInstanceId(exe.getProcessInstance().getId()).activityName("部门领导会签").uniqueResult();
		List<Task> list = ts.getSubTasks(mainTask.getId());
		for(Task subTask : list){
			ts.completeTask(subTask.getId());
		}
		//2：终止主task
		//2.1先恢复
		exe.setHistoryActivityInstanceDbid((Long)exe.getVariable("HistoryAIID"));
		//2.2
		ts.completeTask(mainTask.getId());
	}


}
