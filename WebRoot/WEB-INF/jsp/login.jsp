<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统登录</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/EasyUI/jquery.min.js"></script>
<script type="text/javascript">

</script>
</head>
<body>
	<div style="width:500px;height:300px;background-color:gray;margin:0 auto;border-radius: 20px;margin-top:200px;">
		<form action="${pageContext.request.contextPath }/login.action" method="post" enctype="multipart/form-data" style="text-align:left;padding-left:100px;">
			<br/><br/>
			用户名：<input type="text" name="username" value=""/><br/><br/>
			密&nbsp;码：<input type="password" name="password" value=""/><br/><br/>
			<input type="submit" value="登录"/>
		</form>
	</div>
</body>
</html>