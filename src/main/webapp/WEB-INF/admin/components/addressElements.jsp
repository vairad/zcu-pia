<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="cz.zcu.pia.revoloot.web.FormConfig" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<%--@elvariable id="customer" type="cz.zcu.pia.revoloot.entities.Customer"--%>
<%--@elvariable id="errors" type="java.util.String"--%>

<legend>Trvalé bydliště</legend>
<div class="form-label-group">
    <jsp:include page="../../components/labeledInput.jsp">
        <jsp:param name="TYPE" value="text"/>
        <jsp:param name="FIELD" value="<%=FormConfig.CITY%>"/>
        <jsp:param name="VIEW_NAME" value="Město"/>
        <jsp:param name="ERROR_MESSAGE" value="Je třeba zadat město."/>
        <jsp:param name="VALUE" value="${customer.contactInfo.address.city}"/>
    </jsp:include>
</div>

<div class="row">
    <div class="form-label-group col col-9">
        <jsp:include page="../../components/labeledInput.jsp">
            <jsp:param name="TYPE" value="text"/>
            <jsp:param name="FIELD" value="<%=FormConfig.STREET%>"/>
            <jsp:param name="VIEW_NAME" value="Ulice"/>
            <jsp:param name="ERROR_MESSAGE" value="Je třeba zadat ulici."/>
            <jsp:param name="VALUE" value="${customer.contactInfo.address.street}"/>
        </jsp:include>
    </div>

    <div class="form-label-group col col-3">
        <jsp:include page="../../components/labeledInput.jsp">
            <jsp:param name="TYPE" value="text"/>
            <jsp:param name="FIELD" value="<%=FormConfig.HOUSE_NUMBER%>"/>
            <jsp:param name="VIEW_NAME" value="ČP"/>
            <jsp:param name="ERROR_MESSAGE" value="Je třeba zadat číslo popisné."/>
            <jsp:param name="VALUE" value="${customer.contactInfo.address.houseNo}"/>
        </jsp:include>
    </div>

</div>
<div class="form-label-group">
    <jsp:include page="../../components/labeledInput.jsp">
        <jsp:param name="TYPE" value="text"/>
        <jsp:param name="FIELD" value="<%=FormConfig.POSTAL_CODE%>"/>
        <jsp:param name="VIEW_NAME" value="PSČ"/>
        <jsp:param name="ERROR_MESSAGE" value="Je třeba zadat poštovní směrovací číslo."/>
        <jsp:param name="VALUE" value="${customer.contactInfo.address.postalCode}"/>
    </jsp:include>
</div>

<div class="form-group">
    <label for="<%= FormConfig.STATE%>">Stát</label>
    <select class="form-control" id="<%= FormConfig.STATE%>"
            name="<%= FormConfig.STATE%>">
        <option value="CZ"
                <c:if test="${customer.contactInfo.address.state.CZ == true}">selected</c:if>>Česká Republika
        </option>
        <option value="SR"
                <c:if test="${customer.contactInfo.address.state.SVK == true}">selected</c:if>
        >Slovenská Republika
        </option>
        <option disabled title="Připravujeme">Velká Británie</option>
    </select>
    <c:set value="<%=FormConfig.STATE%>" var="ERROR"/>
    <c:if test="${fn:contains(errors, ERROR) == true}">
        <p class="alert alert-danger">Je potřeba vybrat stát.</p>
    </c:if>
</div>
