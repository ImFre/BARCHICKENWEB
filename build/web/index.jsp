
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


        <%@include file="fragments/fragment_navbar.jsp" %>

        <div class="container text-center">

            <div>

                <h1>Bienvenido a la aplicacion de Lubricenter</h1>


            </div>

            <!-- Carrousel -->
            <div id="carouselExampleIndicators" class="carousel slide"
                 data-ride="carousel">
                <ol class="carousel-indicators">
                    <li data-target="#carouselExampleIndicators" data-slide-to="0"
                        class="active"></li>
                    <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                    <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
                </ol>
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <img class="d-block w-100" src="imgs/plate1.jpg" alt="First slide">
                    </div>
                    <div class="carousel-item">
                        <img class="d-block w-100" src="imgs/plate2.jpg" alt="Second slide">
                    </div>
                    <div class="carousel-item">
                        <img class="d-block w-100" src="imgs/plate3.jpg" alt="Third slide">
                    </div>
                </div>
                <a class="carousel-control-prev" href="#carouselExampleIndicators"
                   role="button" data-slide="prev"> <span
                        class="carousel-control-prev-icon" aria-hidden="true"></span> <span
                        class="sr-only">Previous</span>
                </a> <a class="carousel-control-next" href="#carouselExampleIndicators"
                        role="button" data-slide="next"> <span
                        class="carousel-control-next-icon" aria-hidden="true"></span> <span
                        class="sr-only">Next</span>
                </a>
            </div>
        </div>

    <!-- Footer -->
        <div >

            <footer class="py-5 bg-dark">
                <div class="container">
                    <p class="m-0 text-center text-white">Copyright &copy; Java
                        BarChickenSheting 2022</p>
                </div>
                <!-- /.container -->
            </footer>


        </div>

        <script src="js/jquery.min.js" type="text/javascript"></script>

        <script src="js/bootstrap.bundle.min.js" type="text/javascript"></script>

        <script src="js/adminlte.min.js" type="text/javascript"></script>

    </body>
</html>
