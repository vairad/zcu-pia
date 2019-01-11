<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="cs">
<jsp:include page="../components/head.jsp"/>
<body>

<!-- Main content -->
<main class="container content text-center">
    <h1 class="display-3">Naše produkty</h1>
    <jsp:include page="../components/info.jsp"/>

    <div class="row justify-content-md-center">
        <%--@elvariable id="productList" type="java.util.List"--%>
        <%--@elvariable id="product" type="cz.zcu.pia.revoloot.entities.Product"--%>
        <c:forEach items="#{productList}" var="product">
            <c:if test="${product.marketing}"><div id="account-${product.id}" class="product main-product col col-12 col-md-11 mb-3 p-5 text-center"></c:if>
            <c:if test="${not product.marketing}"><div id="account-${product.id}" class="product <c:if test="${(product.id % 2) == 1}">product-2 offset-md-1</c:if> product-1 col col-12 col-md-5  mb-3 p-5 text-center"></c:if>
                <h2 class="display-4 font-weight-normal">${product.name}</h2>
                <p class="lead font-weight-normal">${product.shortText}</p>
                <button type="button" class="btn btn-dark" data-toggle="modal" data-target="#account-${product.id}-conditions">
                    Podmínky
                </button>
                <%--<a class="btn btn-dark" href="login.html">Sjednat</a>--%>
                <div class="modal fade" id="account-${product.id}-conditions" tabindex="-1" role="dialog" aria-labelledby="id_${product.id}" aria-hidden="true">
                    <div class="modal-dialog modal-lg modal-dialog-centered" role="document" id="id_${product.id}">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title">${product.name}</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                    ${product.terms}
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Zavřít</button>
                                <%--<a class="btn btn-dark" href="login.html">Sjednat</a>--%>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</main>
<!-- Main content -->

<jsp:include page="components/navigation.jsp"/>
<jsp:include page="../components/footer.jsp">
    <jsp:param name="format" value="footer-std footer"/>
</jsp:include>
<jsp:include page="../components/scripts.jsp"/>
</body>
</html>