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
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 *
 * @author JuanArevaloMerchan y StefanyMoron
 */
@Service
public class LocalPersistence implements SharingweatherPersistence {

    // List<Object> objList = Collections.synchronizedList(new ArrayList<Object>());
    private ConcurrentHashMap<AtomicLong, CopyOnWriteArrayList<ReporteClima>> climasPublicados = new ConcurrentHashMap<>();
    private ConcurrentHashMap<AtomicLong, CopyOnWriteArrayList<ReporteClima>> climasNoPublicados = new ConcurrentHashMap<>();
    private AtomicLong numeroPublicados = new AtomicLong(0);
    private AtomicLong numeroNoPublicados = new AtomicLong(0);
    private CopyOnWriteArrayList<Usuario> listaUsuarios = new CopyOnWriteArrayList<>();
    private static final double DISTANCIAMINIMA = 0.7;
    private static final int CANTIDADREPORTESMINIMO = 3;

    @Autowired
    private SimpMessagingTemplate msgt;

    /**
     * Constructor
     */
    public LocalPersistence() {
        Usuario usuario = new Usuario("Juan Arevalo", 25, "juan.arevalo.merchan", "123", "juan.arevalo.merchan@hotmail.com");
        Usuario usuario2 = new Usuario("Juan Arevalo2", 25, "juan.arevalo.merchan2", "123", "juan.arevalo.merchan@hotmail.com2");
        Usuario usuario3 = new Usuario("Juan Arevalo3", 25, "juan.arevalo.merchan3", "123", "juan.arevalo.merchan@hotmail.com3");
        Usuario usuario4 = new Usuario("Juan Arevalo4", 25, "juan.arevalo.merchan4", "123", "juan.arevalo.merchan@hotmail.com4");
        listaUsuarios.add(usuario);
        listaUsuarios.add(usuario2);
        listaUsuarios.add(usuario3);
        listaUsuarios.add(usuario4);
        Ubicacion ub1 = new Ubicacion(4.745798, -74.029147);
        Ubicacion ub2 = new Ubicacion(4.628686, -74.074015);
        Ubicacion ub3 = new Ubicacion(4.628321, -74.073943);
        Ubicacion ub4 = new Ubicacion(4.628300, -74.075153);
        ReporteClima clima1 = new ReporteClima(ub1, 10, usuario,"sol");
        ReporteClima clima2 = new ReporteClima(ub2, 10, usuario2,"sol");
        ReporteClima clima3 = new ReporteClima(ub3, 10, usuario3,"sol");
        ReporteClima clima4 = new ReporteClima(ub4, 10, usuario4,"sol");
        CopyOnWriteArrayList<ReporteClima> objList = new CopyOnWriteArrayList<>();
        objList.add(clima1);
        objList.add(clima2);
        objList.add(clima3);
        objList.add(clima4);
        numeroPublicados.incrementAndGet();
        climasPublicados.put(numeroPublicados, objList);
    }

    @Override
    public void saveReporteClima(ReporteClima clima, Usuario usuario) throws SharingweatherPersistenceException {
        CopyOnWriteArrayList<ReporteClima> objList;
        boolean encontro = false;
        AtomicLong llaveActual;
//Validar primero en climas no publicados
        for (Map.Entry<AtomicLong, CopyOnWriteArrayList<ReporteClima>> entry : climasNoPublicados.entrySet()) {
            objList = entry.getValue();
            llaveActual = entry.getKey();
            for (int i = 0; i < objList.size(); i++) {
                if (objList.get(i).getUbicacion().distanciaEntreUbicaciones(clima.getUbicacion()) <= DISTANCIAMINIMA && clima.getClima().equals(objList.get(i).getClima()) /*&& !clima.getUsuario().getNombreUsuario().equals(objList.get(i).getUsuario().getNombreUsuario())*/) {
                    objList.add(clima);
                    if (objList.size() >= CANTIDADREPORTESMINIMO) {
                        climasPublicados.put(numeroPublicados, objList);
                        numeroPublicados.incrementAndGet();
                        climasNoPublicados.remove(llaveActual);
                        //PUBLICAR ZONA
                        msgt.convertAndSend("/topic/reporteClima", objList);
                        
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
            for (Map.Entry<AtomicLong, CopyOnWriteArrayList<ReporteClima>> entry : climasPublicados.entrySet()) {
                objList = entry.getValue();
                llaveActual = entry.getKey();
                for (int i = 0; i < objList.size(); i++) {
                    if (objList.get(i).getUbicacion().distanciaEntreUbicaciones(clima.getUbicacion()) <= DISTANCIAMINIMA && clima.getClima().equals(objList.get(i).getClima()) /*&& !clima.getUsuario().getNombreUsuario().equals(objList.get(i).getUsuario().getNombreUsuario())*/) {
                        objList.add(clima);
                        climasPublicados.replace(llaveActual, objList);
                        encontro = true;
                        //PUBLICAR
                        msgt.convertAndSend("/topic/reporteClima", objList);
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
            climasNoPublicados.put(numeroNoPublicados, objList2);
            numeroNoPublicados.incrementAndGet();
        }
    }

    @Override
    public ConcurrentHashMap<AtomicLong, CopyOnWriteArrayList<ReporteClima>> getReportesClimaPublicar() throws SharingweatherNotFoundException {
        return climasPublicados;

    }

    @Override
    public ConcurrentHashMap<AtomicLong, CopyOnWriteArrayList<ReporteClima>> getReportesClimaSinPublicar() throws SharingweatherNotFoundException {
        return climasNoPublicados;
    }

    @Override
    public CopyOnWriteArrayList<Usuario> getUsuarios() throws SharingweatherNotFoundException {
        return listaUsuarios;
    }

    @Override
    public void addUsuarios(Usuario usuario) throws SharingweatherNotFoundException {
        listaUsuarios.add(usuario);
    }
}
