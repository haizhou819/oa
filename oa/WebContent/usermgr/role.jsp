<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@taglib prefix="s" uri="/struts-tags" %>
<form action="${pageContext.request.contextPath}/sendFileRequestAction.action" method="post" enctype="multipart/form-data">
	<input type="hidden" name="wm.submitFlag" value="draft">
	<table  cellspacing="0" cellpadding="0">
		<tr>
			<td colspan=6 align="center"><b>角色分配</b></td>
		</tr>
		<tr>
			<td>部门内部审核人</td>
			<td><input type="text" name="sfrm.title"></td>
			<td>部门领导审核人</td>
			<td><input type="text" name="sfrm.draftUser" readonly value='<s:property value="#session['LoginUserId']"/>'></td>
			<td>文书核稿</td>
			<td><input type="text" name="sfrm.mainDep"></td>
		</tr>
		<tr>
			<td colspan="6" align="center">
				<input type="submit" value="临时保存">
			</td>
		</tr>
	</table>
	
</form>
</body>
</html>