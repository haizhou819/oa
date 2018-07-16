<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@ taglib prefix="s" uri="/struts-tags" %>
<table border=1 width="80%">
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