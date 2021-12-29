$(document).ready(function () {


    $("tr #deleteUser").click(function (e) {

        e.preventDefault();
        var cod = $(this).parent().find('#codigo').val();

        swal({
            title: "¿Esta seguro de eliminar?",
            text: "una vez eliminado debera agregar de nuevo",
            type: "warning",
            showCancelButton: true,
            confirmButtonClass: "btn-danger",
            confirmButtonText: "Yes, delete it!",
            cancelButtonText: "No, cancel plx!",
            closeOnConfirm: false,
            closeOnCancel: false
        },
                function (isConfirm) {
                    if (isConfirm) {
                        
                        eliminarUsuario(cod);
                        swal("Eliminado!", "Satisfactoriamente");
                        setTimeout(function (){
                            
                            parent.location.href = "srvUsuario?accion=listarUsuarios"
                            
                        }, 2000);
                    } else {
                        swal("Cancelado", "Cancelaste la eliminación :)", "error");
                    }
                });

    });

    function eliminarUsuario(cod) {

        var url = "srvUsuario?accion=eliminarUsuario&cod=" + cod;

        console.log("Eliminado");

        $.ajax({

            type: 'POST',
            url: url,
            async: true,
            success: function (r) {


            }





        });

    }


});

