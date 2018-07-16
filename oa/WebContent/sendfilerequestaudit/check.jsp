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

<table width="90%" border=1>
	<tr>
		<td>
			<table border=1>
				<tr>
					<td colspan="6" align="center">发文申请单</td>
				</tr>
				<tr>
					<td>申请人</td>
					<td><s:property value="sfrm.reqUser"/></td>
					<td>申请申请时间</td>
					<td><s:property value="sfrm.reqTimeStr"/></td>
					<td>拟稿人</td>
					<td><s:property value="sfrm.draftUserName"/></td>
				</tr>
				<tr>
					<td>执行部门</td>
					<td><s:property value="sfrm.mainDep"/></td>
					<td>紧急程度</td>
					<td><s:property value="sfrm.urgency"/></td>
					<td>保密级别</td>
					<td><s:property value="sfrm.securityLevel"/></td>
				</tr>
				<tr>
					<td>稿纸类型</td>
					<td><s:property value="sfrm.draftType"/></td>
					<td>文件标题</td>
					<td><s:property value="sfrm.title"/></td>
					<td>文件内容</td>
					<td><s:property value="sfrm.contentDesc"/></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td><hr/></td>
	</tr>
	<tr>
		<td>
			<table border=1>
				<tr>
					<td colspan="6" align="center">历史审核记录</td>
				</tr>
				<tr>
					<td>审核人</td>
					<td>审核时间</td>
					<td>审核结果</td>
					<td>审核描述</td>
				</tr>
<s:iterator value="wm.listAR">				
				<tr>
					<td><s:property value="auditUser"/></td>
					<td><s:property value="auditTime"/></td>
					<td><s:property value="auditResult"/></td>
					<td><s:property value="auditDesc"/></td>
				</tr>
</s:iterator>				
			</table>
		</td>
	</tr>
	<tr>
		<td><hr/></td>
	</tr>
	<tr>
		<td>
		<form action="/oa/sendFileAuditAction.action" method="post">
			<input type="hidden" name="wm.submitFlag" value="saveCheck">
			<input type="hidden" name="arm.requestUuid" value='<s:property value="requestUuid"/>'/>
			<input type="hidden" name="taskId" value='<s:property value="taskId"/>'/>
			<input type="hidden" name="requestUuid" value='<s:property value="requestUuid"/>'/>
			<table border=1>
				<tr>
					<td colspan="4" align="center">请文书核稿</td>
				</tr>
				<tr>
					<td>审核结果</td>
					<td>
						同意：<input type="radio" name="arm.auditResult" value="checkOk">
						<br>
						不同意：	<input type="radio" name="arm.auditResult" value="checkNot">
					</td>
					<td>审核描述</td>
					<td>
						<textarea name="arm.auditDesc" cols="20" rows="5"></textarea>
					</td>
				</tr>
				<tr>
					<td colspan=4 align="center">
						<input type="submit" value="保存审核记录">
					</td>
				</tr>
			</table>
	</form>		
		</td>
	</tr>
</table>

</body>
</html>