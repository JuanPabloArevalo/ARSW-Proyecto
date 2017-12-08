/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.sharingweather.cache.stub;

import edu.eci.arsw.sharingweather.cache.CacheNotFoundException;
import edu.eci.arsw.sharingweather.cache.ReportesCache;
import edu.eci.arsw.sharingweather.model.LocalidadesBogota;
import edu.eci.arsw.sharingweather.model.ReporteClima;
import edu.eci.arsw.sharingweather.model.Usuario;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;

/**
 *
 * @author JuanArevaloMerchan y StefanyMoron
 */

public class InMemoryReportesCache implements ReportesCache{
    
    private ConcurrentHashMap<Long, CopyOnWriteArrayList<ReporteClima>> climasPublicados = new ConcurrentHashMap<>();
    private ConcurrentHashMap<Long, CopyOnWriteArrayList<ReporteClima>> climasNoPublicados = new ConcurrentHashMap<>();
    private AtomicLong numeroPublicados = new AtomicLong(1);
    private AtomicLong numeroNoPublicados = new AtomicLong(1);
    private static final double DISTANCIAMINIMA = 0.7;
    private static final int CANTIDADREPORTESMINIMO = 3;
    

    @Autowired
    private SimpMessagingTemplate msgt;
    @Autowired
    private LocalidadesBogota localidades = null;
    
    
    public InMemoryReportesCache(){
//        Ubicacion ub1 = new Ubicacion(4.746147, -74.030096);
//        Ubicacion ub2 = new Ubicacion(4.746040, -74.031995);
//        Ubicacion ub3 = new Ubicacion(4.748638, -74.030353);
//        Usuario usuario = new Usuario(1, "Juan Arevalo", 25, "juanarevalomerchan", "123", "juan.arevalo-m@mail.escuelaing.edu.co");
//        ReporteClima clima1 = new ReporteClima(ub1, 10, usuario, "sol");
//        ReporteClima clima2 = new ReporteClima(ub2, 12, usuario, "sol");
//        ReporteClima clima3 = new ReporteClima(ub3, 18, usuario, "sol");
//        CopyOnWriteArrayList<ReporteClima> objList = new CopyOnWriteArrayList<>();
//        objList.add(clima1);
//        objList.add(clima2);
//        objList.add(clima3);
//        climasPublicados.put(numeroPublicados.incrementAndGet(), objList);
//        ub1 = new Ubicacion(4.733147, -74.035017);
//        ub2 = new Ubicacion(4.729650, -74.031289);
//        ub3 = new Ubicacion(4.732430, -74.033574);
//        clima1 = new ReporteClima(ub1, 25, usuario, "agua");
//        clima2 = new ReporteClima(ub2, 32, usuario, "agua");
//        clima3 = new ReporteClima(ub3, 45, usuario, "agua");
//        objList = new CopyOnWriteArrayList<>();
//        objList.add(clima1);
//        objList.add(clima2);
//        objList.add(clima3);
//        climasPublicados.put(numeroPublicados.incrementAndGet(), objList);
//
//        ub1 = new Ubicacion(4.703313, -74.034446);
//        ub2 = new Ubicacion(4.703313, -74.034446);
//        ub3 = new Ubicacion(4.703377, -74.036817);
//        clima1 = new ReporteClima(ub1, 55, usuario, "nublado");
//        clima2 = new ReporteClima(ub2, 65, usuario, "nublado");
//        clima3 = new ReporteClima(ub3, 75, usuario, "nublado");
//        objList = new CopyOnWriteArrayList<>();
//        objList.add(clima1);
//        objList.add(clima2);
//        objList.add(clima3);
//        climasPublicados.put(numeroPublicados.incrementAndGet(), objList);
    }
    
    @Override
    public void saveReporteClima(ReporteClima clima, Usuario usuario) throws CacheNotFoundException {
        CopyOnWriteArrayList<ReporteClima> objList;
        boolean encontro = false;
        Long llaveActual;
        int numeroLocalidad;
//Validar primero en climas no publicados
        for (Map.Entry<Long, CopyOnWriteArrayList<ReporteClima>> entry : climasNoPublicados.entrySet()) {
            objList = entry.getValue();
            llaveActual = entry.getKey();
            for (int i = 0; i < objList.size(); i++) {
                if (objList.get(i).getUbicacion().distanciaEntreUbicaciones(clima.getUbicacion()) <= DISTANCIAMINIMA && clima.getClima().equals(objList.get(i).getClima())) {
                    if (clima.getUsuario().getNombreUsuario().equals(objList.get(i).getUsuario().getNombreUsuario())) {
                        throw new CacheNotFoundException("Ya has publicado un reporte en la misma zona y con el mismo clima.");
                    }
                    objList.add(clima);
                    if (objList.size() >= CANTIDADREPORTESMINIMO) {
                        climasPublicados.put(numeroPublicados.incrementAndGet(), objList);
                        numeroPublicados.incrementAndGet();
                        climasNoPublicados.remove(llaveActual);
                        //PUBLICAR ZONA
                        msgt.convertAndSend("/topic/reporteClima", objList);
                        //Publicar Zona favorita
                        numeroLocalidad = localidades.getLocalidad(clima.getUbicacion()).getNumero();
                        if(numeroLocalidad!=22){
                           msgt.convertAndSend("/topic/regionFavorita."+numeroLocalidad, clima);  
                           
                        }

                    } else {
                        climasNoPublicados.replace(llaveActual, objList);
                    }
                    encontro = true;
                    break;
                }
            }
            if (encontro) {
                break;
            }
        }
        //Validar en climas publicados       
        if (!encontro) {
            for (Map.Entry<Long, CopyOnWriteArrayList<ReporteClima>> entry : climasPublicados.entrySet()) {
                objList = entry.getValue();
                llaveActual = entry.getKey();
                for (int i = 0; i < objList.size(); i++) {
                    if (objList.get(i).getUbicacion().distanciaEntreUbicaciones(clima.getUbicacion()) <= DISTANCIAMINIMA && clima.getClima().equals(objList.get(i).getClima())) {
                        if (clima.getUsuario().getNombreUsuario().equals(objList.get(i).getUsuario().getNombreUsuario())) {
                            throw new CacheNotFoundException("Ya has publicado un reporte en la misma zona y con el mismo clima.");
                        }
                        objList.add(clima);
                        climasPublicados.replace(llaveActual, objList);
                        encontro = true;
                        //PUBLICAR
                        msgt.convertAndSend("/topic/reporteClima", objList);
                        //Publicar Zona favorita
                        numeroLocalidad = localidades.getLocalidad(clima.getUbicacion()).getNumero();
                        if(numeroLocalidad!=22){
                           msgt.convertAndSend("/topic/regionFavorita."+numeroLocalidad, clima);  
                        }
                        break;
                    }
                }
                if (encontro) {
                    break;
                }
            }
        }
//Insertar
        if (!encontro) {
            CopyOnWriteArrayList<ReporteClima> objList2 = new CopyOnWriteArrayList<>();
            objList2.add(clima);
            climasNoPublicados.put(numeroPublicados.incrementAndGet(), objList2);
            numeroNoPublicados.incrementAndGet();
        }
    }

    @Override
    public ConcurrentHashMap<Long, CopyOnWriteArrayList<ReporteClima>> getReportesClimaPublicar() throws CacheNotFoundException {
        return climasPublicados;
    }

    @Override
    public ConcurrentHashMap<Long, CopyOnWriteArrayList<ReporteClima>> getReportesClimaSinPublicar() throws CacheNotFoundException {
        return climasNoPublicados;
    }
    
}
