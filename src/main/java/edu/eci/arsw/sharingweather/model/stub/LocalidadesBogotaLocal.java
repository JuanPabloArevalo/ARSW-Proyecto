/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.sharingweather.model.stub;

import edu.eci.arsw.sharingweather.model.Localidad;
import edu.eci.arsw.sharingweather.model.LocalidadesBogota;
import java.util.concurrent.CopyOnWriteArrayList;
import edu.eci.arsw.sharingweather.model.Ubicacion;
import org.springframework.stereotype.Service;
/**
 *
 * @author JuanArevaloMerchan
 */
@Service
public class LocalidadesBogotaLocal implements LocalidadesBogota{
    private CopyOnWriteArrayList<Localidad> localidades = new CopyOnWriteArrayList<>();
    
    public LocalidadesBogotaLocal(){
        //Usaquen                                       //latitud   //longitud
        Localidad usaquen = new Localidad(new Ubicacion(4.825441, -74.034615), new Ubicacion(4.820387, -73.999563), new Ubicacion(4.687183, -74.057352), new Ubicacion(4.664781, -74.005831), 1, "Usaquén");
        //Chapinero
        Localidad chapinero = new Localidad(new Ubicacion(4.687329, -74.057877), new Ubicacion(4.668703, -74.008068), new Ubicacion(4.624892, -74.068806), new Ubicacion(4.606264, -73.994837), 2, "Chapinero");
        //Chapinero
        Localidad santafe = new Localidad(new Ubicacion(4.628073, -74.068676), new Ubicacion(4.616590, -73.989504), new Ubicacion(4.591974, -74.089303), new Ubicacion(4.554477, -74.006155), 3, "Santa fé");
        //San Cristoabal
        Localidad sanCristobal = new Localidad(new Ubicacion(4.590559, -74.084904), new Ubicacion(4.565920, -74.023098), new Ubicacion(4.563354, -74.106869), new Ubicacion(4.507054, -74.043526), 4, "San Cristobal");
        //Usme
        Localidad usme = new Localidad(new Ubicacion(4.545445, -74.121013), new Ubicacion(4.542654, -74.068146), new Ubicacion(4.468726, -74.134922), new Ubicacion(4.469068, -74.072094), 5, "Usme");
        //Tunjuelito
        Localidad tunjuelito = new Localidad(new Ubicacion(4.598327, -74.128563), new Ubicacion(4.552580, -74.118281), new Ubicacion(4.595872, -74.154159), new Ubicacion(4.562045, -74.123946), 6, "Tunjuelito");                  
        //Bosa
        Localidad bosa = new Localidad(new Ubicacion(4.655754, -74.194826), new Ubicacion(4.600830, -74.151567), new Ubicacion(4.628036, -74.224009), new Ubicacion(4.599462, -74.203924), 7, "Bosa");                  
        //Kenedy
        Localidad kenedy = new Localidad(new Ubicacion(4.666901, -74.153595), new Ubicacion(4.637473, -74.117203), new Ubicacion(4.645344, -74.193249), new Ubicacion(4.593670, -74.146729), 8, "Kennedy");                  
        //Fontibon
        Localidad fontibon = new Localidad(new Ubicacion(4.719440, -74.156195), new Ubicacion(4.688816, -74.117883), new Ubicacion(4.677011, -74.176934), new Ubicacion(4.637660, -74.118398), 9, "Fontibón");        
        //Engativa
        Localidad engativa = new Localidad(new Ubicacion(4.740607, -74.124155), new Ubicacion(4.686717, -74.075918), new Ubicacion(4.722131, -74.161062), new Ubicacion(4.654038, -74.103384), 10, "Engativa");        
        //Suba
        Localidad suba = new Localidad(new Ubicacion(4.836357, -74.084712), new Ubicacion(4.828147, -74.033557), new Ubicacion(4.741931, -74.134494), new Ubicacion(4.686501, -74.057247), 11, "Suba");         
        //BarriosUnidos
        Localidad barriosUnidos = new Localidad(new Ubicacion(4.689006, -74.073498), new Ubicacion(4.686782, -74.057018), new Ubicacion(4.666251, -74.093926), new Ubicacion(4.649751, -74.064686), 12, "Barrios Unidos");             
        //Teusaquillo
        Localidad teusaquillo = new Localidad(new Ubicacion(4.666207, -74.093985), new Ubicacion(4.649699, -74.064634), new Ubicacion(4.644992, -74.110666), new Ubicacion(4.615477, -74.072815), 13, "Teusaquillo");             
        //Mártires
        Localidad martires = new Localidad(new Ubicacion(4.625038, -74.082900), new Ubicacion(4.615692, -74.072969), new Ubicacion(4.598667, -74.107473), new Ubicacion(4.592079, -74.089191), 14, "Los Mártires");               
        //Antonio Nariño
        Localidad antonio = new Localidad(new Ubicacion(4.598858, -74.107042), new Ubicacion(4.589447, -74.084812), new Ubicacion(4.593553, -74.131246), new Ubicacion(4.576014, -74.094425), 15, "Antonio Nariño");                
        //Puente Aranda
        Localidad puente = new Localidad(new Ubicacion(4.645393, -74.110712), new Ubicacion(4.624604, -74.082988), new Ubicacion(4.596279, -74.139269), new Ubicacion(4.594409, -74.112284), 16, "Puente Aranda");               
        //La candelaria
        Localidad candelaria = new Localidad(new Ubicacion(4.602323, -74.076746), new Ubicacion(4.602131, -74.059790), new Ubicacion(4.593533, -74.082364), new Ubicacion(4.589469, -74.070605), 17, "La candelaria");              
        //Rafael Uribe
        Localidad rafaelUribe = new Localidad(new Ubicacion(4.594636, -74.115737), new Ubicacion(4.575985, -74.093936), new Ubicacion(4.585568, -74.137538), new Ubicacion(4.541590, -74.117625), 18, "Rafael Uribe");              
        //Ciudad Bolivar
        Localidad ciudadBolivar = new Localidad(new Ubicacion(4.599752, -74.152629), new Ubicacion(4.490671, -74.119273), new Ubicacion(4.587184, -74.198924), new Ubicacion(4.469108, -74.179355), 19, "Ciudad Bolivar");            

        localidades.add(usaquen);
        localidades.add(chapinero);
        localidades.add(candelaria);
        localidades.add(santafe);
        localidades.add(sanCristobal);
        localidades.add(usme);
        localidades.add(tunjuelito);
        localidades.add(bosa);
        localidades.add(kenedy);
        localidades.add(fontibon);
        localidades.add(engativa);
        localidades.add(suba);
        localidades.add(barriosUnidos);
        localidades.add(teusaquillo);
        localidades.add(martires);
        localidades.add(antonio);
        localidades.add(puente);
        localidades.add(rafaelUribe);
        localidades.add(ciudadBolivar);
     }
    //4.706553, -74.035508
    
    
    @Override
    public Localidad getLocalidad(Ubicacion ubicacion){
        Localidad retorno = null;
        Localidad seleccionada;
        for(int i=0; i<localidades.size(); i++){
            seleccionada = localidades.get(i);
            if(   (estaEntreLatitud(ubicacion, seleccionada.getCoordenada1(), seleccionada.getCoordenada3()) || estaEntreLatitud(ubicacion, seleccionada.getCoordenada2(), seleccionada.getCoordenada4()) )   && 
                   (  estaEntreLongitud(ubicacion,seleccionada.getCoordenada2() , seleccionada.getCoordenada1()) || estaEntreLongitud(ubicacion, seleccionada.getCoordenada4(),seleccionada.getCoordenada3() )  ) ){
                return seleccionada;
            }
        }
        
        return new Localidad(ubicacion, ubicacion, ubicacion, ubicacion, 22, "Otra");
    }
    
