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

<%
	String piId = request.getParameter("piId");
	ProcessEngine engine = Configuration.getProcessEngine();
	ExecutionService es = engine.getExecutionService();
	ProcessInstance pi = es.findProcessInstanceById(piId);
	String pdId = pi.getProcessDefinitionId();
	RepositoryService rs = engine.getRepositoryService();
%>
	<img alt="" src="viewPic.jsp?pdId=<%=pdId%>">
<%	
	Set<String> activityNames = pi.findActiveActivityNames();
	for(String activityName : activityNames){
		ActivityCoordinates ac = rs.getActivityCoordinates(pdId, activityName);
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