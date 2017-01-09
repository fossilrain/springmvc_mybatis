<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品信息修改</title>
</head>
<body>
<c:if test="${allErrors != null }">
	<c:forEach items="${allErrors }" var="error">
		${error.defaultMessage }<br/>
	</c:forEach>
</c:if>
	<div style="width:500px;height:300px;background-color:gray;margin:0 auto;border-radius: 20px;margin-top:200px;">
		<form action="${pageContext.request.contextPath }/item/editItemSubmit.action" method="post" enctype="multipart/form-data" style="text-align:left;padding-left:100px;">
			<input type="hidden" name="id" value="${itemCustom.id }"/><br/><br/>
			商品名称：<input type="text" name="name" value="${itemCustom.name }"/><br/><br/>
			价&nbsp;&nbsp;格：<input type="text" name="price" value="${itemCustom.price }"/><br/><br/>
			商品描述：<input type="text" name="detail" value="${itemCustom.detail }"/><br/><br/>
			创建时间：<input type="text" name="createtime" value='<fmt:formatDate value="${itemCustom.createtime }" pattern="yyyy-MM-dd HH:mm:ss"/>'/><br/><br/>
			商品图片：<input type="file" name="items_pic"/><br/><br/>
			<input type="submit" value="提交"/> 
		</form>
	</div>
</body>
</html>