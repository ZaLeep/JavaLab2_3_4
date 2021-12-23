<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="resources.pageContent"/>
<html>
<head>
    <title>Buy Ticket</title>
</head>
<body>
<%@include file="languagePanel.html"%>
<jsp:include page="profileBar.jsp"/>
<jsp:include page="navigationPanel.jsp"/>

<table border="1" align="center" width="80%">
    <tr>
        <td><fmt:message key="search.trainNumber"/></td>
        <td><fmt:message key="search.deptStationTime"/></td>
        <td><fmt:message key="search.travelTime"/></td>
        <td><fmt:message key="search.destStationTime"/></td>
        <td><fmt:message key="search.carriages"/></td>
        <td><fmt:message key="search.price"/></td>
        <td><fmt:message key="search.routeInfo"/></td>

    </tr>
    <tr>
        <td><c:out value="${sessionScope.selectedResult.train.train_ID}"/></td>
        <td>
            ${sessionScope.selectedResult.departStation.name}<br>
            <jsp:useBean id="departTime" class="java.util.Date"/>
            <jsp:setProperty name="departTime" property="time" value="${sessionScope.selectedResult.departTime}"/>
            <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${departTime}"/>
        </td>
        <td>
            <jsp:useBean id="wayTime" class="core.supportClasses.TimeFormatter"/>
            <jsp:setProperty name="wayTime" property="total" value="${sessionScope.selectedResult.wayTime}"/>
            ${wayTime.time}
        </td>
        <td>
            ${sessionScope.selectedResult.destStation.name}<br>
            <jsp:useBean id="destTime" class="java.util.Date"/>
            <jsp:setProperty name="destTime" property="time" value="${sessionScope.selectedResult.destTime}"/>
            <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${destTime}"/>
        </td>
        <td>
            <table border="1">
                <tr>
                    <td><fmt:message key="search.carriageNumber"/></td>
                    <td><fmt:message key="search.carriageType"/></td>
                    <td><fmt:message key="search.freePlaces"/></td>
                </tr>
                <c:forEach var="carriage" items="${sessionScope.selectedResult.carriages}">
                    <tr>
                        <td>${carriage.carriageNumber}</td>
                        <td>${carriage.type}</td>
                        <td>${carriage.totalSeats-carriage.reservedSeats}</td>
                    </tr>
                </c:forEach>
            </table>
        </td>
        <td>${sessionScope.selectedResult.price}</td>
        <td>
            <form action="routeInfo" method="get">
                <input type="text" name="route_ID" value="${sessionScope.selectedResult.train.route_ID}" hidden>
                <input type="submit" value="<fmt:message key="search.info"/> ">
            </form>
        </td>
    </tr>
</table>
<br>
<br>
<div align="center">
    <form action="buyTicket" method="post">
        <fmt:message key="ticket.carriageNumber"/>
        <input type="text" name="carriageNumber"><br>
        <input type="submit" value="<fmt:message key="ticket.buy"/>"/>
    </form>
</div>
</body>
</html>
