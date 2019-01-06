<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="cz.zcu.pia.revoloot.web.FormConfig" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--@elvariable id="errors" type="java.util.String"--%>
<%--@elvariable id="turingAsk" type="java.lang.String"--%>

<legend>Ověření poradce</legend>
<div class="form-label-group">
    <jsp:include page="../../components/labeledInput.jsp">
        <jsp:param name="TYPE" value="password"/>
        <jsp:param name="FIELD" value="<%=FormConfig.SELL_PASSWORD%>"/>
        <jsp:param name="VIEW_NAME" value="Obchodní heslo"/>
        <jsp:param name="ERROR_MESSAGE" value="Bylo zadáno špatné obchodní heslo."/>
    </jsp:include>
</div>

<div class="form-label-group">
    <p>${turingAsk}</p>
    <jsp:include page="../../components/labeledInput.jsp">
        <jsp:param name="TYPE" value="text"/>
        <jsp:param name="FIELD" value="<%=FormConfig.TURING%>"/>
        <jsp:param name="VIEW_NAME" value="Odpověď pro turinga"/>
        <jsp:param name="ERROR_MESSAGE" value="Špatná odpověď na Allanovu otázku."/>
    </jsp:include>
</div>
