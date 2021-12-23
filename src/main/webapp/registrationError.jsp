<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="resources.pageContent"/>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration error</title>
</head>
<body>
<%@include file="languagePanel.html"%>
<jsp:include page="profileBar.jsp"/>
<jsp:include page="navigationPanel.jsp"/>
<h3><fmt:message key="registration.failed"/> </h3>
<a href="registration.jsp"><fmt:message key="global.back"/> </a>
</body>
</html>