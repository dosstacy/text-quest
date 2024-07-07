<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/index.css">
    <title>Quest</title>
</head>
<body>
<h2>Hello user!</h2>
<form action="${pageContext.request.contextPath}/start" method="post">
    <input type="text" id="username" name="username" placeholder="Enter username" maxlength="15" minlength="4">
    <input type="submit" value="Submit">
</form>
</body>
</html>



