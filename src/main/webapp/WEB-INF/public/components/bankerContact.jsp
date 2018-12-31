<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="profile card mb-2">
    <div class="row card-body">
        <picture class="col col-6">
            <img src="/banker/img/${param.url}" alt="${param.name} foto" class="img-fluid"/>
        </picture>
        <div class="col col-6">
            <h4>${param.name}</h4>
            <small>
                <cite title="San Francisco, USA">${param.branchAddress}
                    <i class="glyphicon glyphicon-map-marker"></i></cite>
            </small>
            <p>
                <i class="glyphicon glyphicon-envelope"></i>${param.email}
            </p>
        </div>
    </div>
</div>