<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="org.jbpm.api.*" %>
<%@ page import="java.util.*" %>



<table border=1 cellspacing="0" cellpadding="0" width="100%">
	<tr>
		<td colspan=3 align="center">需要您参与完成的工作</td>	
	</tr>
	<tr>
		<td align="center">工作名称</td>
		<td align="center">工作描述</td>
		<td align="center">操作</td>	
	</tr>
	<s:iterator value="wm.listTask">
	<tr>
		<td align="center"><s:property value="activityName"/></td>
		<td align="center"><s:property value="description.substring(0,description.indexOf('$$'))"/></td>
		<td align="center">
			<a href='<s:property value="description.substring((description.indexOf('$$')+2)).trim()"/>&taskId=<s:property value="id"/>'>前往工作</a>
			<a href='/oa/workflow/viewPiPic.jsp?taskId=<s:property value="id"/>'>查看实时流程图</a>
		</td>
	</tr>
	</s:iterator>
</table>

</body>
</html>