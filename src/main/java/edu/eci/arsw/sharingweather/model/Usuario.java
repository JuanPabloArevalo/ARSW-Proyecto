/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.sharingweather.model;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author JuanArevaloMerchan y StefanyMoron
 */
@Document(collection = "usuarios")
public class Usuario {
    
    @Id
    private int id;
    private String nombre;
    private int edad;
    private String nombreUsuario;
    private String password;
    private String correo;
    private List<LocalidadFavorita> localidadesFavoritas ;
    
        /**
     * Constructor
     * @param id
     * @param nombre
     * @param edad
     * @param nombreUsuario
     * @param password
     * @param correo 
     */
    public Usuario(int id, String nombre, int edad, String nombreUsuario, String password, String correo){
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.correo = correo;
        this.localidadesFavoritas = new ArrayList<>();
    }
    
        public Usuario(int id, String nombre, int edad, String nombreUsuario, String password, String correo, List<LocalidadFavorita> localidadesFavoritas){
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.correo = correo;
        this.localidadesFavoritas = localidadesFavoritas;
    }
    
    
    /**
     * Constructor
     */
    public Usuario(){
         
    }


    
    public void addLocalidadFavorita(LocalidadFavorita l){
         getLocalidadesFavoritas().add(l);
    }
    
    /**
     * Metodo encargado de validar si existe la localidad favorita
     * @param lf
     * @return 
     */
    public boolean existeLocalidad(LocalidadFavorita lf){
        for(int i=0; i<getLocalidadesFavoritas().size(); i++){
            if(getLocalidadesFavoritas().get(i).getNumero()==lf.getNumero()){
                return true;
            }
        }
        return false;
    }
    
    /**
     * Metodo encargado de eliminar la localidad favorita
     * @param l 
     */
    public void eliminarLocalidadFavorita(LocalidadFavorita l){
        
        for(int i=0; i<getLocalidadesFavoritas().size(); i++){
            if(getLocalidadesFavoritas().get(i).getNumero()==l.getNumero()){
                getLocalidadesFavoritas().remove(i);
                System.out.println("edu.eci.arsw.sharingweather.model.Usuario.eliminarLocalidadFavorita()");
                break;
            }
        }
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
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

    /**
     * @return the localidadesFavoritas
     */
    public List<LocalidadFavorita> getLocalidadesFavoritas() {
        return localidadesFavoritas;
    }

    /**
     * @param localidadesFavoritas the localidadesFavoritas to set
     */
    public void setLocalidadesFavoritas(List<LocalidadFavorita> localidadesFavoritas) {
        this.localidadesFavoritas = localidadesFavoritas;
    }
    
    
}


