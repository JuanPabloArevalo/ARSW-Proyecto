/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.sharingweather.persistence.imp;

import edu.eci.arsw.sharingweather.model.ReporteClima;
import edu.eci.arsw.sharingweather.model.Ubicacion;
import edu.eci.arsw.sharingweather.model.Usuario;
import edu.eci.arsw.sharingweather.persistence.SharingweatherNotFoundException;
import edu.eci.arsw.sharingweather.persistence.SharingweatherPersistence;
import edu.eci.arsw.sharingweather.persistence.SharingweatherPersistenceException;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author JuanArevaloMerchan y StefanyMoron
 */
public class LocalPersistence implements SharingweatherPersistence{
    // List<Object> objList = Collections.synchronizedList(new ArrayList<Object>());
    private final ConcurrentHashMap<Long,ArrayList<ReporteClima>> climasPublicados = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<Long,ArrayList<ReporteClima>> climasNoPublicados = new ConcurrentHashMap<>();
    
    @Override
    public void saveReporteClima(ReporteClima clima, Usuario usuario) throws SharingweatherPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifyOrAddReporteClima(ReporteClima clima, Usuario usuario) throws SharingweatherPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ReporteClima getReporteClima(Usuario usuario) throws SharingweatherNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ReporteClima getReporteClima(Usuario usuario, Ubicacion ubicacion) throws SharingweatherNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<ReporteClima> getReportesClimaPublicar() throws SharingweatherNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<ReporteClima> getReportesClimaSinPublicar() throws SharingweatherNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<ReporteClima> getReportesClimaAll() throws SharingweatherNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
