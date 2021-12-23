<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="resources.pageContent"/>
<c:choose>
    <c:when test="${sessionScope.role.role eq 'admin'}">
        <table align=center width="40%" border="1">
            <tr>
                <td align="center">
                    <a href="index.jsp"><fmt:message key="navigation.toMain"/> </a>
                </td>
                <td align="left">
                    <a href="stationsEdit.jsp"><fmt:message key="navigation.stations"/> </a>
                </td>
                <td align="center">
                    <a href="routesEdit.jsp"><fmt:message key="navigation.routes"/> </a>
                </td>
            </tr>
        </table>
        <br>
        <br>
    </c:when>
    <c:when test="${sessionScope.role.role eq 'user'}">
        <table align=center>
            <tr>
                <td align="center">
                    <a href="index.jsp"><fmt:message key="navigation.toMain"/> </a>
                </td>
            </tr>
        </table>
    </c:when>
</c:choose>




