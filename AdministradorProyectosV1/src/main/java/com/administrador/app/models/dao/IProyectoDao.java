package com.administrador.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.administrador.app.models.entity.Proyecto;

/**
 * Interface que contiene los metodos crud para poder realizar operaciones con tabla proyecto en la base de datos
 * @author: Ricardo Gonzalez Ceballos
 * @version: 1.0
 */
public interface IProyectoDao extends CrudRepository<Proyecto, Long> {

}
