/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.sharingWheatherApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 * @author JuanArevaloMerchan y Stefany Moron
 */
@SpringBootApplication
@ComponentScan(basePackages = {"edu.eci.arsw.sharingWheather"})
public class SharingWeatherAPIApplication {
    public static void main(String[] args) {
	SpringApplication.run(SharingWeatherAPIApplication.class, args);
    }
}
