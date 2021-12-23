<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isErrorPage="false" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="resources.pageContent"/>
<html>
<head>
    <title>Error Page</title>
</head>
<body>
<%@include file="languagePanel.html"%>
<jsp:include page="profileBar.jsp"/>
<jsp:include page="navigationPanel.jsp"/>
<div align="center">
    <fmt:message key="global.errorMessage"/><br>
    <a href="${header.referer}"><fmt:message key="global.back"/> </a>
</div>
</body>
</html>
