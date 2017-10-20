/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.sharingweather.controllers;

import edu.eci.arsw.sharingweather.persistence.SharingweatherNotFoundException;
import edu.eci.arsw.sharingweather.services.SharingweatherServices;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Juan Pablo Arévalo y Stefany Moron
 */
@RestController
@RequestMapping(value = "/sharingweather/V1.0/")
public class SharingweatherAPIController {
    @Autowired
    SharingweatherServices swS = null;
    
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> manejadorGetAllReportesPublicados(){
        try {
            return new ResponseEntity<>(swS.getReportesPublicados(),HttpStatus.ACCEPTED);
        } catch (SharingweatherNotFoundException ex) {
            Logger.getLogger(SharingweatherAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("NO existen reportes publicados ",HttpStatus.NOT_FOUND);
        }
    
    }

}