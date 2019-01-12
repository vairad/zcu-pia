<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="cz.zcu.pia.revoloot.web.FormConfig" %>
<main class="container content text-center">
    <h1 class="display-4 mb-3 font-weight-normal">Založit produkt</h1>

    <form class="row" action="<%=FormConfig.ACTION_PRODUCT%>" method="post">
        <div class="form-group col col-12 col-md-4">
            <%--@elvariable id="errors" type="java.lang.String"--%>
            <select class="form-control" id="<%=FormConfig.PRODUCT%>" name="<%=FormConfig.PRODUCT%>" class=" <c:if test="${fn:contains(errors, param.FIELD)}">alert-danger</c:if>">
            <%--@elvariable id="productList" type="java.util.List"--%>
            <%--@elvariable id="product" type="cz.zcu.pia.revoloot.entities.Product"--%>
            <c:forEach items="#{productList}" var="product">
                <option value="${product.id}" <c:if test="${not product.available}">disabled
                        title="Připravujeme"</c:if>>${product.name}</option>
            </c:forEach>
            </select>
            <label for="<%=FormConfig.PRODUCT%>">Produkt</label>
        </div>

        <div class="form-group col col-12 col-md-2">
            <jsp:include page="../../components/currencySelect.jsp" />
        </div>

        <div class="form-label-group col col-12 col-md-6">
            <input type="text"
                   id="<%=FormConfig.RBI%>"
                   name="<%=FormConfig.RBI%>"
                   class="form-control"
            <%--@elvariable id="selectedCustomer" type="cz.zcu.pia.revoloot.entities.Customer"--%>
            <c:if test="${not empty selectedCustomer}"> value="${selectedCustomer.RBI}" disabled</c:if>
                   placeholder="RBI" required>
            <c:if test="${not empty selectedCustomer}"><label
                    for="<%=FormConfig.RBI%>">${selectedCustomer.name} ${selectedCustomer.surname}</label></c:if>
            <c:if test="${empty selectedCustomer}"><label for="<%=FormConfig.RBI%>">Revolut Banking Index</label></c:if>
            <c:if test="${not empty selectedCustomer}"><input type="hidden" name="<%=FormConfig.RBI%>" value="${selectedCustomer.RBI}" /></c:if>
        </div>

        <div class="form-label-group col col-12">
        <jsp:include page="authorizeElements.jsp"/>
        </div>

        <s:csrfInput/>

        <button type="submit" class="btn btn-dark col col-10 offset-1">Založit produkt</button>
    </form>
</main>