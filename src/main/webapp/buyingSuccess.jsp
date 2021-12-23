<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="my" uri="resources/custom.tld" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="resources.pageContent"/>
<html>
<head>
    <title>Buying success</title>
    <link rel="stylesheet" href="test.css">
</head>
<body>
<%@include file="languagePanel.html"%>
<jsp:include page="profileBar.jsp"/>
<jsp:include page="navigationPanel.jsp"/>
<div align="center">
    <h2>
        <fmt:message key="ticket.successMessage"/>
    </h2>
    <br>
    <br>
    <fmt:message key="ticket.yourTicket"/>
    <table align="center" border="1" width="40%">
        <tr>
            <td>
                <label><fmt:message key="trains.id"/>:</label>${sessionScope.ticket.train_ID}
                <br>
                <label><fmt:message key="carriage.carriageNum"/>:</label>${sessionScope.ticket.carriageNumber}
                <br>
                <label><fmt:message key="ticket.depStation"/>:</label>
                <my:stationName ID="${sessionScope.ticket.deptStation_ID}" showID="false"/>
                <br>
                <label><fmt:message key="route.deptTime"/>:</label>
                <jsp:useBean id="deptTime" class="java.util.Date"/>
                <jsp:setProperty name="deptTime" property="time" value="${sessionScope.ticket.deptTime}"/>
                <fmt:formatDate pattern="dd-MM-yyyy HH:mm" value="${deptTime}"/>
                <br>
                <label><fmt:message key="ticket.destStation"/>:</label>
                <my:stationName ID="${sessionScope.ticket.destStation_ID}" showID="false"/>
                <br>
                <label><fmt:message key="route.destTime"/>:</label>
                <jsp:useBean id="destTime" class="java.util.Date"/>
                <jsp:setProperty name="destTime" property="time" value="${sessionScope.ticket.destTime}"/>
                <fmt:formatDate pattern="dd-MM-yyyy HH:mm" value="${destTime}"/>
                <br>
                <label><fmt:message key="search.price"/>:</label> ${sessionScope.ticket.price}

            </td>
        </tr>
    </table>
</div>
</body>
</html>
