/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var gestionarFavoritos = (function(){
    
    var numero;
    var nombre;
    
    return {
        
          addMisFavoritos(){
              numero = $( "#localidades" ).val();
              nombre = $( "#localidades option:selected" ).text();
              
              let promesa = apiclientMisFavoritos.adicionarMisFavoritos(sessionStorage.getItem("nombreUsuario"), numero, nombre); 
                 promesa.then(
                 function(){
                     //window.location.href = "iniciarSesion.html";
                      var filasTabla = "<tr class=\"filas\"><td>" + numero + "</td><td>" + nombre + "</td><td><button type=\"button\" class=\"btn btn-info\" onclick=\"app.actualizarPlano('"+numero+"')\">Borrar</button></td></tr>";
                      $("table tbody").append(filasTabla);
                      alerta("primero");
                 },
                 function(){
                     alerta("segundo");
                 });
          }
        
        
    };
    
}());