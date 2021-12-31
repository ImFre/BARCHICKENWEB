
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>JSP Page</title>

        <style>

            body{
                background-image: url(imgs/imagen.png);



            }

        </style>

        <script>


        </script>



        <script src="https://www.google.com/recaptcha/api.js"></script>

        <script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
        <script src="https://apis.google.com/js/platform.js" async defer></script>
        <meta name="google-signin-scope" content="profile email">
        <meta name="google-signin-client_id" content="901092594047-2ks1ppeq4opdvpc9h3eq0980ins7vb47.apps.googleusercontent.com">

        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&amp;display=fallback">

        <script src="js/fontawesome.all.min.js" type="text/javascript"></script>

        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>

        <link href="css/adminlte.min.css" rel="stylesheet" type="text/css"/>
    </head>
    <body class="login-page" style="min-height: 466px">

        <!--.login-box -->
        <div class="login-box">

            <!-- .cardlogin-logo -->
            <div class="card card-outline card-primary">

                <!-- .HEADER -->
                <div class="card-header text-center">

                    <a class="h1">
                        <b>Polleria</b>
                        SHETING

                    </a>  

                </div>
                <!-- /.HEADER -->



                <!-- .card-body -->
                <div class="card-body">

                    <!-- .Form -->

                    <form action="srvUsuario?accion=verificar"  onsubmit="return miFuncion(this)" method="POST" class="mt-3" >
                        <!--    <input type="hidden" name="accion" value="verificar" /> -->

                        <div class="input-group mb-3"> 

                            <input type="text" name="txtUsu" maxlength="23" class="form-control" placeholder="UserName">

                            <div class="input-group-append">

                                <div class="input-group-text">
                                    <span class="fas fa-envelope">



                                    </span>


                                </div>


                            </div>


                        </div>
                        <div class="input-group mb-3"> 

                            <input type="password" name="txtPass" maxlength="9" class="form-control" placeholder="Password">

                            <div class="input-group-append">

                                <div class="input-group-text">
                                    <span class="fas fa-lock">



                                    </span>


                                </div>


                            </div>


                        </div>

                        <div class="row">

                            <div class="col-12">

                                <!--<div class="icheck-primary">
                                    <input type="checkbox" id="remenber">
                                    <label for="remenber">
                                    
                                        "Recuerdame Contraseña"

                                    </label>


                                </div> -->

                                <div class="g-recaptcha"  data-theme="white" data-sitekey="6Le8rc0UAAAAAFf7UUabjFs7uwCPs8GuVoUePpsy" data-callback="enabledSubmit"></div>

                            </div>

                        </div>

                        <div class="col-8 mr-5 ml-5 mt-3">




                            <button type="submit" name="verificar"  value="Verificar" class="btn btn-primary btn-block">Ingresar</button>


                        </div>
                    </form>

                    <!-- .Form -->

                    <!-- .social-auth-links -->


                    <!--  <div class="g-signin2" data-onsuccess="onSignIn" >
                      </div>-->

                    <div class="social-auth-links text-center mt-2 mb-2">


                        <a href="#" class="btn btn-block btn-danger">
                            <i class="fas fa-info"></i> Mensaje:
                            ${msje}
                        </a>

                        <p class="mb-1">
                            <a href="forgot-password.html">Olvidé mi contraseña...</a>
                        </p>





                    </div>
                    <!-- /.card-body -->
                </div>
                <!-- /. cardlogin-logo -->


            </div>
            <!-- /.login-box -->






            <script src="js/jquery.min.js" type="text/javascript"></script>

            <script src="js/bootstrap.bundle.min.js" type="text/javascript"></script>

            <script src="js/adminlte.min.js" type="text/javascript"></script>

            <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>

            <script>

            function miFuncion(a) {

                var response = grecaptcha.getResponse();

                if (response.length == 0) {

                    Swal.fire({

                        title: 'Captcha no verificado!',
                        icon: 'warning',
                        confirmButtonText: 'Volver',
                        footer: '<span class="Red">¿Acaso Eres un robot?</span>',
                        width: '35%',
                        allowOutsideClick: false,
                        allowEscapeKey: false,
                        allowEnterKey: false,
                        stopkeydownPropagation: false
                    });

                    return false;
                    event.preventDefault();
                } else {

                    return true;
                }
            }


            </script>

    </body>
</html>
