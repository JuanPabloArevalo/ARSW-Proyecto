/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.sharingweather.persistence.imp;

import edu.eci.arsw.sharingweather.model.LocalidadFavorita;
import edu.eci.arsw.sharingweather.model.Usuario;
import edu.eci.arsw.sharingweather.persistence.LocalidadesRepository;
import edu.eci.arsw.sharingweather.persistence.PersistenceNotFoundException;
import edu.eci.arsw.sharingweather.persistence.UsersRepository;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 *
 * @author JuanArevaloMerchan y StefanyMoron
 */
@Service
public class LocalidadesRepositoryStub implements LocalidadesRepository{

    @Autowired
    private UsersRepository usuarios = null;
    
    
    @Override
    public void addRegionFavorita(Usuario usaurio, LocalidadFavorita lf) throws PersistenceNotFoundException {
        boolean encontro = false;
        for(int i=0; i<usuarios.getUsuarios().size(); i++){
            if(usaurio.getNombreUsuario().equalsIgnoreCase(usuarios.getUsuarios().get(i).getNombreUsuario())){
                encontro = true;
                if(!usuarios.getUsuarios().get(i).existeLocalidad(lf)){
                    usuarios.getUsuarios().get(i).addLocalidadFavorita(lf);
                }
                else{
                    throw new UnsupportedOperationException("La localidad ya existe");
                }
                
                break;
            }
        }
        
        if(!encontro){
            throw new UnsupportedOperationException("Usuario Invalido."); //To change body of generated methods, choose Tools | Templates.
    
        }
    }

    @Override
    public CopyOnWriteArrayList<LocalidadFavorita> getRegionesFavoritas(Usuario usuario) throws PersistenceNotFoundException {
       for(int i=0; i<usuarios.getUsuarios().size(); i++){
            if(usuario.getNombreUsuario().equalsIgnoreCase(usuarios.getUsuarios().get(i).getNombreUsuario())){
                return usuarios.getUsuarios().get(i).getLocalidadesFavoritas();
            }
        }
        throw new UnsupportedOperationException("Usuario inválido"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminarRegionFavorita(Usuario usaurio, LocalidadFavorita lf) throws PersistenceNotFoundException {
        Usuario usuarioEncontrado = null;
        for(int i=0; i<usuarios.getUsuarios().size(); i++){
            if(usaurio.getNombreUsuario().equalsIgnoreCase(usuarios.getUsuarios().get(i).getNombreUsuario())){
                usuarioEncontrado = usuarios.getUsuarios().get(i);
                break;
            }
        }
        
        if(usuarioEncontrado!=null){
            usuarioEncontrado.eliminarLocalidadFavorita(lf);
        }
        else{
            throw new PersistenceNotFoundException("Usuario no encontrado");
        }
    }
    
}
