<%@ page import="cz.zcu.pia.revoloot.web.ServletNaming" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Navigation -->
<nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
    <a class="navbar-brand" href="#">Revolution Loot</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault"
            aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarsExampleDefault">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="#">Úvod<span class="sr-only">(aktuální)</span></a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="https://example.com" id="dropdown01" data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false">Účty</a>
                <div class="dropdown-menu" aria-labelledby="dropdown01">
                    <a class="dropdown-item" href="account.html">
                        <small class="font-weight-bold">1562987445/2584</small>
                        <small class="font-weight-light small">(12 000,45Kč)</small>
                    </a>
                    <a class="dropdown-item" href="account.html">
                        <small class="font-weight-bold">1562987445/2584</small>
                        <small class="font-weight-light small">(12 000,45Kč)</small>
                    </a>
                    <a class="dropdown-item" href="account.html">
                        <small class="font-weight-bold">1562987445/2584</small>
                        <small class="font-weight-light small">(12 000,45Kč)</small>
                    </a>
                    <a class="dropdown-item" href="account.html">
                        <small class="font-weight-bold">1562987445/2584</small>
                        <small class="font-weight-light small">(12 000,45Kč)</small>
                    </a>
                    <a class="dropdown-item" href="account.html">
                        <small class="font-weight-bold">1562987445/2584</small>
                        <small class="font-weight-light small">(12 000,45Kč)</small>
                    </a>
                </div>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="">Zprávy z banky <span class="badge badge-light">4</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="paymentOrder.html">Příkaz k úhradě</a>
            </li>
            <li class="nav-item">
                <a class="nav-link disabled" href="">Přehled karet</a>
            </li>
            <li class="nav-item">
                <a class="nav-link disabled" href="">Nový produkt</a>
            </li>
        </ul>

        <jsp:include page="../../components/logoutButton.jsp" />

        <a href="" class="btn btn-outline-success my-2 my-sm-0 m-2" role="button">
            <span class="octicon octicon-person"></span>
            <span class="sr-only">Profil</span>
        </a>
    </div>

</nav>
