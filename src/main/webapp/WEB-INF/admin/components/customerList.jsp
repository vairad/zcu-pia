<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="table-responsive">
    <table class="table table-hover">
        <thead>
        <tr>
            <th scope="col">Jméno</th>
            <th scope="col">Příjmení</th>
            <th scope="col">Adresa</th>
            <th scope="col">Email</th>
            <th scope="col">ČísloOP</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <%--@elvariable id="customerList" type="java.util.List"--%>
        <%--@elvariable id="customer" type="cz.zcu.pia.revoloot.entities.Customer"--%>
        <c:forEach items="#{customerList}" var="customer">
            <tr>
                <td>${customer.name}</td>
                <td>${customer.surname}</td>
                <td>${customer.contactInfo.address}</td>
                <td>${customer.contactInfo.email}</td>
                <td>${customer.cardID}</td>
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