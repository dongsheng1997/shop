<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<table border="0" width="100%" cellpadding="0" cellspacing="0">
	<s:iterator value="list" var="orderItem">
		<tr>
			<td>图片</td>
			<td>数量</td>
			<td>小计</td>
		</tr>
		<tr>
			<td><img width="40" height="45" alt="" src="${pageContext.request.contextPath }/<s:property value="#orderItem.product.image"/>"></td>
			<td><s:property value="#orderItem.count"/></td>
			<td><s:property value="#orderItem.subtotal"/></td>
		</tr>
	</s:iterator>
</table>