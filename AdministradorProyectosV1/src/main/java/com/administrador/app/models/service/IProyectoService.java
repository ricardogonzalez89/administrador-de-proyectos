package com.administrador.app.models.service;

import java.util.List;
import java.util.Optional;

import com.administrador.app.models.entity.Proyecto;

/**
 * Interface del servicio proyectos 
 * @author: Ricardo Gonzalez Ceballos
 * @version: 1.0
 */
public interface IProyectoService {

	List<Proyecto> findAll();

	void save(Proyecto cliente);

	Optional<Proyecto> findOne(Long id);

	void delete(Long id);

}
