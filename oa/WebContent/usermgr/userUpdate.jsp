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
<form action="${pageContext.request.contextPath}/userAction.action" method="post" enctype="multipart/form-data">
	<input type="hidden" name="wm.submitFlag" value="userUpdate">
	<input type="hidden" name="um.uuid" value='<s:property value="um.uuid"/>'>
	<table border=0 cellspacing="0" cellpadding="0" width="100%">
		<tr>
			<td colspan=4 align="center"><b>用户信息修改</b></td>
		</tr>
		<tr>
			<td>ID</td>
			<td>
				<input type="text" name="um.uuid" value='<s:property value="um.uuid"/>' readonly>
			</td>
			<td>姓名</td>
			<td>
				<input type="text" name="um.name" value='<s:property value="um.name"/>'>
			</td>
		</tr>
		<tr>
			<td>密码</td>
			<td>
				<input type="text" name="um.password" value='<s:property value="um.password"/>'>
			</td>
			<td>电话</td>
			<td>
				<input type="text" name="um.phone" value='<s:property value="um.phone"/>'>
			</td>
		</tr>
		<tr>
			<td>邮件</td>
			<td>
				<input type="text" name="um.mail" value='<s:property value="um.mail"/>'>
			</td>
			<td>角色</td>
			<td><input type="text" name="um.role" value='<s:property value="um.role"/>'></td>
		</tr>
		<tr>
			<td>权限</td>
			<td colspan=3><input type="text" name="um.authority" value='<s:property value="um.authority"/>'></td>
		</tr>
		<tr>
			<td colspan="4" align="center">
				<input type="submit" value="保存">
				<input type="button" value="返回" onclick="history.back(-1);">
			</td>
		</tr>
		
	</table>
</form>
</body>
</html>