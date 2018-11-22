package com.administrador.app.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;


/**
 * Pojo que mapea la tabla en base de datos proyectos
 * @author: Ricardo Gonzalez Ceballos
 * @version: 1.0
 */
@Entity
@Table(name = "proyectos")
public class Proyecto implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 8226304587367506470L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String nombre;
	
	@NotBlank
	@Column(name="project_manager")
	private String projectManager;
	
	@NotBlank
	private String descripcion;
	
	@NotBlank
	private String desarrollador;
	

//	@NotBlank
	@Column(name = "fecha_entrega")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date fechaEntrega;
	
//	@NotBlank
	@Column(name = "fecha_inicio")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date fechaInicio;

	/**
	 * @return id retorna el identificador unico
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id identificador unico para asignar
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return retorna el nombre del proyecto
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre el nombre para asignar al proyecto
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return retorna el project  manager a cargo del proyecto
	 */
	public String getProjectManager() {
		return projectManager;
	}

	/**
	 * @param projectManager  projectManager para asignar al proeycto
	 */
	public void setProjectManager(String projectManager) {
		this.projectManager = projectManager;
	}

	/**
	 * @return retorna la descripcion del proyecto
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion  descripcion para asignar al proyecto
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return retorna el el desarrollador asignado al proyecto
	 */
	public String getDesarrollador() {
		return desarrollador;
	}

	/**
	 * @param desarrollador  desarrollador para asignar al proyecto
	 */
	public void setDesarrollador(String desarrollador) {
		this.desarrollador = desarrollador;
	}

	/**
	 * @return retorna la fechaEntrega
	 */
	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	/**
	 * @param fechaEntrega asigna la fechaEntrega del proyecto
	 */
	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	/**
	 * @return retorna la fechaInicio
	 */
	public Date getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * @param fechaInicio asigna la fechaInicio del proyecto
	 */
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	
	
	

}
