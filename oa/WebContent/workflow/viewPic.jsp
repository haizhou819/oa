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

<%!
	public InputStream showWebPicture(String pdId){
		ProcessEngine engine = Configuration.getProcessEngine();
		RepositoryService rs = engine.getRepositoryService();
		ProcessDefinition pd = rs.createProcessDefinitionQuery().processDefinitionId(pdId).uniqueResult();
		InputStream in = rs.getResourceAsStream(pd.getDeploymentId(),pd.getName()+".png");
		return in;
	}
%>
<%
	String pdId = request.getParameter("pdId");
	InputStream in = showWebPicture(pdId);
	byte bs[] = new byte[1024];
	while(in.read(bs) != -1){
		response.getOutputStream().write(bs);
	}
	out.clear();
	out = pageContext.pushBody();
%>
</body>
</html>