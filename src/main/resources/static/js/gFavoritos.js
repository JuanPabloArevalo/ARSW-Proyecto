/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/* global apiclientMisFavoritos */

var gFavoritos = (function(){
    
    var numero = 0;
    var nombre = "";
    
    return {
            adicionarfavoritos(){
            
              numero = $( "#localidades" ).val();
              nombre = $( "#localidades option:selected" ).text();
              alert("esto hay"+sessionStorage.getItem("nombreUsuario"));
              
              var promesa = apiclientMisFavoritos.adicionarMisFavoritos(numero, nombre); 
                 promesa.then(
                 function(){
                     //window.location.href = "iniciarSesion.html";
                      var filasTabla = "<tr class=\"filas\"><td>" + numero + "</td><td>" + nombre + "</td><td><button type=\"button\" class=\"btn btn-info\" onclick=\"app.actualizarPlano('"+numero+"')\">Borrar</button></td></tr>";
                      $("table tbody").append(filasTabla);
                      alert("primero");
                 },
                 function(){
                     alert("segundo");
                 });
        }
    };
    
}());