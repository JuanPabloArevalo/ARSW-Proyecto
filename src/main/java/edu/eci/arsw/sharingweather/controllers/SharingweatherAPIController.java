/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.sharingweather.controllers;

import edu.eci.arsw.sharingweather.model.LocalidadFavoritas;
import edu.eci.arsw.sharingweather.model.ReporteClima;
import edu.eci.arsw.sharingweather.model.Usuario;
import edu.eci.arsw.sharingweather.persistence.SharingweatherNotFoundException;
import edu.eci.arsw.sharingweather.persistence.SharingweatherPersistenceException;
import edu.eci.arsw.sharingweather.services.SharingweatherServices;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Juan Pablo Arévalo y Stefany Moron
 */
@RestController
@RequestMapping(value = "/sharingweather/V1")
public class SharingweatherAPIController {

    @Autowired
    private SharingweatherServices sws = null;

    @RequestMapping(path = "/reportesClima/publicados", method = RequestMethod.GET)
    public ResponseEntity<?> manejadorGetAllReportesPublicados() {
        try {
            return new ResponseEntity<>(sws.getReportesPublicados(), HttpStatus.ACCEPTED);
        } catch (SharingweatherNotFoundException ex) {
            Logger.getLogger(SharingweatherAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("NO existen reportes publicados ", HttpStatus.NOT_FOUND);
        }

    }

    @RequestMapping(path = "/reportesClima/noPublicados", method = RequestMethod.GET)
    public ResponseEntity<?> manejadorGetAllReportesNoPublicados() {
        try {
            return new ResponseEntity<>(sws.getReportesSinPublicar(), HttpStatus.ACCEPTED);
        } catch (SharingweatherNotFoundException ex) {
            Logger.getLogger(SharingweatherAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("NO existen reportes publicados ", HttpStatus.NOT_FOUND);
        }

    }

    @RequestMapping(path = "/Usuarios/registrados", method = RequestMethod.GET)
    public ResponseEntity<?> manejadorGetRestrado() {
        try {
            return new ResponseEntity<>(sws.getUsuarios(), HttpStatus.ACCEPTED);
        } catch (SharingweatherNotFoundException ex) {
            Logger.getLogger(SharingweatherAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("NO hay usuarios registrados ", HttpStatus.NOT_FOUND);
        }

    }

    @RequestMapping(path = "/Usuarios/{usuario}/{password}", method = RequestMethod.GET)
    public ResponseEntity<?> manejadorGetRegistrado(@PathVariable("usuario") String nombreU, @PathVariable("password") String password) {
        try {
            return new ResponseEntity<>(sws.iniciarSesion(nombreU, password), HttpStatus.ACCEPTED);
        } catch (SharingweatherNotFoundException ex) {
            Logger.getLogger(SharingweatherAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }

    }
    
     @RequestMapping(path = "/Usuarios/{usuario}/Favoritos", method = RequestMethod.GET)
    public ResponseEntity<?> manejadorGetFavoritos(@PathVariable("usuario") String nombreU) {
        try {
            return new ResponseEntity<>(sws.getFavoritos(nombreU), HttpStatus.ACCEPTED);
        } catch (SharingweatherNotFoundException ex) {
            Logger.getLogger(SharingweatherAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }

    }
    
    @RequestMapping(path = "/reportesClima", method = RequestMethod.POST)
    public ResponseEntity<?> manejadorPostRecursoAdicionarReporteClima(@RequestBody ReporteClima repClima) {
        try {
            sws.addNewReporteClima(repClima);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (SharingweatherNotFoundException ex) {
            Logger.getLogger(SharingweatherAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
        } catch (SharingweatherPersistenceException ex) {
            Logger.getLogger(SharingweatherAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/Usuarios", method = RequestMethod.POST)
    public ResponseEntity<?> manejadorPostAdicionarUsuario(@RequestBody Usuario usuario) {
        try {
            sws.addUsuarios(usuario);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (SharingweatherNotFoundException ex) {
            Logger.getLogger(SharingweatherAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @RequestMapping(path = "/regionesFavoritas/{usuario}", method = RequestMethod.GET)
    public ResponseEntity<?> manejadorGetRegionesFavoritas(@PathVariable("usuario") String loginU) {
        //Retorna todas las regiones a las que este inscrito
        LocalidadFavoritas usaquen = new LocalidadFavoritas(1, "Usaquén");
        LocalidadFavoritas chapi = new LocalidadFavoritas(2, "Chapinero");
        Set<LocalidadFavoritas> localidades = new HashSet<LocalidadFavoritas>();
        localidades.add(usaquen);
        localidades.add(chapi);
        return new ResponseEntity<>(localidades, HttpStatus.ACCEPTED);

    }
    
    @RequestMapping(path = "/Usuarios/GestionFavoritos/{usuario}", method = RequestMethod.POST)
    public ResponseEntity<?> manejadorPostAdicionarFavorito(@PathVariable("usuario") String nombreU, @RequestBody Usuario u ) {
        try {
            sws.addLocalidadesFavoritas(nombreU, u.getlocalidad());
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (SharingweatherNotFoundException ex) {
            Logger.getLogger(SharingweatherAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }

    }
    
    

}
