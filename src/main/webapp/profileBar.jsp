<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="resources.pageContent"/>
<table align="right" border="1">
    <tr>
        <td>
            <c:choose>
                <c:when test="${sessionScope.user.logged}">
                    <div align="center">
                            ${sessionScope.user.firstName} ${sessionScope.user.lastName}
                                <br>
                        <form action="logout" method="post">
                            <input type="submit" value=<fmt:message key="profile.logout"/>></form>
                    </div>
                </c:when>
                <c:otherwise>
                    <form action="login" method="post">
                        <table>
                            <tr>
                                <td align="left"><fmt:message key="login.login"/>:</td>
                                <td align="right"><input type="text" name="login"></td>
                            </tr>
                            <tr>
                                <td align="left"><fmt:message key="login.password"/>:</td>
                                <td align="right"><input type="password" name="password"></td>
                            </tr>
                        </table>
                        <div align="center">
                            <input type="submit" value="<fmt:message key="login.submit"/>">
                        </div>
                    </form>
                    <div align="center">
                        <a href="registration.jsp"><fmt:message key="login.registration"/></a>
                    </div>
                </c:otherwise>
            </c:choose>

        </td>
    </tr>

</table>