<%@ page contentType="text/html;charset=UTF-8" %>
<%
    String backgroundImage = "";
    String question = (String) request.getSession().getAttribute("question");
    if (question != null) {
        if (question.contains("beast")) {
            backgroundImage = "black.png";
        } else if (question.contains("animals") || question.contains("escape from dragon")) {
            backgroundImage = "village.png";
        } else if (question.contains("forest")) {
            backgroundImage = "forest.png";
        }else if (question.contains("portal")) {
            backgroundImage = "portal.png";
        } else if (question.contains("dragon")) {
            backgroundImage = "warrior.png";
        } else if (question.contains("power")) {
            backgroundImage = "artifact.png";
        } else if (question.contains("artifact")) {
            backgroundImage = "witch.png";
        } else {
            backgroundImage = "page.png";
        }
    }
%>
<html>
<head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <script src="<c:url value="/static/jquery-3.6.0.min.js"/>"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/end.css">
    <style>
        body {
            background-image: url('static/img/<%= backgroundImage %>');
            background-size: cover;
            background-position: center;
            background-repeat: no-repeat;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            margin: 0;
        }
    </style>
    <title>Quest</title>
</head>
<body>
<h2>${question}</h2>
<h3>Thank you for playing, ${username}!</h3>
<button onclick="restart()">Start again</button>
<script>
    function restart() {
        $.ajax({
            url: '/quest/restart',
            type: 'POST',
            contentType: 'application/json;charset=UTF-8',
            async: true,
            success: function () {
                window.location.href = '/quest';
                console.log(sessionStorage)
            }
        });
    }
</script>
</body>
</html>

