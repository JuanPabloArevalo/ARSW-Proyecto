
/* global apiclientReportarClima */

function inicializarElementos() {
    $("#txtTiempo").val("");
    $("#comboClima").val("");
    $("#txtLatitud").val("");
    $("#txtLongitud").val("");
}

var reportarClima = (function () {
    var longitud;
    var latitud;
    var clima;
    var minutosClima;
    var usuario = "";
    return{
        init() {
            if ("undefined" === sessionStorage.getItem("nombreUsuario") || null === sessionStorage.getItem("nombreUsuario")) {
                alert("Para esta función, debe iniciar sesión primero.");
                window.location.href = "index.html";
            }
            else{
                reportarClima.obtenerUbicacion();
            }
        },
        obtenerUbicacion() {
            if (navigator.geolocation) {
                navigator.geolocation.getCurrentPosition(function (objPosition) {
                    longitud = objPosition.coords.longitude;
                    latitud = objPosition.coords.latitude;
                    $("#txtLongitud").val(longitud);
                    $("#txtLatitud").val(latitud);
                    $("#btnPublicar").attr("disabled", false);
                },
                        function (objPositionError) {
                            longitud = 0;
                            latitud = 0;
                            $("#txtLongitud").val(longitud);
                            $("#txtLatitud").val(latitud);
                            $("#btnPublicar").attr("disabled", true);
                            switch (objPositionError.code) {
                                case objPositionError.PERMISSION_DENIED:
                                    alert("No se ha permitido el acceso a la posición del usuario.");
                                    break;
                                case objPositionError.POSITION_UNAVAILABLE:
                                    alert("No se ha podido acceder a la información de su posición.");
                                    break;
                                case objPositionError.TIMEOUT:
                                    alert("El servicio ha tardado demasiado tiempo en responder.");
                                    break;
                                default:
                                    alert("Error desconocido.");
                            }
                        },
                        {
                            maximumAge: 0,
                            timeout: 15000
                        }
                );
            } else {
                alert("Su navegador no soporta la API de geolocalización.");
            }
        },
        addNewReporteClima() {
            if ("undefined" === sessionStorage.getItem("nombreUsuario") || null === sessionStorage.getItem("nombreUsuario")) {
                alert("Para esta función, debe iniciar sesión primero.");
                window.location.href = "index.html";
            }
            var falta = "";
            var puedeAdicionar = true;
            //Validar longitud
            longitud = $("#txtLongitud").val();
            if ("0" === longitud || "" === longitud) {
                falta = falta + "Longitud. ";
                puedeAdicionar = false;
            }
            //Validar latitud
            latitud = $("#txtLatitud").val();
            if ("0" === latitud || "" === latitud) {
                falta = falta + "Latitud. ";
                puedeAdicionar = false;
            }
            //Validar Clima
            clima = $("#comboClima").val();
            if (null === clima) {
                falta = falta + "Clima. ";
                puedeAdicionar = false;
            }
            //Validar Minutos
            minutosClima = $("#txtTiempo").val();
            if ("" === minutosClima) {
                falta = falta + "Minutos. ";
                puedeAdicionar = false;
            }

            usuario = sessionStorage.getItem("nombreUsuario");

            if (puedeAdicionar === false) {
                $("#mensajeFalta").text(falta);
                $("#divError").show();
            } else {
                $("#mensajeFalta").text("");
                $("#divError").hide();
                var promesaDelete = apiclientReportarClima.addNewSharingweather(longitud, latitud, clima, minutosClima, usuario);
                promesaDelete.then(
                        function () {
                            $("#myModal").modal("show");
                            $("#idTituloModal").text("Enviado!!");
                            $("#idTextoModal").text("Gracias por reportar el clima en tu zona!!");
                            inicializarElementos();
                        },
                        function () {
                            $("#myModal").modal("show");
                            $("#idTituloModal").text("UPPPS!!");
                            $("#idTextoModal").text("Algo salio mal. " + promesaDelete.responseText);
                        }
                );
            }
        }
    };
}());

