/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/* global apiclientInicioSesion */

var iniciarSesion = (function(){
    
    var nombreUsuario;
    var password;
    
    return {
            init(){
                alert("sesion");
            },
            iniciar(){
                alert("Entro");
                console.info("entro");
             var error = "";
             var activarBotonRegistrar = true;
             
             nombreUsuario = $("#nombreInicioSesion").val();
             if(nombreUsuario === ""){
                error = error + "NombreUsuario.";
                activarBotonRegistrar = false;
             }
             password = $("#contraseñaInicioSesion").val();
              if(password === ""){
                error = error + "Contraseña.";
                activarBotonRegistrar = false;
              }
              if(activarBotonRegistrar===false){
                $("#mensajeFalta").text(error); 
                $("#divError").show();
                
              }else{
                $("#mensajeFalta").text(""); 
                $("#divError").hide();
                $.when(apiclientInicioSesion.autenticacion(nombreUsuario,password )).done(function (usuario){
                    sessionStorage.setItem("nombreUsuario", usuario.nombreUsuario);
                    sessionStorage.setItem("password", usuario.password);
                    //alert("Bienvenido, " + data.name);
                    window.location.href = "reportarClima.html";
                }).fail(function(errorThrown){
                console.log(errorThrown);
                alert("Usted no se ha autenticado correctamente.");
                });
            
                }
            }   
    };
    
}());