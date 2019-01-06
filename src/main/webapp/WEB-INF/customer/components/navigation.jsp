<%@ page import="cz.zcu.pia.revoloot.web.ServletNaming" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Navigation -->
<nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
    <a class="navbar-brand" href="<%=ServletNaming.WELCOME%>">Revolution Loot</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarToggle"
            aria-controls="navbarToggle" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarToggle">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="<%=ServletNaming.WELCOME%>">Úvod<span class="sr-only">(aktuální)</span></a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="dropdown01" data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false">Účty</a>
                <div class="dropdown-menu" aria-labelledby="dropdown01">
                    <%--@elvariable id="accountList" type="java.util.List"--%>
                    <%--@elvariable id="account" type="cz.zcu.pia.revoloot.entities.Account"--%>
                    <c:forEach items="#{accountList}" var="account">
                        <a class="dropdown-item" href="/customer/account/${account.accountInfo.number}">
                            <small class="font-weight-bold">${account.accountInfo}</small>
                            <small class="font-weight-light small">(${account.amount})</small>
                        </a>
                    </c:forEach>
                </div>
            </li>
            <%--<li class="nav-item">--%>
                <%--<a class="nav-link" href="">Zprávy z banky <span class="badge badge-light">4</span></a>--%>
            <%--</li>--%>
            <li class="nav-item">
                <a class="nav-link" href=<%=ServletNaming.CUSTOMER_PAYMENT%>>Příkaz k úhradě</a>
            </li>
            <%--<li class="nav-item">--%>
                <%--<a class="nav-link disabled" href="">Přehled karet</a>--%>
            <%--</li>--%>
            <%--<li class="nav-item">--%>
                <%--<a class="nav-link disabled" href="">Nový produkt</a>--%>
            <%--</li>--%>
        </ul>

        <jsp:include page="../../components/logoutButton.jsp" />

        <a href="<%=ServletNaming.CUSTOMER_PROFILE%>" class="btn btn-outline-success my-2 my-sm-0 m-2" role="button">
            <span class="octicon octicon-person"></span>
            <span class="sr-only">Profil</span>
        </a>
    </div>

</nav>
