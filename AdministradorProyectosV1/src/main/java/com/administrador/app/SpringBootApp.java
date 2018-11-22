package com.administrador.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase que define la aplicacion como aplicacion de spring boot
 * @author: Ricardo Gonzalez Ceballos
 * @version: 1.0
 */
@SpringBootApplication
public class SpringBootApp implements CommandLineRunner {

	/**
     * metodo main para ejecutar la aplicacion Spring boot
     * @param args argumentos que recibe la aplicacion
     */
	public static void main(String[] args) {
		SpringApplication.run(SpringBootApp.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}
}
