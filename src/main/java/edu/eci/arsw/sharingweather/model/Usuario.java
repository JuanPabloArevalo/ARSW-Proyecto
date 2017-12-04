/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.sharingweather.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author JuanArevaloMerchan y StefanyMoron
 */
public class Usuario {

    private String nombre;
    private int edad;
    private String nombreUsuario;
    private String password;
    private String correo;
    private LocalidadFavoritas localidad;
    private CopyOnWriteArrayList<LocalidadFavoritas> localidadesFavoritas = new CopyOnWriteArrayList<>();
    
    /**
     * Constructor
     */
    public Usuario(){
         
    }
    /**
     * Constructor
     * @param nombre
     * @param edad
     * @param nombreUsuario
     * @param password
     * @param correo 
     */
    public Usuario(String nombre, int edad, String nombreUsuario, String password, String correo,LocalidadFavoritas lFavoritas){
        this.nombre = nombre;
        this.edad = edad;
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.correo = correo;
        this.localidad = lFavoritas;
        
    }
    
    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the edad
     */
    public int getEdad() {
        return edad;
    }

    /**
     * @param edad the edad to set
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }

    /**
     * @return the nombreUsuario
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * @param nombreUsuario the nombreUsuario to set
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    public LocalidadFavoritas getlocalidad(){
       return localidad;
    }
    
    public CopyOnWriteArrayList<LocalidadFavoritas> getLocalidadesFavoritas(){
        return localidadesFavoritas;
    }
    
    public void addLocalidadFavorita(LocalidadFavoritas l){
         localidadesFavoritas.add(l);
    }
    
    /**
     * Metodo encargado de validar si existe la localidad favorita
     * @param lf
     * @return 
     */
    public boolean existeLocalidad(LocalidadFavoritas lf){
        for(int i=0; i<localidadesFavoritas.size(); i++){
            if(localidadesFavoritas.get(i).getNumero()==lf.getNumero()){
                return true;
            }
        }
        
        return false;
    }
}
