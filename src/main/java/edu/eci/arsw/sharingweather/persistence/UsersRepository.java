/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.sharingweather.persistence;

import edu.eci.arsw.sharingweather.model.Usuario;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

/**
 *
 * @author JuanArevaloMerchan y Stefany Moron
 */
public interface UsersRepository extends MongoRepository<Usuario, Integer>{


    /**
     * Metodo encargado de traer La lista de los usuarios
     * @return
     * @throws PersistenceNotFoundException 
     */
    @Query("{}")
    public ArrayList<Usuario> getAllUsers()throws PersistenceNotFoundException ;
    
   /**
     * Metodo encargado de adicionar los usuarios a la lista de usuarios
     * @param usuario
     * @return 
     * @throws PersistenceNotFoundException 
     */
    @Query("{}")
    public void insertar(Usuario usuario)throws PersistenceNotFoundException;
    

}
