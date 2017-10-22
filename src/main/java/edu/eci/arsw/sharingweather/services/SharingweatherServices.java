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
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;
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
     * @param rcl
     * @param usuario
     * @throws edu.eci.arsw.sharingweather.persistence.SharingweatherPersistenceException
     */
    public void addNewReporteClima(ReporteClima rcl, Usuario usuario) throws SharingweatherPersistenceException{
        swp.saveReporteClima(rcl, usuario);
    }
    
    /**
     * Metodo encargado de retornar los reportes publicados
     * @return
     * @throws SharingweatherNotFoundException 
     */
    public ConcurrentHashMap<AtomicLong,CopyOnWriteArrayList<ReporteClima>> getReportesPublicados() throws SharingweatherNotFoundException{
        return swp.getReportesClimaPublicar();
    }
    
        
    /**
     * Metodo encargado de retornar los reportes sin publicar
     * @return
     * @throws SharingweatherNotFoundException 
     */
    public ConcurrentHashMap<AtomicLong,CopyOnWriteArrayList<ReporteClima>> getReportesSinPublicar() throws SharingweatherNotFoundException{
        return swp.getReportesClimaSinPublicar();
    }
    
    /**
     * Metodo encargado de traer La lista de los usuarios
     * @return
     * @throws SharingweatherNotFoundException 
     */
    public CopyOnWriteArrayList<Usuario> getUsuarios()throws SharingweatherNotFoundException {
        return swp.getUsuarios();
    }
    
   /**
     * Metodo encargado de adicionar los usuarios a la lista de usuarios
     * @return
     * @throws SharingweatherNotFoundException 
     */
    public  void addUsuarios(Usuario usuario)throws SharingweatherNotFoundException{ 
       for(int i = 0; i < swp.getUsuarios().size();i ++){
           if(swp.getUsuarios().get(i).getNombreUsuario().equals(usuario.getNombreUsuario())|| swp.getUsuarios().get(i).getCorreo().equals(usuario.getCorreo()) ){
           
           }else{
           swp.addUsuarios(usuario);
           }       
       }
    }
}
