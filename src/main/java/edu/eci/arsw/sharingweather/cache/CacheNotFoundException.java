/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.sharingweather.cache;

/**
 *
 * @author JuanArevaloMerchan
 */
public class CacheNotFoundException extends Exception{
    
    public CacheNotFoundException(String message) {
        super(message);
    }

    public CacheNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
