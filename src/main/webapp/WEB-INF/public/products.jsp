<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="cs">
<jsp:include page="../components/head.jsp"/>
<body>

<!-- Main content -->
<main class="container content text-center">

    <h1 class="display-3">Naše produkty</h1>

    <!-- MAIN PRODUCT -->
    <div class="row justify-content-md-center">
        <div id="account" class="product main-product col col-12 col-md-11 mb-3 p-5 text-center">
            <h2 class="display-4 font-weight-normal">Běžný účet</h2>
            <p class="lead font-weight-normal">Standardní běžný účet pro každodenní potřeby bez poplatků.</p>
            <button type="button" class="btn btn-dark" data-toggle="modal" data-target="#account-conditions">
                Podmínky
            </button>
            <a class="btn btn-dark" href="login.html">Sjednat</a>

            <div class="modal fade" id="account-conditions" tabindex="-1" role="dialog" aria-labelledby="account-modal"
                 aria-hidden="true">
                <div class="modal-dialog modal-lg modal-dialog-centered" role="document" id="account-modal">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">Běžný účet</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <p>...Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Sed vel lectus. Donec odio
                                tempus molestie, porttitor ut, iaculis quis, sem. Ut enim ad minima veniam, quis nostrum
                                exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi
                                consequatur? Sed convallis magna eu sem. Nulla non lectus sed nisl molestie malesuada.
                                Maecenas lorem. Nullam dapibus fermentum ipsum. Etiam commodo dui eget wisi. Sed vel
                                lectus. Donec odio tempus molestie, porttitor ut, iaculis quis, sem. Etiam quis
                                quam....</p>
                            <p> ...Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Sed vel lectus. Donec odio
                                tempus molestie, porttitor ut, iaculis quis, sem. Ut enim ad minima veniam, quis nostrum
                                exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi
                                consequatur? Sed convallis magna eu sem. Nulla non lectus sed nisl molestie malesuada.
                                Maecenas lorem. Nullam dapibus fermentum ipsum. Etiam commodo dui eget wisi. Sed vel
                                lectus. Donec odio tempus molestie, porttitor ut, iaculis quis, sem. Etiam quis
                                quam....</p>
                            <p>...Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Sed vel lectus. Donec odio
                                tempus molestie, porttitor ut, iaculis quis, sem. Ut enim ad minima veniam, quis nostrum
                                exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi
                                consequatur? Sed convallis magna eu sem. Nulla non lectus sed nisl molestie malesuada.
                                Maecenas lorem. Nullam dapibus fermentum ipsum. Etiam commodo dui eget wisi. Sed vel
                                lectus. Donec odio tempus molestie, porttitor ut, iaculis quis, sem. Etiam quis
                                quam....</p>
                            <p>...Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Sed vel lectus. Donec odio
                                tempus molestie, porttitor ut, iaculis quis, sem. Ut enim ad minima veniam, quis nostrum
                                exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi
                                consequatur? Sed convallis magna eu sem. Nulla non lectus sed nisl molestie malesuada.
                                Maecenas lorem. Nullam dapibus fermentum ipsum. Etiam commodo dui eget wisi. Sed vel
                                lectus. Donec odio tempus molestie, porttitor ut, iaculis quis, sem. Etiam quis
                                quam....</p>

                            <table class="table table-striped">
                                <thead class="thead-dark">
                                <tr>
                                    <th>Služba</th>
                                    <th>Cena</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td>Výpisy</td>
                                    <td>0&nbsp;Kč</td>
                                </tr>
                                <tr>
                                    <td>Příkazy k úhradě</td>
                                    <td>0&nbsp;Kč</td>
                                </tr>

                                <tr>
                                    <td>Výběry z bankomatů</td>
                                    <td>0&nbsp;Kč</td>
                                </tr>

                                <tr>
                                    <td>Inkaso</td>
                                    <td>0&nbsp;Kč</td>
                                </tr>
                                <tr>
                                    <td>Boo</td>
                                    <td>0&nbsp;Kč</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Zavřít</button>
                            <a class="btn btn-dark" href="login.html">Sjednat</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- OTHER PRODUCTS -->
    <div class="row justify-content-md-center">
        <div id="saving" class="product product-1 col col-12 col-md-5 mb-3 p-5 text-center">
            <h2 class="display-4 font-weight-normal">Spořák</h2>
            <p class="lead font-weight-normal">Velký úrok i na spořícím účtu 0,1%pa.</p>
            <a class="btn btn-dark" href="#">Podmínky</a>
            <a class="btn btn-dark" href="login.html">Sjednat</a>
        </div>

        <div id="business" class="product product-2 col col-12 col-md-5 offset-md-1 mb-3 p-5 text-center">
            <h2 class="display-4 font-weight-normal">Podnikatel</h2>
            <p class="lead font-weight-normal">Ideální pro začínající podnikatele.</p>
            <a class="btn btn-dark" href="#">Podmínky</a>
            <a class="btn btn-dark" href="login.html">Sjednat</a>
        </div>
    </div>

    <div class="row justify-content-md-center">

        <div class="product product-2 col col-12 col-md-5 mb-3 p-5 text-center">
            <h2 class="display-4 font-weight-normal">Lorem</h2>
            <p class="lead font-weight-normal">Velký úrok i na spořícím účtu 0,1%pa.</p>
            <a class="btn btn-dark" href="#">Podmínky</a>
            <a class="btn btn-dark" href="login.html">Sjednat</a>
        </div>

        <div class="product product-1 col col-12 col-md-5 offset-md-1 mb-3 p-5 text-center">
            <h2 class="display-4 font-weight-normal">Ipsum</h2>
            <p class="lead font-weight-normal">Ideální pro začínající podnikatele.</p>
            <a class="btn btn-dark" href="#">Podmínky</a>
            <a class="btn btn-dark" href="login.html">Sjednat</a>
        </div>

    </div>

    <div class="row justify-content-md-center">

        <div class="product product-1 col col-12 col-md-5 mb-3 p-5 text-center">
            <h2 class="display-4 font-weight-normal">Dolor</h2>
            <p class="lead font-weight-normal">Velký úrok i na spořícím účtu 0,1%pa.</p>
            <a class="btn btn-dark" href="#">Podmínky</a>
            <a class="btn btn-dark" href="login.html">Sjednat</a>
        </div>

        <div class="product product-2 col col-12 col-md-5 offset-md-1 mb-3 p-5 text-center">
            <h2 class="display-4 font-weight-normal align-self-end">Sit</h2>
            <p class="lead font-weight-normal">Ideální pro začínající podnikatele.</p>
            <a class="btn btn-dark" href="#">Podmínky</a>
            <a class="btn btn-dark" href="login.html">Sjednat</a>
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