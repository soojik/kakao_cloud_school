<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Spring MVC - Home</title>
</head>
<body>
	<P>  The time on the server is ${serverTime}. </P>
	<a href="hello">안녕</a>
	<br />
	<a href="message/detail/60">URL에 포함된 매개변수 test</a>
	<br />
	<a href="redirect">리다이렉트</a>
	<br />
	<a href="item">아이템</a>
	
	<br></br>
	
	<!-- action은 생략하면 현재 요청
		method는 기본이 GET
		enctype은 파일이 있는 경우, multipart/form-data
		id는 스크립트에서 사용하기 위해서 -->
	<form action="param1">
		이름:<input type="text" name="name" /> <br />
		비밀번호:<input type="password" name="passwd"/ > <br />
		<input type="submit" value="전송" />
	</form>
	<br />
	<form action="param2" method ="post">
		이름:<input type="text" name="name" /> <br />
		비밀번호:<input type="password" name="passwd"/ > <br />
		<input type="submit" value="전송" />
	</form>
	
	<br />
	<form action="param3" method ="post">
		이름:<input type="text" name="name" /> <br />
		비밀번호:<input type="password" name="passwd"/ > <br />
		<input type="submit" value="전송" />
	</form>
	
</body>
</html>