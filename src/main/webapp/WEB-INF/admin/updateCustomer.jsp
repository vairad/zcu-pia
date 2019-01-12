<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="cz.zcu.pia.revoloot.web.FormConfig" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="cs">

<jsp:include page="../components/head.jsp"/>
<body>
<main class="container content text-center">
    <h1 class="display-4 mb-3 font-weight-normal">Osobní údaje: ${customer.name} ${customer.surname}</h1>
    <%--@elvariable id="customer" type="cz.zcu.pia.revoloot.entities.Customer"--%>
    <jsp:include page="../components/info.jsp"/>
    <form action="<%=FormConfig.ACTION_ADMIN_CUSTOMER%>/${customer.RBI}" method="post" accept-charset="UTF-8">

        <div class="row">
            <fieldset class="form-group col-12 col-md-6">
                <jsp:include page="components/personElements.jsp"/>
            </fieldset>

            <fieldset class="form-group col-12 col-md-6">
                <jsp:include page="../components/addressElements.jsp"/>
            </fieldset>

            <fieldset class="form-group col-12 col-md-6">
                <jsp:include page="../components/contactElements.jsp"/>
            </fieldset>

            <fieldset class="form-group col-12 col-md-6">
                <jsp:include page="components/authorizeElements.jsp"/>
            </fieldset>

            <sec:csrfInput/>

        </div>
        <button class="btn btn-lg btn-default btn-block" type="submit">Upravit údaje</button>
    </form>
</main>
<jsp:include page="components/navigation.jsp"/>
<jsp:include page="../components/scripts.jsp"/>
</body>
</html>
