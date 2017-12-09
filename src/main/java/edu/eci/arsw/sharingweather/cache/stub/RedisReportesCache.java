/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.sharingweather.cache.stub;

import edu.eci.arsw.sharingweather.cache.CacheNotFoundException;
import edu.eci.arsw.sharingweather.cache.ReportesCache;
import edu.eci.arsw.sharingweather.model.LocalidadesBogota;
import edu.eci.arsw.sharingweather.model.ReporteClima;
import edu.eci.arsw.sharingweather.model.Ubicacion;
import edu.eci.arsw.sharingweather.model.Usuario;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 *
 * @author JuanArevaloMerchan
 */
@Service
public class RedisReportesCache implements ReportesCache{
    private static final double DISTANCIAMINIMA = 0.7;
    private static final int CANTIDADREPORTESMINIMO = 3;
    
    @Autowired
    private RedisTemplate<String, String> template;
    @Autowired
    private SimpMessagingTemplate msgt;
    @Autowired
    private LocalidadesBogota localidades = null;
    
    @Override
    public void saveReporteClima(ReporteClima clima, Usuario usuario) throws CacheNotFoundException {
        ArrayList<ReporteClima> objList;
        boolean encontro = false;
        Long llaveActual;
        int numeroLocalidad;
        HashMap<Long,ArrayList<ReporteClima>> climasNoPublicados = getReportesClimaSinPublicar();
        HashMap<Long,ArrayList<ReporteClima>> climasPublicados = getReportesClimaPublicar();
        
        
//Validar primero en climas no publicados
        for (Map.Entry<Long, ArrayList<ReporteClima>> entry : climasNoPublicados.entrySet()) {
            objList = entry.getValue();
            llaveActual = entry.getKey();
            for (int i = 0; i < objList.size(); i++) {
                if (objList.get(i).getUbicacion().distanciaEntreUbicaciones(clima.getUbicacion()) <= DISTANCIAMINIMA && clima.getClima().equals(objList.get(i).getClima())) {
                    if (clima.getUsuario().getNombreUsuario().equals(objList.get(i).getUsuario().getNombreUsuario())) {
                        throw new CacheNotFoundException("Ya has publicado un reporte en la misma zona y con el mismo clima.");
                    }
                    objList.add(clima);
                    if (objList.size() >= CANTIDADREPORTESMINIMO) {
                        String repor = "";
                        for(int j=0; j<objList.size(); j++){
                            if(j>0){
                                repor = repor + ",";
                            }
                            repor = repor +"{'latitud':'"+objList.get(j).getUbicacion().getLatitud()+"', 'longitud':'"+objList.get(j).getUbicacion().getLongitud()+"', 'usuario':'"+objList.get(j).getUsuario().getNombreUsuario()+"','clima':'"+objList.get(j).getClima()+"'}";
                        }
                        repor = "{rep:["+repor+"]}";
                        Random aleatorio = new Random(System.currentTimeMillis());
                        int intAletorio = aleatorio.nextInt(777777777);
                        template.opsForHash().put("publicados",String.valueOf(intAletorio) ,repor);
//                        template.expire(repor, 0, TimeUnit.HOURS)
                        template.opsForHash().delete("nopublicados",llaveActual.toString());
                        //PUBLICAR ZONA
                        msgt.convertAndSend("/topic/reporteClima", objList);
                        //Publicar Zona favorita
                        numeroLocalidad = localidades.getLocalidad(clima.getUbicacion()).getNumero();
                        if(numeroLocalidad!=22){
                           msgt.convertAndSend("/topic/regionFavorita."+numeroLocalidad, clima);  
                        }
                    } else {
                        String repor = "";
                        for(int j=0; j<objList.size(); j++){
                            if(j>0){
                                repor = repor + ",";
                            }
                            repor = repor +"{'latitud':'"+objList.get(j).getUbicacion().getLatitud()+"', 'longitud':'"+objList.get(j).getUbicacion().getLongitud()+"', 'usuario':'"+objList.get(j).getUsuario().getNombreUsuario()+"','clima':'"+objList.get(j).getClima()+"'}";
                        }
                        repor = "{rep:["+repor+"]}";
                        template.opsForHash().put("nopublicados",llaveActual.toString() ,repor);
                    }
                    encontro = true;
                    break;
                }
            }
            if (encontro) {
                break;
            }
        }
        //Validar en climas publicados       
        if (!encontro) {
            for (Map.Entry<Long, ArrayList<ReporteClima>> entry : climasPublicados.entrySet()) {
                objList = entry.getValue();
                llaveActual = entry.getKey();
                for (int i = 0; i < objList.size(); i++) {
                    if (objList.get(i).getUbicacion().distanciaEntreUbicaciones(clima.getUbicacion()) <= DISTANCIAMINIMA && clima.getClima().equals(objList.get(i).getClima())) {
                        if (clima.getUsuario().getNombreUsuario().equals(objList.get(i).getUsuario().getNombreUsuario())) {
                            throw new CacheNotFoundException("Ya has publicado un reporte en la misma zona y con el mismo clima.");
                        }
                        objList.add(clima);
                        String repor = "";
                        for(int j=0; j<objList.size(); j++){
                            if(j>0){
                                repor = repor + ",";
                            }
                            repor = repor +"{'latitud':'"+objList.get(j).getUbicacion().getLatitud()+"', 'longitud':'"+objList.get(j).getUbicacion().getLongitud()+"', 'usuario':'"+objList.get(j).getUsuario().getNombreUsuario()+"','clima':'"+objList.get(j).getClima()+"'}";
                        }
                        repor = "{rep:["+repor+"]}";
                        template.opsForHash().put("publicados",llaveActual.toString() ,repor);
                        encontro = true;
                        //PUBLICAR
                        msgt.convertAndSend("/topic/reporteClima", objList);
                        //Publicar Zona favorita
                        numeroLocalidad = localidades.getLocalidad(clima.getUbicacion()).getNumero();
                        if(numeroLocalidad!=22){
                           msgt.convertAndSend("/topic/regionFavorita."+numeroLocalidad, clima);  
                        }
                        break;
                    }
                }
                if (encontro) {
                    break;
                }
            }
        }
//Insertar
        if (!encontro) {
            Random aleatorio = new Random(System.currentTimeMillis());
            int intAletorio = aleatorio.nextInt(777777777);
            template.opsForHash().put("nopublicados",String.valueOf(intAletorio) ,"{'rep':[{'latitud':'"+clima.getUbicacion().getLatitud()+"', 'longitud':'"+clima.getUbicacion().getLongitud()+"', 'usuario':'"+clima.getUsuario().getNombreUsuario()+"','clima':'"+clima.getClima()+"'}]}");
        }
    }

