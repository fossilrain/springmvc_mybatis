<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品列表</title>
</head>
<body>
当前用户：${username },
<c:if test="${username != null }">
	<a href="${pageContext.request.contextPath }/logout.action">退出</a>
</c:if>
<br/>
	<a href="${pageContext.request.contextPath }/item/editItemsLi.action">批量修改商品</a><br/><br/>
	<form action="${pageContext.request.contextPath }/item/queryItems.action" method="post" style="text-align:left;">
		商品名称：<input type="text" name="itemCustom.name" value=""/>&nbsp;&nbsp;&nbsp;
		商品类别：<select name="">
			<c:forEach items="${itemTypes }" var="itemType">
				<option value="${itemType.key }" >${itemType.value }</option>
			</c:forEach>
		</select>
		<input type="submit" value="查询"/>
	</form><br/>
	<form action="${pageContext.request.contextPath }/item/deleteItems.action" method="post" style="text-align:center;">
		<table border="1" cellspacing="3">
			<tr>
				<th>商品名称</th>
				<th>价格</th>
				<th>创建日期</th>
				<th>商品描述</th>
				<th>操作</th>
				<th><input type="submit" value="批量删除"/></th>
			</tr>
			<c:forEach items="${itemList }" var="item">
				<tr>
					<td>${item.name }</td>
					<td>${item.price }</td>
					<td><fmt:formatDate value="${item.createtime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td>${item.detail }</td>
					<td><a href="${pageContext.request.contextPath }/item/editItem.action?id=${item.id}">修改</a></td>
					<td><input type="checkbox" name="item_id" value="${item.id}"></input></td>
				</tr>
			</c:forEach>
		</table>
	</form>
</body>
</html>