/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var apoclientRegistrate = (function(){
    
    return{
        adicionarUsuario: function(nombre,edad,nombreUsuario,contrasena,correoElectronico){
            return $.ajax({
                url: "/sharingweather/V1/Usuarios",
                type: 'POST',
                data: '{"nombre":"'+nombre+'","edad":"'+edad+'", "nombreUsuario":'+nombreUsuario+', "password":"'+contrasena+'","correo":"'+correoElectronico+'"}',
                contentType: "application/json"
            });
            alert("Se adiciono un usuario");
        }
    };
    
}());