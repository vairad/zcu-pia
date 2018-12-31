<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="cs">
<jsp:include page="../components/head.jsp"/>

<body class="body-title">

<!-- Main content -->
<main class="container content text-center">

    <!-- Money logo -->
    <div class="money shadow-sm d-none d-sm-none d-md-block d-lg-block">
        <div class="money-center"></div>
    </div>

    <h1>Vítej kámo</h1>
    <p class="lead">Teď můžeš spravovat svoje lováče.</p>

</main>
<!-- Main content -->

<jsp:include page="components/navigation.jsp"/>
<jsp:include page="../components/footer.jsp">
    <jsp:param name="format" value="footer"/>
</jsp:include>
<jsp:include page="../components/scripts.jsp"/>
</body>
</html>