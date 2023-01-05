<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="hello-servlet">Hello Servlet</a>
<br />
<a href="first-servlet">First Servlet</a>
<br />
<a href="input.jsp">page controller</a>
<br />
<%
    int sum = 0;
    for (int i = 0; i < 10; i++) {
        sum += i;
    }
%>

<h1>1부터 9까지의 합 = <%= sum%></h1>
</body>
</html>