    /**
     * Metodo encargado de evaluar la latitud (Latitud 1 es mayor a latitud2)
     * @param aEvaluar
     * @param ubicacion1
     * @param ubicacion2
     * @return boolean <br>
     * <b>true</b>: Si la latitud a evaluar esta entre las dos ubicaciones<br>
     * <b>false</b>: SI la latitud no esta entre las dos ubicaciones<br>
     */
    public boolean estaEntreLatitud(Ubicacion aEvaluar, Ubicacion ubicacion1, Ubicacion ubicacion2){
        double latitudEvaluar = aEvaluar.getLatitud();        
        double latitud1 = ubicacion1.getLatitud();
        double latitud2 = ubicacion2.getLatitud();
        
        if(latitud2>latitud1){
            latitud1 = latitud2;
            latitud2 = ubicacion1.getLatitud();
        }
        
        return latitudEvaluar<=latitud1 && latitudEvaluar>=latitud2;
    }
    
    
        /**
     * Metodo encargado de evaluar la longitud (longitud 1 es mayor a longitud 2)
     * @param aEvaluar
     * @param ubicacion1
     * @param ubicacion2
     * @return boolean <br>
     * <b>true</b>: Si la longitud a evaluar esta entre las dos ubicaciones<br>
     * <b>false</b>: SI la longitud no esta entre las dos ubicaciones<br>
     */
    public boolean estaEntreLongitud(Ubicacion aEvaluar, Ubicacion ubicacion1, Ubicacion ubicacion2){
        double longitudEvaluar = aEvaluar.getLongitud();
        double longitud1 = ubicacion1.getLongitud();
        double longitud2 = ubicacion2.getLongitud();
        
        if(longitud2>longitud1){
            longitud1 = longitud2;
            longitud2 = ubicacion1.getLongitud();
        }
        
        return longitudEvaluar<=longitud1 && longitudEvaluar>=longitud2;
    }
}
