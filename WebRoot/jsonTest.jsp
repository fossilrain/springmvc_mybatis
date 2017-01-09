<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>json交互测试</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/EasyUI/jquery.min.js"></script>
<script type="text/javascript">
	//请求json，响应json
	function requestJson(){
		jQuery.ajax({
			url:'${pageContext.request.contextPath }/requestJson.action',
			contentType:"application/json;charset=UTF-8",//设置请求类型为json，默认为"application/x-www-form-urlencoded"
			type:"post",
			dataType:"json",
			data:'{"name":"iphone","price":"6300.00"}',
			async:false,
			success:function(serverinfo){
				alert(JSON.stringify(serverinfo));
			}
		});
	}
	//请求key/value，响应json
	function requestKeyVal(){
		jQuery.ajax({
			url:'${pageContext.request.contextPath }/requestKeyVal.action',
			type:"post",
			dataType:"json",
			data:{"name":"iphone","price":"6300.00"},
			async:false,
			success:function(serverinfo){
				alert(JSON.stringify(serverinfo));
			}
		});
	}
	//请求json，响应json[map测试]
	function requestJsonMap(){
		jQuery.ajax({
			url:'${pageContext.request.contextPath }/requestJsonMap.action',
			contentType:"application/json;charset=UTF-8",//设置请求类型为json，默认为"application/x-www-form-urlencoded"
			type:"post",
			dataType:"json",
			data:{"name":"iphone","price":"6300.00"},
			async:false,
			success:function(serverinfo){
				alert(JSON.stringify(serverinfo));
			}
		});
	}
</script>
</head>
<body>
	<input type="button" value="请求json，响应json" onclick="requestJson();"/>
	<input type="button" value="请求key/value，响应json" onclick="requestKeyVal();"/>
	<input type="button" value="请求json，响应json[map]" onclick="requestJsonMap();"/>
</body>
</html>