<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="resources.pageContent"/>
<html>
<head>
    <title>Registration</title>
<link rel="stylesheet" href="test.css">
</head>
<body>
<%@include file="languagePanel.html"%>
<jsp:include page="profileBar.jsp"/>
<jsp:include page="navigationPanel.jsp"/>
<h1 align="center"><fmt:message key="registration.title"/></h1>

<table align="center">
    <tr>
        <td>
        <form action="registration" method="post">
            <label><fmt:message key="registration.login"/>:</label>
            <input type="text" name="login"><br>
            <label><fmt:message key="registration.firstname"/>: </label>
            <input type="text" name="firstName"><br>
            <label><fmt:message key="registration.lastname"/>: </label>
            <input type="text" name="lastName"><br>
            <label><fmt:message key="registration.password"/>: </label>
            <input type="password" name="password"><br>
            <label><fmt:message key="registration.confirmPassword"/>: </label>
            <input type="password" name="confirmPassword"><br><br>
            <div align="center"><input type="submit" value=<fmt:message key="registration.submit"/>></div>
        </form>
        </td>
    </tr>
</table>
</body>
</html>
