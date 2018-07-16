<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>
var _hmt = _hmt || [];
(function() {
  var hm = document.createElement("script");
  hm.src = "https://hm.baidu.com/hm.js?03ff06c7e7d36a676cbc37ed8d599d5a";
  var s = document.getElementsByTagName("script")[0]; 
  s.parentNode.insertBefore(hm, s);
})();
</script>

</head>
<body>
<form action="${pageContext.request.contextPath}/sendFileRequestAction.action" method="post">
	<input type="hidden" name="wm.submitFlag" value="add">
	<table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableLine DoubleColorTable">
		<tr>
			<td colspan=4 align="center"">发文新增</td>
		</tr>
		<tr>
			<td>申请人</td>
			<td><input type="text" name="sfrm.reqUser"></td>
			<td>申请时间</td>
			<td><input type="text" name="sfrm.reqTime"></td>
		</tr>
		<tr>
			<td>拟稿人</td>
			<td><input type="text" name="sfrm.draftUser"></td>
			<td>执行部门</td>
			<td><input type="text" name="sfrm.mainDep"></td>
		</tr>
		<tr>
			<td>紧急程度</td>
			<td><input type="text" name="sfrm.urgency"></td>
			<td>保密级别</td>
			<td><input type="text" name="sfrm.securityLevel"></td>
		</tr>
		<tr>
			<td>稿纸类型</td>
			<td><input type="text" name="sfrm.draftType"></td>
			<td>文件标题</td>
			<td><input type="text" name="sfrm.title"></td>
		</tr>
		<tr>
			<td>内容描述</td>
			<td><input type="text" name="sfrm.contentDesc"></td>
			<td>文件号码	</td>
			<td><input type="text" name="sfrm.fileNum"></td>
		</tr>
		<tr>
			<td>打印份数</td>
			<td><input type="text" name="sfrm.printNums"></td>
			<td>打字员</td>
			<td><input type="text" name="sfrm.printer"></td>
		</tr>
		<tr>
			<td>复核人</td>
			<td><input type="text" name="sfrm.auditor"></td>
			<td>状态</td>
			<td><input type="text" name="sfrm.state"></td>
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