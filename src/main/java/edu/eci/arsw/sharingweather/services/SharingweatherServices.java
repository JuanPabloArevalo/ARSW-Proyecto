/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.sharingweather.services;

import edu.eci.arsw.sharingweather.model.LocalidadFavoritas;
import edu.eci.arsw.sharingweather.model.ReporteClima;
import edu.eci.arsw.sharingweather.model.Usuario;
import edu.eci.arsw.sharingweather.persistence.SharingweatherNotFoundException;
import edu.eci.arsw.sharingweather.persistence.SharingweatherPersistence;
import edu.eci.arsw.sharingweather.persistence.SharingweatherPersistenceException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Juan Pablo Arevalo y Stefany Moron
 */
@Service
public class SharingweatherServices {

    @Autowired
    private SharingweatherPersistence swp = null;

    /**
     * Metodo encargado de adicionar un nuevo reporte del clima
     *
     * @param rcl
     * @throws
     * edu.eci.arsw.sharingweather.persistence.SharingweatherNotFoundException
     * @throws edu.eci.arsw.sharingweather.persistence.SharingweatherPersistenceException
     */
    public void addNewReporteClima(ReporteClima rcl) throws SharingweatherNotFoundException, SharingweatherPersistenceException {
            Usuario usuario = getUsuario(rcl.getUsuario().getNombreUsuario());
            rcl.setUsuario(usuario);
            swp.saveReporteClima(rcl, usuario);
        
    }

    /**
     * Metodo encargado de retornar los reportes publicados
     *
     * @return
     * @throws SharingweatherNotFoundException
     */
    public Set<ReporteClima> getReportesPublicados() throws SharingweatherNotFoundException {
        ConcurrentHashMap<Long, CopyOnWriteArrayList<ReporteClima>> mapa = swp.getReportesClimaPublicar();
        Set<ReporteClima> reportes = new HashSet<>();
        CopyOnWriteArrayList<ReporteClima> objList;
        for (Map.Entry<Long, CopyOnWriteArrayList<ReporteClima>> entry : mapa.entrySet()) {
            objList = entry.getValue();
            for (int i = 0; i < objList.size(); i++) {
                reportes.add(objList.get(i));
            }
        }
        return reportes;
    }

    /**
     * Metodo encargado de retornar los reportes sin publicar
     *
     * @return
     * @throws SharingweatherNotFoundException
     */
    public Set<ReporteClima> getReportesSinPublicar() throws SharingweatherNotFoundException {
        Set<ReporteClima> reportes = new HashSet<>();
        CopyOnWriteArrayList<ReporteClima> objList;
        for (Map.Entry<Long, CopyOnWriteArrayList<ReporteClima>> entry : swp.getReportesClimaSinPublicar().entrySet()) {
            objList = entry.getValue();
            for (int i = 0; i < objList.size(); i++) {
                reportes.add(objList.get(i));
            }
        }
        return reportes;
    }

    /**
     * Metodo encargado de traer La lista de los usuarios
     *
     * @return
     * @throws SharingweatherNotFoundException
     */
    public Set<Usuario> getUsuarios() throws SharingweatherNotFoundException {
        Set<Usuario> usuarios = new HashSet<>();
        CopyOnWriteArrayList<Usuario> usuariostemp = swp.getUsuarios();
        for (int i = 0; i < usuariostemp.size(); i++) {
            usuarios.add(usuariostemp.get(i));
        }
        return usuarios;
    }

    /**
     * Metodo encargado de traer un usuario en particular
     *
     * @param nombreUsuario
     * @return
     * @throws SharingweatherNotFoundException
     */
    public Usuario getUsuario(String nombreUsuario) throws SharingweatherNotFoundException {
        for (int i = 0; i < swp.getUsuarios().size(); i++) {
            if (swp.getUsuarios().get(i).getNombreUsuario().equals(nombreUsuario)) {
                return swp.getUsuarios().get(i);
            }
        }
        return null;
    }

