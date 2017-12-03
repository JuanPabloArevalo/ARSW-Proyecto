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
public interface LocalidadesBogota {
       
    /**
     * Metodo encargado de traer la localidad a la que pertenece el reporte, si traer el número 22 es porque 
     * no se ha encontrado la localidad
     * @param ubicacion
     * @return 
     */
    public Localidad getLocalidad(Ubicacion ubicacion);

}
