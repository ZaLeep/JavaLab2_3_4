<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="my" uri="resources/custom.tld" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="resources.pageContent"/>
<html>
<head>
    <title>Routes edit</title>
    <link rel="stylesheet" href="test.css">
</head>
<body>
<%@include file="languagePanel.html"%>
<jsp:include page="profileBar.jsp"/>
<jsp:include page="navigationPanel.jsp"/>
<table align="center" width="90%" border="1">
    <tr>
        <td>
            <fmt:message key="route.createRoute"/> :<br>
            <form action="createRoute" method="post">
                <div>
                    <label><fmt:message key="route.depStatID"/>:</label><input type="text" name="deptStationID"><br>
                    <label><fmt:message key="route.deptTime"/>:</label><input type="date" name="deptDate"> <input
                        type="time"
                        name="deptTime"><br>
                    <label><fmt:message key="route.destStatID"/>:</label><input type="text" name="destStationID"><br>
                    <label><fmt:message key="route.destTime"/>:</label><input type="date" name="destDate"><input
                        type="time"
                        name="destTime"><br>
                    <label></label><input type="submit" value="<fmt:message key="route.create"/>">
                </div>
            </form>
        </td>
        <td>
            <fmt:message key="route.editRoute"/> :<br>
            <form action="editRoute" method="post">
                <div>
                    <label><fmt:message key="route.routeID"/> :</label><input type="text" name="routeID"><br>
                    <label><fmt:message key="route.depStatID"/>:</label><input type="text" name="deptStationID"><br>
                    <label><fmt:message key="route.deptTime"/>:</label><input type="date" name="deptDate"> <input
                        type="time"
                        name="deptTime"><br>
                    <label><fmt:message key="route.destStatID"/>:</label><input type="text" name="destStationID"><br>
                    <label><fmt:message key="route.destTime"/>:</label><input type="date" name="destDate"><input
                        type="time"
                        name="destTime"><br>
                    <label></label><input type="submit" value="<fmt:message key="admin.edit"/> ">
                </div>
            </form>
        </td>
        <td>
            <fmt:message key="route.deleteRoute"/> :
            <form action="deleteRoute" method="post">
                <div>
                    <label><fmt:message key="route.routeID"/>:</label><input type="text" name="routeID">
                    <label></label><input type="submit" value="<fmt:message key="admin.delete"/>"></div>
            </form>
        </td>
    </tr>
</table>
<br>
<br>
<table align="center" width="90%">
    <tr>
        <td>
            <div align="center"><fmt:message key="route.routes"/></div>
            <br>
            <table border="1" align="center" width="90%">
                <tr>
                    <td><fmt:message key="route.routeID"/></td>
                    <td><fmt:message key="route.depStatID"/></td>
                    <td><fmt:message key="route.deptTime"/></td>
                    <td><fmt:message key="route.destStatID"/></td>
                    <td><fmt:message key="route.destTime"/></td>
                </tr>
                <c:forEach var="route" items="${sessionScope.routes}">
                    <tr>
                        <td>${route.route_ID}</td>
                        <td><my:stationName ID="${route.departStation_ID}"/></td>
                        <td>
                            <jsp:useBean id="deptTime" class="java.util.Date"/>
                            <jsp:setProperty name="deptTime" property="time" value="${route.departTime}"/>
                            <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${deptTime}"/>
                        </td>
                        <td><my:stationName ID="${route.destStation_ID}"/></td>
                        <td>
                            <jsp:useBean id="destTime" class="java.util.Date"/>
                            <jsp:setProperty name="destTime" property="time" value="${route.destTime}"/>
                            <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${destTime}"/>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </td>
    </tr>
</table>

</body>
</html>