    /**
     * Metodo encargado de adicionar los usuarios a la lista de usuarios
     *
     * @param usuario
     * @throws SharingweatherNotFoundException
     */
    public void addUsuarios(Usuario usuario) throws SharingweatherNotFoundException {

        for (int i = 0; i < swp.getUsuarios().size(); i++) {
            if (swp.getUsuarios().get(i).getNombreUsuario().equals(usuario.getNombreUsuario()) || swp.getUsuarios().get(i).getCorreo().equals(usuario.getCorreo())) {
                throw new SharingweatherNotFoundException("El usuario ya existe.");
            }
        }
        swp.addUsuarios(usuario);
    }

    /**
     * Metodo encargado de adicionar los usuarios a la lista de usuarios
     *
     * @param nombreUsuario
     * @param password
     * @return
     * @throws SharingweatherNotFoundException
     */
    public Usuario iniciarSesion(String nombreUsuario, String password) throws SharingweatherNotFoundException {
        CopyOnWriteArrayList<Usuario> usuarios = swp.getUsuarios();
        Usuario usuario = null;
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getNombreUsuario().equals(nombreUsuario) && usuarios.get(i).getPassword().equals(password)) {

                usuario = getUsuario(nombreUsuario);
                return usuario;
            }
        }
        if (usuario == null) {
            throw new SharingweatherNotFoundException(" El usuario o la contraseña son incorrectos.");
        }
        return null;

    }
    
    public void addLocalidadesFavoritas(String nombreUsuario, LocalidadFavoritas l) throws SharingweatherNotFoundException{
        Usuario usuario = null;      
        System.out.println("2():"+nombreUsuario);
        CopyOnWriteArrayList<Usuario> usuarios = swp.getUsuarios();
        for (int i = 0; i < usuarios.size(); i++) {
            System.out.println("1():"+usuarios.get(i).getNombreUsuario());
            if (usuarios.get(i).getNombreUsuario().equalsIgnoreCase(nombreUsuario)) {
                   usuario = usuarios.get(i);
                   break;
            }
        }
         
        if(usuario!=null){
            swp.addRegionFavorita(usuario, l);
        }
        else{
            throw new SharingweatherNotFoundException("Usuario no válido");
        }
    
    }
    
    public Set<LocalidadFavoritas> getFavoritos(String nombreUsuario) throws SharingweatherNotFoundException{
        Set<LocalidadFavoritas> localidades = new HashSet<>();
        CopyOnWriteArrayList<Usuario> usuarios = swp.getUsuarios();
        Usuario usuario = null;     
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getNombreUsuario().equalsIgnoreCase(nombreUsuario)) {
                   usuario = usuarios.get(i);
                   break;
            }
        }
                 
        if(usuario!=null){
            CopyOnWriteArrayList<LocalidadFavoritas> localidadesTemp = swp.getRegionesFavoritas(usuario);
            for (int i = 0; i < localidadesTemp.size(); i++) {
                localidades.add(localidadesTemp.get(i));
            }
            return localidades;
        }
        else{
            throw new SharingweatherNotFoundException("Usuario no válido");
        }

       
    }
    
   public void EliminarFavoritos(String nombreUsuario, LocalidadFavoritas l) throws SharingweatherNotFoundException{
       CopyOnWriteArrayList<Usuario> usuarios = swp.getUsuarios();
       Usuario usuario = null;
       for (int i = 0; i < usuarios.size(); i++) {
           if (usuarios.get(i).getNombreUsuario().equalsIgnoreCase(nombreUsuario)) {
               usuario = usuarios.get(i);
                for (int j = 0; j < usuario.getLocalidadesFavoritas().size(); j++) {
                    if(usuario.getLocalidadesFavoritas().get(j).equals(l)){
                       usuario.getLocalidadesFavoritas().remove(j);
                    }
                    
                }    
            } 
       }
        if(usuario==null){
            throw new SharingweatherNotFoundException("Usuario no válido");
        }
   }  
    
}
