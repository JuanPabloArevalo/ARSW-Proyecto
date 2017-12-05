/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.sharingweather.services;

import edu.eci.arsw.sharingweather.cache.CacheNotFoundException;
import edu.eci.arsw.sharingweather.cache.ReportesCache;
import edu.eci.arsw.sharingweather.model.LocalidadFavorita;
import edu.eci.arsw.sharingweather.model.ReporteClima;
import edu.eci.arsw.sharingweather.model.Usuario;
import edu.eci.arsw.sharingweather.persistence.LocalidadesRepository;
import edu.eci.arsw.sharingweather.persistence.PersistenceNotFoundException;
import edu.eci.arsw.sharingweather.persistence.PersistenceException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.eci.arsw.sharingweather.persistence.UsersRepository;

/**
 *
 * @author Juan Pablo Arevalo y Stefany Moron
 */
@Service
public class SharingweatherServices {

    @Autowired
    private UsersRepository usuarios = null;
    @Autowired
    private ReportesCache reportes = null;
    @Autowired
    private LocalidadesRepository localidades = null;

    /**
     * Metodo encargado de adicionar un nuevo reporte del clima
     *
     * @param rcl
     * @throws
     * edu.eci.arsw.sharingweather.persistence.PersistenceNotFoundException
     * @throws edu.eci.arsw.sharingweather.persistence.PersistenceException
     * @throws edu.eci.arsw.sharingweather.cache.CacheNotFoundException
     */
    public void addNewReporteClima(ReporteClima rcl) throws PersistenceNotFoundException, PersistenceException, CacheNotFoundException {
            Usuario usuario = getUsuario(rcl.getUsuario().getNombreUsuario());
            rcl.setUsuario(usuario);
            reportes.saveReporteClima(rcl, usuario);
        
    }

    /**
     * Metodo encargado de retornar los reportes publicados
     *
     * @return
     * @throws PersistenceNotFoundException
     * @throws edu.eci.arsw.sharingweather.cache.CacheNotFoundException
     */
    public Set<ReporteClima> getReportesPublicados() throws PersistenceNotFoundException, CacheNotFoundException {
        ConcurrentHashMap<Long, CopyOnWriteArrayList<ReporteClima>> mapa = reportes.getReportesClimaPublicar();
        Set<ReporteClima> reportesL = new HashSet<>();
        CopyOnWriteArrayList<ReporteClima> objList;
        for (Map.Entry<Long, CopyOnWriteArrayList<ReporteClima>> entry : mapa.entrySet()) {
            objList = entry.getValue();
            for (int i = 0; i < objList.size(); i++) {
                reportesL.add(objList.get(i));
            }
        }
        return reportesL;
    }

    /**
     * Metodo encargado de retornar los reportes sin publicar
     *
     * @return
     * @throws PersistenceNotFoundException
     * @throws edu.eci.arsw.sharingweather.cache.CacheNotFoundException
     */
    public Set<ReporteClima> getReportesSinPublicar() throws PersistenceNotFoundException, CacheNotFoundException {
        Set<ReporteClima> reportesL = new HashSet<>();
        CopyOnWriteArrayList<ReporteClima> objList;
        for (Map.Entry<Long, CopyOnWriteArrayList<ReporteClima>> entry : reportes.getReportesClimaSinPublicar().entrySet()) {
            objList = entry.getValue();
            for (int i = 0; i < objList.size(); i++) {
                reportesL.add(objList.get(i));
            }
        }
        return reportesL;
    }

    /**
     * Metodo encargado de traer La lista de los usuarios
     *
     * @return
     * @throws PersistenceNotFoundException
     */
    public Set<Usuario> getUsuarios() throws PersistenceNotFoundException {
        Set<Usuario> usuariosL = new HashSet<>();
        CopyOnWriteArrayList<Usuario> usuariostemp = usuarios.getUsuarios();
        for (int i = 0; i < usuariostemp.size(); i++) {
            usuariosL.add(usuariostemp.get(i));
        }
        return usuariosL;
    }

    /**
     * Metodo encargado de traer un usuario en particular
     *
     * @param nombreUsuario
     * @return
     * @throws PersistenceNotFoundException
     */
    public Usuario getUsuario(String nombreUsuario) throws PersistenceNotFoundException {
        for (int i = 0; i < usuarios.getUsuarios().size(); i++) {
            if (usuarios.getUsuarios().get(i).getNombreUsuario().equals(nombreUsuario)) {
                return usuarios.getUsuarios().get(i);
            }
        }
        return null;
    }

