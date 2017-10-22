/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var apiclientRegistrarse = (function(){
    
    return{
        adicionarUsuario: function(nombre,edad,nombreUsuario,contrasena,correoElectronico){
            alert("Se adiciono un usuario");
            return $.ajax({
                url: "/sharingweather/V1/Usuarios",
                type: 'POST',
                data: '{"nombre":"'+nombre+'","edad":'+edad+', "nombreUsuario":"'+nombreUsuario+'", "password":"'+contrasena+'","correo":"'+correoElectronico+'"}',
                contentType: "application/json"
            });
            
        }
    };
    
}());