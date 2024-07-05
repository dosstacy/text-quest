<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<h2>Hello ${username}!</h2>
<p>${question}</p>
<form action="${pageContext.request.contextPath}/next" method="post">
    <input type="hidden" name="username" value="${username}">
    <input type="hidden" name="currentNode" value="${question}">
    <input type="submit" name="answer" value="Yes">
    <input type="submit" name="answer" value="No">
</form>
</body>
</html>
