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
<%@taglib prefix="s" uri="/struts-tags" %>
<form action="${pageContext.request.contextPath}/sendFileRequestAction.action" method="post" enctype="multipart/form-data">
	<input type="hidden" name="wm.submitFlag" value="draft">
	<table  cellspacing="0" cellpadding="0">
		<tr>
			<td colspan=6 align="center"><b>发文拟稿</b></td>
		</tr>
		<tr>
			<td>文件标题</td>
			<td><input type="text" name="sfrm.title"></td>
			<td>拟稿人</td>
			<td><input type="text" name="sfrm.draftUser" readonly value='<s:property value="#session['LoginUserId']"/>'></td>
			<td>执行部门</td>
			<td><input type="text" name="sfrm.mainDep"></td>
		</tr>
		<tr>
			<td>紧急程度</td>
			<td><input type="text" name="sfrm.urgency"></td>
			<td>保密级别</td>
			<td><input type="text" name="sfrm.securityLevel"></td>
			<td>稿纸类型</td>
			<td><input type="text" name="sfrm.draftType"></td>
		</tr>
		<tr>
			<td>文件描述</td>
			<td >
				<textarea name="sfrm.contentDesc"></textarea>
			</td>
		</tr>
		<tr>
			<td colspan="6">
				<table>
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
			<td colspan="6" align="center">
				<input type="submit" value="临时保存">
			</td>
		</tr>
	</table>
	
</form>
</body>
</html>