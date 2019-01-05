<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="cz.zcu.pia.revoloot.web.FormConfig" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--@elvariable id="customer" type="cz.zcu.pia.revoloot.entities.Customer"--%>
<%--@elvariable id="errors" type="java.util.Set"--%>

<legend>Údaje pro systém</legend>
<div class="form-label-group">
    <input type="email" id="<%= FormConfig.EMAIL_1%>"
           name="<%= FormConfig.EMAIL_1%>"
           class="form-control  <c:if test="${fn:contains(errors, FormConfig.EMAIL_1) == true}">alert-danger</c:if>"
           placeholder="Kontaktní email"
           required>
    <label for="<%= FormConfig.EMAIL_1%>"
            <c:if test="${fn:contains(errors, FormConfig.EMAIL_1) == true}">
                title="Zadejte emailovou adresu v platném formátu (exm@revoloot.cz)."
                data-toggle="tooltip"
                data-placement="top"
            </c:if>
    >Kontaktní email</label>
</div>
<div class="form-label-group">
    <input type="email" id="<%= FormConfig.EMAIL_2%>"
           name="<%= FormConfig.EMAIL_2%>"
           class="form-control  <c:if test="${fn:contains(errors, FormConfig.EMAIL_2) == true}">alert-danger</c:if>"
           placeholder="Ověření emailu"
           required>
    <label for="<%= FormConfig.EMAIL_2%>"
            <c:if test="${fn:contains(errors, FormConfig.EMAIL_2) == true}">
                title="Zadejte shodný mail pro ověření."
                data-toggle="tooltip"
                data-placement="top"
            </c:if>
    >Ověření emailu</label>
</div>
<%--<div class="form-label-group">--%>
<%--<input type="text" id="login" class="form-control" placeholder="Login" required>--%>
<%--<label for="login">Přihlašovací jméno</label>--%>
<%--</div>--%>
