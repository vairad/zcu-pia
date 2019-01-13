<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="cz.zcu.pia.revoloot.web.ServletNaming" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
    <a class="navbar-brand" href="#">Revolution Loot</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault"
            aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarsExampleDefault">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="<c:url value="<%=ServletNaming.WELCOME%>" /> ">Dashboard</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:url value="<%=ServletNaming.ADMIN_REGISTER%>"/>">Založit zákazníka <!--<span class="sr-only">(aktuální)</span>--></a>
            </li>
            <li class="nav-item">
                <a class="nav-link " href="<c:url value="<%=ServletNaming.ADMIN_PRODUCT%>"/>">Sjednat produkt</a>
            </li>
        </ul>

        <jsp:include page="../../components/logoutButton.jsp" />

        <%--<a href="" class="btn btn-outline-success my-2 my-sm-0 m-2" role="button">--%>
            <%--<span class="octicon octicon-person"></span>--%>
            <%--<span class="sr-only">Profil</span>--%>
        <%--</a>--%>
    </div>

</nav>