    @Override
    public HashMap<Long, ArrayList<ReporteClima>> getReportesClimaPublicar() throws CacheNotFoundException {
//        template.execute( new SessionCallback< List< Object > >() {
//	    @SuppressWarnings("unchecked")
//            @Override
//            public< K, V > List<Object> execute( final RedisOperations< K, V > operations ) throws DataAccessException {
//                operations.watch( ( K )"longitud");
//                operations.watch( ( K )"latitud");
//                operations.watch( ( K )"clima");
//                operations.watch( ( K )"usuario");
//                operations.multi();                         
//                longitud = template.opsForList().range("longitud", 0, -1);
//                latitud = template.opsForList().range("latitud", 0, -1);
//                clima = template.opsForList().range("clima", 0, -1);
//                usuario = template.opsForList().range("usuario", 0, -1);
//                
//                System.out.println("Esto esta:"+longitud.get(0));
//                return operations.exec();
//            }
//	} );

//System.out.println("edu.eci.arsw.sharingweather.cache.stub.RedisReportesCache.getReportesClimaPublicar()"+longitud.size());
//        template.setEnableTransactionSupport(true);
//        longitud = template.opsForList().range("longitud", 0, -1);
//        latitud = template.opsForList().range("latitud", 0, -1);
//        clima = template.opsForList().range("clima", 0, -1);
//        usuario = template.opsForList().range("usuario", 0, -1);
//        reporte = template.opsForList().range("reporte", 0, -1);
//        HashMap<Long, ArrayList<ReporteClima>> rep = new HashMap<>();
//        ReporteClima repoclim;
//        Usuario us = new Usuario();
//        ArrayList<ReporteClima> arregloClima;
//        numeroPublicados = 0;
//        for(int i=0; i<longitud.size(); i++){
//            us.setNombreUsuario(usuario.get(i));
//            repoclim = new ReporteClima(new Ubicacion(Double.parseDouble(latitud.get(i)), Double.parseDouble(longitud.get(i))), 0, us, clima.get(i));
//            if(rep.containsKey(Long.valueOf(reporte.get(i)))){
//                arregloClima = rep.get(Long.valueOf(reporte.get(i)));
//                arregloClima.add(repoclim);
//                rep.put(Long.valueOf(reporte.get(i)), arregloClima);
//            }
//            else{
//                arregloClima = new ArrayList<>();
//                arregloClima.add(repoclim);
//                rep.put(Long.valueOf(reporte.get(i)), arregloClima);
//            }
//            
//            if(Long.valueOf(reporte.get(i))>numeroPublicados){
//                numeroPublicados = Long.valueOf(reporte.get(i));
//            }
//        }
        
//        List<String> aaa = template.opsForList().range("prueba", 0, -1);
//        System.out.println("1: "+aaa.get(0));
//        
//        JSONObject jsonObject = new JSONObject(aaa.get(0));
//        System.out.println("2: "+jsonObject);
//        
//        JSONArray arregloJs = jsonObject.getJSONArray("rep");
////        System.out.println("3: "+jsonObject.getJSONObject("rep"));
//        System.out.println("4: "+arregloJs);
//        System.out.println("5: "+arregloJs.get(0));
//        System.out.println("6: "+jsonObject.get("rep"));
//        System.out.println("7: "+jsonObject.getJSONArray("rep"));
//        System.out.println("8: "+jsonObject.getJSONArray("rep").getJSONObject(0).getString("clima"));
//        Object a = arregloJs.get(0);
//        
        
        
//        System.out.println("9: "+template.opsForHash().entries("prueba2"));
        Map<Object,Object> publicados = null;
        try{
            publicados = template.opsForHash().entries("publicados");
        }catch(Exception e){
            throw new CacheNotFoundException("Se ha perdido conexión con la base de datos. "+e.getMessage());
        }

//        System.out.println("10: "+publicados.get("1"));
//        System.out.println("11: "+publicados.get("2"));
//        JSONObject jsonObject2 = new JSONObject(publicados.get("2").toString());
//        JSONArray arregloJs2 = jsonObject2.getJSONArray("rep");
//        System.out.println("12: "+arregloJs2);
//        System.out.println("13: "+arregloJs2.getJSONObject(0));
//        System.out.println("14: "+arregloJs2.getJSONObject(0).getString("clima"));
        
        HashMap<Long, ArrayList<ReporteClima>> rep = new HashMap<>();
        Object objList;
        Object llaveActual;
        ReporteClima report;
        Usuario usuari = new Usuario();
        JSONObject objetoJSON;
        JSONArray arregloJSON;
        JSONObject arregloJSON2;
        ArrayList<ReporteClima> arregloClima;
        for (Map.Entry<Object,Object> entry : publicados.entrySet()) {
            objList = entry.getValue();
            llaveActual = entry.getKey();
            objetoJSON = new JSONObject(objList.toString());
            arregloJSON = objetoJSON.getJSONArray("rep");
            arregloClima = new ArrayList<>();
            for(int i=0; i<arregloJSON.length(); i++){
                arregloJSON2 = arregloJSON.getJSONObject(i);
                usuari.setNombreUsuario(arregloJSON2.get("usuario").toString());
//                System.out.println("14: "+llaveActual);
//                System.out.println("15: "+arregloJSON2);
//                System.out.println("Latitud: "+arregloJSON2.get("latitud"));
//                System.out.println("Longitud: "+arregloJSON2.get("longitud"));
//                System.out.println("Clima: "+arregloJSON2.get("clima"));
//                System.out.println("Usuario: "+arregloJSON2.get("usuario"));
                report = new ReporteClima(new Ubicacion(Double.parseDouble(arregloJSON2.get("latitud").toString()), Double.parseDouble(arregloJSON2.get("longitud").toString())), 0, usuari, arregloJSON2.get("clima").toString());
                arregloClima.add(report);
            }
            rep.put(Long.parseLong(llaveActual.toString()), arregloClima);
        }
        return rep;
    }

