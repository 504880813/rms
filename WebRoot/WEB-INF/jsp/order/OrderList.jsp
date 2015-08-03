<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>订单页面</title>
</head>
<body>
订单列表:
<table width="100%" border=1>
<tr>
	<td>餐桌号</td>
	<td>用餐人数</td>
	<td>服务员</td>
	<td>出菜状态</td>
	<td>买单状态</td>
	<td>消费金额</td>
	<td>操作</td>
</tr>
<c:forEach items="${diningTables}" var="diningTable">
<tr>	
	<td>
		<c:if test="${diningTable.isfree}">
			<img alt="空闲" src="${diningTable.freeimage}">
		</c:if>
		<c:if test="${!diningTable.isfree}">
		   <img alt="繁忙" src="${diningTable.busyimage}">
		</c:if>
	</td>
	<td>${diningTable.seatnumber}</td>
	<td>${diningTable.isfree}</td>
	<td>
	<c:if test="${diningTable.isfree }">
	<a href="${pageContext.request.contextPath }/order/startOrder.action?diningTableId=${diningTable.id}&&diningTableSeatnumber=${diningTable.seatnumber}">开台</a>
	</c:if>
	</td>
</tr>
</c:forEach>
</table>
</body>
</html>