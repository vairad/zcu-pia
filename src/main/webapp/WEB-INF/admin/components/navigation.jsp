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
                <a class="nav-link" href="index.html">Dashboard</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="https://example.com" id="overviews" data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false">Přehledy</a>
                <div class="dropdown-menu" aria-labelledby="overviews">
                    <a class="dropdown-item" href="list.html">
                        <small class="font-weight-bold">Domicilující klienti</small>
                    </a>
                    <a class="dropdown-item" href="list.html">
                        <small class="font-weight-bold">Klienti v debetu</small>
                    </a>
                </div>
            </li>
            <li class="nav-item">
                <a class="nav-link disabled" href="">Úkoly k vyřízení <span class="badge badge-light">4</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link active" href="createCustomer.html">Založit zákazníka <span
                        class="sr-only">(aktuální)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link " href="product.html">Sjednat produkt</a>
            </li>
        </ul>

        <jsp:include page="../../components/logoutButton.jsp" />

        <a href="" class="btn btn-outline-success my-2 my-sm-0 m-2" role="button">
            <span class="octicon octicon-person"></span>
            <span class="sr-only">Profil</span>
        </a>
    </div>

</nav>