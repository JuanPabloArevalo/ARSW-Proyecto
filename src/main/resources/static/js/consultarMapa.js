/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* global google, Stomp */
var map;
var consultarMapa = (function () {
    return{
        init(){
            consultarMapa.connectAndSubscribe();
            consultarMapa.myMap();
        },
        myMap() {
            var mapOptions = {
                center: new google.maps.LatLng(4.6537726, -74.0660075),
                zoom: 10,
                mapTypeId: google.maps.MapTypeId.ROADMAP
            };
            map = new google.maps.Map(document.getElementById("idMap"), mapOptions);

            // Create the search box and link it to the UI element.
            var input = document.getElementById("idTxtBusqueda");
            var searchBox = new google.maps.places.SearchBox(input);
            map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);
            map.addListener("bounds_changed", function () {
                searchBox.setBounds(map.getBounds());
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
                        map: map,
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
                map.fitBounds(bounds);
            });
        },
        dibujarReporteClima() {
            var image = "/image/puntolluvia.png";
            var beachMarker = new google.maps.Marker({
                position: {lat: 4.753730, lng: -74.033273},
                map: map,
                icon: image
            });
            var cityCircle = new google.maps.Circle({
                strokeColor: '#FF0000',
                strokeOpacity: 0.8,
                strokeWeight: 2,
                fillColor: '#FF0000',
                fillOpacity: 0.35,
                map: map,
                center: {lat: 4.6537726, lng: -74.0660075},
                radius: 1000
            });
        },
        connectAndSubscribe(){
            console.info('Connecting to WS...');
            var socket = new SockJS("/stompendpoint");
            stompClient = Stomp.over(socket);
            //subscribe to /topic/reporteClima
            stompClient.connect({}, function (frame) {
                console.log('Connected: ' + frame);
                stompClient.subscribe("/topic/reporteClima", function (eventbody) {
                    console.log(eventbody.body);
                    dibujarReporteClima(JSON.parse(eventbody.body));
                });
            });
        }
    };
}());