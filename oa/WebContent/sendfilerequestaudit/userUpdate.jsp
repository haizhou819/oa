<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ taglib prefix="s" uri="/struts-tags"%>

	<table width="100%" border=1>
		<tr>
			<td>
				<table border=1 cellspacing="0" cellpadding="0" width="100%">
					<tr>
						<td colspan="6" align="center">您的发文申请单的审核记录</td>
					</tr>
					<tr>
						<td>审核人</td>
						<td>审核时间</td>
						<td>审核结果</td>
						<td>审核描述</td>
					</tr>
					<s:iterator value="wm.listAR">
						<tr>
							<td><s:property value="auditUser" /></td>
							<td><s:property value="auditTime" /></td>
							<td><s:property value="auditResult" /></td>
							<td><s:property value="auditDesc" /></td>
						</tr>
					</s:iterator>
				</table>
			</td>
		</tr>
		<tr>
			<td><hr /></td>
		</tr>
		<tr>
			<td>
				<form
					action="${pageContext.request.contextPath}/sendFileAuditAction.action"
					method="post">
					<input type="hidden" name="wm.submitFlag" value="saveMyUpdate">
					<input type="hidden" name="sfrm.uuid"
						value='<s:property value="sfrm.uuid"/>'> <input
						type="hidden" name="sfrm.reqUser"
						value='<s:property value="sfrm.reqUser"/>'> <input
						type="hidden" name="sfrm.reqTime"
						value='<s:property value="sfrm.reqTime"/>'> <input
						type="hidden" name="requestUuid"
						value='<s:property value="requestUuid"/>'> <input
						type="hidden" name="taskId" value='<s:property value="taskId"/>'>
					<table width="100%">
						<tr>
							<td colspan=4 align="center"">修改发文拟稿</td>
						</tr>
						<tr>
							<td>拟稿人</td>
							<td><input type="text" name="sfrm.draftUserName"
								value='<s:property value="sfrm.draftUserName"/>' readonly>
								<input type="hidden" name="sfrm.draftUser"
								value='<s:property value="sfrm.draftUser"/>'></td>

							<td>执行部门</td>
							<td><input type="text" name="sfrm.mainDep"
								value='<s:property value="sfrm.mainDep"/>'></td>
						</tr>
						<tr>
							<td>紧急程度</td>
							<td><input type="text" name="sfrm.urgency"
								value='<s:property value="sfrm.urgency"/>'></td>
							<td>保密级别</td>
							<td><input type="text" name="sfrm.securityLevel"
								value='<s:property value="sfrm.securityLevel"/>'></td>
						</tr>
						<tr>
							<td>稿纸类型</td>
							<td><input type="text" name="sfrm.draftType"
								value='<s:property value="sfrm.draftType"/>'></td>
							<td>文件标题</td>
							<td><input type="text" name="sfrm.title"
								value='<s:property value="sfrm.title"/>'></td>
						</tr>
						<tr>
							<td>内容描述</td>
							<td colspan=3><input type="text" name="sfrm.contentDesc"
								value='<s:property value="sfrm.contentDesc"/>'></td>
						</tr>
						<tr>
							<td>修改后的动作</td>
							<td colspan=3>
								提交<input type="radio"name="sfrm.afterUpdate" value="goon"> 
								<br> 
								取消 <input type="radio" name="sfrm.afterUpdate" value="dispose">
							</td>
						</tr>
						<tr>
							<td colspan="4" align="center">
							<input type="submit" value="保存修改"></td>
						</tr>

					</table>
				</form>
			</td>
		</tr>
	</table>
</body>
</html>