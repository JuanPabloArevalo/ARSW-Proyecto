/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.sharingweather.controllers;

import edu.eci.arsw.sharingweather.model.ReporteClima;
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
@RequestMapping(value = "/sharingweather/V1/reporteClima")
public class SharingweatherAPIController {
    @Autowired
    private SharingweatherServices sws = null;
    
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> manejadorGetAllReportesPublicados(){
        try {
            return new ResponseEntity<>(sws.getReportesPublicados(),HttpStatus.ACCEPTED);
        } catch (SharingweatherNotFoundException ex) {
            Logger.getLogger(SharingweatherAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("NO existen reportes publicados ",HttpStatus.NOT_FOUND);
        }
    
    }
    
    @RequestMapping(method = RequestMethod.POST)	
    public ResponseEntity<?> manejadorPostRecursoAdicionarReporteClima(@RequestBody ReporteClima repClima){
        System.out.println("LLego aca"+repClima);
        try {
            sws.addNewBlueprint(repClima, repClima.getUsuario());
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (SharingweatherPersistenceException ex) {
            Logger.getLogger(SharingweatherAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);            
        }        

    }

}
