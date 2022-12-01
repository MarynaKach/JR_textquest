<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>SPACE QUEST</title>
    <style>
        h1 {
            background: darkblue;
            color: goldenrod;
            padding: 2px;
            text-align: center;
        }

        div {
            text-align: center;
        }

    </style>
</head>
<body>
<jsp:include page="parts/statistic.jsp"/>
<h1>Text: ${message}</h1>
<div ${hidden}>
    <button onclick="window.location='${linkYes}'">YES</button>
    <button onclick="window.location='${linkNo}'">NO</button>
</div>
<div>
    <img src=${pageContext.request.contextPath}"/images/${pictureId}.jpeg" alt="${pictureId} picture"/>
</div>
</body>
</html>
