<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="cz.zcu.pia.revoloot.web.FormConfig" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%--@elvariable id="customer" type="cz.zcu.pia.revoloot.entities.Customer"--%>
<%--@elvariable id="errors" type="java.util.Set"--%>

<legend>Údaje pro systém</legend>
<div class="form-label-group">
    <jsp:include page="labeledInput.jsp">
        <jsp:param name="TYPE" value="text"/>
        <jsp:param name="FIELD" value="<%=FormConfig.EMAIL_1%>"/>
        <jsp:param name="VIEW_NAME" value="Kontakrní email"/>
        <jsp:param name="ERROR_MESSAGE" value="Zadejte emailovou adresu v platném formátu (např. exm@revoloot.cz)."/>
        <jsp:param name="VALUE" value="${customer.contactInfo.email}"/>
        <jsp:param name="REQ" value="true"/>
    </jsp:include>
</div>
<div class="form-label-group">
    <jsp:include page="labeledInput.jsp">
        <jsp:param name="TYPE" value="text"/>
        <jsp:param name="FIELD" value="<%=FormConfig.EMAIL_2%>"/>
        <jsp:param name="VIEW_NAME" value="Kontrola emailu"/>
        <jsp:param name="ERROR_MESSAGE" value="Zopakujte shodnou adresu."/>
        <jsp:param name="VALUE" value="${customer.contactInfo.email}"/>
        <jsp:param name="REQ" value="true"/>
    </jsp:include>
</div>
<%--<div class="form-label-group">--%>
<%--<input type="text" id="login" class="form-control" placeholder="Login" required>--%>
<%--<label for="login">Přihlašovací jméno</label>--%>
<%--</div>--%>
