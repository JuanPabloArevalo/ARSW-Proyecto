/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var iniciarSesion = (function(){
    
    var nombreUsuario;
    var correo;
    
    return {
           iniciarS(){
             var error = "";
             var activarBotonRegistrar = true;
             nombreUsuario = $("#nombreUsuario").val();
             if(nombreUsuario === ""){
                error = error + "NombreUsuario. ";
                activarBotonRegistrar = false;
             }
             correo = $("#correolectronico").val();
              if(correoElectronico === ""){
                error = error + "Correo Electronico. ";
                activarBotonRegistrar = false;
              }
              if(activarBotonRegistrar===false){
                $("#mensajeFalta").text(error); 
                $("#divError").show();
              }
               
           }
        
    };
    
});