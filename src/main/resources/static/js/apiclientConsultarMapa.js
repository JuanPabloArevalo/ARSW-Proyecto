/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var apiclientConsultarMapa=(function(){
	return {
            getAllReportesPublicados(callback){
                return $.get("/sharingweather/V1/reporteClima/publicados",callback); 
            }
	};	

}());

