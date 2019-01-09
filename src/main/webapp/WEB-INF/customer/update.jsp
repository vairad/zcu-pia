<%@ page import="cz.zcu.pia.revoloot.web.ServletNaming" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="cs">
<jsp:include page="../components/head.jsp"/>

<body>
<%--@elvariable id="account" type="cz.zcu.pia.revoloot.entities.Account"--%>

<!-- Main content -->
<main class="container content text-center">
    <h1 class="display-4">Upravit údaje</h1>
    <jsp:include page="../components/info.jsp"/>

</main>
<!-- Main content -->

<jsp:include page="components/navigation.jsp"/>
<jsp:include page="../components/footer.jsp">
    <jsp:param name="format" value="footer"/>
</jsp:include>
<jsp:include page="../components/scripts.jsp"/>
</body>
</html>
