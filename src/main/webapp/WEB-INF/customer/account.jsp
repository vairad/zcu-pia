<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="cs">
<jsp:include page="../components/head.jsp"/>

<body>
<%--@elvariable id="account" type="cz.zcu.pia.revoloot.entities.Account"--%>
<jsp:useBean id="dateFormatter" scope="application" class="cz.zcu.pia.revoloot.utils.CzechFormatter" type="cz.zcu.pia.revoloot.utils.IDateFormatter"/>

<!-- Main content -->
<main class="container content text-center">
    <h1 class="display-4">Detail účtu</h1>
    <jsp:include page="../components/info.jsp"/>

    <div class="product product-2 p-3 row mb-2">
        <h3 class="m-3">Disponibilní zůstatek:&nbsp;${account.amount}&nbsp;Kč</h3>
        <p class="m-3">Účetní zůstatek:&nbsp;${account.trueAmount}&nbsp;Kč</p>
        <p class="m-3">Číslo účtu: ${account.accountInfo}</p>
    </div>

    <h2>Přehled operací:</h2>

    <div class="table-responsive">
        <table class="table table-hover">
            <thead>
            <tr>
                <th></th>
                <th scope="col">Datum splatnosti<br />Datum zaúčtování</th>
                <th scope="col">Protiúčet</th>
                <th scope="col"><p>SS</p> <p>VS</p> <p>KS</p></th>
                <th scope="col">Částka</th>
                <th scope="col">Popis</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <%--@elvariable id="move" type="cz.zcu.pia.revoloot.entities.Move"--%>
            <c:forEach items="#{account.moves}" var="move">
                <tr>
                    <td class="align-middle"><c:if test="${move.income}"><p><span class="octicon octicon-diff-added"></span><span class="sr-only">Příjem</span></p></c:if>
                        <c:if test="${not move.income}"><p><span class="octicon octicon-diff-removed"></span><span class="sr-only">Výdej</span></p></c:if></td>
                    <td class="align-middle">
                        <p>${dateFormatter.dateTimeFormat(move.submissionDate)}</p>
                        <p>${dateFormatter.dateTimeFormat(move.transferDate)}</p>
                    </td>
                    <td class="align-middle">
                        <p>${move.destination}</p>
                    </td>
                    <td class="align-middle">
                        <p>${move.specificSymbol}</p>
                        <p>${move.variableSymbol}</p>
                        <p>${move.constantSymbol}</p>
                    </td>
                    <td class="align-middle"><c:if test="${move.income}">+</c:if><c:if test="${not move.income}">-</c:if>${move.amount}</td>
                    <td class="align-middle">
                        <p>${move.message}</p>
                        <i>${move.note}</i>
                        <p>${move.bankNote}</p>
                    </td>
                    <td class="align-middle">
                        <button type="button" class="btn btn-dark"><span class="octicon octicon-credit-card"></span>
                        </button>
                        <button type="button" class="btn btn-dark"><span class="octicon octicon-file-pdf"></span></button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

<!-- TODO stránkování -->
    <nav aria-label="Stránkování výpisu">
        <ul class="pagination justify-content-center">
            <li class="page-item disabled">
                <a class="page-link" href="#" tabindex="-1">Předcházející</a>
            </li>
            <li class="page-item"><a class="page-link " href="#">1</a></li>
            <li class="page-item"><a class="page-link btn-dark" href="#">2</a></li>
            <li class="page-item"><a class="page-link btn-dark" href="#">3</a></li>
            <li class="page-item">
                <a class="page-link btn-dark" href="#">Další</a>
            </li>
        </ul>
    </nav>


</main>
<!-- Main content -->

<jsp:include page="components/navigation.jsp"/>
<jsp:include page="../components/footer.jsp">
    <jsp:param name="format" value="footer"/>
</jsp:include>
<jsp:include page="../components/scripts.jsp"/>
</body>
</html>