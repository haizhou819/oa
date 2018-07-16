<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<body bgcolor="#F0F9FE">
<table width="80%">
	<tr>
		<td>
			<img src="../images/logo.gif" width=200 height=100></img>
		</td>
		<td align="center">
			<b><font size="5" color="blue">欢迎使用公文审批系统</font></b>
		</td>
	</tr>
	<tr>
		<td colspan=2 align="right">
			<%
				Object obj = session.getAttribute("LoginUserId");
				if(obj != null){
%>
					欢迎<%=obj %>登录本系统，<a href="#" onclick='top.window.location.href="/oa/login.jsp"'>重新登录</a>
<%					
				}else{
%>
					<a href="#" onclick='top.window.location.href="/oa/login.jsp"'>请登录</a>
<%					
					
				}
			%>
			
		</td>
	</tr>
</table>			
<hr noshade size=1 color=#C0C0C0>
</body>
