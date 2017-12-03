/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.sharingweather.persistence.imp;

import edu.eci.arsw.sharingweather.model.LocalidadesBogota;
import edu.eci.arsw.sharingweather.model.ReporteClima;
import edu.eci.arsw.sharingweather.model.Ubicacion;
import edu.eci.arsw.sharingweather.model.Usuario;
import edu.eci.arsw.sharingweather.model.imp.LocalidadesBogotaLocal;
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

    private ConcurrentHashMap<Long, CopyOnWriteArrayList<ReporteClima>> climasPublicados = new ConcurrentHashMap<>();
    private ConcurrentHashMap<Long, CopyOnWriteArrayList<ReporteClima>> climasNoPublicados = new ConcurrentHashMap<>();
    private AtomicLong numeroPublicados = new AtomicLong(1);
    private AtomicLong numeroNoPublicados = new AtomicLong(1);
    private CopyOnWriteArrayList<Usuario> listaUsuarios = new CopyOnWriteArrayList<>();
    private static final double DISTANCIAMINIMA = 0.7;
    private static final int CANTIDADREPORTESMINIMO = 3;

    @Autowired
    private SimpMessagingTemplate msgt;
    @Autowired
    private LocalidadesBogota localidades = null;
    /**
     * Constructor
     */
    public LocalPersistence() {
        Usuario usuario = new Usuario("Juan Arevalo", 25, "juan.arevalo.merchan", "123", "juan.arevalo-m@mail.escuelaing.edu.co");
        Usuario usuario2 = new Usuario("Stefany Moron", 27, "stefany.moron", "1234", "stefany.moron@mail.escuelaing.edu.co");
        Usuario usuario3 = new Usuario("Hector Cadavid", 18, "hector.cadavid", "12345", "hector.cadavid@mail.escuelaing.edu.co");
        listaUsuarios.add(usuario);
        listaUsuarios.add(usuario2);
        listaUsuarios.add(usuario3);
        Ubicacion ub1 = new Ubicacion(4.746147, -74.030096);
        Ubicacion ub2 = new Ubicacion(4.746040, -74.031995);
        Ubicacion ub3 = new Ubicacion(4.748638, -74.030353);
        ReporteClima clima1 = new ReporteClima(ub1, 10, usuario, "sol");
        ReporteClima clima2 = new ReporteClima(ub2, 12, usuario2, "sol");
        ReporteClima clima3 = new ReporteClima(ub3, 18, usuario3, "sol");
        CopyOnWriteArrayList<ReporteClima> objList = new CopyOnWriteArrayList<>();
        objList.add(clima1);
        objList.add(clima2);
        objList.add(clima3);
        climasPublicados.put(numeroPublicados.incrementAndGet(), objList);
        ub1 = new Ubicacion(4.733147, -74.035017);
        ub2 = new Ubicacion(4.729650, -74.031289);
        ub3 = new Ubicacion(4.732430, -74.033574);
        clima1 = new ReporteClima(ub1, 25, usuario, "agua");
        clima2 = new ReporteClima(ub2, 32, usuario2, "agua");
        clima3 = new ReporteClima(ub3, 45, usuario3, "agua");
        objList = new CopyOnWriteArrayList<>();
        objList.add(clima1);
        objList.add(clima2);
        objList.add(clima3);
        climasPublicados.put(numeroPublicados.incrementAndGet(), objList);

        ub1 = new Ubicacion(4.776487, -74.061366);
        ub2 = new Ubicacion(4.703313, -74.034446);
        ub3 = new Ubicacion(4.703377, -74.036817);
        clima1 = new ReporteClima(ub1, 55, usuario, "nublado");
        clima2 = new ReporteClima(ub2, 65, usuario2, "nublado");
        clima3 = new ReporteClima(ub3, 75, usuario3, "nublado");
        objList = new CopyOnWriteArrayList<>();
        objList.add(clima1);
        objList.add(clima2);
        objList.add(clima3);
        climasPublicados.put(numeroPublicados.incrementAndGet(), objList);
        
        localidades = new LocalidadesBogotaLocal();
//        System.out.println("lb.getLocalidad(ub1): "+localidades.getLocalidad(ub1).getNombre());
    }

    @Override
    public void saveReporteClima(ReporteClima clima, Usuario usuario) throws SharingweatherPersistenceException {
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
                        throw new SharingweatherPersistenceException("Ya has publicado un reporte en la misma zona y con el mismo clima.");
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
                           msgt.convertAndSend("/topic/regionFavorita."+numeroLocalidad, objList);  
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
                            throw new SharingweatherPersistenceException("Ya has publicado un reporte en la misma zona y con el mismo clima.");
                        }
                        objList.add(clima);
                        climasPublicados.replace(llaveActual, objList);
                        encontro = true;
                        //PUBLICAR
                        msgt.convertAndSend("/topic/reporteClima", objList);
                        //Publicar Zona favorita
                        numeroLocalidad = localidades.getLocalidad(clima.getUbicacion()).getNumero();
                        if(numeroLocalidad!=22){
                           msgt.convertAndSend("/topic/regionFavorita."+numeroLocalidad, objList);  
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
    public ConcurrentHashMap<Long, CopyOnWriteArrayList<ReporteClima>> getReportesClimaPublicar() throws SharingweatherNotFoundException {
        return climasPublicados;

    }

    @Override
    public ConcurrentHashMap<Long, CopyOnWriteArrayList<ReporteClima>> getReportesClimaSinPublicar() throws SharingweatherNotFoundException {
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
