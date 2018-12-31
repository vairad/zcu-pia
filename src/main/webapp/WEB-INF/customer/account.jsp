<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="cs">
<jsp:include page="../components/head.jsp"/>

<body>
<%--@elvariable id="account" type="cz.zcu.pia.revoloot.entities.Account"--%>

<!-- Main content -->
<main class="container content text-center">
    <h1 class="display-4">Detail účtu</h1>

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
                <th scope="col">Datum</th>
                <th scope="col">Typ transakce</th>
                <th scope="col">SS <br/> VS <br/> KS</th>
                <th scope="col">Částka</th>
                <th scope="col">Popis</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <%--@elvariable id="move" type="cz.zcu.pia.revoloot.entities.Move"--%>
            <c:forEach items="#{account.moves}" var="move">
                <tr>
                    <td>${move.transferDate}</td>
                    <td>${move.destination}</td>
                    <td>${move.specificSymbol}<br/>
                            ${move.variableSymbol} <br/>
                            ${move.constantSymbol}
                    </td>
                    <td>${move.amount}</td>
                    <td>${move.message} <br/>
                        <i>${move.note}</i>
                    </td>
                    <td>
                        <button type="button" class="btn btn-dark"><span class="octicon octicon-credit-card"></span>
                        </button>
                        <button type="button" class="btn btn-dark"><span class="octicon octicon-file-pdf"></span></button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
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