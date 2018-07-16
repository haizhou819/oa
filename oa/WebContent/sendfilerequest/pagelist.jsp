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
<input type="hidden" name="wm.submitFlag" value="query">
<table width="100%" border="0" cellpadding="0" cellspacing="1">
	<tr>
		<td>申请人</td>
		<td><input type="text" name="sfrqm.reqUser"
				<s:if test="#session['isQuery'] == 'false'">
				value='<s:property value="#session['qm'].reqUser"/>'
				</s:if>
			/>
		</td>
		<td>文件标题</td>
		<td><input type="text" name="sfrqm.title" value='<s:property value="#session['qm'].title"/>'></td>
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
			<td align="center"><s:property value="reqTime"/></td>
			<td align="center"><s:property value="draftUser"/></td>
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
<script language="javascript">
	function funToPage(){
		var v = document.getElementById("toPage").value;
		
		if(parseInt(v)< 1 || parseInt(v)>parseInt(<s:property value="wm.maxPage"/>)){
			alert('对不起，输入的页面号超出了范围，您可以输入的值为1-<s:property value="wm.maxPage"/>');
			return;
		}
		
		
		window.location.href="${pageContext.request.contextPath}/sendFileRequestAction.action?wm.submitFlag=toList&wm.nowPage="+v;
	}

</script>
<tr>
<td colspan=18>
	<div align="center"  class="page">
		<s:if test="wm.nowPage==1">
			首页 
  		| 上一页
		</s:if>
		<s:if test="wm.nowPage > 1 ">
	  		<a href="${pageContext.request.contextPath}/sendFileRequestAction.action?wm.submitFlag=toList&wm.nowPage=1">首页</a> 
	  		| <a href='${pageContext.request.contextPath}/sendFileRequestAction.action?wm.submitFlag=toList&wm.nowPage=<s:property value="wm.nowPage-1"/>'>上一页</a>
  		</s:if>
  		<s:if test="wm.nowPage==wm.maxPage">
	  		| 下一页 
	  		| 尾页
  		</s:if>
  		<s:if test="wm.nowPage >= 1 && wm.nowPage < wm.maxPage">
	  		| <a href='${pageContext.request.contextPath}/sendFileRequestAction.action?wm.submitFlag=toList&wm.nowPage=<s:property value="wm.nowPage+1"/>'>下一页</a> 
  			| <a href='${pageContext.request.contextPath}/sendFileRequestAction.action?wm.submitFlag=toList&wm.nowPage=<s:property value="wm.maxPage"/>'>尾页</a>	
	  	</s:if>
  		 
  		| 页次：<s:property value="wm.nowPage"/>/<s:property value="wm.maxPage"/> 页 | 共有<s:property value="wm.count"/>条 
  		 <input id="toPage" type="text" class="input" size="2" /> 
  		 <a href="#" onclick="funToPage();">转到</a>
  	</div>
	</td>
	</tr>
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