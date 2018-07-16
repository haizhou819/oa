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
<form id="myQueryForm" action="${pageContext.request.contextPath}/sendFileRequestAction.action" method="post">
<input type="hidden" name="wm.submitFlag" value="draftQuery">
<table cellspacing="0" cellpadding="0" width="100%">
	<tr>
		<td>申请人</td>
		<td><input type="text" name="sfrqm.reqUser" 
			<s:if test="#session['isQuery'] == 'false'">
			value='<s:property value="#session['draftQm'].reqUser"/>'
			</s:if>
		/>
		</td>
		<td>文件标题</td>
		<td><input type="text" name="sfrqm.title" value='<s:property value="#session['draftQm'].title"/>'></td>
		<td>
		<input type="submit" value="快速查询">
		&nbsp;&nbsp;&nbsp;<a href=''>高级查询</a></td>
	</tr>
</table>
</form>
<form>
	<table border=1 cellspacing="0" cellpadding="0" width="100%">
		<tr>
			<td colspan=15 align="center"><b>初稿列表</b></td>
		</tr>
		<tr>
			<td align="center">拟稿人</td>
			<td align="center">执行部门</td>
			<td align="center">紧急程度</td>
			<td align="center">保密级别</td>
			<td align="center">稿纸类型</td>
			<td align="center">文件标题</td>
			<td align="center">内容描述</td>
			<td align="center">状态</td>
			<td align="center">操作</td>
		</tr>
<s:iterator  value="wm.listCol">		
		<tr>	
			<td align="center"><s:property value="draftUserName"/></td>
			<td align="center"><s:property value="mainDep"/></td>
			<td align="center"><s:property value="urgency"/></td>
			<td align="center"><s:property value="securityLevel"/></td>
			<td align="center"><s:property value="draftType"/></td>
			<td align="center"><s:property value="title"/></td>
			<td align="center"><s:property value="contentDesc"/></td>
			<td align="center"><s:property value="state"/></td>
			<td align="center">
				<a href='${pageContext.request.contextPath}/sendFileRequestAction.action?wm.submitFlag=toDraftUpdate&sfrm.uuid=<s:property value="uuid"/>'>修改</a>
				
				<a href=''>删除</a>
				
				<a href='${pageContext.request.contextPath}/sendFileRequestAction.action?wm.submitFlag=toDepAudit&sfrm.uuid=<s:property value="uuid"/>'>提交部门内审</a>
			</td>
		</tr>
</s:iterator>	
<%@taglib prefix="javass" uri="/javass-tags" %>
<javass:myPage actionName="sendFileRequestAction" toList="toDraftList"></javass:myPage>	 
		<tr>
			<td colspan="15" align="center">
				<input type="button" value="继续起草稿件" onclick="window.location='${pageContext.request.contextPath}/sendFileRequestAction.action?wm.submitFlag=toDraft'">
				<input type="button" value="转到查询" onclick="window.location='${pageContext.request.contextPath}/sendFileRequestAction.action?wm.submitFlag=toDraftQuery'">
			</td>
		</tr>
	</table>
</form>
</body>
</html>