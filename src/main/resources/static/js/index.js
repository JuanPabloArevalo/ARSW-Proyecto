/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var index = (function () {
    return{
        init() {
            if ("undefined" === sessionStorage.getItem("nombreUsuario") || null === sessionStorage.getItem("nombreUsuario")) {
                //no inicio sesion
                $("#idReportarClima").attr("class", "hide");
                $("#idMisFavoritos").attr("class", "hide");
                $("#idMiPerfil").attr("class", "hide");
                $("#idCerrarSesion").attr("class", "hide");
                $("#idIniciarSesion").attr("class", "");
                $("#idRegistrarse").attr("class", "");
            } else {
                //no inicio sesion
                $("#idReportarClima").attr("class", "");
                $("#idMisFavoritos").attr("class", "");
                $("#idMiPerfil").attr("class", "");
                $("#idCerrarSesion").attr("class", "");
                $("#idIniciarSesion").attr("class", "hide");
                $("#idRegistrarse").attr("class", "hide");
            }
        }
    };
}());