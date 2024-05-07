package com.proyecto.damii.service;

import java.util.List;

import com.proyecto.damii.model.dto.BoletaDto;
import com.proyecto.damii.model.entity.Boleta;

public interface IBoletaService {
	
	List<Boleta> listAll();
	
	Boleta save(BoletaDto boleta);
	
	Boleta findById(Integer id);
	
	void delete(Boleta boleta);
	
	boolean existsById(Integer id);

}
