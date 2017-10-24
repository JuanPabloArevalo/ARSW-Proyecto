/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var iniciarSesion = (function(){
    
    var nombreUsuario;
    var password;
    
    return {
           iniciar(){
             var error = "";
             var activarBotonRegistrar = true;
             nombreUsuario = $("#nombreUsuario").val();
             if(nombreUsuario === ""){
                error = error + "NombreUsuario.";
                activarBotonRegistrar = false;
             }
             password = $("#contrasena").val();
              if(correoElectronico === ""){
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
                    sessionStorage.setItem("usuario", usuario.nombreUsuario);
                    sessionStorage.setItem("contrasena", usuario.password);
                    //alert("Bienvenido, " + data.name);
                    window.location.href = "reportarClima.html";
                }).fail(function(errorThrown){
                console.log(errorThrown);
                alert("Usted no se ha autenticado correctamente.");
                });
            
                }
               
           }
        
    };
    
});