<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="cs">
<jsp:include page="../components/head.jsp"/>
<body>

<!-- Main content -->
<main class="container content text-center">
    <form class="form-signin" action="../private/index.html">
        <div class="text-center mb-4">
            <h1 class="h3 mb-3 font-weight-normal">Přihlášení</h1>
            <p>Zadejte prosím vaše přihlašovací údaje.</p>
        </div>

        <div class="form-label-group">
            <input type="text" id="userID" class="form-control" placeholder="Uživatelské ID" required autofocus>
            <label for="userID">Uživatelské ID</label>
        </div>

        <div class="form-label-group">
            <input type="password" id="userPIN" class="form-control" placeholder="PIN" required>
            <label for="userPIN">PIN</label>
        </div>
        <button class="btn btn-lg btn-default btn-block" type="submit">Přihlásit</button>
    </form>
</main>
<!-- Main content -->

<jsp:include page="components/navigation.jsp"/>
<jsp:include page="../components/footer.jsp">
    <jsp:param name="format" value="footer"/>
</jsp:include>
<jsp:include page="../components/scripts.jsp"/>
</body>
</html>