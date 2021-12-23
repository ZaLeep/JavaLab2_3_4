<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="resources.pageContent"/>
<html>
<head>
    <title>SearchResults</title>
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
        <c:if test="${sessionScope.user.logged}">
            <td></td>
        </c:if>
    </tr>
    <c:forEach var="result" items="${sessionScope.searchResults}">
        <tr>
            <td><c:out value="${result.train.train_ID}"/></td>
            <td>
                    ${result.departStation.name}<br>
                <jsp:useBean id="departTime" class="java.util.Date"/>
                <jsp:setProperty name="departTime" property="time" value="${result.departTime}"/>
                <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${departTime}"/>
            </td>
            <td>
                <jsp:useBean id="wayTime" class="core.supportClasses.TimeFormatter"/>
                <jsp:setProperty name="wayTime" property="total" value="${result.wayTime}"/>
                    ${wayTime.time}
            </td>
            <td>
                    ${result.destStation.name}<br>
                <jsp:useBean id="destTime" class="java.util.Date"/>
                <jsp:setProperty name="destTime" property="time" value="${result.destTime}"/>
                <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${destTime}"/>
            </td>
            <td>
                <table align="center" width="90%">
                    <tr>
                        <td><fmt:message key="search.carriageNumber"/></td>
                        <td><fmt:message key="search.carriageType"/></td>
                        <td><fmt:message key="search.freePlaces"/></td>
                    </tr>
                    <c:forEach var="carriage" items="${result.carriages}">
                        <tr>
                            <td>${carriage.carriageNumber}</td>
                            <td>${carriage.type}</td>
                            <td>${carriage.totalSeats-carriage.reservedSeats}</td>
                        </tr>
                    </c:forEach>
                </table>
            </td>
            <td>${result.price}</td>
            <td>
                <form action="routeInfo" method="get">
                    <input type="text" name="route_ID" value="${result.train.route_ID}" hidden>
                    <input type="submit" value="<fmt:message key="search.info"/> ">
                </form>
            </td>
            <c:if test="${sessionScope.user.logged}">
                <td>
                    <form action="selectTrain" method="get">
                        <input type="text" name="result_ID" value="${result.result_ID}" hidden>
                        <input type="submit" value="<fmt:message key="ticket.buy"/> "/>
                    </form>
                </td>
            </c:if>
        </tr>
    </c:forEach>
</table>
</body>
</html>
