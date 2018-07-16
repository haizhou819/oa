<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/sendFileRequestAction.action" method="post">
	<input type="hidden" name="wm.submitFlag" value="draftQuery">
	<table>
		<tr>
			<td colspan=4 align="center">发文查询</td>
		</tr>
		<tr>
			<td>申请人</td>
			<td><input type="text" name="sfrqm.reqUser"></td>
			<td colspan=2>申请时间大于等于<input type="text" name="sfrqm.reqTime">
				申请时间小于等于<input type="text" name="sfrqm.reqTime2">
			</td>
		</tr>
		<tr>
			<td>拟稿人</td>
			<td><input type="text" name="sfrqm.draftUser"></td>
			<td>执行部门</td>
			<td><input type="text" name="sfrqm.mainDep"></td>
		</tr>
		<tr>
			<td>紧急程度</td>
			<td><input type="text" name="sfrqm.urgency"></td>
			<td>保密级别</td>
			<td><input type="text" name="sfrqm.securityLevel"></td>
		</tr>
		<tr>
			<td>稿纸类型</td>
			<td><input type="text" name="sfrqm.draftType"></td>
			<td>文件标题</td>
			<td><input type="text" name="sfrqm.title"></td>
		</tr>
		<tr>
			<td>内容描述</td>
			<td><input type="text" name="sfrqm.contentDesc"></td>
			<td>文件号码	</td>
			<td><input type="text" name="sfrqm.fileNum"></td>
		</tr>
		<tr>
			<td>打印份数</td>
			<td><input type="text" name="sfrqm.printNums"></td>
			<td>打字员</td>
			<td><input type="text" name="sfrqm.printer"></td>
		</tr>
		<tr>
			<td>复核人</td>
			<td><input type="text" name="sfrqm.auditor"></td>
			<td>状态</td>
			<td><input type="text" name="sfrqm.state"></td>
		</tr>
		<tr>
			<td colspan="4" align="center">
				<input type="submit" value="查询">
				<input type="button" value="返回" onclick="history.back(-1);">
			</td>
		</tr>
		
	</table>
</form>
</body>
</html>