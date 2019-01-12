<%@ page import="cz.zcu.pia.revoloot.web.FormConfig" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<label for="<%=FormConfig.CURRENCY%>" class="sr-only">Měna:</label>
<select class="form-control" id="<%=FormConfig.CURRENCY%>" name="<%=FormConfig.CURRENCY%>">
    <option value="CZK">
        Kč (CZK)
    </option>
    <option value="EUR">
        € (EUR)
    </option>
    <option value="GBP">
        £ (GBP)
    </option>
    <option disabled>
        $ (USD)
    </option>
</select>