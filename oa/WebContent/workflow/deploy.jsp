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

<form action="${pageContext.request.contextPath}/deployAction.action?submitFlag=deploy" method="post" enctype="multipart/form-data">
	<table>
		<tr>
			<td>请选择要部署的流程定义</td>	
		</tr>
		<tr>
			<td>
				<input type="file" name="myFile">
			</td>	
		</tr>
		<tr>
			<td><input type="submit"></td>
		</tr>
	</table>
</form>

</body>
</html>