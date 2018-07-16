<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欢迎登陆公文审批系统</title>
<link href="${pageContext.request.contextPath}/common/css/login.css" rel="stylesheet" type="text/css">
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
<%@ page import="org.jbpm.api.*" %>
<%@ page import="java.util.*" %>

<%@ page import="com.yhz.framework.login.JavassServlet" %>
<%
		String submitFlag = request.getParameter("submitFlag");
		if("login".equals(submitFlag)){
			String userCaptchaResponse = request.getParameter("userCaptchaResponse");
			
			/* boolean flag = JavassServlet.validateResponse(request,userCaptchaResponse); */
			boolean flag = true;
			if(!flag){
				out.println("你的验证码不正确==="+userCaptchaResponse);
			}else{
				session.setAttribute("LoginUserId",request.getParameter("userId"));
				session.setAttribute("password",request.getParameter("password"));
				System.out.println("登录操作---"+session.getAttribute("LoginUserId"));
			%>
				<script language="javascript">
					<!-- top.window.location.href="/oa/"; -->
					top.window.location.href="/oa/userAction.action?wm.submitFlag=userLogin";
				</script>
			<%	
			System.out.println("登录成功---------------");
				return;
			} 
		}
%>
<script language="javascript">
	function changeImg(){
		var obj = document.getElementById("jcaptcha");
		obj.src = "/oa/jc?"+Math.random();
		setTimeout("delay()",500);
	}
	function delay(){
		
	}
</script>
<div class="main-login">
	<div class="login-content">
		<h2>公文审批系统用户登录</h2>
		<form action="/oa/login2.jsp" method="post" id="login-form" name="login-form">
			<input type="hidden" name="submitFlag" value="login"> 
			 <ul style="border:1px solid #CCC;">
				<li class="p10">
					<label class="fl w80 fb" ><b>用户名：</b></label> 
					<input class="txt w150 h25 lh25" name="userId" id="userId" type="text"/>
				</li>
					
				<li class="p10">	
					<label class="fl w80 fb"><b>密&#12288;码：</b></label> 
					<input class="txt w150 h25 lh25" name="password" id="password" type="password"  />
				</li>
				 
				<!-- <li class="p10">	
					<label class="fl w80 fb"><b>验证码：</b></label> 
					<input class="txt w150 h25 lh25" type="text" id="userCaptchaResponse" 
					        name="userCaptchaResponse" width="50"> 
					<a href="javascript:void(0)" onclick='changeImg();'>
						<img id="jcaptcha" src="/oa/jc" class="vm pointer" onclick='changeImg();' 
						     style="border:1px solid #CCC;margin-top:10px"/>
	   				</a>
				</li> -->
				
				<li class="login-oper" >
					<input name="" type="submit" value="登 录" class="login-btn" style="center"/>
				</li>
			</ul>
		</form>
	</div>
	</div>
	
</body>

</html>


