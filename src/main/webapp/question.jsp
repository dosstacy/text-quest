<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String backgroundImage = "";
    String question = (String) request.getSession().getAttribute("question");
    if (question != null) {
        if (question.contains("unknown")) {
            backgroundImage = "world.png";
        } else if (question.contains("dragon")) {
            backgroundImage = "warrior.png";
        } else if (question.contains("village")) {
            backgroundImage = "village.png";
        }else if (question.contains("old man")) {
            backgroundImage = "wizard.png";
        } else if (question.contains("beasts") || question.contains("wild animals")) {
            backgroundImage = "animals.png";
        } else if (question.contains("temple")) {
            backgroundImage = "temple.png";
        } else if (question.contains("portal")) {
            backgroundImage = "portal.png";
        } else if (question.contains("mage")) {
            backgroundImage = "artifact.png";
        } else {
            backgroundImage = "page.png";
        }
    }
%>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/pages.css">
    <style>
        body {
            background-image: url('static/img/<%= backgroundImage %>');
            background-size: cover;
            background-position: center;
            background-repeat: no-repeat;
        }
    </style>
</head>
<body>
<h3>${question}</h3>
<form action="${pageContext.request.contextPath}/next" method="post">
    <input type="hidden" name="username" value="${username}">
    <input type="hidden" name="currentNode" value="${question}">
    <input type="submit" name="answer" value="Yes">
    <input type="submit" name="answer" value="No">
</form>
</body>
</html>
