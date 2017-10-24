/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* global google, Stomp, apiclientConsultarMapa, SockJS */
var mapaGoogle;

function dibujarPuntos(reporte){
    var color ="#FF0000";
    if("sol"===reporte.clima){
        color ="#F1F417";
    }
    else if("agua"===reporte.clima){
        color ="#1724F4";
    }
    else{
        color ="#7F8088";
    }
    var marca = new google.maps.Circle({
                strokeColor: color,
                strokeOpacity: 0.2,
                strokeWeight: 2,
                fillColor: color,
                fillOpacity: 0.15,
                map: mapaGoogle,
                center: {lat: reporte.ubicacion.latitud, lng: reporte.ubicacion.longitud},
                radius: 400
    });
}

var consultarMapa = (function () {
    return{
        init(){
            consultarMapa.connectAndSubscribe();
            consultarMapa.myMap();
            consultarMapa.iniciarMapaClimaPublicado();
        },
        myMap() {
            var mapOptions = {
                center: new google.maps.LatLng(4.6537726, -74.0660075),
                zoom: 12,
                mapTypeId: google.maps.MapTypeId.ROADMAP,
		draggable: true
            };
            mapaGoogle = new google.maps.Map(document.getElementById("idMap"), mapOptions);

            // Create the search box and link it to the UI element.
            var input = document.getElementById("idTxtBusqueda");
            var searchBox = new google.maps.places.SearchBox(input);
            mapaGoogle.controls[google.maps.ControlPosition.TOP_LEFT].push(input);
            mapaGoogle.addListener("bounds_changed", function () {
                searchBox.setBounds(mapaGoogle.getBounds());
            });

            var markers = [];
            // Listen for the event fired when the user selects a prediction and retrieve
            // more details for that place.
            searchBox.addListener("places_changed", function () {
                var places = searchBox.getPlaces();

                if (places.length === 0) {
                    return;
                }

                // Clear out the old markers.
                markers.forEach(function (marker) {
                    marker.setMap(null);
                });
                markers = [];

                // For each place, get the icon, name and location.
                var bounds = new google.maps.LatLngBounds();
                places.forEach(function (place) {
                    if (!place.geometry) {
                        return;
                    }
                    var icon = {
                        url: place.icon,
                        size: new google.maps.Size(71, 71),
                        origin: new google.maps.Point(0, 0),
                        anchor: new google.maps.Point(17, 34),
                        scaledSize: new google.maps.Size(25, 25)
                    };

                    // Create a marker for each place.
                    markers.push(new google.maps.Marker({
                        map: mapaGoogle,
                        icon: icon,
                        title: place.name,
                        position: place.geometry.location
                    }));

                    if (place.geometry.viewport) {
                        // Only geocodes have viewport.
                        bounds.union(place.geometry.viewport);
                    } else {
                        bounds.extend(place.geometry.location);
                    }
                });
                mapaGoogle.fitBounds(bounds);
            });
        },
        connectAndSubscribe(){
            var socket = new SockJS("/stompendpoint");
            var stompClient = Stomp.over(socket);
            //subscribe to /topic/reporteClima
            stompClient.connect({}, function () {
                stompClient.subscribe("/topic/reporteClima", function (eventbody) {
                    var arreglo = JSON.parse(eventbody.body);
                    arreglo.map(dibujarPuntos);
                });
            });
        },
        getMapa(){
            return mapaGoogle;
        },
        iniciarMapaClimaPublicado(){
            apiclientConsultarMapa.getAllReportesPublicados( function(lbp){ 
                    lbp.map(dibujarPuntos);
                } 
            ); 
        }
    };
}());

