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

	<table border=1 cellspacing="0" cellpadding="0" width="100%">
		<tr>
			<td colspan=3 align="center"><b>流程实例列表</b></td>	
		</tr>
		<tr>
			<td align="center">ID</td>
			<td align="center">状态</td>
			<td align="center">操作</td>
		</tr>
		<s:iterator value="listPI">
		<tr>
			<td align="center"><s:property value="id"/></td>
			<td align="center"><s:property value="state"/></td>
			<td align="center">
				<a href='/oa/workflow/viewPiPic2.jsp?piId=<s:property value="id"/>'>查看实时流程图</a>
			</td>
		</tr>
		</s:iterator>
	</table>

</body>
</html>