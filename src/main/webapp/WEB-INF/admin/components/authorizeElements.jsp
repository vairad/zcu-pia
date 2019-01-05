<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="cz.zcu.pia.revoloot.web.FormConfig" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--@elvariable id="errors" type="java.util.Set"--%>

<legend>Ověření poradce</legend>
<div class="form-label-group">
    <input type="password" id="<%= FormConfig.SELL_PASSWORD%>"
           name="<%= FormConfig.SELL_PASSWORD%>"
           class="form-control <c:if test="${fn:contains(errors, FormConfig.SELL_PASSWORD) == true}">alert-danger</c:if>"
           placeholder="Obchodní heslo"
           required>
    <label for="<%= FormConfig.SELL_PASSWORD%>"
            <c:if test="${fn:contains(errors, FormConfig.SELL_PASSWORD) == true}">
                title="Bylo zadáno špatné obchodní heslo."
                data-toggle="tooltip"
                data-placement="top"
            </c:if>
    >Obchodní heslo</label>
    <c:if test="${fn:contains(errors, FormConfig.SELL_PASSWORD) == true}">
        <small class="d-block d-sm-none">Bylo zadáno špatné heslo.</small>
    </c:if>
</div>
<div class="form-label-group">
    <p>${sessionScope.turingAsk}</p>
    <input type="text" id="<%= FormConfig.TURING%>"
           name="<%= FormConfig.TURING%>"
           class="form-control <c:if test="${fn:contains(errors, FormConfig.TURING) == true}">alert-danger</c:if>"
           placeholder="Odpověď pro turinga"
           required>
    <label for="<%= FormConfig.TURING%>"
            <c:if test="${fn:contains(errors, FormConfig.TURING) == true}">
                title="Špatná odpověď na Allanovu otázku."
                data-toggle="tooltip"
                data-placement="top"
            </c:if>
    >Odpověď pro turinga</label>
    <c:if test="${fn:contains(errors, FormConfig.TURING) == true}">
        <small class="d-block d-sm-none">Špatná odpověď na Allanovu otázku.</small>
    </c:if>
</div>
