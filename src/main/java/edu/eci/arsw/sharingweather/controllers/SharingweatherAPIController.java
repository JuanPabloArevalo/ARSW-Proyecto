/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.sharingweather.controllers;

import edu.eci.arsw.sharingweather.cache.CacheNotFoundException;
import edu.eci.arsw.sharingweather.model.LocalidadFavorita;
import edu.eci.arsw.sharingweather.model.ReporteClima;
import edu.eci.arsw.sharingweather.model.Usuario;
import edu.eci.arsw.sharingweather.persistence.PersistenceNotFoundException;
import edu.eci.arsw.sharingweather.persistence.PersistenceException;
import edu.eci.arsw.sharingweather.services.SharingweatherServices;
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
        } catch (PersistenceNotFoundException ex) {
            Logger.getLogger(SharingweatherAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("NO existen reportes publicados ", HttpStatus.NOT_FOUND);
        } catch (CacheNotFoundException ex) {
            Logger.getLogger(SharingweatherAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @RequestMapping(path = "/reportesClima/noPublicados", method = RequestMethod.GET)
    public ResponseEntity<?> manejadorGetAllReportesNoPublicados() {
        try {
            return new ResponseEntity<>(sws.getReportesSinPublicar(), HttpStatus.ACCEPTED);
        } catch (PersistenceNotFoundException ex) {
            Logger.getLogger(SharingweatherAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("NO existen reportes publicados ", HttpStatus.NOT_FOUND);
        } catch (CacheNotFoundException ex) {
            Logger.getLogger(SharingweatherAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @RequestMapping(path = "/Usuarios/registrados", method = RequestMethod.GET)
    public ResponseEntity<?> manejadorGetRestrado() {
        try {
            return new ResponseEntity<>(sws.getUsuarios(), HttpStatus.ACCEPTED);
        } catch (PersistenceNotFoundException ex) {
            Logger.getLogger(SharingweatherAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("NO hay usuarios registrados ", HttpStatus.NOT_FOUND);
        }

    }

    @RequestMapping(path = "/Usuarios/{usuario}/{password}", method = RequestMethod.GET)
    public ResponseEntity<?> manejadorGetRegistrado(@PathVariable("usuario") String nombreU, @PathVariable("password") String password) {
        try {
            return new ResponseEntity<>(sws.iniciarSesion(nombreU, password), HttpStatus.ACCEPTED);
        } catch (PersistenceNotFoundException ex) {
            Logger.getLogger(SharingweatherAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }

    }
    
    
    
    @RequestMapping(path = "/reportesClima", method = RequestMethod.POST)
    public ResponseEntity<?> manejadorPostRecursoAdicionarReporteClima(@RequestBody ReporteClima repClima) {
        try {
            sws.addNewReporteClima(repClima);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (PersistenceNotFoundException ex) {
            Logger.getLogger(SharingweatherAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
        } catch (PersistenceException | CacheNotFoundException ex) {
            Logger.getLogger(SharingweatherAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/Usuarios", method = RequestMethod.POST)
    public ResponseEntity<?> manejadorPostAdicionarUsuario(@RequestBody Usuario usuario) {
        try {
            sws.addUsuarios(usuario);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (PersistenceNotFoundException ex) {
            Logger.getLogger(SharingweatherAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @RequestMapping(path = "/regionesFavoritas/{usuario}", method = RequestMethod.GET)
    public ResponseEntity<?> manejadorGetRegionesFavoritas(@PathVariable("usuario") String loginU) {
        try {
            return new ResponseEntity<>(sws.getFavoritos(loginU), HttpStatus.ACCEPTED);
        } catch (PersistenceNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        } 
    }
    
    /**
     *
     * @param loginU
     * @param l
     * @return
     */
    @RequestMapping(path = "/regionesFavoritas/{usuario}", method = RequestMethod.POST)
    public ResponseEntity<?> manejadorPostAdicionarFavorito(@PathVariable("usuario") String loginU, @RequestBody LocalidadFavorita l ) {
        try {
            System.out.println("loginU()"+loginU);
            sws.addLocalidadesFavoritas(loginU, l);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (PersistenceNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }

    }
    
    /**
     *
     * @param loginU
     * @param l
     * @return
     */
    @RequestMapping(value = "/regionesFavoritas/{usuario}", method = RequestMethod.DELETE)    
    public ResponseEntity<?> manejadorDeleteFavoritos(@PathVariable("usuario") String loginU, @RequestBody LocalidadFavorita l){
            try { 
            sws.eliminarFavoritos(loginU, l);
            //registrar dato
            return new ResponseEntity<>(HttpStatus.CREATED);
            } catch (PersistenceNotFoundException ex) {
            Logger.getLogger(SharingweatherAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error bla bla bla",HttpStatus.FORBIDDEN);            
             }        
    } 
    
    

}
