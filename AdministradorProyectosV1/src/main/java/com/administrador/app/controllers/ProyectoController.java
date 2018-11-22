package com.administrador.app.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.administrador.app.models.entity.Proyecto;
import com.administrador.app.models.service.IProyectoService;


/**
 * Controller que se encarga de recibir las peticiones desde el front para mostrar las diferentes vistas, apis rest, recibir y enviar los modelos
 * @author: Ricardo Gonzalez Ceballos
 * @version: 1.0
 */
@Controller
@SessionAttributes("proyecto")
public class ProyectoController {

	@Autowired
	private IProyectoService proyectoService;
	
	
	/**
     * metodo que se ejecuta al hacer una peticion desde la ruta /
     * @param model recibe un modelo para enviar informacion a la vista
     * @return retorna un string que es el nombre de la vista que sera mostrara despues de la peticion
     */
	@RequestMapping(value = "/")
	public String inicio(Map<String, Object> model) {
		model.put("titulo1", "Administrador de proyectos");
		model.put("parrafo", "Sistema de gestion de proyectos de desarrollo "
				+ "en cual se pueden generar nuevos proyectos, editar proyectos existentes y eliminar proyectos"
				+ ". Estas acciones estan limitadas por roles ya que solo los administradores podran hacer las operaciones mencionada"
				+ ". Los usuarios de consulta solo podran consultar los proyectos cargados y ver detalles de ellos"
				+ ". Tambien se expone un API Rest el cual muestra una lista de objectos tipo proyecto en formato JSON.");
		return "index";
	}
	
	/**
     * metodo que se ejecuta al hacer una peticion desde la ruta /auth/consultaproyectosrest expone un API Rest
     * @return retorna un objecto JSON de la informacion contenida en la tabala proyectos
     */
	@GetMapping(value = "/auth/consultaproyectosrest")
	public @ResponseBody List<Proyecto> consultarRestAuth() {
		return proyectoService.findAll();
	}
	
	/**
     * metodo que se ejecuta al hacer una peticion desde la ruta /open/consultaproyectosrest expone un API Rest
     * @return retorna un objecto JSON de la informacion contenida en la tabala proyectos
     */
	@GetMapping(value = "/open/consultaproyectosrest")
	public @ResponseBody List<Proyecto> consultarRestOpen() {
		return proyectoService.findAll();
	}
	
	/**
     * metodo que se ejecuta al hacer una peticion desde la ruta /consultaproyectos y envia a la vista la lista de proyectos existentes en la base de datos
     * @param model recibe un modelo para enviar informacion a la vista
     * @return retorna un string que es el nombre de la vista que sera mostrara despues de la peticion
     */
	@RequestMapping(value = "/consultaproyectos", method = RequestMethod.GET)
	public String consultar(Model model) {
		model.addAttribute("titulo", "Listado de proyectos");
		model.addAttribute("proyectos", proyectoService.findAll());
		return "consultaproyectos";
	}
	
	/**
     * metodo que se ejecuta al hacer una peticion desde la ruta /eliminarproyecto y elimina el registro en la tabla proyectos con el id recibido 
     * @param id identificador del registro a eliminar de la tabla proyectos
     * @param flash parametro para enviar un mensaje de exito a la vista
     * @return retorna un string que es el nombre de la vista que sera mostrara despues de la peticion
     */
	@RequestMapping(value="/eliminarproyecto/{id}")
	public String eliminar(@PathVariable(value="id") Long id,RedirectAttributes flash) {
		
		if(id > 0) {
			proyectoService.delete(id);
			flash.addFlashAttribute("success", "Proyecto eliminado con éxito!");
		}
		
		return "redirect:/consultaproyectos";
	}
	
	/**
     * metodo que se ejecuta al hacer una peticion desde la ruta /capturaproyecto  
     * @param model recibe un modelo para enviar informacion a la vista
     * @return retorna un string que es el nombre de la vista que sera mostrara despues de la peticion
     */
	@RequestMapping(value = "/capturaproyecto")
	public String crear(Map<String, Object> model) {

		Proyecto proyecto = new Proyecto();
		model.put("proyecto", proyecto);
		model.put("titulo", "Captura un nuevo proyecto");
		model.put("labelBoton", "Guardar");
		return "capturaproyecto";
	}
	
	/**
     * metodo que se ejecuta al hacer una peticion desde la ruta /guardar  
     * @param proyecto recibe un objeto tipo proyecto para insertarlo en la tabla proyectos de la base de datos
     * @param result parametro para ayudar a validar los campos capturados o errores en la peticion
     * @param model recibe un modelo para enviar informacion a la vista
     * @param status permite completar el proceso de la session
     * @param flash parametro para enviar un mensaje de exito a la vista 
     * @return retorna un string que es el nombre de la vista que sera mostrara despues de la peticion
     */
	@RequestMapping(value = "/guardar", method = RequestMethod.POST)
	public String guardar(@Valid Proyecto proyecto, BindingResult result, Model model, SessionStatus status,RedirectAttributes flash) {

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Captura un nuevo proyecto");
			model.addAttribute("labelBoton", "Guardar");
			return "capturaproyecto";
		}

		proyectoService.save(proyecto);
		status.setComplete();
		flash.addFlashAttribute("success", "Proyecto guardado con éxito!");
		return "redirect:consultaproyectos";
	}
	
	/**
     * metodo que se ejecuta al hacer una peticion desde la ruta /editarproyecto para mostrar informacion de un proyecto a editar
     * @param id identificador unico para consultar un proyecto 
     * @param model recibe un modelo para enviar informacion a la vista
     * @return retorna un string que es el nombre de la vista que sera mostrara despues de la peticion
     */
	@RequestMapping(value="/editarproyecto/{id}")
	public String editar(@PathVariable(value="id") Long id, Map<String, Object> model) {
		
		Optional<Proyecto> proyecto = null;
		
		if(id > 0) {
			proyecto = proyectoService.findOne(id);
		} else {
			return "redirect:/consultarproyectos";
		}
		model.put("proyecto", proyecto);
		model.put("titulo", "Editar proyecto");
		model.put("labelBoton", "Editar");
		return "capturaproyecto";
	}
	
	/**
     * metodo que se ejecuta al hacer una peticion desde la ruta /detalle para mostrar el detalle de un proyecto
     * @param id identificador unico para consultar un proyecto 
     * @param model recibe un modelo para enviar informacion a la vista
     * @return retorna un string que es el nombre de la vista que sera mostrara despues de la peticion
     */
	@RequestMapping(value="/detalle/{id}")
	public String detalle(@PathVariable(value="id") Long id, Map<String, Object> model) {
		
		Optional<Proyecto> proyecto = null;
		
		if(id > 0) {
			proyecto = proyectoService.findOne(id);
		} else {
			return "redirect:/consultarproyectos";
		}
		model.put("proyecto", proyecto.get());
		model.put("titulo", "Detalle del proyecto : " + proyecto.get().getNombre() );
		model.put("labelBoton", "Regresar");
		return "ver";
	}
}
