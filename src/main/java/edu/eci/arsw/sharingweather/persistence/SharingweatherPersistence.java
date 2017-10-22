/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.sharingweather.persistence;

import edu.eci.arsw.sharingweather.model.ReporteClima;
import edu.eci.arsw.sharingweather.model.Ubicacion;
import edu.eci.arsw.sharingweather.model.Usuario;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

/**
 *
 * @author JuanArevaloMerchan y Stefany Moron
 */
public interface SharingweatherPersistence {
    /**
     * Metodo encargado de guardar un nuevo reporte del clima
     * @param clima
     * @param usuario
     * @throws SharingweatherPersistenceException
     */
    public void saveReporteClima(ReporteClima clima, Usuario usuario) throws SharingweatherPersistenceException;

    /**
     * Metodo encargado de traer el reporte de un clima especifico de un usuario y la ubicacion
     * @param usuario
     * @param ubicacion
     * @return
     * @throws SharingweatherNotFoundException 
     */
    public ReporteClima getReporteClima(Usuario usuario,Ubicacion ubicacion) throws SharingweatherNotFoundException;
    /**
     * Metodo encargado de traer todos los reportes que ya se han publicado
     * @return
     * @throws SharingweatherNotFoundException 
     */
    public ConcurrentHashMap<AtomicLong,CopyOnWriteArrayList<ReporteClima>> getReportesClimaPublicar() throws SharingweatherNotFoundException;
    
    /**
     * Metodo encargado de traer los reposrtes de los climas sin publicar
     * @return
     * @throws SharingweatherNotFoundException 
     */
    public ConcurrentHashMap<AtomicLong,CopyOnWriteArrayList<ReporteClima>> getReportesClimaSinPublicar() throws SharingweatherNotFoundException;
    
    /**
     * Metodo encargado de traer La lista de los usuarios
     * @return
     * @throws SharingweatherNotFoundException 
     */
    public CopyOnWriteArrayList<Usuario> getUsuarios()throws SharingweatherNotFoundException ;
    
   /**
     * Metodo encargado de adicionar los usuarios a la lista de usuarios
     * @return
     * @throws SharingweatherNotFoundException 
     */
    public  void addUsuarios(Usuario usuario)throws SharingweatherNotFoundException;

}
