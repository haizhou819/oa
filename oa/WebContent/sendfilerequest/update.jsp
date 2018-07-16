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
		alert(b);
	}
</script>
<form action="${pageContext.request.contextPath}/sendFileRequestAction.action" method="post">
	<input type="hidden" name="wm.submitFlag" value="update">
	<input type="hidden" name="sfrm.uuid" value='<s:property value="sfrm.uuid"/>'>
	<table>
		<tr>
			<td colspan=4 align="center"">发文修改</td>
		</tr>
		<tr>
			<td>申请人</td>
			<td><input type="text" name="sfrm.reqUser" value='<s:property value="sfrm.reqUser"/>'></td>
			<td>申请时间</td>
			<td>
				<input name="sfrm.reqTimeStr" type="text" value='<s:property value="sfrm.reqTimeStr"/>' maxlength="20" class="Wdate" onclick="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'});"/>
			</td>
		</tr>
		<tr>
			<td>拟稿人</td>
			<td><input type="text" name="sfrm.draftUserName" value='<s:property value="sfrm.draftUserName"/>' readonly>
				<input type="button" value="点击选择" onclick="choiseUser1();">
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
			<td><input type="text" name="sfrm.contentDesc" value='<s:property value="sfrm.contentDesc"/>'></td>
			<td>文件号码	</td>
			<td><input type="text" name="sfrm.fileNum" value='<s:property value="sfrm.fileNum"/>'></td>
		</tr>
		<tr>
			<td>打印份数</td>
			<td><input type="text" name="sfrm.printNums" value='<s:property value="sfrm.printNums"/>'></td>
			<td>打字员</td>
			<td><input type="text" name="sfrm.printer" value='<s:property value="sfrm.printer"/>'></td>
		</tr>
		<tr>
			<td>复核人</td>
			<td><input type="text" name="sfrm.auditor" value='<s:property value="sfrm.auditor"/>'></td>
			<td>状态</td>
			<td><input type="text" name="sfrm.state" value='<s:property value="sfrm.state"/>'></td>
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