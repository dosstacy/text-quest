<html>
<body>
<h2>Hello user!</h2>
<form action="${pageContext.request.contextPath}/start" method="post">
    <label for="username">Please enter your name: </label>
    <input type="text" id="username" name="username"><br>
    <input type="submit" value="Submit">
</form>

<%
    String username = (String) session.getAttribute("username");

    if (username != null) {
%>
<h2>Hello, <%= username %>!</h2>
<%
    }
%>
</body>
</html>
