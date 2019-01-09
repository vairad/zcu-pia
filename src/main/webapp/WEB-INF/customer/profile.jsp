<%@ page import="cz.zcu.pia.revoloot.web.ServletNaming" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="cs">
<jsp:include page="../components/head.jsp"/>

<body>
<%--@elvariable id="customer" type="cz.zcu.pia.revoloot.entities.Customer"--%>

<!-- Main content -->
<main class="container content text-center">
    <h1 class="display-4">Váš profil</h1>
    <jsp:include page="../components/info.jsp"/>

    <a href="<%=ServletNaming.CUSTOMER_UPDATE%>" class="btn btn-outline-success my-2 my-sm-0 m-2" role="button">
        <span class="octicon octicon-clippy"></span>
        Upravit údaje
    </a>
    <div class="row">
        <div class="col-xs-12 col-sm-6 col-md-6 mb-2 mt-2">
            <div class="profile card mb-2 p-2">
                <h2>Osobní informace</h2>
                <p>Jméno: ${customer.name} </p>
                <p>Příjmení: ${customer.surname} </p>
                <p>Rodné číslo: ${customer.printPersonID} </p>
                <p>Číslo dokladu: ${customer.cardID} </p>
            </div>
        </div>
        <div class="col-xs-12 col-sm-6 col-md-6 mb-2 mt-2">
            <div class="profile card mb-2 p-2">
                <h2>Kontaktní adresa</h2>
                <p>Ulice: ${customer.contactInfo.address.street} ${customer.contactInfo.address.houseNo}  </p>
                <p>Město: ${customer.contactInfo.address.city} ${customer.contactInfo.address.postalCode} </p>
                <p>Stát: ${customer.contactInfo.address.state} </p>
            </div>
        </div>
        <div class="col-xs-12 col-sm-6 col-md-6 mb-2 mt-2">
            <div class="profile card mb-2 p-2">
                <h2>Informace systému</h2>
                <p>Login: ${customer.login} </p>
                <p>Email: ${customer.contactInfo.email}</p>
            </div>
        </div>
    </div>
</main>
<!-- Main content -->

<jsp:include page="components/navigation.jsp"/>
<jsp:include page="../components/footer.jsp">
    <jsp:param name="format" value="footer"/>
</jsp:include>
<jsp:include page="../components/scripts.jsp"/>
</body>
</html>
