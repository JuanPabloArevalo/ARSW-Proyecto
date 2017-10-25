/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.sharingweather.services;

import edu.eci.arsw.sharingweather.model.ReporteClima;
import edu.eci.arsw.sharingweather.model.Usuario;
import edu.eci.arsw.sharingweather.persistence.SharingweatherNotFoundException;
import edu.eci.arsw.sharingweather.persistence.SharingweatherPersistence;
import edu.eci.arsw.sharingweather.persistence.SharingweatherPersistenceException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
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
    
    private boolean existeUsuario = false;
    private String mensaje ="";

    /**
     * Metodo encargado de adicionar un nuevo reporte del clima
     *
     * @param rcl
     * @param usuario
     * @throws
     * edu.eci.arsw.sharingweather.persistence.SharingweatherPersistenceException
     */
    public void addNewReporteClima(ReporteClima rcl, Usuario usuario) throws SharingweatherPersistenceException {
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
        System.out.println("Aca hay: "+mapa.size());
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
    public Set<ReporteClima>  getReportesSinPublicar() throws SharingweatherNotFoundException {
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
        for(int i=0; i < usuariostemp.size(); i++){
           usuarios.add(usuariostemp.get(i));
        }
        return usuarios;
    }
      /**
     * Metodo encargado de traer un usuario en particular
     *
     * @return
     * @throws SharingweatherNotFoundException
     */
     public Usuario getUsuario(String nombreUsuario) throws SharingweatherNotFoundException {
        for(int i=0; i < swp.getUsuarios().size(); i++){
            if(swp.getUsuarios().get(i).getNombreUsuario().equals(nombreUsuario)){
                return swp.getUsuarios().get(i);
            }
        }
        return null;
    }
     
    /**
     * Metodo encargado de adicionar los usuarios a la lista de usuarios
     *
     * @return
     * @throws SharingweatherNotFoundException
     */
    public void addUsuarios(Usuario usuario) throws SharingweatherNotFoundException {
        
        for(int i = 0; i < swp.getUsuarios().size();i ++){
        if(swp.getUsuarios().get(i).getNombreUsuario().equals(usuario.getNombreUsuario())|| swp.getUsuarios().get(i).getCorreo().equals(usuario.getCorreo())){
            existeElUsuario();
            throw new SharingweatherNotFoundException("El usuario ya existe.");
            }   
        }
            swp.addUsuarios(usuario);
    }
    
    
    
    public void existeElUsuario(){
      existeUsuario = true;
    }
    
    /**
     * Metodo encargado de adicionar los usuarios a la lista de usuarios
     *
     * @return
     * @throws SharingweatherNotFoundException
     */
    public Usuario IniciarSesion(String nombreUsuario, String password) throws SharingweatherNotFoundException {
         CopyOnWriteArrayList<Usuario> usuarios= swp.getUsuarios();
         Usuario usuario = null ;
            for(int i = 0; i < usuarios.size();i ++){
                if(usuarios.get(i).getNombreUsuario().equals(nombreUsuario)&& usuarios.get(i).getPassword().equals(password)){
                    
                usuario = getUsuario(nombreUsuario);
                }   
            }
            if(usuario == null){
             throw new SharingweatherNotFoundException("El usuario no se encuntra registrado.");
            }
           
    }
}
