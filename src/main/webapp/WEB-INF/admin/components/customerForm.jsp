<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="cz.zcu.pia.revoloot.web.FormConfig" %>

<%--@elvariable id="customer" type="cz.zcu.pia.revoloot.entities.Customer"--%>

<form action="<c:url value="<%=FormConfig.ACTION_REGISTER%>"/>" method="post">

    <div class="row">
        <fieldset class="form-group col-12 col-md-6">
            <legend class="col-form-legend col-12">Osobní údaje</legend>

            <div class="form-label-group">
                <input type="text" id="<%= FormConfig.NAME %>"
                       name="<%= FormConfig.NAME %>" class="form-control"
                       placeholder="Jméno" required autofocus
                       value="${customer.name}">
                <label for="<%= FormConfig.NAME %>">Jméno</label>
            </div>

            <div class="form-label-group">
                <input type="text" id="<%= FormConfig.SURNAME %>" class="form-control"
                       name="<%= FormConfig.SURNAME %>" placeholder="Příjmení" required
                       value="${customer.surname}">
                <label for="<%= FormConfig.SURNAME %>">Příjmení</label>
            </div>

            <div class="form-label-group">
                <input type="date" id="<%= FormConfig.BIRTH_DATE %>"
                       name="<%= FormConfig.BIRTH_DATE %>" class="form-control" required
                       value="${customer.birthDate}">
                <label for="<%= FormConfig.BIRTH_DATE %>">Datum narození</label>
            </div>

            <div class="form-label-group">
                <input type="text" id="<%= FormConfig.PERSON_ID %>" class="form-control alert-danger"
                       name="<%= FormConfig.PERSON_ID %>" placeholder="Rodné číslo" required
                       value="${customer.personID}">
                <label for="<%= FormConfig.PERSON_ID %>" title="Rodné číslo neodpovídá datu narození."
                       data-toggle="tooltip" data-placement="top">Rodné číslo</label>
                <small class="d-block d-sm-none">Rodné číslo neodpovídá datu narození.</small>
            </div>

            <div class="form-label-group">
                <input type="text" id="<%= FormConfig.CARD_ID %>"
                       name="<%= FormConfig.CARD_ID %>" class="form-control"
                       placeholder="číslo OP" required
                        value="${customer.cardID}">
                <label for="<%= FormConfig.CARD_ID %>">číslo OP</label>
            </div>
            <div class="row">
                <div class="form-check col col-6">
                    <label class="form-check-label">
                        <input type="radio" class="form-check-input" name="<%= FormConfig.GENDER %>" id="male" value="male" required>
                        Muž
                    </label>
                </div>
                <div class="form-check col col-6">
                    <label class="form-check-label">
                        <input type="radio" class="form-check-input" name="<%= FormConfig.GENDER %>" id="female" value="female">
                        Žena
                    </label>
                </div>
            </div>
        </fieldset>

        <fieldset class="form-group col-12 col-md-6">
            <legend>Trvalé bydliště</legend>
            <div class="form-label-group">
                <input type="text" id="<%= FormConfig.CITY%>" name="<%= FormConfig.CITY%>" class="form-control" placeholder="Město" required>
                <label for="<%= FormConfig.CITY%>">Město</label>
            </div>

            <div class="row">
                <div class="form-label-group col col-9">
                    <input type="text" id="<%= FormConfig.STREET%>" name="<%= FormConfig.STREET%>" class="form-control" placeholder="Ulice" required>
                    <label for="<%= FormConfig.STREET%>">Ulice</label>
                </div>

                <div class="form-label-group col col-3">
                    <input type="text" id="<%= FormConfig.HOUSE_NUMBER%>" name="<%= FormConfig.HOUSE_NUMBER%>" class="form-control" placeholder="ČP" required>
                    <label for="<%= FormConfig.HOUSE_NUMBER%>">ČP</label>
                </div>

            </div>
            <div class="form-label-group">
                <input type="text" id="<%= FormConfig.POSTAL_CODE%>" name="<%= FormConfig.POSTAL_CODE%>" class="form-control" placeholder="PSČ" required>
                <label for="<%= FormConfig.POSTAL_CODE%>">PSČ</label>
            </div>

            <div class="form-group">
                <label for="<%= FormConfig.STATE%>">Stát</label>
                <select class="form-control" id="<%= FormConfig.STATE%>"
                        name="<%= FormConfig.STATE%>">
                    <option value="CZ">Česká Republika</option>
                    <option value="SR">Slovenská Republika</option>
                    <option disabled title="Připravujeme">Velká Británie</option>
                </select>
            </div>
        </fieldset>

        <fieldset class="form-group col-12 col-md-6">
            <legend>Údaje pro systém</legend>
            <div class="form-label-group">
                <input type="email" id="<%= FormConfig.EMAIL_1%>"
                       name="<%= FormConfig.EMAIL_1%>"
                       class="form-control" placeholder="Kontaktní email" required>
                <label for="<%= FormConfig.EMAIL_1%>">Kontaktní email</label>
            </div>
            <div class="form-label-group">
                <input type="email" id="<%= FormConfig.EMAIL_2%>"
                       name="<%= FormConfig.EMAIL_2%>" class="form-control" placeholder="Ověření emailu" required>
                <label for="<%= FormConfig.EMAIL_2%>">Ověření emailu</label>
            </div>
            <%--<div class="form-label-group">--%>
                <%--<input type="text" id="login" class="form-control" placeholder="Login" required>--%>
                <%--<label for="login">Přihlašovací jméno</label>--%>
            <%--</div>--%>
        </fieldset>

        <fieldset class="form-group col-12 col-md-6">
            <legend>Ověření poradce</legend>
            <div class="form-label-group">
                <input type="password" id="<%= FormConfig.PASSWORD%>" name="<%= FormConfig.PASSWORD%>" class="form-control" placeholder="Obchodní heslo" required>
                <label for="<%= FormConfig.PASSWORD%>">Obchodní heslo</label>
            </div>
            <div class="form-label-group">
                <p>${sessionScope.turingAsk}</p>
                <input type="text" id="<%= FormConfig.TURING%>" name="<%= FormConfig.TURING%>"
                       class="form-control" placeholder="Odpověď pro turinga" required>
                <label for="<%= FormConfig.TURING%>">Odpověď pro turinga</label>
            </div>
        </fieldset>

        <sec:csrfInput/>
        <sec:csrfMetaTags/>

    </div>
    <button class="btn btn-lg btn-default btn-block" type="submit">Založit zákazníka</button>
</form>
