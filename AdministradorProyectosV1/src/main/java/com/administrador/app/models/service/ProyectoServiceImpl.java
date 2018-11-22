package com.administrador.app.models.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.administrador.app.models.dao.IProyectoDao;
import com.administrador.app.models.entity.Proyecto;

/**
 * Servicio que contiene la logica de negocios y las transacciones 
 * @author: Ricardo Gonzalez Ceballos
 * @version: 1.0
 */
@Service
public class ProyectoServiceImpl implements IProyectoService {
	
	@Autowired
	IProyectoDao iProyectoDao;
	
	 /**
     * Servicio para llamar el metodo findAll en el dao(Consulta todos los registros de la tabla proyectos)
     * @return Regresa una lista objectos de tipo Proyecto de todos los proyectos que existen en la base de datos  
     */
	@Override
	@Transactional(readOnly = true)
	public List<Proyecto> findAll() {
		// TODO Auto-generated method stub
		return (List<Proyecto>) iProyectoDao.findAll();
	}

	/**
     * Servicio para llamar el metodo save en el dao (Inserta un registro a la tabla proyectos)
     * @param Recibe un objecto de tipo proyecto para hacer la insercion en la base de datos
     */
	@Override
	@Transactional
	public void save(Proyecto proyecto) {
		iProyectoDao.save(proyecto);
		
	}

	/**
     * Servicio para llamar el metodo findById en el dao (Busca un registro por el id en la tabla proyectos )
     * @param Recibe un id de tipo long que corresponde al proyecto que se quiere buscar en la base de datos
     * @return Regresa un objeto de tipo proyecto si es que existe en la base de datos
     */
	@Override
	@Transactional(readOnly = true)
	public Optional<Proyecto> findOne(Long id) {
		// TODO Auto-generated method stub
		return iProyectoDao.findById(id);
	}
	
	/**
     * Servicio para llamar el metodo deleteById en el dao (Elimina un registro en la tabla proyectos con respecto al id)  
     * @param Recibe un id de tipo long que corresponde al proyecto que se quiere eliminar   
     */
	@Override
	@Transactional
	public void delete(Long id) {
		iProyectoDao.deleteById(id);
		
	}

}
