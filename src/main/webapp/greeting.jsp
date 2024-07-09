<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/greeting.css">
</head>
<body>
<h2>Hello ${username}!</h2>
<h3>${question}</h3>
<form action="${pageContext.request.contextPath}/quest" method="post">
    <input type="submit" name="answer" value="Yes">
    <input type="submit" name="answer" value="No">
</form>
</body>
</html>
