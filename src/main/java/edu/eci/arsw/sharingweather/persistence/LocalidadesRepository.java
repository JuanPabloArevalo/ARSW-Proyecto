/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.sharingweather.persistence;

import edu.eci.arsw.sharingweather.model.LocalidadFavorita;
import edu.eci.arsw.sharingweather.model.Usuario;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author JuanArevaloMerchan
 */
public interface LocalidadesRepository {
        
    /**
     * Metodo encargado de adicionar una localidad favorita
     * @param usaurio
     * @param lf 
     * @throws edu.eci.arsw.sharingweather.persistence.PersistenceNotFoundException 
     */
    public void addRegionFavorita(Usuario usaurio, LocalidadFavorita lf) throws PersistenceNotFoundException;
    
        /**
     * Metodo encargado de traer La lista de los usuarios
     * @param usuario
     * @return 
     * @throws edu.eci.arsw.sharingweather.persistence.PersistenceNotFoundException 
     */
    public CopyOnWriteArrayList<LocalidadFavorita> getRegionesFavoritas(Usuario usuario)throws PersistenceNotFoundException ;
    
    
}
