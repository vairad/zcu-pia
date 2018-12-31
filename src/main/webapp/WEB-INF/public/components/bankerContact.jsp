<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="profile card mb-2">
    <div class="row card-body">
        <picture class="col col-6">
            <img src="<c:url value="/img/banker/${param.url}"/>" alt="${param.name} foto" class="img-fluid"/>
        </picture>
        <div class="col col-6">
            <h4>${param.name}</h4>
            <small>
                <cite title="${param.branchAddress}"><i class="octicon octicon-home"></i>${param.branchAddress}</cite>
            </small>
            <p>
                <i class="octicon octicon-mail"></i>${param.email}
            </p>
        </div>
    </div>
</div>