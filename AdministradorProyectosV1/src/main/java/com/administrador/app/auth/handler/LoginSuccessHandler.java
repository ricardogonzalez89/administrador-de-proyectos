package com.administrador.app.auth.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.support.SessionFlashMapManager;

/**
 * Clase que se encarga de la autenticacion de los usuarios en el login
 * @author: Ricardo Gonzalez Ceballos
 * @version: 1.0
 */
@Component
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{

	 /**
     * El metodo onAuthenticationSuccess para validar el acceso al sistema  
     * @param request contiene informacion de la peticion http
     * @param response se usa para enviar respuesta http
     * @param authentication este parametro realiza la autenticacion en el sistema
     */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		SessionFlashMapManager flashMapManager = new SessionFlashMapManager();
		
		FlashMap flashMap = new FlashMap();
		
		flashMap.put("success", "Hola " +authentication.getName()+ ", haz iniciado sesión con éxito!");
		
		flashMapManager.saveOutputFlashMap(flashMap, request, response);
		
		if(authentication != null) {
			logger.info("El usuario '"+authentication.getName()+"' ha iniciado sesión con éxito");
		}
		
		super.onAuthenticationSuccess(request, response, authentication);
	}

	
}
