<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
<title>Insert title here</title>
</head>
<body>
<%@ page import="org.jbpm.api.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<%@ page import="org.jbpm.api.model.*" %>

<%!
	public ProcessInstance getProcessInstanceByTaskId(String taskId){
		ProcessEngine engine = Configuration.getProcessEngine();
		TaskService ts = engine.getTaskService();
		ExecutionService es = engine.getExecutionService();
		String executionId = ts.getTask(taskId).getExecutionId();
		return (ProcessInstance) es.findExecutionById(executionId).getProcessInstance();
	}
%>
<%
	ProcessEngine engine = Configuration.getProcessEngine();
	RepositoryService rs = engine.getRepositoryService();
	String taskId = request.getParameter("taskId");
	ProcessInstance pi = getProcessInstanceByTaskId(taskId);
	String pdId = pi.getProcessDefinitionId();
	
%>
	<img alt="" src="viewPic.jsp?pdId=<%=pdId%>">
<%	
	Set<String> activityNames = pi.findActiveActivityNames();
	for(String activityName : activityNames){
		
		ActivityCoordinates ac = rs.getActivityCoordinates(pdId, activityName);
		System.out.println("activityName="+activityName);
		System.out.println("x="+ac.getX());
		System.out.println("y="+ac.getY());
		System.out.println("height="+ac.getHeight());
		System.out.println("width="+ac.getWidth());
		System.out.println("==================");
%>
<div style="position:absolute;border:1px solid red;
	left:<%=ac.getX()+7%>px;top:<%=ac.getY()+7%>px;
	width:<%=ac.getWidth()%>px;height:<%=ac.getHeight()%>px;
	">	
</div>

<%
	}
%>
</body>
</html>