    /**
     * Metodo encargado de adicionar los usuarios a la lista de usuarios
     *
     * @param usuario
     * @throws PersistenceNotFoundException
     */
    public void addUsuarios(Usuario usuario) throws PersistenceNotFoundException {

        for (int i = 0; i < usuarios.getUsuarios().size(); i++) {
            if (usuarios.getUsuarios().get(i).getNombreUsuario().equals(usuario.getNombreUsuario()) || usuarios.getUsuarios().get(i).getCorreo().equals(usuario.getCorreo())) {
                throw new PersistenceNotFoundException("El usuario ya existe.");
            }
        }
        usuarios.addUsuarios(usuario);
    }

    /**
     * Metodo encargado de adicionar los usuarios a la lista de usuarios
     *
     * @param nombreUsuario
     * @param password
     * @return
     * @throws PersistenceNotFoundException
     */
    public Usuario iniciarSesion(String nombreUsuario, String password) throws PersistenceNotFoundException {
        CopyOnWriteArrayList<Usuario> usuariosL = usuarios.getUsuarios();
        Usuario usuario = null;
        for (int i = 0; i < usuariosL.size(); i++) {
            if (usuariosL.get(i).getNombreUsuario().equals(nombreUsuario) && usuariosL.get(i).getPassword().equals(password)) {

                usuario = getUsuario(nombreUsuario);
                return usuario;
            }
        }
        if (usuario == null) {
            throw new PersistenceNotFoundException(" El usuario o la contraseña son incorrectos.");
        }
        return null;

    }
    
    public void addLocalidadesFavoritas(String nombreUsuario, LocalidadFavorita l) throws PersistenceNotFoundException{
        Usuario usuario = null;      
        CopyOnWriteArrayList<Usuario> usuariosL = usuarios.getUsuarios();
        for (int i = 0; i < usuariosL.size(); i++) {
            if (usuariosL.get(i).getNombreUsuario().equalsIgnoreCase(nombreUsuario)) {
                   usuario = usuariosL.get(i);
                   break;
            }
        }
         
        if(usuario!=null){
            localidades.addRegionFavorita(usuario, l);
        }
        else{
            throw new PersistenceNotFoundException("Usuario no válido");
        }
    
    }
    
    public Set<LocalidadFavorita> getFavoritos(String nombreUsuario) throws PersistenceNotFoundException{
        Set<LocalidadFavorita> localidadesL = new HashSet<>();
        CopyOnWriteArrayList<Usuario> usuariosL = usuarios.getUsuarios();
        Usuario usuario = null;     
        for (int i = 0; i < usuariosL.size(); i++) {
            if (usuariosL.get(i).getNombreUsuario().equalsIgnoreCase(nombreUsuario)) {
                   usuario = usuariosL.get(i);
                   break;
            }
        }
                 
        if(usuario!=null){
            CopyOnWriteArrayList<LocalidadFavorita> localidadesTemp = localidades.getRegionesFavoritas(usuario);
            for (int i = 0; i < localidadesTemp.size(); i++) {
                localidadesL.add(localidadesTemp.get(i));
            }
            return localidadesL;
        }
        else{
            throw new PersistenceNotFoundException("Usuario no válido");
        }

       
    }
    
   public void eliminarFavoritos(String nombreUsuario, LocalidadFavorita l) throws PersistenceNotFoundException{
       CopyOnWriteArrayList<Usuario> usuariosL = usuarios.getUsuarios();
       Usuario usuario = null;
       for (int i = 0; i < usuariosL.size(); i++) {
           if (usuariosL.get(i).getNombreUsuario().equalsIgnoreCase(nombreUsuario)) {
               usuario = usuariosL.get(i);
                for (int j = 0; j < usuario.getLocalidadesFavoritas().size(); j++) {
                    if(usuario.getLocalidadesFavoritas().get(j).equals(l)){
                       usuario.getLocalidadesFavoritas().remove(j);
                    }
                    
                }    
            } 
       }
        if(usuario==null){
            throw new PersistenceNotFoundException("Usuario no válido");
        }
   }  
    
}
