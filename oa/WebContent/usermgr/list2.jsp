<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<%@taglib prefix="s" uri="/struts-tags" %>

<%@ page import="java.util.*" %>
<%@ page import="com.yhz.oa.usermgr.vo.*" %>
<%@ page import="com.yhz.oa.usermgr.dao.dao.*" %>
<%@ page import="com.yhz.oa.filemgr.sendfile.sendfilerequest.*" %>
<%@ page import="com.yhz.framework.web.*" %>

<script language="javascript">
	function init(){
		//var a = dialogArguments;
		
		var a = '<%=request.getParameter("nowUsers")%>';
		var ss = a.split(",");
		
		for(var i=0;i<ss.length;i++){
			document.getElementById(ss[i]).checked=true;
		}
	}
	function myBack(){
		var str = "";
		var strId = "";
		for(var i=0;i<document.all("cu").length;i++){
			if(document.all("cu")[i].checked==true){
				str = str + document.all("cu")[i].value +",";
				strId = strId + document.all("cu")[i].id +",";
			}
		}
		str = str.substring(0,str.length-1);		
		strId = strId.substring(0,strId.length-1);
		
		window.opener.window.document.all("wm.depManagerSignUserNames").value=str;
		window.opener.window.document.all("wm.depManagerSignUserIds").value = strId;
		
	    window.close();
	}
</script>

<%
	UserDao dao = Client.getUserDao();
	List<UserModel> list = dao.getAll(new UserQueryModel(),false,0,0);
	request.setAttribute("list",list);
	
	WebModel wm = new WebModel();
	wm.setCount(100);
	wm.setNowPage(1);

	request.setAttribute("wm",wm);
%>
<%@taglib prefix="javass" uri="/javass-tags" %>
<body onload="init();">
<table>
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
<form>
	<table border=1>
		<tr>
			<td colspan=15 align="center">用户列表</td>
		</tr>
		<tr>
			<td>选择</td>
			<td>uuid</td>
			<td>姓名</td>
		</tr>
<s:iterator  value="#request['list']">		
		<tr>	
			<td><input type="checkbox" name="cu" value='<s:property value="name"/>' id='<s:property value="uuid"/>'/></td>
			<td><s:property value="uuid"/></td>
			<td><s:property value="name"/></td>
		</tr>
</s:iterator>	

		
		<tr>
			<td colspan="15" align="center">
				<input type="button" value="确定选择" onclick="myBack();">
			</td>
		</tr>
		
	</table>
</form>
</body>
</html>