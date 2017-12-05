/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/* global apiclientMisFavoritos */

var gFavoritos = (function(){
    
    var numero = 0;
    var nombre = "";
    var nombreUsuario = "";

    function inicializarElementos(){
        $(".filas").remove("tr");
    }

    function adicionarFila(item){
        var markup = "<tr class=\"filas\"><td>" + item.numero + "</td><td>" + item.nombre + "</td><td><button type=\"button\" class=\"btn btn-info\" onclick=\"gFavoritos.eliminarFavoritos('"+item.numero+"','"+ item.nombre +"')\">Borrar</button></td></tr>";
        $("table tbody").append(markup);
    }
    
    return {
        adicionarfavoritos(){
            var numero = $( "#localidades" ).val();
            var nombre = $( "#localidades option:selected" ).text();
            var nombreUsuario = sessionStorage.getItem("nombreUsuario");
            var promesa = apiclientMisFavoritos.adicionarMisFavoritos(numero, nombre, nombreUsuario); 
            promesa.then(
                function () {   
                    gFavoritos.cargarFavoritos();
                    
                },
                function (dato) {
                    alert(dato.responseJSON.message);
                }
            );
        },
        cargarFavoritos(){
            var nombreUsuario = sessionStorage.getItem("nombreUsuario");
            var promesaConsulta = apiclientMisFavoritos.getFavoritos(nombreUsuario);
            promesaConsulta.then(
                function (datos) {  
                    inicializarElementos();
                    datos.map(adicionarFila);
                },
                function (dato) {
                    alert(dato.responseJSON.message);
                }
            );
        },
        eliminarFavoritos(numero, nombre){
           var nombreUsuario = sessionStorage.getItem("nombreUsuario");
           var promesa = apiclientMisFavoritos.eliminarMisFavoritos(numero, nombre, nombreUsuario); 
            promesa.then(
                function () {   
                    gFavoritos.cargarFavoritos();  
                },
                function (dato) {
                    alert(dato.responseJSON.message);
                }
            );
           
        }
    };
    
}());