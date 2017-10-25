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
                var promesa = apiclientInicioSesion.autenticacion(nombreUsuario,password , function(usuario){ 
                    sessionStorage.setItem("nombreUsuario", usuario.nombreUsuario);
                    sessionStorage.setItem("nombre", usuario.nombre);
                    alert("Bienvenido, " + usuario.nombreUsuario + usuario.password);
                    window.location.href = "reportarClima.html";
                }); 
            promesa.then(function(){},function(){
                alert("Ha el siguiente error: "+promesa.responseText)
                $("#mensajeFalta").text(promesa.responseText); 
                $("#divError").show();
                });
             }
         
         }
    };
    
}());