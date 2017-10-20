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
public class SharingweatherNotFoundException extends Exception{

    public SharingweatherNotFoundException(String message) {
        super(message);
    }

    public SharingweatherNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
