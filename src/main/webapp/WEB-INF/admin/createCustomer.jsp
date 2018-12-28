<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="cs">

<jsp:include page="../components/head.jsp" />
<body>
<main class="container content text-center">
    <h1 class="display-4 mb-3 font-weight-normal">Vytvoř zákazníka</h1>
<%--TODO alerts--%>
<%--<div class="row">--%>
<%--<!--global alert info-->--%>
<%--<div class="text-center col col-12">--%>
<%--<p class="alert alert-danger">Ověřte existenci zákazníka pomocí dokladu totožnosti!</p>--%>
<%--<p class="alert alert-success">Zákazník byl úspěšně zadán do systému.</p>--%>
<%--</div>--%>
<%--</div>--%>
    <jsp:include page="components/customerForm.jsp" />
</main>
<jsp:include page="components/navigation.jsp" />
<jsp:include page="../components/scripts.jsp"/>
</body>
</html>
