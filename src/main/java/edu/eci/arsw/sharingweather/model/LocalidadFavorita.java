/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.sharingweather.model;

/**
 *
 * @author User
 */
public class LocalidadFavorita {
    
    private int numero;
    private String nombre;
    
    public LocalidadFavorita(){
        
    }
    
    
    public LocalidadFavorita(int numero , String nombre){
     this.numero = numero;
     this.nombre = nombre;
            
    }
    
    public int getNumero(){
       return numero;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public void setNumero(int numero){
        this.numero = numero;
    
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
}
