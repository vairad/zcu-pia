<%@ page import="cz.zcu.pia.revoloot.web.FormConfig" %>
<%@ page import="cz.zcu.pia.revoloot.web.ServletNaming" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="cs">
<jsp:include page="../components/head.jsp"/>

<body>

<!-- Main content -->
<main class="container content text-center">
    <%--@elvariable id="move" type="cz.zcu.pia.revoloot.entities.Move"--%>
    <h1 class="display-4">Příkaz k úhradě</h1>
    <form class="row" action="<%=ServletNaming.CUSTOMER_PAYMENT%>" method="post">

        <div class="form-group col col-12">
            <label for="<%=FormConfig.MY_ACCOUNT%>">Z účtu:</label>
            <select class="form-control" id="<%=FormConfig.MY_ACCOUNT%>">
                <%--@elvariable id="accountList" type="java.util.List"--%>
                    <%--@elvariable id="account" type="cz.zcu.pia.revoloot.entities.Account"--%>
                <c:forEach items="#{accountList}" var="account">
                    <option value="${account.accountInfo.number}">
                        ${account.accountInfo} (${account.amount}${account.currency})
                    </option>
                </c:forEach>
            </select>
        </div>

        <div class="form-label-group col col-12 col-md-3">
            <jsp:include page="../components/labeledInput.jsp">
                <jsp:param name="TYPE" value="text"/>
                <jsp:param name="FIELD" value="<%=FormConfig.PRE_ACC_NUM%>"/>
                <jsp:param name="VIEW_NAME" value="Předčíslí účtu"/>
                <jsp:param name="ERROR_MESSAGE" value="Je třeba zadat město."/>
                <jsp:param name="VALUE" value="${move.destination.prepend}"/>
            </jsp:include>
        </div>
        <div class="form-label-group col col-12 col-md-6">
            <jsp:include page="../components/labeledInput.jsp">
                <jsp:param name="TYPE" value="text"/>
                <jsp:param name="FIELD" value="<%=FormConfig.ACC_NUM%>"/>
                <jsp:param name="VIEW_NAME" value="Číslo účtu"/>
                <jsp:param name="ERROR_MESSAGE" value="Je třeba zadat město."/>
                <jsp:param name="VALUE" value="${move.destination.number}"/>
            </jsp:include>
        </div>
        <div class="form-label-group col   col-12 col-md-3">
            <jsp:include page="../components/labeledInput.jsp">
                <jsp:param name="TYPE" value="text"/>
                <jsp:param name="FIELD" value="<%=FormConfig.BANK_CODE%>"/>
                <jsp:param name="VIEW_NAME" value="Kód banky"/>
                <jsp:param name="ERROR_MESSAGE" value="Je třeba zadat kód banky."/>
                <jsp:param name="VALUE" value="${move.destination.bankCode}"/>
            </jsp:include>
        </div>
        <div class="form-label-group col col-12 col-md-10">
            <jsp:include page="../components/labeledInput.jsp">
                <jsp:param name="TYPE" value="text"/>
                <jsp:param name="FIELD" value="<%=FormConfig.AMOUNT%>"/>
                <jsp:param name="VIEW_NAME" value="Částka"/>
                <jsp:param name="ERROR_MESSAGE" value="Je třeba zadat částku"/>
                <jsp:param name="VALUE" value="${move.amount}"/>
            </jsp:include>
        </div>
        <div class="form-group col col-12 col-md-2">
            <label for="<%=FormConfig.CURRENCY%>" class="sr-only">Měna:</label>
            <select class="form-control" id="<%=FormConfig.CURRENCY%>" name="<%=FormConfig.CURRENCY%>">
                <option value="CZK">
                    Kč (CZK)
                </option>
                <option value="EUR">
                    € (EUR)
                </option>
                <option>
                    £ (GBP)
                </option>
                <option disabled>
                    $ (USD)
                </option>
            </select>
        </div>
        <div class="form-label-group col col-12 col-md-3">
            <jsp:include page="../components/labeledInput.jsp">
                <jsp:param name="TYPE" value="text"/>
                <jsp:param name="FIELD" value="<%=FormConfig.SPECIFIC_SYMBOL%>"/>
                <jsp:param name="VIEW_NAME" value="Specifický symbol"/>
                <jsp:param name="ERROR_MESSAGE" value="Je třeba zadat specifický symbol"/>
                <jsp:param name="VALUE" value="${move.specificSymbol}"/>
            </jsp:include>
        </div>
        <div class="form-label-group col col-12 col-md-3">
            <jsp:include page="../components/labeledInput.jsp">
                <jsp:param name="TYPE" value="text"/>
                <jsp:param name="FIELD" value="<%=FormConfig.CONSTANT_SYMBOL%>"/>
                <jsp:param name="VIEW_NAME" value="Konstantní symbol"/>
                <jsp:param name="ERROR_MESSAGE" value="Je třeba zadat konstantní symbol"/>
                <jsp:param name="VALUE" value="${move.constantSymbol}"/>
            </jsp:include>
        </div>
        <div class="form-label-group col col-12 col-md-3">
            <jsp:include page="../components/labeledInput.jsp">
                <jsp:param name="TYPE" value="text"/>
                <jsp:param name="FIELD" value="<%=FormConfig.VARIABLE_SYMBOL%>"/>
                <jsp:param name="VIEW_NAME" value="Variabilní symbol"/>
                <jsp:param name="ERROR_MESSAGE" value="Je třeba zadat variabilní symbol"/>
                <jsp:param name="VALUE" value="${move.variableSymbol}"/>
            </jsp:include>
        </div>
        <div class="form-label-group col col-12 col-md-3">
            <jsp:include page="../components/labeledInput.jsp">
                <jsp:param name="TYPE" value="text"/>
                <jsp:param name="FIELD" value="<%=FormConfig.DUE_DATE%>"/>
                <jsp:param name="VIEW_NAME" value="Datum splatnosti"/>
                <jsp:param name="ERROR_MESSAGE" value="Je třeba zadat datum splatnosti"/>
                <jsp:param name="VALUE" value="${move.submissionDate}"/>
            </jsp:include>
        </div>
        <div class="form-label-group col col-12 col-md-6">
            <textarea id="<%=FormConfig.MESSAGE%>" class="form-control" aria-label="Zpráva pro příjemce"></textarea>
            <label for="<%=FormConfig.MESSAGE%>">Zpráva pro příjemce</label>
        </div>
        <div class="form-label-group col col-12 col-md-6">
            <textarea id="<%=FormConfig.NOTE%>" class="form-control" aria-label="Poznámka"></textarea>
            <label for="<%=FormConfig.NOTE%>">Poznámka</label>
        </div>

        <sec:csrfInput/>

        <button type="submit" value="pay" name="submit" class="btn btn-dark col col-10 col-md-4 offset-1 mb-2">Odeslat platbu</button>
        <button type="submit" value="save" name="submit" class="btn btn-dark col col-10 col-md-4 offset-1 offset-md-2">Uložit jako šablonu</button>
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
