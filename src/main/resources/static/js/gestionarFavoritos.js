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
              nombre = $( "#myselect option:selected" ).text();
              var markup = "<tr class=\"filas\"><td>" + numero + "</td><td>" + nombre + "</td><td><button type=\"button\" class=\"btn btn-info\" onclick=\"app.actualizarPlano('"+numero+"')\">Borrar</button></td></tr>";
              $("table tbody").append(markup);
              
          }
        
        
    };
    
}());