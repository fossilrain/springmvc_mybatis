<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>批量修改商品</title>
</head>
<body>
	<form action="${pageContext.request.contextPath }/item/editItemsLiSubmit.action" method="post" style="text-align:left;">
		<input type="submit" value="批量修改"/>
		<table border="1" cellspacing="3">
			<tr>
				<th>商品名称</th>
				<th>价格</th>
				<th>创建日期</th>
				<th>商品描述</th>
			</tr>
			<c:forEach items="${itemList }" var="item" varStatus="status">
				<tr>
					<td><input type="text" name="itemList[${status.index }].name" value="${item.name }"/></td>
					<td><input type="text" name="itemList[${status.index }].price" value="${item.price }"/></td>
					<td><input type="text" name="itemList[${status.index }].createtime" value='<fmt:formatDate value="${item.createtime }" pattern="yyyy-MM-dd HH:mm:ss"/>'/></td>
					<td><input type="text" name="itemList[${status.index }].detail" value="${item.detail }"/></td>
				</tr>
			</c:forEach>
		</table>
	</form>
</body>
</html>