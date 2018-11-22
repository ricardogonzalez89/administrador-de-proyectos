package com.administrador.app.controllers;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controlador para peticiones que se realicen de la ruta /login 
 * @author: Ricardo Gonzalez Ceballos
 * @version: 1.0
 */

@Controller
public class LoginController {

	 /**
     * Metodo para la validacion del login y envio de mensajes con respecto a los parametros recibidos 
     * @param error . parametro para saber si existion un error
     * @param logout parametros para saber si se esta cerrando la session
     * @param model parametro para enviar datos en el modelo
     * @param principal parametro para saber si ya se inicio sesion
     * @param flash  parametro para enviar mensajes a la vista
     * @return retorna un tipo string que es el nombre de la vista que se cargara al terminar la peticion
     */
	@GetMapping("/login")
	public String login(@RequestParam(value="error", required=false) String error,
			@RequestParam(value="logout", required = false) String logout,
			Model model, Principal principal, RedirectAttributes flash) {
		
		if(principal != null) {
			flash.addFlashAttribute("info", "Ya ha inciado sesión anteriormente");
			return "redirect:/";
		}
		
		if(error != null) {
			model.addAttribute("error", "Error en el login: Nombre de usuario o contraseña incorrecta, por favor vuelva a intentarlo!");
		}
		
		if(logout != null) {
			model.addAttribute("success", "Ha cerrado sesión con éxito!");
		}
		
		return "login";
	}
}
