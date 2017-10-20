/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var apiclientReportarClima=(function(){
	return {
            addNewSharingweather:function(longitud,latitud,clima,minutos,usuario){
                   //, fechaHora:'+new Date()+'
                return $.ajax({
                    url: "/sharingweather/V1/reporteClima",
                    type: 'POST',
                    data: '{"tiempo":"'+minutos+'","ubicacion":{longitud:"'+longitud+'",latitud:"'+latitud+'"}, "usuario":{nombre:"", edad : 0, nombreUsuario:'+usuario+',password:"", correo:""}, clima:'+clima+'}',
                    contentType: "application/json"
                });
            }
	};	

})();

