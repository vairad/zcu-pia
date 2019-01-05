<%@ page import="cz.zcu.pia.revoloot.web.FormConfig" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<legend>Ověření poradce</legend>
<div class="form-label-group">
    <input type="password" id="<%= FormConfig.PASSWORD%>" name="<%= FormConfig.PASSWORD%>"
           class="form-control" placeholder="Obchodní heslo" required>
    <label for="<%= FormConfig.PASSWORD%>">Obchodní heslo</label>
</div>
<div class="form-label-group">
    <p>${sessionScope.turingAsk}</p>
    <input type="text" id="<%= FormConfig.TURING%>" name="<%= FormConfig.TURING%>"
           class="form-control" placeholder="Odpověď pro turinga" required>
    <label for="<%= FormConfig.TURING%>">Odpověď pro turinga</label>
</div>
