<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	p {
		width : 300px;
		background-image : linear-gradient(to bottom, orange, white);
		border : 1px dotted red;
	}
</style>
</head>
<body>
<div>
<p>${ currentTime.hour }시 ${ currentTime.minute }분에 당첨!! 추카추카~~</p>
<img src="/edu/images/clover.png" width="50"><br>
</div>
</body>
</html>