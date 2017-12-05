/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var apiclientMisFavoritos = (function(){
    return{
       
       adicionarMisFavoritos(numero, nombre, nombreUsuario){
            return $.ajax({
                url: "/sharingweather/V1/regionesFavoritas/"+nombreUsuario,
                type: "POST",
                data: '{"numero":'+ numero +', "nombre":"'+ nombre +'"}',
                contentType: "application/json"
            });
        },
        getFavoritos(nombreUsuario){
            return $.get("/sharingweather/V1/regionesFavoritas/"+nombreUsuario); 
        },
        
        eliminarMisFavoritos(numero, nombre, nombreUsuario){
            return $.ajax({
                url:  "/sharingweather/V1/regionesFavoritas/"+nombreUsuario,
                type: "DELETE",
                data: '{"numero":'+numero+', "nombre":"'+nombre+'"}',
                contentType: "application/json"
            });
        }
      };
    
}());