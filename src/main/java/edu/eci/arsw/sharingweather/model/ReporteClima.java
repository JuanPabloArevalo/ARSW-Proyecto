/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.sharingweather.model;

import java.sql.Timestamp;

/**
 *
 * @author JuanArevaloMerchan y StefaniMoron
 */
public class ReporteClima {
    private Ubicacion ubicacion;
    private int tiempo;
    private Usuario usuario;
    private Timestamp fechaHora;
    private String clima;
    /**
     * Constructor
     */
    public ReporteClima(){
        this.fechaHora = new Timestamp(System.currentTimeMillis());
    }
    
    /**
     * Constructor
     * @param ubicacion
     * @param tiempo
     * @param usuario
     * @param clima 
     */
    public ReporteClima(Ubicacion ubicacion, int tiempo, Usuario usuario, String clima){
        this.ubicacion = ubicacion;
        this.tiempo = tiempo;
        this.usuario = usuario;
        this.fechaHora = new Timestamp(System.currentTimeMillis());
        this.clima = clima;
    }   
    

    /**
     * @return the tiempo
     */
    public int getTiempo() {
        return tiempo;
    }

    /**
     * @param tiempo the tiempo to set
     */
    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the ubicacion
     */
    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    /**
     * @param ubicacion the ubicacion to set
     */
    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    /**
     * @return the clima
     */
    public String getClima() {
        return clima;
    }

    /**
     * @param clima the clima to set
     */
    public void setClima(String clima) {
        this.clima = clima;
    }

    /**
     * @return the fechaHora
     */
    public Timestamp getFechaHora() {
        return fechaHora;
    }

    /**
     * @param fechaHora the fechaHora to set
     */
    public void setFechaHora(Timestamp fechaHora) {
        this.fechaHora = fechaHora;
    }
    
    
}
