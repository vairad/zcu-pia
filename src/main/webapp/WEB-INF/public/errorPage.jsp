<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>

<!DOCTYPE html>
<html lang="cs">
<jsp:include page="../components/head.jsp"/>

<body class="body-title">

<!-- Main content -->
<main class="container content text-center">
    <jsp:include page="../components/info.jsp"/>

    <!-- Money logo -->
    <div class="money shadow-sm d-none d-sm-none d-md-block d-lg-block">
        <div class="money-center"></div>
    </div>

    <h1>Nevíme co hledáte</h1>
    <p class="lead">${errorMsg}</p>

</main>
<!-- Main content -->

<jsp:include page="components/navigation.jsp"/>
<jsp:include page="../components/footer.jsp">
    <jsp:param name="format" value="footer"/>
</jsp:include>
<jsp:include page="../components/scripts.jsp"/>
</body>
</html>