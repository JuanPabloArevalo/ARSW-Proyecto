var reportarClima = (function(){
    var longitud;
    var latitud;
    
    return{
        obtenerUbicacion:function(){
            if (navigator.geolocation){
                navigator.geolocation.getCurrentPosition(function(objPosition)
		{
			longitud = objPosition.coords.longitude;
			latitud = objPosition.coords.latitude;
                        $('#txtLongitud').val(longitud);
                        $('#txtLatitud').val(latitud);
		}, function(objPositionError)
		{
                    longitud = 0;
                    latitud = 0;
                    $('#txtLongitud').val(longitud);
                    $('#txtLatitud').val(latitud);
                    switch (objPositionError.code)
                    {
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
		}, {
			maximumAge: 75000,
			timeout: 15000
		});
            }
            else{
                alert("Su navegador no soporta la API de geolocalización.");
            }
        }
    };
})();