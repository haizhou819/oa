<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>sendfile list</title>
<link href="${pageContext.request.contextPath}/common/css/css.css" rel="stylesheet" type="text/css">
</head>
<%@taglib prefix="s" uri="/struts-tags" %>
<body>
<script language="javascript">
	function myQuery(){
		alert(document.getElementById("myQueryForm"));
		document.getElementById("myQueryForm").submit();
	}
</script>

<form id="myQueryForm" action="${pageContext.request.contextPath}/sendFileRequestAction.action" method="post">
<input type="hidden" name="wm.submitFlag" value="highQuery">
<table width="50%" border="0" cellpadding="0" cellspacing="1" class="tableLine DoubleColorTable">
	<tr>
		<td>申请人</td>
		<td><input type="text" name="sfrqm.reqUser"
				<s:if test="#session['isQuery'] == 'false'">
				value='<s:property value="#session['qm'].reqUser"/>'
				</s:if>
			/>
		</td>
		<td>文件标题</td>
		<td><input type="text" name="sfrqm.title" 
			<s:if test="#session['isQuery'] == 'false'">
			value='<s:property value="#session['qm'].title"/>'
			</s:if>
			/>
		</td>
		<td>
		<input type="submit" value="快速查询">
		&nbsp;&nbsp;&nbsp;<a href=''>高级查询</a></td>
	</tr>
</table>
</form>
	<table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableLine DoubleColorTable">
		<tr>
			<td colspan=15 align="center"><b>发文列表</b></td>
		</tr>
		<tr>
			<td align="center"  class="tableLineBge">申请人</td>
			<td align="center"  class="tableLineBge">申请时间</td>
			<td align="center"  class="tableLineBge">拟稿人</td>
			<td align="center"  class="tableLineBge">执行部门</td>
			<td align="center"  class="tableLineBge">紧急程度</td>
			<td align="center"  class="tableLineBge">保密级别</td>
			<td align="center"  class="tableLineBge">稿纸类型</td>
			<td align="center"  class="tableLineBge">文件标题</td>
			<td align="center"  class="tableLineBge">内容描述</td>
			<td align="center"  class="tableLineBge">文件号码	</td>
			<td align="center"  class="tableLineBge">打印份数</td>
			<td align="center"  class="tableLineBge">打字员</td>
			<td align="center"  class="tableLineBge">复核人</td>
			<td align="center"  class="tableLineBge">状态</td>
			<td align="center"  class="tableLineBge">操作</td>
		</tr>
<s:iterator  value="wm.listCol">		
		<tr>	
			<td align="center"><s:property value="reqUser"/></td>
			<td align="center"><s:property value="reqTimeStr"/></td>
			<td align="center"><s:property value="draftUserName"/></td>
			<td align="center"><s:property value="mainDep"/></td>
			<td align="center"><s:property value="urgency"/></td>
			<td align="center"><s:property value="securityLevel"/></td>
			<td align="center"><s:property value="draftType"/></td>
			<td align="center"><s:property value="title"/></td>
			<td align="center"><s:property value="contentDesc"/></td>
			<td align="center"><s:property value="fileNum"/></td>
			<td align="center"><s:property value="printNums"/></td>
			<td align="center"><s:property value="printer"/></td>
			<td align="center"><s:property value="auditor"/></td>
			<td align="center"><s:property value="state"/></td>
			<td align="center">
				<a href='${pageContext.request.contextPath}/sendFileRequestAction.action?wm.submitFlag=toUpdate&sfrm.uuid=<s:property value="uuid"/>'>修改</a>
				<a href="javascript: if (confirm('是否确认删除?')) window.location.href='${pageContext.request.contextPath}/sendFileRequestAction.action?wm.submitFlag=delete&sfrm.uuid=<s:property value="uuid"/>'">删除</a>
			</td>
		</tr>
</s:iterator>	
<%@taglib prefix="javass" uri="/javass-tags" %>
<javass:myPage actionName="sendFileRequestAction" toList="toList"></javass:myPage>
		
		<tr>
			<td colspan="15" align="center">
				<input type="button" value="转到新增" onclick="window.location='${pageContext.request.contextPath}/sendFileRequestAction.action?wm.submitFlag=toAdd'">
				<input type="button" value="转到查询" onclick="window.location='${pageContext.request.contextPath}/sendFileRequestAction.action?wm.submitFlag=toQuery'">
			</td>
		</tr>
		
	</table>
</form>
</body>
</html>