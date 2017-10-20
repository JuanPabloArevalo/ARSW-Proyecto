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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.atomic.AtomicLong;

/**
 *
 * @author JuanArevaloMerchan y StefanyMoron
 */
public class LocalPersistence implements SharingweatherPersistence{
    // List<Object> objList = Collections.synchronizedList(new ArrayList<Object>());
    private final ConcurrentHashMap<AtomicLong,ArrayList<ReporteClima>> climasPublicados = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<AtomicLong,ArrayList<ReporteClima>> climasNoPublicados = new ConcurrentHashMap<>();
    private AtomicLong numeroPublicados = new AtomicLong(0);
    private AtomicLong numeroNoPublicados = new AtomicLong(0);
    private static final double DISTANCIAMINIMA = 0.7;
    private static final int CANTIDADREPORTESMINIMO = 3;
    @Override
    public void saveReporteClima(ReporteClima clima, Usuario usuario) throws SharingweatherPersistenceException {
        ArrayList<ReporteClima> objList;
        boolean encontro = false;
        AtomicLong llaveActual;
//Validar primero en climas no publicados
        for (Map.Entry<AtomicLong, ArrayList<ReporteClima>> entry : climasNoPublicados.entrySet()) {
            objList = entry.getValue();
            llaveActual = entry.getKey();
            for(int i=0; i<objList.size(); i++){
               if(objList.get(i).getUbicacion().distanciaEntreUbicaciones(clima.getUbicacion())<=DISTANCIAMINIMA){
                   objList.add(clima);
                   if(objList.size()>=CANTIDADREPORTESMINIMO){
                       climasPublicados.put(numeroPublicados, objList);
                       numeroPublicados.incrementAndGet();
                       climasNoPublicados.remove(llaveActual);
                       //PUBLICAR ZONA
                   }
                   else{
                       climasNoPublicados.replace(llaveActual,objList);
                   }
                   encontro = true;
                   break;
               }
            }
            if(encontro){
                break;
            }
        }
 //Validar en climas publicados       
        if(!encontro){
            for (Map.Entry<AtomicLong, ArrayList<ReporteClima>> entry : climasPublicados.entrySet()) {
                objList = entry.getValue();
                llaveActual = entry.getKey();
                for(int i=0; i<objList.size(); i++){
                    if(objList.get(i).getUbicacion().distanciaEntreUbicaciones(clima.getUbicacion())<=DISTANCIAMINIMA){
                        objList.add(clima);
                        climasPublicados.replace(llaveActual,objList);
                        encontro = true;
                        break;
                        //PUBLICAR
                    }
                }
                if(encontro){
                    break;
                }
            }
        }
//Insertar
        if(!encontro){
            List<ReporteClima> objList2 = Collections.synchronizedList(new ArrayList<>());
            objList2.add(clima);
            climasNoPublicados.put(numeroNoPublicados, (ArrayList<ReporteClima>) objList2);
            numeroNoPublicados.incrementAndGet();
        }
    }


    @Override
    public ReporteClima getReporteClima(Usuario usuario, Ubicacion ubicacion) throws SharingweatherNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ConcurrentHashMap<AtomicLong, ArrayList<ReporteClima>> getReportesClimaPublicar() throws SharingweatherNotFoundException {
        return climasPublicados;
        
    }

    @Override
    public ConcurrentHashMap<AtomicLong, ArrayList<ReporteClima>> getReportesClimaSinPublicar() throws SharingweatherNotFoundException {
        return climasNoPublicados;
    }

    
}
