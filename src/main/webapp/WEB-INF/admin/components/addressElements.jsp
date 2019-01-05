<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="cz.zcu.pia.revoloot.web.FormConfig" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--@elvariable id="customer" type="cz.zcu.pia.revoloot.entities.Customer"--%>
<%--@elvariable id="errors" type="java.util.Set"--%>

<legend>Trvalé bydliště</legend>
<div class="form-label-group">
    <input type="text" id="<%= FormConfig.CITY%>"
           name="<%= FormConfig.CITY%>"
           class="form-control  <c:if test="${fn:contains(errors, FormConfig.CITY) == true}">alert-danger</c:if>"
           placeholder="Město"
           required
           value="${customer.contactInfo.address.city}">
    <label for="<%= FormConfig.CITY%>"
            <c:if test="${fn:contains(errors, FormConfig.CITY) == true}">
                title="Je potřeba vyplnit město."
                data-toggle="tooltip"
                data-placement="top"
            </c:if>
    >Město</label>
    <c:if test="${fn:contains(errors, FormConfig.CITY) == true}">
        <small class="d-block d-sm-none">Je třeba vyplnit město.</small>
    </c:if>
</div>

<div class="row">
    <div class="form-label-group col col-9">
        <input type="text" id="<%= FormConfig.STREET%>"
               name="<%= FormConfig.STREET%>"
               class="form-control <c:if test="${fn:contains(errors, FormConfig.STREET) == true}">alert-danger</c:if>"
               placeholder="Ulice"
               required
               value="${customer.contactInfo.address.street}">
        <label for="<%= FormConfig.STREET%>"
                <c:if test="${fn:contains(errors, FormConfig.STREET) == true}">
                    title="Je potřeba vyplnit ulici."
                    data-toggle="tooltip"
                    data-placement="top"
                </c:if>
        >Ulice</label>
        <c:if test="${fn:contains(errors, FormConfig.STREET) == true}">
            <small class="d-block d-sm-none">Je třeba vyplnit ulici.</small>
        </c:if>
    </div>

    <div class="form-label-group col col-3">
        <input type="text" id="<%= FormConfig.HOUSE_NUMBER%>"
               name="<%= FormConfig.HOUSE_NUMBER%>"
               class="form-control <c:if test="${fn:contains(errors, FormConfig.HOUSE_NUMBER) == true}">alert-danger</c:if>"
               placeholder="ČP"
               required
               value="${customer.contactInfo.address.houseNo}">
        <label for="<%= FormConfig.HOUSE_NUMBER%>"
                <c:if test="${fn:contains(errors, FormConfig.HOUSE_NUMBER) == true}">
                    title="Je potřeba vyplnit číslo popisné."
                    data-toggle="tooltip"
                    data-placement="top"
                </c:if>
        >ČP</label>
        <c:if test="${fn:contains(errors, FormConfig.HOUSE_NUMBER) == true}">
            <small class="d-block d-sm-none">Je třeba vyplnit číslo popisné.</small>
        </c:if>
    </div>

</div>
<div class="form-label-group">
    <input type="text" id="<%= FormConfig.POSTAL_CODE%>"
           name="<%= FormConfig.POSTAL_CODE%>"
           class="form-control <c:if test="${fn:contains(errors, FormConfig.POSTAL_CODE) == true}">alert-danger</c:if>"
           placeholder="PSČ"
           required
           value="${customer.contactInfo.address.postalCode}">
    <label for="<%= FormConfig.POSTAL_CODE%>"
            <c:if test="${fn:contains(errors, FormConfig.POSTAL_CODE) == true}">
                title="Je potřeba vyplnit poštovní směrovací číslo."
                data-toggle="tooltip"
                data-placement="top"
            </c:if>
    >PSČ</label>
    <c:if test="${fn:contains(errors, FormConfig.POSTAL_CODE) == true}">
        <small class="d-block d-sm-none">Je třeba vyplnit poštovní směrovací číslo.</small>
    </c:if>
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
    <c:if test="${fn:contains(errors, FormConfig.STATE) == true}">
        <p class="alert alert-danger">Je potřeba vybrat stát.</p>
    </c:if>
</div>
