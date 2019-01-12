<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="cs">
<jsp:include page="../components/head.jsp"/>

<body>
<%--@elvariable id="account" type="cz.zcu.pia.revoloot.entities.Account"--%>
<jsp:useBean id="dateFormatter" scope="application" class="cz.zcu.pia.revoloot.utils.CzechFormatter"
             type="cz.zcu.pia.revoloot.utils.IDateFormatter"/>

<!-- Main content -->
<main class="container content text-center">
    <h1 class="display-4">Detail účtu</h1>
    <jsp:include page="../components/info.jsp"/>

    <div class="product product-2 p-3 row mb-2">
        <div class="col col-12 col-md-7 align-middle">
            <h3 class="m-3">Disponibilní zůstatek:&nbsp;${account.amount}&nbsp;${account.currency}</h3>
        </div>
        <div class="col col-12 col-md-5">
            <p class="m-3">Účetní zůstatek:&nbsp;${account.trueAmount}&nbsp;${account.currency}</p>
            <p class="m-3">Číslo účtu:&nbsp;${account.accountInfo}</p>
            <p class="m-3">Typ účtu:&nbsp;${account.product.name}</p>
            <p class="m-3">Měna:&nbsp;${account.currency}</p>
        </div>
    </div>

    <h2>Přehled operací:</h2>
<jsp:include page="../components/pages.jsp"/>
    <div class="table-responsive">
        <table class="table table-hover">
            <thead>
            <tr>
                <th></th>
                <th scope="col">Datum splatnosti<br/>Datum zaúčtování</th>
                <th scope="col">Protiúčet</th>
                <th scope="col">Parametry</th>
                <th scope="col">Částka</th>
                <th scope="col">Popis</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <%--@elvariable id="move" type="cz.zcu.pia.revoloot.entities.Move"--%>
            <c:forEach items="#{account.moves}" var="move">
                <tr>
                    <td class="align-middle"><c:if test="${move.income}"><p><span
                            class="octicon octicon-diff-added"></span><span class="sr-only">Příjem</span></p></c:if>
                        <c:if test="${not move.income}"><p><span class="octicon octicon-diff-removed"></span><span
                                class="sr-only">Výdej</span></p></c:if></td>
                    <td class="align-middle">
                        <p>${dateFormatter.dateTimeFormat(move.submissionDate)}</p>
                        <p>${dateFormatter.dateTimeFormat(move.transferDate)}</p>
                    </td>
                    <td class="align-middle">
                        <p>${move.destination}</p>
                    </td>
                    <td class="align-middle">
                        <c:if test="${not empty move.specificSymbol}"><p>SS:${move.specificSymbol}</p></c:if>
                        <c:if test="${not empty move.variableSymbol}"><p>VS:${move.variableSymbol}</p></c:if>
                        <c:if test="${not empty move.constantSymbol}"><p>KS:${move.constantSymbol}</p></c:if>
                    </td>
                    <td class="align-middle"><c:if test="${move.income}">+</c:if><c:if
                            test="${not move.income}">-</c:if>${move.amount}&nbsp;${move.currency}</td>
                    <td class="align-middle">
                        <p>${move.message}</p>
                        <i>${move.note}</i>
                        <p>${move.bankNote}</p>
                    </td>
                    <td class="align-middle">
                        <button type="button" class="btn btn-dark"><span class="octicon octicon-credit-card"></span>
                        </button>
                        <button type="button" class="btn btn-dark"><span class="octicon octicon-file-pdf"></span>
                        </button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

<jsp:include page="../components/pages.jsp"/>


</main>
<!-- Main content -->

<jsp:include page="components/navigation.jsp"/>
<jsp:include page="../components/footer.jsp">
    <jsp:param name="format" value="footer"/>
</jsp:include>
<jsp:include page="../components/scripts.jsp"/>
</body>
</html>