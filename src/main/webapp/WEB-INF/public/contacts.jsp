<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="cs">
<jsp:include page="../components/head.jsp"/>
<body>

<!-- Main content -->
<main class="container content text-center">

    <h1 class="first-element display-3">Revolution loot</h1>

    <p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Sed vel lectus. Donec odio tempus molestie, porttitor
        ut, iaculis quis, sem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam,
        nisi ut aliquid ex ea commodi consequatur? Sed convallis magna eu sem. Nulla non lectus sed nisl molestie
        malesuada. Maecenas lorem. Nullam dapibus fermentum ipsum. Etiam commodo dui eget wisi. Sed vel lectus. Donec
        odio tempus molestie, porttitor ut, iaculis quis, sem. Etiam quis quam.</p>

    <h2>Naši bankéři</h2>

    <div class="row">
        <div class="col-xs-12 col-sm-6 col-md-6">
            <div class="profile card mb-2">
                <div class="row card-body">
                    <img src="http://placehold.it/100x100" alt="Thief Peter foto" class="col col-6"/>
                    <div class="col col-6">
                        <h4> Thief Peter</h4>
                        <small>
                            <cite title="San Francisco, USA">San Francisco, USA <i
                                    class="glyphicon glyphicon-map-marker"></i></cite>
                        </small>
                        <p>
                            <i class="glyphicon glyphicon-envelope"></i>thief@revoloot.com
                        </p>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-xs-12 col-sm-6 col-md-6">
            <div class="profile card mb-2">
                <div class="row card-body">
                    <img src="http://placehold.it/100x100" alt="Thief Peter foto" class="col col-6"/>
                    <div class="col col-6">
                        <h4> Thief Peter</h4>
                        <small>
                            <cite title="San Francisco, USA">San Francisco, USA <i
                                    class="glyphicon glyphicon-map-marker"></i></cite>
                        </small>
                        <p>
                            <i class="glyphicon glyphicon-envelope"></i>thief@revoloot.com
                        </p>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-xs-12 col-sm-6 col-md-6">
            <div class="profile card mb-2">
                <div class="row card-body">
                    <img src="http://placehold.it/100x100" alt="Thief Peter foto" class="col col-6"/>
                    <div class="col col-6">
                        <h4> Thief Peter</h4>
                        <small>
                            <cite title="San Francisco, USA">San Francisco, USA <i
                                    class="glyphicon glyphicon-map-marker"></i></cite>
                        </small>
                        <p>
                            <i class="glyphicon glyphicon-envelope"></i>thief@revoloot.com
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </div>

</main>
<!-- Main content -->

<jsp:include page="components/navigation.jsp"/>
<jsp:include page="../components/footer.jsp">
    <jsp:param name="format" value="footer-std footer"/>
</jsp:include>
<jsp:include page="../components/scripts.jsp"/>
</body>
</html>