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
        init(){
            $("#btnPublicar").attr("disabled", false);
        }
        registrarUsuario(){
             var error = "";
             var activarBotonRegistrar = true;
             nombre = $("#nombre").val(); 
             if(nombre === ""){
                error = error + "Nombre. ";
                activarBotonRegistrar = false;
             }
             edad = $("#edad").val(); 
              if(edad === ""){
                error = error + "Edad. ";
                activarBotonRegistrar = false;
              }
             nombreUsuario = $("#nombreUsuario").val();
             if(nombreUsuario === ""){
                error = error + "NombreUsuario. ";
                activarBotonRegistrar = false;
              }
             contrasena = $("#contrasena").val(); 
              if(contrasena === ""){
                error = error + "Contraseña. ";
                activarBotonRegistrar = false;
              }
             correoElectronico = $("#correolectronico").val();
              if(correoElectronico === ""){
                error = error + "Correo Electronico. ";
                activarBotonRegistrar = false;
              }
              if(activarBotonRegistrar===false){
                $("#mensajeFalta").text(error); 
                $("#divError").show();
              }else{
                $("#mensajeFalta").text(""); 
                $("#divError").hide();
                let promesa = apiclientRegistrarse.adicionarUsuario(nombre,edad,nombreUsuario,contrasena,correoElectronico); 
                 promesa.then(
                 function(){
                $("#btnPublicar").attr("disabled", false);
                 },
                 function(){
                $("#mensajeFalta").text(promesa.responseText); 
                $("#divError").show();
                 });
            
                }
            }
    };
}());
