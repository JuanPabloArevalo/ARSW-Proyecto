/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.sharingweather.cache;

import edu.eci.arsw.sharingweather.model.ReporteClima;
import edu.eci.arsw.sharingweather.model.Usuario;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author JuanArevaloMerchan
 */
public interface ReportesCache {
        /**
     * Metodo encargado de guardar un nuevo reporte del clima
     * @param clima
     * @param usuario
     * @throws edu.eci.arsw.sharingweather.cache.CacheNotFoundException
     */
    public void saveReporteClima(ReporteClima clima, Usuario usuario) throws CacheNotFoundException;
    /**
     * Metodo encargado de traer todos los reportes que ya se han publicado
     * @return 
     * @throws edu.eci.arsw.sharingweather.cache.CacheNotFoundException 
     */
    public ConcurrentHashMap<Long,CopyOnWriteArrayList<ReporteClima>> getReportesClimaPublicar() throws CacheNotFoundException;
    
    /**
     * Metodo encargado de traer los reposrtes de los climas sin publicar
     * @return 
     * @throws edu.eci.arsw.sharingweather.cache.CacheNotFoundException 
     */
    public ConcurrentHashMap<Long,CopyOnWriteArrayList<ReporteClima>> getReportesClimaSinPublicar() throws CacheNotFoundException;
    
}
