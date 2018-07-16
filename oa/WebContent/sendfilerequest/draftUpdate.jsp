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
<script language="javascript" src="${pageContext.request.contextPath }/common/js/comp/My97Date4.7/WdatePicker.js"></script>

<script type="text/javascript">
	function choiseUser(){
		window.open("/oa/usermgr/list.jsp?trueValue="+document.all("sfrm.draftUser").value
				, "ChoiceUser");
	}
	function choiseUser1(){
		var ret = window.showModalDialog("/oa/usermgr/list.jsp", document.all("sfrm.draftUser").value
				, "dialogHeight:540px;dialogWidth:650px");
		
		var ss = ret.split(",");
		document.all("sfrm.draftUserName").value=ss[1];
		document.all("sfrm.draftUser").value=ss[0];		
	}
</script>
<form action="${pageContext.request.contextPath}/sendFileRequestAction.action" method="post" enctype="multipart/form-data">
	<input type="hidden" name="wm.submitFlag" value="draftUpdate">
	<input type="hidden" name="sfrm.uuid" value='<s:property value="sfrm.uuid"/>'>
	<table border=1 cellspacing="0" cellpadding="0" width="100%">
		<tr>
			<td colspan=4 align="center"><b>发文草稿修改</b></td>
		</tr>
		<tr>
			<td>拟稿人</td>
			<td><input type="text" name="sfrm.draftUserName" value='<s:property value="sfrm.draftUserName"/>' readonly>
				<input type="button" value="选择拟稿人" onclick="choiseUser();">
				<input type="hidden" name="sfrm.draftUser" value='<s:property value="sfrm.draftUser"/>'>
			</td>
			
			<td>执行部门</td>
			<td><input type="text" name="sfrm.mainDep" value='<s:property value="sfrm.mainDep"/>'></td>
		</tr>
		<tr>
			<td>紧急程度</td>
			<td><input type="text" name="sfrm.urgency" value='<s:property value="sfrm.urgency"/>'></td>
			<td>保密级别</td>
			<td><input type="text" name="sfrm.securityLevel" value='<s:property value="sfrm.securityLevel"/>'></td>
		</tr>
		<tr>
			<td>稿纸类型</td>
			<td><input type="text" name="sfrm.draftType" value='<s:property value="sfrm.draftType"/>'></td>
			<td>文件标题</td>
			<td><input type="text" name="sfrm.title" value='<s:property value="sfrm.title"/>'></td>
		</tr>
		<tr>
			<td>内容描述</td>
			<td colspan=3><input type="text" name="sfrm.contentDesc" value='<s:property value="sfrm.contentDesc"/>'></td>
		</tr>
		<tr>
			<td colspan="4">
				<table>
					<tr>
						<td>
							<table>
								<tr>
									<td colspan=5 align="center">现有的附件信息</td>
								</tr>
								<tr>
									<td>文件名称</td>
									<td>内容描述</td>
									<td>操作</td>
								</tr>
						<s:iterator value="listAttach">								
								<tr>
									<td><s:property value="fileName"/></td>
									<td><s:property value="contentDesc"/></td>
									<td><a href='/oa/sendFileRequestAction.action?wm.submitFlag=viewFile&attachUuid=<s:property value="uuid"/>'>查看文件内容</a></td>
								</tr>
						</s:iterator>		
							</table>
							
						</td>
					</tr>
					<tr>
						<td>请上传附件</td>	
					</tr>
					<tr>
						<td>
							<input type="file" name="myFile">
						</td>	
					</tr>
				</table>
			</td>
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