<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--@elvariable id="errors" type="java.lang.String"--%>

<input type="${param.TYPE}"
       id="${param.FIELD}"
       name="${param.FIELD}"
       class="form-control <c:if test="${fn:contains(errors, param.FIELD)}">alert-danger</c:if>"
       <c:if test="${!(param.TYPE == 'date')}">placeholder="${param.VIEW_NAME}"</c:if>
       value="${param.VALUE}"
       required>
<label for="${param.FIELD}"
        <c:if test="${fn:contains(errors, param.FIELD)}">
            title="${param.ERROR_MESSAGE}"
            data-toggle="tooltip"
            data-placement="top"
        </c:if>
>${param.VIEW_NAME}</label>
<c:if test="${fn:contains(errors, param.FIELD)}">
    <small class="d-block d-sm-none">${param.ERROR_MESSAGE}</small>
</c:if>
