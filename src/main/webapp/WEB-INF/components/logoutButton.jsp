<%@ page import="cz.zcu.pia.revoloot.web.ServletNaming" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!-- logout button -->
<form action="<c:url value="<%=ServletNaming.LOGOUT_PROCESS%>"/>" method="post">
    <sec:csrfInput/>
    <button class="btn btn-outline-success my-2 my-sm-0 m-2" type="submit">Odhlásit</button>
</form>
<!-- logout button -->
