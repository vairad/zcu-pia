<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="cz.zcu.pia.revoloot.web.FormConfig" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%--@elvariable id="customer" type="cz.zcu.pia.revoloot.entities.Customer"--%>
<%--@elvariable id="errors" type="java.util.String"--%>

<legend class="col-form-legend col-12">Osobní údaje</legend>

<div class="form-label-group">
    <jsp:include page="../../components/labeledInput.jsp">
        <jsp:param name="TYPE" value="text"/>
        <jsp:param name="FIELD" value="<%=FormConfig.NAME%>"/>
        <jsp:param name="VIEW_NAME" value="Jméno"/>
        <jsp:param name="ERROR_MESSAGE" value="Zadejte jméno."/>
        <jsp:param name="VALUE" value="${customer.name}"/>
    </jsp:include>
</div>

<div class="form-label-group">
    <jsp:include page="../../components/labeledInput.jsp">
        <jsp:param name="TYPE" value="text"/>
        <jsp:param name="FIELD" value="<%=FormConfig.SURNAME%>"/>
        <jsp:param name="VIEW_NAME" value="Příjmení"/>
        <jsp:param name="ERROR_MESSAGE" value="Zadejte příjmení."/>
        <jsp:param name="VALUE" value="${customer.surname}"/>
    </jsp:include>
</div>

<div class="form-label-group">
    <jsp:include page="../../components/labeledInput.jsp">
        <jsp:param name="TYPE" value="date"/>
        <jsp:param name="FIELD" value="<%=FormConfig.BIRTH_DATE%>"/>
        <jsp:param name="VIEW_NAME" value="Datum narozeí"/>
        <jsp:param name="ERROR_MESSAGE" value="Zadejte datum narození ve formátu: dd-MM-YYYY"/>
    </jsp:include>
</div>

<div class="form-label-group">
    <jsp:include page="../../components/labeledInput.jsp">
        <jsp:param name="TYPE" value="text"/>
        <jsp:param name="FIELD" value="<%=FormConfig.PERSON_ID%>"/>
        <jsp:param name="VIEW_NAME" value="Rodné číslo"/>
        <jsp:param name="ERROR_MESSAGE" value="Zadejte rodné číslo"/>
        <jsp:param name="VALUE" value="${customer.personID}"/>
    </jsp:include>
</div>

<div class="form-label-group">
    <jsp:include page="../../components/labeledInput.jsp">
        <jsp:param name="TYPE" value="text"/>
        <jsp:param name="FIELD" value="<%=FormConfig.CARD_ID%>"/>
        <jsp:param name="VIEW_NAME" value="Číslo OP"/>
        <jsp:param name="ERROR_MESSAGE" value="Zadejte číslo dokladu totožnosti."/>
        <jsp:param name="VALUE" value="${customer.cardID}"/>
    </jsp:include>
</div>

<c:set value="<%=FormConfig.GENDER%>" var="errorFlag"/>
<div class="row <c:if test="${fn:contains(errors, errorFlag)}">alert-danger</c:if>">
    <div class="form-check col col-6">
        <label class="form-check-label">
            <input type="radio"
                   class="form-check-input"
                   name="<%= FormConfig.GENDER %>"
                   id="male"
                   value="male"
                   required/>
            Muž
        </label>
    </div>
    <div class="form-check col col-6">
        <label class="form-check-label">
            <input type="radio" class="form-check-input" name="<%= FormConfig.GENDER %>" id="female"
                   value="female">
            Žena
        </label>
    </div>
    <c:if test="${fn:contains(errors, errorFlag) == true}">
        <small class="d-block d-sm-none">Je třeba vybrat datum pohlaví.</small>
    </c:if>
</div>
