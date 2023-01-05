<%--
  Created by IntelliJ IDEA.
  User: soo
  Date: 2022/12/29
  Time: 4:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
request 객체: <%=request.getAttribute("result") %>
<br/>
session 객체: <%=session.getAttribute("result") %>
<br/>
application 객체: <%=request.getAttribute("result") %>
<br/>
</body>
</html>
