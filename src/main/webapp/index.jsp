<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="resources.pageContent"/>
<html lang="${applicationScope.language}">
<head>
    <title>Railway</title>
</head>
<body>
<%@include file="languagePanel.html" %>
<h1 align=center><fmt:message key="main.title"/></h1>
<jsp:include page="profileBar.jsp"/>
<jsp:include page="navigationPanel.jsp"/>
<div align="center"><fmt:message key="search.search"/></div>
<table align="center" border="1">
    <tr>
        <td>
            <form action="search" method="get">
                <table width="30%" align="center">
                    <tr>
                        <td>
                            <fmt:message key="search.from"/>: <input type="text" id="from" name="departStation">
                        </td>
                        <td>
                            <fmt:message key="search.to"/>: <input type="text" id="to" name="destStation">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <fmt:message key="search.date"/>:<input type="date" id="date" name="date">
                        </td>
                    </tr>
                </table>
                <div align="center">
                    <input type="submit" value=<fmt:message key="search.submit"/>>
                </div>
            </form>
        </td>
    </tr>
</table>
</body>
</html>
