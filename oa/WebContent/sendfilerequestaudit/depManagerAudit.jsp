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

<table cellspacing="0" cellpadding="0" width="100%">
	<tr>
		<td>
			<table border=1 cellspacing="0" cellpadding="0" width="100%">
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
			<table border=1 cellspacing="0" cellpadding="0" width="100%">
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
<script language="javascript">	
	function showUser(){
		document.getElementById("myDiv").style.display = "";		
	}
	function closUser(){
		document.all("wm.depManagerSignUserNames").value="";
		document.all("wm.depManagerSignUserIds").value="";
		document.getElementById("myDiv").style.display = "none";
	}
	function showChoiceUser(){
		window.open("/oa/usermgr/list2.jsp?nowUsers="+document.all("wm.depManagerSignUserIds").value
				, "ChoiceUser");
	}
</script>
	
		<td>
		<form action="/oa/sendFileAuditAction.action" method="post">
			<input type="hidden" name="wm.submitFlag" value="saveDepManagerAudit">
			<input type="hidden" name="arm.requestUuid" value='<s:property value="requestUuid"/>'/>
			<input type="hidden" name="taskId" value='<s:property value="taskId"/>'/>
			<input type="hidden" name="requestUuid" value='<s:property value="requestUuid"/>'/>
			<table border=1 cellspacing="0" cellpadding="0" width="100%">
				<tr>
					<td colspan="4" align="center">请填写发文申请单的部门领导核稿记录</td>
				</tr>
				<tr>
					<td>是否需要会签</td>
					<td>
						需要会签：<input type="radio" name="wm.needDepMangerSign" value="true" onclick="showUser();">
						<div id="myDiv" style="display:none">
							参与会签的人员
							<input type="text" name="wm.depManagerSignUserNames" readonly>
							<input type="text" name="wm.depManagerSignUserIds">
							<input type="button" value="请选择需要参与会签的人员" onclick="showChoiceUser();">
						</div>
						<br>
						不需要会签：	<input type="radio" name="wm.needDepMangerSign" value="false" onclick="closUser();">
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