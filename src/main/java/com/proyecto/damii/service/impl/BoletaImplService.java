package com.proyecto.damii.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.damii.model.dao.BoletaDao;
import com.proyecto.damii.model.dto.BoletaDto;
import com.proyecto.damii.model.entity.Boleta;
import com.proyecto.damii.service.IBoletaService;



@Service
public class BoletaImplService implements IBoletaService {

	
	@Autowired
	private BoletaDao boletaDao;
	
	@Transactional
	@Override
	public Boleta save(BoletaDto boletaDto) {
		Boleta boleta = Boleta.builder()
				.cod_boleta(boletaDto.getCod_boleta())
				.totalPagar(boletaDto.getTotalPagar())
				.usuario(boletaDto.getUsuario())
				.build();
		return boletaDao.save(boleta);
	}
	

	@Transactional(readOnly = true)
	@Override
	public Boleta findById(Integer id) {
		return boletaDao.findById(id).orElse(null);
	}

	
	@Transactional
	@Override
	public void delete(Boleta boleta) {
		boletaDao.delete(boleta);
		
	}


	@Override
	public boolean existsById(Integer id) {
		// TODO Auto-generated method stub
		return boletaDao.existsById(id);
	}


	@Override
	public List<Boleta> listAll() {
		// TODO Auto-generated method stub
		return (List) boletaDao.findAll();
	}



	

}
