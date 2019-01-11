<%@ page import="cz.zcu.pia.revoloot.web.ServletNaming" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <a class="nav-link" href="<c:url value="<%=ServletNaming.WELCOME%>"/>">Úvod <!--<span class="sr-only">(current)</span>--></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:url value="<%=ServletNaming.CONTACTS%>"/>">Kontakt</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="<c:url value="<%=ServletNaming.PRODUCTS%>"/>" id="dropdown-products" data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false">Nabídky</a>
                <div class="dropdown-menu" aria-labelledby="dropdown-products">
                    <a class="dropdown-item" href="<c:url value="<%=ServletNaming.PRODUCTS%>"/>">Nabídky</a>
                    <hr/>
                    <%--@elvariable id="productList" type="java.util.List"--%>
                    <%--@elvariable id="product" type="cz.zcu.pia.revoloot.entities.Product"--%>
                    <c:forEach items="#{productList}" var="product">
                    <a class="dropdown-item" href="<%=ServletNaming.PRODUCTS%>#account-${product.id}">${product.name}</a>
                    </c:forEach>
                </div>
            </li>
        </ul>
        <a href="<c:url value="<%=ServletNaming.LOGIN%>"/>" class="btn btn-outline-success my-2 my-sm-0" role="button">Internet Banking</a>
    </div>
</nav>
