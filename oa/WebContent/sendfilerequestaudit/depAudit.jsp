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
					<td>申请时间</td>
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
<script language="javascript">
	var myRequest;
	function showMsg(){
		var divObj = document.getElementById("msgDiv");
		var trObj = document.getElementById("auditDetail");
		
		
		if (trObj.style.display == "none"){
			trObj.style.display="";
			divObj.innerHTML = "<a href='javascript:void' onclick='showMsg();'>(-)点击收缩详细的记录</a>";
			//触发ajax，向后台去获取需要展示的数据
			myRequest = new ActiveXObject("Msxml2.XMLHTTP");
			myRequest.onreadystatechange = retWork3;
			myRequest.open("GET",'/oa/sendFileAuditAction.action?wm.submitFlag=getHistoryAudit3&requestUuid=<s:property value="requestUuid"/>',true);
			myRequest.send(null);
			
		}else{
			trObj.style.display="none";
			divObj.innerHTML = "<a href='javascript:void' onclick='showMsg();'>(+)点击展开详细的记录</a>";
			clearAjaxData();
		}
	}
	function clearAjaxData(){
		var obj = document.getElementById("detailTable");
		for(var i=(obj.rows.length-1);i>0;i--){
			obj.deleteRow(i);
		}
	}
	
	function retWork3(){
		if(myRequest.readyState==4){
			var ret = myRequest.responseText;	
			//后续处理
			
			var obj = eval("("+ret+")");
			
			var myTable = document.getElementById("detailTable");
			for(var i=0;i<obj.length;i++){
				var tr1 = myTable.insertRow();
				var c0 = tr1.insertCell();
				var c1 = tr1.insertCell();
				var c2 = tr1.insertCell();
				var c3 = tr1.insertCell();
				
				c0.innerText = obj[i].auditUser;
				c1.innerText = obj[i].auditTime;
				c2.innerText = obj[i].auditResult;
				c3.innerText = obj[i].auditDesc;
			}
		}
	}
	
	function retWork2(){
		if(myRequest.readyState==4){
			var ret = myRequest.responseText;	
			//后续处理
			//alert(ret);
			var obj = document.getElementById("detailTable");
			
			var ops = ret.split("|");
			for(var i=0;i<ops.length;i++){
				var op = ops[i];				
				var ss = op.split(",");
				
				var tr1 = obj.insertRow();
				
				var c0 = tr1.insertCell();
				var c1 = tr1.insertCell();
				var c2 = tr1.insertCell();
				var c3 = tr1.insertCell();
				
				c0.innerText = ss[1];
				c1.innerText = ss[0];
				c2.innerText = ss[2];
				c3.innerText = ss[3];
			}
		}
	}
	
	
	function retWork(){
		if(myRequest.readyState==4){
			var ret = myRequest.responseText;	
			//后续处理
			//alert(ret);
			document.getElementById("detailTD").innerHTML=ret;
		}
	}
</script>
	<tr>
		<td>
			<table border=1 width="100%">
				<tr>
					<td colspan="6" align="center">历史审核记录&nbsp;&nbsp;
						<div id="msgDiv"><a href="javascript:void" onclick="showMsg();">(+)点击展开详细的记录</a></div>
					</td>
				</tr>
				<tr style="display:none" id="auditDetail">
					<td id="detailTD">
						<table border=1 width="100%" id="detailTable">
							<tr>
								<td>审核人</td>
								<td>审核时间</td>
								<td>审核结果</td>
								<td>审核描述</td>
							</tr>
							
						</table>
					</td>
				</tr>				
			</table>
		</td>
	</tr>
	<tr>
		<td><hr/></td>
	</tr>
	<tr>
		<td>
		<form action="/oa/sendFileAuditAction.action" method="post">
			<input type="hidden" name="wm.submitFlag" value="saveDepAudit">
			<input type="hidden" name="arm.requestUuid" value='<s:property value="requestUuid"/>'/>
			<input type="hidden" name="taskId" value='<s:property value="taskId"/>'/>
			<input type="hidden" name="requestUuid" value='<s:property value="requestUuid"/>'/>
			<table border=1 cellspacing="0" cellpadding="0" width="100%">
				<tr>
					<td colspan="4" align="center">请填写发文申请单的部门内审记录</td>
				</tr>
				<tr>
					<td>审核结果</td>
					<td>
						同    意：<input type="radio" name="arm.auditResult" value="needDepMangerAudit">
						<br>
						不同意：	<input type="radio" name="arm.auditResult" value="needUpdate">
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