/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.sharingweather.persistence;

import edu.eci.arsw.sharingweather.model.Usuario;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author JuanArevaloMerchan y Stefany Moron
 */
public interface UsersRepository {


    /**
     * Metodo encargado de traer La lista de los usuarios
     * @return
     * @throws PersistenceNotFoundException 
     */
    public CopyOnWriteArrayList<Usuario> getUsuarios()throws PersistenceNotFoundException ;
    
   /**
     * Metodo encargado de adicionar los usuarios a la lista de usuarios
     * @param usuario
     * @throws PersistenceNotFoundException 
     */
    public  void addUsuarios(Usuario usuario)throws PersistenceNotFoundException;

}
