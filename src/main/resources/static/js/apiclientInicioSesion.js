/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var apiclientInicioSesion = (function(){
    
    return{
            autenticacion(nombreUsuario,contrasena){
            return $.ajax({
                url: "/sharingweather/V1/Usuarios/loggeados",
                type: "POST",
                data: '{"nombreUsuario":"'+nombreUsuario+'", "password":"'+contrasena+'"}',
                contentType: "application/json"
            });
        }
        
    };
    
});