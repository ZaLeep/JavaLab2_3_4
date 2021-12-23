<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="resources.pageContent"/>
<html>
<head>
    <meta charset="UTF-8">
    <title>LoginError</title>
</head>
<body>
<%@include file="languagePanel.html"%>
<jsp:include page="profileBar.jsp"/>
<jsp:include page="navigationPanel.jsp"/>
<div align="center">
    <h3><fmt:message key="authorization.fail"/> </h3>
    <a href=${header.referer}><fmt:message key="global.back"/> </a>
</div>
</body>
</html>