<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="cz.zcu.pia.revoloot.web.FormConfig" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<form class="form-signin"
      action="<%= FormConfig.ACTION_LOGIN %>"
      method="post">
    <div class="text-center mb-4">
        <h1 class="h3 mb-3 font-weight-normal">Přihlášení</h1>
        <p>Zadejte prosím vaše přihlašovací údaje.</p>
        <c:if test="${not empty param.error}">
            <p class="alert alert-danger" role="alert">
                <span class="octicon octicon-issue-opened"></span>
                Chybné přilašovací údaje
                <span class="octicon octicon-issue-opened"></span>
            </p>
        </c:if>
    </div>

    <%
//        if(request.getParameter(LoginFormConfig.USER_ID) == null){
//            request.setAttribute(LoginFormConfig.USER_ID, "");
//        }else{
//            request.setAttribute(LoginFormConfig.USER_ID, request.getParameter(LoginFormConfig.USER_ID));
//        }
        //TODO auto pass login into form
    %>

    <div class="form-label-group">
        <input type="text"
               id="<%= FormConfig.USER_ID %>"
               name="<%= FormConfig.USER_ID %>"
               class="form-control"
               placeholder="Uživatelské ID"
               required autofocus >
        <label for="<%= FormConfig.USER_ID %>">Uživatelské ID</label>
    </div>

    <div class="form-label-group">
        <input type="password"
               id="<%= FormConfig.PASSWORD %>"
               name="<%= FormConfig.PASSWORD %>"
               class="form-control" placeholder="PIN" required >
        <label for="<%= FormConfig.PASSWORD %>">PIN</label>
    </div>

    <sec:csrfInput/>

    <button class="btn btn-lg btn-default btn-block" type="submit">Přihlásit</button>
</form>