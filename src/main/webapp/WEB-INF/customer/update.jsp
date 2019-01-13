<%@ page import="cz.zcu.pia.revoloot.web.FormConfig" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="cs">
<jsp:include page="../components/head.jsp"/>

<body>
<%--@elvariable id="account" type="cz.zcu.pia.revoloot.entities.Account"--%>

<!-- Main content -->
<main class="container content text-center">
    <h1 class="display-4">Upravit údaje</h1>
    <jsp:include page="../components/info.jsp"/>

    <form action="<c:url value="<%=FormConfig.ACTION_CUSTOMER_UPDATE%>"/>" method="post" accept-charset="UTF-8">

        <div class="row">
            <fieldset class="form-group col-12 col-md-6">
                <jsp:include page="../components/addressElements.jsp"/>
            </fieldset>

            <fieldset class="form-group col-12 col-md-6">
                <jsp:include page="../components/contactElements.jsp"/>
            </fieldset>

            <fieldset class="form-group col-12 col-md-6">
                <legend>Ověření</legend>
                <div class="col col-12">
                    <p>${turingAsk}</p>
                </div>
                <div class="form-label-group col col-12 col-md-6">
                    <jsp:include page="../components/labeledInput.jsp">
                        <jsp:param name="TYPE" value="text"/>
                        <jsp:param name="FIELD" value="<%=FormConfig.TURING%>"/>
                        <jsp:param name="VIEW_NAME" value="Odpověď pro turinga"/>
                        <jsp:param name="ERROR_MESSAGE" value="Špatná odpověď na Allanovu otázku."/>
                        <jsp:param name="REQ" value="true"/>
                    </jsp:include>
                </div>
            </fieldset>

            <sec:csrfInput/>

        </div>
        <button class="btn btn-lg btn-default btn-block" type="submit">Upravit údaje</button>
    </form>

</main>
<!-- Main content -->

<jsp:include page="components/navigation.jsp"/>
<jsp:include page="../components/footer.jsp">
    <jsp:param name="format" value="footer"/>
</jsp:include>
<jsp:include page="../components/scripts.jsp"/>
</body>
</html>
