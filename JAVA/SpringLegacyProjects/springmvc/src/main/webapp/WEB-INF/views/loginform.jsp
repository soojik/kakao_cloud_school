<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<!-- 스프링 태그 라이브러리 설정 -->
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Form</title>
</head>
<body>

<form:form modelAttribute="member">

	<p>
		<label for="email">
			<!-- laebl.properties에서 설정한 이름 -->
			<spring:message code="email" />
		</label>
		<form:input path="email"/>
		<form:errors path="email"/>
	</p>
	<p>
		<label for="password">
			<spring:message code="password" />
		</label>
		<!-- member 데이터의 이름과 맞춰야함 -->
		<form:input path="passwd"/>
		<form:errors path="passwd"/>
	</p>
	
	<p>
		<form:select path="loginType" items="${loginTypes}"/>
	</p>
	
	<input type="submit" value="로그인"/>
	
</form:form>
</body>
</html>
