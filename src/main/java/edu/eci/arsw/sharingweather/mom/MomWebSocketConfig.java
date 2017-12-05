/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.sharingweather.mom;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 *
 * @author JuanArevaloMerchan
 */
@Configuration
@EnableWebSocketMessageBroker
public class MomWebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {
    @Value("${host}")
    private String host;
    
    @Value("${puerto}")
    private int puerto;
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableStompBrokerRelay("/topic/").setRelayHost(host).setRelayPort(puerto).
                setClientLogin("saueihsf").
                setClientPasscode("scm9EgSU6tsqMkbs9ktwASuB-uYr17To").
                setSystemLogin("saueihsf").
                setSystemPasscode("scm9EgSU6tsqMkbs9ktwASuB-uYr17To").
                setVirtualHost("saueihsf");
        config.setApplicationDestinationPrefixes("/reporteClima");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/stompendpoint").setAllowedOrigins("*").withSockJS();

    }
}
