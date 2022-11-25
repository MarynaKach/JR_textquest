<%--
  Created by IntelliJ IDEA.
  User: Mvkachalova
  Date: 18.11.2022
  Time: 09:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>SPACE QUEST</title>
    <style>
        h1 {
            background: darkblue; /* Цвет фона под заголовком */
            color: goldenrod; /* Цвет текста */
            padding: 2px; /* Поля вокруг текста */
            text-align:  center;
        }
        div {
            text-align:  center;
        }

    </style>
<%--


out.println("<h1>" + name + " " + message + "</h1>");

int nextIdYes = getNextId(messageId, "yes");
int nextIdNo = getNextId(messageId, "no");
if (messageId == 4 || messageId == 0) {
out.println("<div><button onclick=\"window.location='/index.jsp'\">YES</button>");
    out.println("<button onclick=\"window.location='/quest?message=" + nextIdNo + "'\">NO</button>");
    } else {
    out.println("<div><button onclick=\"window.location='/quest?message=" + nextIdYes + "'\">YES</button>");
        out.println("<button onclick=\"window.location='/quest?message=" + nextIdNo + "'\">NO</button>");
        }
        out.println("</div>");
    out.println("<h3>" + "Name: " + name + "</h1>");
        out.println("<h4>" + "Number Of Games: " + numberOfGames + "</h2></body></html>");--%>
</head>
<body>
<jsp:include page="parts/statistic.jsp" />
<h1>Text: ${message}</h1>

        <%--  <c:choose>
            <c:when messageId="${messageId == 4 || messageId == 0}">--%>
<div ${hidden}>
    <button onclick="window.location='${linkYes}'">YES</button>
    <button onclick="window.location='${linkNo}'">NO</button>
</div>
           <%-- </c:when>
            <c:otherwise>
            <div>
                <button onclick="window.location='/quest?messageId=${nextIdYes}'">YES</button>"
                <button onclick="window.location='/quest?messageId=${nextIdNo}'">NO</button>"
            </div>
            </c:otherwise>
        </c:choose>--%>
       <%-- <form action="${pageContext.request.contextPath}/quest" method="get">
            <input type="hidden" name="nextIdYes" value="${nextIdYes}">
            <button type="submit">YES</button>
        </form>
            <form action="${pageContext.request.contextPath}/quest" method="get">
                <input type="hidden" name="nextIdNo" value="${nextIdNo}">
                <button type="submit">NO</button>
            </form>
            <div>
            <button onclick="window.location='/index.jsp'">YES</button>"
            <button onclick="window.location='/quest?messageId=${nextIdNo}'">NO</button>
            </div>
        </c:choose>--%>
<div>
    <img src=${pageContext.request.contextPath}"/images/${pictureId}.jpeg"  alt="${pictureId} picture"/>
</div>
</body>
</html>
