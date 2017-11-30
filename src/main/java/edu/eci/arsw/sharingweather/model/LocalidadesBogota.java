/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.sharingweather.model;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author JuanArevaloMerchan
 */
public class LocalidadesBogota {
    private CopyOnWriteArrayList<Localidad> localidades = new CopyOnWriteArrayList<>();
    
    public LocalidadesBogota(){
        //Usaquen                                       //latitud   //longitud
        Localidad usaquen = new Localidad(new Ubicacion(4.825128, -74.034118), new Ubicacion(4.824097, -74.004789), new Ubicacion(4.686902, -74.054378), new Ubicacion(4.677236, -74.011291), 1, "Usaquen");
        //Chapinero
        Localidad chapinero = new Localidad(new Ubicacion(4.686397, -74.056800), new Ubicacion(4.663754, -73.992262), new Ubicacion(4.628336, -74.067964), new Ubicacion(4.613964, -74.004621), 2, "Chapinero");
        //Chapinero
        Localidad santafe = new Localidad(new Ubicacion(4.627452, -74.068549), new Ubicacion(4.613339, -73.993280), new Ubicacion(4.591951, -74.089067), new Ubicacion(4.556188, -74.008730), 3, "Santa fe");
    
        localidades.add(usaquen);
        localidades.add(chapinero);
        localidades.add(santafe);
    }
    
    
    public Localidad getLocalidad(Ubicacion ubicacion){
        Localidad retorno = null;
        Localidad seleccionada;
        for(int i=0; i<localidades.size(); i++){
            seleccionada = localidades.get(i);
            //Comparar 1 y 2 Longitud es la que varia
            //Comparar 1 y 3 Latitud es la que varia
//            if(seleccionada.getCoordenada1().getLongitud()){
//                
//            }
        }
        
        return retorno;
    }
}
