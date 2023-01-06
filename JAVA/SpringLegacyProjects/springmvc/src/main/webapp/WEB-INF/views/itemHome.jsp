<!-- jstl 사용을 위한 태그 라이브러리 설정 : JSP에서만 사용 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Goods List</title>
<!-- css 파일 적용 -->
<!-- 주의! 현재 경로가 /school/item으로 되어있으므로 한 디렉토리 위로 올라가야한다. -->
<link rel="stylesheet" href="./css/style.css" />
</head>
<body>

	<a href="./item/item.xls">excel 다운</a><br />
	<a href="./item/item.pdf">pdf 다운</a><br />
	<a href="./item/itemlist.json">json 다운</a><br />
	<a href="./item/item.csv">텍스트 출력</a><br />
	<a href="./item/itemlistrest.json">json 다운</a><br />
	<a href="./item/exception">예외 발생</a><br />
	<a href="./item/message">메세지 출력</a><br />

	<div align="center" class="body">
		<h2>상품 목록</h2>
		<table border="1">
			<tr class="header">
				<th align="center" width="80">상품 아이디</th>
				<th align="center" width="320">상품 이름</th>
				<th align="center" width="100">상품 가격</th>
			</tr>
			
			<c:forEach var="good" items="${list}">
				<tr class="body">
					<th align="center" width="80">${good.code}</th>
					<th align='center' width='320'>
						<a href="./item/detail/${good.code}">${good.name}</a>
					</th>
					<!-- <th align="center" width="320">${good.name}</th> -->
					<th align="center" width="100">${good.price}</th>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>