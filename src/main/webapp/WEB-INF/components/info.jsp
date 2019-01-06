<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:if test="${not empty requestScope.success}">
    <p class="alert alert-success">${requestScope.success}</p>
</c:if>
<c:if test="${not empty requestScope.error}">
    <p class="alert alert-error">${requestScope.error}</p>
</c:if>

