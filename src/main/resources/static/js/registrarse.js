/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* global apiclientRegistrarse */

var registrarse = (function(){
    
    var nombre;
    var edad;
    var nombreUsuario;
    var contrasena;
    var correoElectronico;
    var usuarios=[];
    
    return {
        registrarUsuario(){
             nombre = $("#nombre").val(); 
             edad = $("#edad").val(); 
             nombreUsuario = $("#nombreUsuario").val(); 
             contrasena = $("#contrasena").val(); 
             correoElectronico = $("#correolectronico").val(); 
             
             let promesa = apiclientRegistrarse.adicionarUsuario(nombre,edad,nombreUsuario,contrasena,correoElectronico, function(lbp){
                 
             }); 
            promesa.then(function(apiclientRegistrarse.adicionarUsuario(nombre,edad,nombreUsuario,contrasena,correoElectronico)){},function(){alert("El usuario ya existe: "+promesa.responseText);});
             
        }
    };
}());
