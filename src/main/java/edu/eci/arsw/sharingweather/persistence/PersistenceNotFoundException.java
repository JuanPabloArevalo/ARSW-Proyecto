/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.sharingweather.persistence;

/**
 *
 * @author JuanArevaloMerchan
 */
public class PersistenceNotFoundException extends Exception{

    public PersistenceNotFoundException(String message) {
        super(message);
    }

    public PersistenceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
