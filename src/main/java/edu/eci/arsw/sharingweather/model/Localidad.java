/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.sharingweather.model;

/**
 *
 * @author JuanArevaloMerchan
 */
public class Localidad {

    private Ubicacion coordenada1;
    private Ubicacion coordenada2;
    private Ubicacion coordenada3;
    private Ubicacion coordenada4;
    private int numero;
    private String nombre;
    
    
    public Localidad(){
        
    }
    
    public Localidad(Ubicacion coordenada1, Ubicacion coordenada2, Ubicacion coordenada3, Ubicacion coordenada4, int numero, String nombre){
        this.coordenada1 = coordenada1;
        this.coordenada2 = coordenada2;
        this.coordenada3 = coordenada3;
        this.coordenada4 = coordenada4;
        this.numero = numero;
        this.nombre = nombre;
    }
    /**
     * @return the coordenada1
     */
    public Ubicacion getCoordenada1() {
        return coordenada1;
    }

    /**
     * @param coordenada1 the coordenada1 to set
     */
    public void setCoordenada1(Ubicacion coordenada1) {
        this.coordenada1 = coordenada1;
    }

    /**
     * @return the coordenada2
     */
    public Ubicacion getCoordenada2() {
        return coordenada2;
    }

    /**
     * @param coordenada2 the coordenada2 to set
     */
    public void setCoordenada2(Ubicacion coordenada2) {
        this.coordenada2 = coordenada2;
    }

    /**
     * @return the numero
     */
    public int getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(int numero) {
        this.numero = numero;
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
     * @return the coordenada3
     */
    public Ubicacion getCoordenada3() {
        return coordenada3;
    }

    /**
     * @param coordenada3 the coordenada3 to set
     */
    public void setCoordenada3(Ubicacion coordenada3) {
        this.coordenada3 = coordenada3;
    }

    /**
     * @return the coordenada4
     */
    public Ubicacion getCoordenada4() {
        return coordenada4;
    }

    /**
     * @param coordenada4 the coordenada4 to set
     */
    public void setCoordenada4(Ubicacion coordenada4) {
        this.coordenada4 = coordenada4;
    }

}
