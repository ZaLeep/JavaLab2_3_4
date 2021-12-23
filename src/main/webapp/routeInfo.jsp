<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="resources.pageContent"/>
<html>
<head>
    <title>Route info</title>
</head>
<body>
<%@include file="languagePanel.html"%>
<jsp:include page="profileBar.jsp"/>
<jsp:include page="navigationPanel.jsp"/>
<table align="center" width="50%" border="1">
    <tr>
        <td><fmt:message key="routeInfo.station"/></td>
        <td><fmt:message key="routeInfo.arriveTime"/></td>
        <td><fmt:message key="routeInfo.departTime"/></td>
        <td><fmt:message key="routeInfo.waitingTime"/></td>
    </tr>
    <tr>
        <td>${requestScope.routeInfo.departStation.name}</td>
        <td></td>
        <td>
            <jsp:useBean id="departTime" class="java.util.Date"/>
            <jsp:setProperty name="departTime" property="time" value="${requestScope.routeInfo.route.departTime}"/>
            <fmt:formatDate pattern="dd-MM-yyyy HH:mm" value="${departTime}"/>


        </td>
        <td></td>
    </tr>
    <tr>
        <td>${requestScope.routeInfo.destStation.name}</td>
        <td>
            <jsp:useBean id="destTime" class="java.util.Date"/>
            <jsp:setProperty name="destTime" property="time" value="${requestScope.routeInfo.route.destTime}"/>
            <fmt:formatDate pattern="dd-MM-yyyy HH:mm" value="${destTime}"/>
        </td>
        <td></td>
        <td></td>
    </tr>
</table>
<br>
<br>
<div align="center">
    <a href="searchResults.jsp"><fmt:message key="global.back"/> </a>
</div>
</body>
</html>
