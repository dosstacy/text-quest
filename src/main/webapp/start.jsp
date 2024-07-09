<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/start.css">
    <title>Quest</title>
</head>
<body>
<h2>Hello user!</h2>
<form action="${pageContext.request.contextPath}/start" method="post">
    <input type="text" name="username" placeholder="Enter username" maxlength="15" minlength="4" required>
    <input type="submit" value="Submit">
</form>
</body>
</html>



