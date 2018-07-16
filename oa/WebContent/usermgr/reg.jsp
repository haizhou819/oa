<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.divcss{width:100%;height:150px;border:1px solid #000}
</style>
</head>
<%@taglib prefix="s" uri="/struts-tags" %>
<body>
<form action="${pageContext.request.contextPath}/userAction.action" method="post">
	<input type="hidden" name="wm.submitFlag" value="add">
	<div class="divcss">
	<table cellspacing="0" cellpadding="0" width="100%">
		<tr>
			<td colspan=4 align="center">新用户注册</td>
		</tr>
		<tr>
			<td align="right">账号:</td>
			<td><input type="text" name="um.uuid" /></td>
			<td align="right">姓名:</td>
			<td><input type="text" name="um.name" /></td>
		</tr>
		
		<tr>
			<td align="right">密码:</td>
			<td><input name="um.password" type="text" /></td>
			<td align="right">手机:</td>
			<td><input type="text" name="um.phone" /></td>
		</tr>
		<tr>
			<td align="right">邮件:</td>
			<td><input type="text" name="um.mail" /></td>
			<td align="right">权限:</td>
			<td><input type="text" name="um.authority" /></td>
		</tr>
		<tr>
			<td align="right">角色:</td>
			<td><input type="text" name="um.role" /></td>
		</tr>
		<tr>
			<td colspan="4" align="center">
				<input type="submit" value="保存">
				<input type="button" value="返回" onclick="history.back(-1);">
			</td>
		</tr>
		
	</table>
	</div>
</form>
</body>
</html>