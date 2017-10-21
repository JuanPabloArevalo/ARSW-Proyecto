/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var registrarse = (function(){
    
    var nombre;
    var edad;
    var nombreUsuario;
    var contraseña;
    var correoElectronico;
    
    return {
        regitrarUsuario:function(){
             nombre = $('#nombre').val(); 
             edad = $('#edad').val(); 
             nombreUsuario = $('#nombreUsuario').val(); 
             contrasena = $('#contrasena').val(); 
             correoElectronico = $('#correoElectronico').val(); 
             
            
        }
        
        
    };
}());

function addUsiario(nombre, edad, nombreUsuario, contrasena, correoElectronico){
    
        return $.ajax({
            url: "/sharingweather/V1.0/",
            type: 'POST',
            data: '{"author":"'+authname+'","name":"'+bpname+'", "points":'+JSON.stringify(points)+'}',
            contentType: "application/json"
        });
}

