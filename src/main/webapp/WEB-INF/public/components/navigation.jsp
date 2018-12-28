<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Navigation -->
<nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
    <a class="navbar-brand" href="home">Revolution Loot</a>

    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse"
            aria-controls="navbarCollapse" aria-expanded="false" aria-label="Zobraz menu">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarCollapse">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="home">Úvod <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="contacts">Kontakt</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="https://example.com" id="dropdown01" data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false">Nabídky</a>
                <div class="dropdown-menu" aria-labelledby="dropdown01">
                    <a class="dropdown-item" href="products">Nabídky</a>
                    <hr/>
                    <a class="dropdown-item" href="products#account">Běžný účet</a>
                    <a class="dropdown-item" href="products#saving">Spořící účet</a>
                    <a class="dropdown-item" href="products#business">Podnikatelský účet</a>
                </div>
            </li>
        </ul>
        <a href="login" class="btn btn-outline-success my-2 my-sm-0" role="button">Internet Banking</a>
    </div>
</nav>