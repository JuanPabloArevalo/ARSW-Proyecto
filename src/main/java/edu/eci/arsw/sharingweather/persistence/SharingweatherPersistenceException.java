/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.sharingweather.persistence;

/**
 *
 * @author hcadavid
 */
public class SharingweatherPersistenceException extends Exception{

    public SharingweatherPersistenceException(String message) {
        super(message);
    }

    public SharingweatherPersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
