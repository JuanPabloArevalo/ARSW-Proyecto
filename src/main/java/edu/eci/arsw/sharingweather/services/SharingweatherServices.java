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
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
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
    SharingweatherPersistence swp = null;
    
    /**
     * Metodo encargado de adicionar un nuevo reporte del clima 
     * @param rcl
     * @param usuario
     * @throws edu.eci.arsw.sharingweather.persistence.SharingweatherPersistenceException
     */
    public void addNewBlueprint(ReporteClima rcl, Usuario usuario) throws SharingweatherPersistenceException{
        swp.saveReporteClima(rcl, usuario);
    }
    
    /**
     * Metodo encargado de retornar los reportes publicados
     * @return
     * @throws SharingweatherNotFoundException 
     */
    public ConcurrentHashMap<AtomicLong,ArrayList<ReporteClima>> getReportesPublicados() throws SharingweatherNotFoundException{
        return swp.getReportesClimaPublicar();
    }
    
        
    /**
     * Metodo encargado de retornar los reportes sin publicar
     * @return
     * @throws SharingweatherNotFoundException 
     */
    public ConcurrentHashMap<AtomicLong,ArrayList<ReporteClima>> getReportesSinPublicar() throws SharingweatherNotFoundException{
        return swp.getReportesClimaSinPublicar();
    }
}
