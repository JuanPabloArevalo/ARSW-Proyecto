/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.sharingweather.controllers;

import edu.eci.arsw.sharingweather.model.ReporteClima;
import edu.eci.arsw.sharingweather.model.Usuario;
import edu.eci.arsw.sharingweather.persistence.SharingweatherNotFoundException;
import edu.eci.arsw.sharingweather.persistence.SharingweatherPersistenceException;
import edu.eci.arsw.sharingweather.services.SharingweatherServices;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    
    @RequestMapping(path ="/reportesClima/publicados", method = RequestMethod.GET)
    public ResponseEntity<?> manejadorGetAllReportesPublicados(){
        try {
            return new ResponseEntity<>(sws.getReportesPublicados(),HttpStatus.ACCEPTED);
        } catch (SharingweatherNotFoundException ex) {
            Logger.getLogger(SharingweatherAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("NO existen reportes publicados ",HttpStatus.NOT_FOUND);
        }
    
    }
        @RequestMapping(path ="/reportesClima/noPublicados", method = RequestMethod.GET)
    public ResponseEntity<?> manejadorGetAllReportesNoPublicados(){
        try {
            return new ResponseEntity<>(sws.getReportesSinPublicar(),HttpStatus.ACCEPTED);
        } catch (SharingweatherNotFoundException ex) {
            Logger.getLogger(SharingweatherAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("NO existen reportes publicados ",HttpStatus.NOT_FOUND);
        }
    
    }
    
    
    @RequestMapping(path ="/Usuarios/registrados",method = RequestMethod.GET)
    public ResponseEntity<?> manejadorGetRestrado(){
        try {
            return new ResponseEntity<>(sws.getUsuarios(),HttpStatus.ACCEPTED);
        } catch (SharingweatherNotFoundException ex) {
            Logger.getLogger(SharingweatherAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("NO hay usuarios registrados ",HttpStatus.NOT_FOUND);
        }
    
    }
    
    @RequestMapping(path ="/Usuarios/loggeados",method = RequestMethod.GET)
    public ResponseEntity<?> manejadorPostRegistrado(@RequestBody String nombreU , @RequestBody String password){
        try {
            return new ResponseEntity<>(sws.IniciarSesion(nombreU, password),HttpStatus.ACCEPTED);
        } catch (SharingweatherNotFoundException ex) {
            Logger.getLogger(SharingweatherAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("El usuario no esta registrado  ",HttpStatus.NOT_FOUND);
        }
    
    }
    
    @RequestMapping(path ="/reportesClima", method = RequestMethod.POST)	
    public ResponseEntity<?> manejadorPostRecursoAdicionarReporteClima(@RequestBody ReporteClima repClima){
        System.out.println("LLego aca"+repClima);
        System.out.println("Clima"+repClima.getClima());
        try {
            sws.addNewReporteClima(repClima, repClima.getUsuario());
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (SharingweatherPersistenceException ex) {
            Logger.getLogger(SharingweatherAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);            
        }        
    }
    
    @RequestMapping(path ="/Usuarios", method = RequestMethod.POST)	
    public ResponseEntity<?> manejadorPostAdicionarUsuario(@RequestBody Usuario usuario){
        try {
            sws.addUsuarios(usuario);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (SharingweatherNotFoundException ex) {
            Logger.getLogger(SharingweatherAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);            
        }        

    }

}