    @Override
    public HashMap<Long, ArrayList<ReporteClima>> getReportesClimaSinPublicar() throws CacheNotFoundException {
//        longitud = template.opsForList().range("nolongitud", 0, -1);
//        latitud = template.opsForList().range("nolatitud", 0, -1);
//        clima = template.opsForList().range("noclima", 0, -1);
//        usuario = template.opsForList().range("nousuario", 0, -1);
//        reporte = template.opsForList().range("noreporte", 0, -1);
//        HashMap<Long, ArrayList<ReporteClima>> rep = new HashMap<>();
//        ReporteClima repoclim;
//        Usuario us = new Usuario();
//        ArrayList<ReporteClima> arregloClima;
//        numeroNoPublicados = 0;
//        for(int i=0; i<longitud.size(); i++){
//            us.setNombreUsuario(usuario.get(i));
//            repoclim = new ReporteClima(new Ubicacion(Double.parseDouble(latitud.get(i)), Double.parseDouble(longitud.get(i))), 0, us, clima.get(i));
//            if(rep.containsKey(Long.valueOf(reporte.get(i)))){
//                arregloClima = rep.get(Long.valueOf(reporte.get(i)));
//                arregloClima.add(repoclim);
//                rep.put(Long.valueOf(reporte.get(i)), arregloClima);
//            }
//            else{
//                arregloClima = new ArrayList<>();
//                arregloClima.add(repoclim);
//                rep.put(Long.valueOf(reporte.get(i)), arregloClima);
//            }
//            
//            if(Long.valueOf(reporte.get(i))>numeroNoPublicados){
//                numeroNoPublicados = Long.valueOf(reporte.get(i));
//            }
//        }



        Map<Object,Object> publicados = null;
        try{
            publicados = template.opsForHash().entries("nopublicados");
        }catch(Exception e){
            throw new CacheNotFoundException("Se ha perdido conexión con la base de datos. "+e.getMessage());
        }
        HashMap<Long, ArrayList<ReporteClima>> rep = new HashMap<>();
        Object objList;
        Object llaveActual;
        ReporteClima report;
        Usuario usuari = new Usuario();
        JSONObject objetoJSON;
        JSONArray arregloJSON;
        JSONObject arregloJSON2;
        ArrayList<ReporteClima> arregloClima;
        for (Map.Entry<Object,Object> entry : publicados.entrySet()) {
            objList = entry.getValue();
            llaveActual = entry.getKey();
            objetoJSON = new JSONObject(objList.toString());
            arregloJSON = objetoJSON.getJSONArray("rep");
            arregloClima = new ArrayList<>();
            for(int i=0; i<arregloJSON.length(); i++){
                arregloJSON2 = arregloJSON.getJSONObject(i);
                usuari.setNombreUsuario(arregloJSON2.get("usuario").toString());
                report = new ReporteClima(new Ubicacion(Double.parseDouble(arregloJSON2.get("latitud").toString()), Double.parseDouble(arregloJSON2.get("longitud").toString())), 0, usuari, arregloJSON2.get("clima").toString());
                arregloClima.add(report);
            }
            rep.put(Long.parseLong(llaveActual.toString()), arregloClima);
        }
        return rep; 
    }
    
}
