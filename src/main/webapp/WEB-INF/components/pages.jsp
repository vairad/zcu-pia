<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--@elvariable id="page" type="cz.zcu.pia.revoloot.entities.Pages"--%>

<nav aria-label="Stránkování výpisu" class="row">
    <ul class="pagination justify-content-center col col-12 col-md-6">
        <li class="page-item">
            <a class="page-link" href="<c:url value="?p=${page.page-1}&s=${page.pageSize+0}"/>" tabindex="-1">Předcházející</a>
        </li>
        <c:forEach begin="0" end="${page.pagesCount - 1}" varStatus="loop">
            <li class="page-item">
                <a class="page-link <c:if test="${page.page != loop.index}">btn-dark</c:if> "
                   href="<c:url value="?p=${loop.index}&s=${page.pageSize+0}"/>">
                        ${loop.index + 1}
                </a>
            </li>
        </c:forEach>
        <li class="page-item">
            <a class="page-link"
               href="<c:url value="?p=${page.page + 1}&s=${page.pageSize+0}"/>"
               tabindex="-1">Následující</a>
        </li>
    </ul>

    <ul class="pagination justify-content-center col col-12 col-md-6">
        <li class="page-item"><a class="page-link <c:if test="${page.pageSize == 1}">disabled</c:if>"
                                 href="<c:url value="?p=0&s=1"/>">1</a></li>
        <li class="page-item"><a class="page-link <c:if test="${page.pageSize == 5}">disabled</c:if>"
                                 href="<c:url value="?p=0&s=5"/>">5</a></li>
        <li class="page-item"><a class="page-link <c:if test="${page.pageSize == 10}">disabled</c:if>"
                                 href="<c:url value="?p=0&s=10"/>">10</a></li>
        <li class="page-item"><a class="page-link <c:if test="${page.pageSize == 20}">disabled</c:if>"
                                 href="<c:url value="?p=0&s=20"/>">20</a></li>
    </ul>
</nav>

