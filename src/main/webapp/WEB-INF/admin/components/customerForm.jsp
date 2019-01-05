<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="cz.zcu.pia.revoloot.web.FormConfig" %>

<%--@elvariable id="customer" type="cz.zcu.pia.revoloot.entities.Customer"--%>

<form action="<c:url value="<%=FormConfig.ACTION_REGISTER%>"/>" method="post">

    <div class="row">
        <fieldset class="form-group col-12 col-md-6">
            <jsp:include page="personElements.jsp" />
        </fieldset>

        <fieldset class="form-group col-12 col-md-6">
            <jsp:include page="addressElements.jsp"/>
        </fieldset>

        <fieldset class="form-group col-12 col-md-6">
            <jsp:include page="contactElements.jsp" />
        </fieldset>

        <fieldset class="form-group col-12 col-md-6">
            <jsp:include page="authorizeElements.jsp" />
        </fieldset>

        <sec:csrfInput/>
        <sec:csrfMetaTags/>

    </div>
    <button class="btn btn-lg btn-default btn-block" type="submit">Založit zákazníka</button>
</form>
