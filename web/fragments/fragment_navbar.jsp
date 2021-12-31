
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>JSP Principal</title>


        <script src="js/fontawesome.all.min.js" type="text/javascript"></script>

        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>

    </head>
    <body>

        <!-- Navigation -->
        <div>

            <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
                <div class="container">
                    <a class="navbar-brand" href="#">PolleriaSheting</a>

                    <form class="form-inline my-2 my-lg-0" method="post" action="#">
                        <input class="form-control mr-sm-2" type="search"
                               placeholder="Buscar" aria-label="Search" name="nombre"
                               autocomplete="off">
                        <!--  <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Buscar</button>-->
                    </form>


                    <button class="navbar-toggler" type="button" data-toggle="collapse"
                            data-target="#navbarResponsive" aria-controls="navbarResponsive"
                            aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>

                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav me-auto mb-2 mb-lg-0">

                            <li class="nav-item"><a class="nav-link active"
                                                    aria-current="page" href="#">Inicio</a>
                            </li>
                            <li class="nav-item"><a class="nav-link"
                                                    href="#">Sobre Nosotros</a>
                            </li>

                            <li class="nav-item">
                                <a class="nav-link 
                                   disabled">Contactanos
                                </a>
                            </li>
                        </ul>
                    </div>


                    <div class="collapse navbar-collapse" id="navbarResponsive">
                        <ul class="navbar-nav ml-auto">
                            <li class="nav-item"><a class="nav-link" href="carrito.html">Carrito</a></li>
                            <li class="nav-item"><a class="nav-link" th:href="@{/login}">Login</a></li>
                        </ul>
                    </div>
                </div>
            </nav>


        </div>

        <script src="js/jquery.min.js" type="text/javascript"></script>

        <script src="js/bootstrap.bundle.min.js" type="text/javascript"></script>

        <script src="js/adminlte.min.js" type="text/javascript"></script>

    </body>
</html>
