<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:if test="${not empty requestScope.success}">
    <div class="alert alert-success">${requestScope.success}</div>
</c:if>
<c:if test="${not empty requestScope.error}">
    <div class="alert alert-error">${requestScope.error}</div>
</c:if>

