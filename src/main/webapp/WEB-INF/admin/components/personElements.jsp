<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="cz.zcu.pia.revoloot.web.FormConfig" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--@elvariable id="customer" type="cz.zcu.pia.revoloot.entities.Customer"--%>
<%--@elvariable id="errors" type="java.util.Set"--%>

<legend class="col-form-legend col-12">Osobní údaje</legend>

<div class="form-label-group">
    <input type="text" id="<%= FormConfig.NAME %>"
           name="<%= FormConfig.NAME %>"
           class="form-control <c:if test="${fn:contains(errors, FormConfig.NAME) == true}">alert-danger</c:if>"
           placeholder="Jméno"
           required autofocus
           value="${customer.name}">
    <label for="<%= FormConfig.NAME %>"
            <c:if test="${fn:contains(errors, FormConfig.NAME) == true}">
                title="Je potřeba vyplnit jméno."
                data-toggle="tooltip"
                data-placement="top"
            </c:if>
    >Jméno</label>
    <c:if test="${fn:contains(errors, FormConfig.NAME) == true}">
        <small class="d-block d-sm-none">Je třeba vyplnit jméno.</small>
    </c:if>
</div>

<div class="form-label-group">
    <input type="text" id="<%= FormConfig.SURNAME %>"
           class="form-control <c:if test="${fn:contains(errors, FormConfig.SURNAME) == true}">alert-danger</c:if>"
           name="<%= FormConfig.SURNAME %>"
           placeholder="Příjmení"
           required
           value="${customer.surname}">
    <label for="<%= FormConfig.SURNAME %>"
            <c:if test="${fn:contains(errors, FormConfig.SURNAME) == true}">
                title="Je potřeba vyplnit příjmení."
                data-toggle="tooltip"
                data-placement="top"
            </c:if>
    >Příjmení</label>
    <c:if test="${fn:contains(errors, FormConfig.SURNAME) == true}">
        <small class="d-block d-sm-none">Je třeba vyplnit příjmení.</small>
    </c:if>
</div>

<div class="form-label-group">
    <input type="date" id="<%= FormConfig.BIRTH_DATE %>"
           name="<%= FormConfig.BIRTH_DATE %>"
           class="form-control <c:if test="${fn:contains(errors, FormConfig.BIRTH_DATE) == true}">alert-danger</c:if>"
           required
           value="${customer.birthDate}">
    <label for="<%= FormConfig.BIRTH_DATE %>"
            <c:if test="${fn:contains(errors, FormConfig.BIRTH_DATE) == true}">
                title="Je potřeba vypnit datum narození.(dd-MM-YYYY)"
                data-toggle="tooltip"
                data-placement="top"
            </c:if>
    >Datum narození</label>
    <c:if test="${fn:contains(errors, FormConfig.BIRTH_DATE) == true}">
        <small class="d-block d-sm-none">Je třeba vyplnit datum narození. (dd-MM-YYYY)</small>
    </c:if>
</div>

<div class="form-label-group">
    <input type="text" id="<%= FormConfig.PERSON_ID %>"
           class="form-control <c:if test="${fn:contains(errors, FormConfig.PERSON_ID) == true}">alert-danger</c:if>"
           name="<%= FormConfig.PERSON_ID %>"
           placeholder="Rodné číslo"
           required
           value="${customer.personID}">
    <label for="<%= FormConfig.PERSON_ID %>"
            <c:if test="${fn:contains(errors, FormConfig.PERSON_ID) == true}">
                title="Je potřeba vyplnit rodné číslo."
                data-toggle="tooltip"
                data-placement="top"
            </c:if>
    >Rodné číslo</label>
    <c:if test="${fn:contains(errors, FormConfig.PERSON_ID) == true}">
        <small class="d-block d-sm-none">Je třeba vyplnit rodné číslo.</small>
    </c:if>
</div>

<div class="form-label-group">
    <input type="text" id="<%= FormConfig.CARD_ID %>"
           name="<%= FormConfig.CARD_ID %>"
           class="form-control <c:if test="${fn:contains(errors, FormConfig.CARD_ID) == true}">alert-danger</c:if>"
           placeholder="číslo OP"
           required
           value="${customer.cardID}">
    <label for="<%= FormConfig.CARD_ID %>"
            <c:if test="${fn:contains(errors, FormConfig.CARD_ID) == true}">
                title="Je potřeba vyplnit číslo dokladu totožnosti."
                data-toggle="tooltip"
                data-placement="top"
            </c:if>
    >číslo OP</label>
    <c:if test="${fn:contains(errors, FormConfig.CARD_ID) == true}">
        <small class="d-block d-sm-none">Je třeba vyplnit číslo dokladu totožnosti.</small>
    </c:if>
</div>

<div class="row <c:if test="${fn:contains(errors, FormConfig.GENDER) == true}">alert-danger</c:if>">
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
    <c:if test="${fn:contains(errors, FormConfig.CITY) == true}">
        <small class="d-block d-sm-none">Je třeba vybrat datum pohlaví.</small>
    </c:if>
</div>
