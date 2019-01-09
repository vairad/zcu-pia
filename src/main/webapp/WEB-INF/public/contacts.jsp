<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="cs">
<jsp:include page="../components/head.jsp"/>
<body>

<!-- Main content -->
<main class="container content text-center">
    <h1 class="first-element display-3">Revolution loot</h1>
    <jsp:include page="../components/info.jsp"/>

    <p class="h2">... jsme důvěryhodní a klientům vždy nablízku...</p>

    <p>
        Každý den Vám přinášíme to nejlepší ze světa tradičních bank i moderních technologií.
        Se zájmem a péčí obsluhujeme Vás a další milion klientů, což z nás dělá čtvrtou největší banku v zemi.
        S internetovým bankovnictvím Revoloot jsme Vám vždy nablízku.
    </p>

    <h2>Naši bankéři</h2>

    <div class="row">
        <%--@elvariable id="bankers" type="java.util.List"--%>
        <%--@elvariable id="banker" type="cz.zcu.pia.revoloot.entities.Banker"--%>
        <c:forEach items="#{bankers}" var="banker">
            <div class="col-xs-12 col-sm-6 col-md-6">
                <jsp:include page="components/bankerContact.jsp">
                    <jsp:param name="url" value="${banker.photo}" />
                    <jsp:param name="name" value="${banker.printName}" />
                    <jsp:param name="branchAddress" value="${banker.branch}" />
                    <jsp:param name="email" value="${banker.email}" />
                </jsp:include>
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