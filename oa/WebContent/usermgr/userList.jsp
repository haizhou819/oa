<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<%@taglib prefix="s" uri="/struts-tags" %>
<body>
<form id="myQueryForm" action="${pageContext.request.contextPath}/userAction.action" method="post">
<input type="hidden" name="wm.submitFlag" value="userQuery">
<table cellspacing="0" cellpadding="0" width="100%">
	<tr>
		<td>ID:</td>
		<td><input type="text" name="uqm.uuid" 
			<s:if test="#session['isQuery'] == 'true'">
			value='<s:property value="#session['uQm'].uuid"/>'
			</s:if>
		/>
		</td>
		<td>姓名:</td>
		<td><input type="text" name="uqm.name" value='<s:property value="#session['uQm'].name"/>'></td>
		<td>
		<input type="submit" value="快速查询">
		&nbsp;&nbsp;&nbsp;<a href=''>高级查询</a></td>
	</tr>
</table>
</form>
<form>
	<table border=1 cellspacing="0" cellpadding="0" width="100%">
		<tr>
			<td colspan=15 align="center"><b>用户信息</b></td>
		</tr>
		<tr>
			<td align="center">ID</td>
			<td align="center">姓名</td>
			<td align="center">密码</td>
			<td align="center">电话</td>
			<td align="center">邮件</td>
			<td align="center">角色</td> 
			<td align="center">权限</td>
			<td align="center">操作</td>
		</tr>
<s:iterator  value="wm.listCol">		
		<tr>	
			<td align="center"><s:property value="uuid"/></td>
			<td align="center"><s:property value="name"/></td>
			<td align="center"><s:property value="password"/></td>
			<td align="center"><s:property value="phone"/></td>
			<td align="center"><s:property value="mail"/></td>
			<td align="center"><s:property value="role"/></td>
			<td align="center"><s:property value="authority"/></td>
			<td align="center">
				<a href='${pageContext.request.contextPath}/userAction.action?wm.submitFlag=toUserUpdate&um.uuid=<s:property value="uuid"/>'>修改</a>
				
				<a href='${pageContext.request.contextPath}/userAction.action?wm.submitFlag=userDelete&um.uuid=<s:property value="uuid"/>'>删除</a>
				
				<!--  <a href='${pageContext.request.contextPath}/userAction.action?wm.submitFlag=toAdd&um.uuid=<s:property value="uuid"/>'>新增</a>-->
			</td>
		</tr>
</s:iterator>	
<%@taglib prefix="javass" uri="/javass-tags" %>
<javass:myPage actionName="userAction" toList="toList"></javass:myPage>	 
	</table>
</form>
</body>
</html>