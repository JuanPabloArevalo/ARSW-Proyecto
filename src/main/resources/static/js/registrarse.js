/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var registrarse = (function(){
    
    var nombre;
    var edad;
    var nombreUsuario;
    var contrasena;
    var correoElectronico;
    
    return {
        regitrarUsuario:function(){
             nombre = $('#nombre').val(); 
             edad = $('#edad').val(); 
             nombreUsuario = $('#nombreUsuario').val(); 
             contrasena = $('#contrasena').val(); 
             correoElectronico = $('#correoElectronico').val(); 
             
        },
        existeUsuario: function(){
           var usurios = $.get("/sharingweather/V1/Usuarios");
           alert(usuarios);
        
        } 
    };
}());

function addUsuario(nombre, edad, nombreUsuario, contrasena, correoElectronico){
        return $.ajax({
            url: "/sharingweather/V1",
            type: 'POST',
            data: '{"nombre":"'+nombre+'","edad":"'+edad+'", "nombreUsuario":'+nombreUsuario+', "password":"'+contrasena+'","correo":"'+correoElectronico+'"}',
            contentType: "application/json"
        });
}


