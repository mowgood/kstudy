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
		background-image : linear-gradient(to bottom, grey, white);
		border : 1px dotted black;
	}
</style>
</head>
<body>
<div>
<p>${ currentTime.hour }시 ${ currentTime.minute }분에 당첨 실패!! 아쉽아쉽</p>
<img src="/edu/images/duke.png" width="50"><br>
</div>
<a href="/mvc/exercise/lottoForm.html">로또응모링크</a>
</body>
</html>