<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="cz.zcu.pia.revoloot.web.FormConfig" %>

<form action="<c:url value="/admin/register"/>">

    <div class="row">
        <fieldset class="form-group col-12 col-md-6">
            <legend class="col-form-legend col-12">Osobní údaje</legend>

            <div class="form-label-group">
                <input type="text" id="<%= FormConfig.NAME %>" class="form-control" placeholder="Jméno" required autofocus>
                <label for="<%= FormConfig.NAME %>">Jméno</label>
            </div>

            <div class="form-label-group">
                <input type="text" id="surname" class="form-control" placeholder="Jméno" required>
                <label for="surname">Příjmení</label>
            </div>

            <div class="form-label-group">
                <input type="date" id="birthDate" class="form-control" required>
                <label for="birthDate">Datum narození</label>
            </div>

            <div class="form-label-group">
                <input type="text" id="personID" class="form-control alert-danger"
                       placeholder="Rodné číslo" required>
                <label for="personID" title="Rodné číslo neodpovídá datu narození."
                       data-toggle="tooltip" data-placement="top">Rodné číslo</label>
                <small class="d-block d-sm-none">Rodné číslo neodpovídá datu narození.</small>
            </div>

            <div class="form-label-group">
                <input type="text" id="idNum" class="form-control" placeholder="číslo OP" required>
                <label for="idNum">číslo OP</label>
            </div>
            <div class="row">
                <div class="form-check col col-6">
                    <label class="form-check-label">
                        <input type="radio" class="form-check-input" name="gender" id="male" value="male" required>
                        Muž
                    </label>
                </div>
                <div class="form-check col col-6">
                    <label class="form-check-label">
                        <input type="radio" class="form-check-input" name="gender" id="female" value="female">
                        Žena
                    </label>
                </div>
            </div>
        </fieldset>

        <fieldset class="form-group col-12 col-md-6">
            <legend>Trvalé bydliště</legend>
            <div class="form-label-group">
                <input type="text" id="city" class="form-control" placeholder="Město" required>
                <label for="city">Město</label>
            </div>

            <div class="row">
                <div class="form-label-group col col-9">
                    <input type="text" id="street" class="form-control" placeholder="Ulice" required>
                    <label for="street">Ulice</label>
                </div>

                <div class="form-label-group col col-3">
                    <input type="text" id="houseNumber" class="form-control" placeholder="ČP" required>
                    <label for="houseNumber">ČP</label>
                </div>

            </div>
            <div class="form-label-group">
                <input type="text" id="postalCode" class="form-control" placeholder="PSČ" required>
                <label for="postalCode">PSČ</label>
            </div>

            <div class="form-group">
                <label for="state">Stát</label>
                <select class="form-control" id="state">
                    <option value="CZ">Česká Republika</option>
                    <option value="SR">Slovenská Republika</option>
                    <option disabled title="Připravujeme">Velká Británie</option>
                </select>
            </div>
        </fieldset>

        <fieldset class="form-group col-12 col-md-6">
            <legend>Údaje pro systém</legend>
            <div class="form-label-group">
                <input type="email" id="email1" class="form-control" placeholder="Kontaktní email" required>
                <label for="email1">Kontaktní email</label>
            </div>
            <div class="form-label-group">
                <input type="email" id="email2" class="form-control" placeholder="Ověření emailu" required>
                <label for="email2">Ověření emailu</label>
            </div>
            <div class="form-label-group">
                <input type="text" id="login" class="form-control" placeholder="Login" required>
                <label for="login">Přihlašovací jméno</label>
            </div>
        </fieldset>

        <fieldset class="form-group col-12 col-md-6">
            <legend>Ověření poradce</legend>
            <div class="form-label-group">
                <input type="password" id="password" class="form-control" placeholder="Obchodní heslo" required>
                <label for="password">Obchodní heslo</label>
            </div>
            <div class="form-label-group">
                <input type="text" id="turing" class="form-control" placeholder="Odpověď pro turinga" required>
                <label for="turing">Odpověď pro turinga</label>
            </div>
        </fieldset>

    </div>
    <button class="btn btn-lg btn-default btn-block" type="submit">Založit zákazníka</button>
</form>