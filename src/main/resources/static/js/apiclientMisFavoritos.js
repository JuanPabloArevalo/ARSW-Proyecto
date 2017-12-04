/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var apiclientMisFavoritos = (function(){
    return{
       
       adicionarMisFavoritos(nombreUsuario, numero, nombre){
            return $.ajax({
                url: "/sharingweather/V1/Favoritos",
                type: "POST",
                data: '{"nombreUsuario":"'+ nombreUsuario +'",{"numero":'+ numero +', "nombre":"'+ nombre +'"}}',
                contentType: "application/json"
            });
        }
      };
    
}());