/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.sharingweather.model;

/**
 *
 * @author JuanArevaloMerchan
 */
public class Ubicacion {
    private double latitud;
    private double longitud;

    
    /**
     * @return the latitud
     */
    public double getLatitud() {
        return latitud;
    }

    /**
     * @param latitud the latitud to set
     */
    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    /**
     * @return the longitud
     */
    public double getLongitud() {
        return longitud;
    }

    /**
     * @param longitud the longitud to set
     */
    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }
    
    /**
     * MEtodo encargado de calcular la distancia entre dos ubucaciones
     * @param ubicacionF
     * @return 
     */
    public double distanciaEntreUbicaciones(Ubicacion ubicacionF){        
        double radioTierra = 6371;//en kilómetros  
        double dLat = Math.toRadians(ubicacionF.getLatitud() - getLatitud());  
        double dLng = Math.toRadians(ubicacionF.getLongitud() - getLongitud());  
        double sindLat = Math.sin(dLat / 2);  
        double sindLng = Math.sin(dLng / 2);  
        double va1 = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)  
                * Math.cos(Math.toRadians(getLatitud())) * Math.cos(Math.toRadians(ubicacionF.getLatitud()));  
        double va2 = 2 * Math.atan2(Math.sqrt(va1), Math.sqrt(1 - va1));  
        double distancia = radioTierra * va2;  
        return distancia;
    }

    
    
}
