/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.sharingweather.cache.stub;

import edu.eci.arsw.sharingweather.cache.CacheNotFoundException;
import edu.eci.arsw.sharingweather.cache.ReportesCache;
import edu.eci.arsw.sharingweather.model.ReporteClima;
import edu.eci.arsw.sharingweather.model.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 *
 * @author JuanArevaloMerchan
 */
@Service
public class RedisReportesCache implements ReportesCache{
    private List<String> longitud = new ArrayList<>();
    
    
    @Autowired
    private StringRedisTemplate template; 

    @Override
    public void saveReporteClima(ReporteClima clima, Usuario usuario) throws CacheNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ConcurrentHashMap<Long, CopyOnWriteArrayList<ReporteClima>> getReportesClimaPublicar() throws CacheNotFoundException {
//        template.execute( new SessionCallback< List< Object > >() {
//	    @SuppressWarnings("unchecked")
//            @Override
//            public< K, V > List<Object> execute( final RedisOperations< K, V > operations ) throws DataAccessException {
//                operations.watch( ( K )"longitud");
//                operations.watch( ( K )"latitud");
//                operations.multi();                         
//                longitud = (ArrayList<Long>) operations.opsForList().range((K) "longitud", 0, -1);
//                return operations.exec();
//            }
//	} );
System.out.println("edu.eci.arsw.sharingweather.cache.stub.RedisReportesCache.getReportesClimaPublicar()"+longitud.size());
        
        longitud =  template.opsForList().range("longitud", 0, 5);
        System.out.println("edu.eci.arsw.sharingweather.cache.stub.RedisReportesCache.getReportesClimaPublicar()"+longitud.size());
        
        
        
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ConcurrentHashMap<Long, CopyOnWriteArrayList<ReporteClima>> getReportesClimaSinPublicar() throws CacheNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
