package com.proyecto.damii.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.damii.model.dto.BoletaDto;
import com.proyecto.damii.model.entity.Boleta;
import com.proyecto.damii.model.payload.MensajeResponse;
import com.proyecto.damii.service.IBoletaService;

@RestController
@RequestMapping("/api/v1")

public class BoletaController {
	
	@Autowired
	private IBoletaService boletaService;
	
	
	@GetMapping("boletas")	
	public ResponseEntity<?> showAll() {
		List<Boleta> getList = boletaService.listAll();
		
		if(getList == null) {
			return new ResponseEntity<>(
					MensajeResponse.builder()
						.mensaje("No hay registros")
						.object(null)
						.build()
					,HttpStatus.OK);
			
		}
		
		return new ResponseEntity<>(MensajeResponse.builder()
						.mensaje("")
						.object(getList)
						.build()
				, HttpStatus.OK);
	}
	
	
	
	
	
	@PostMapping("boleta")
	public ResponseEntity<?> create(@RequestBody BoletaDto boletaDto) {	
		
		Boleta boletaSave = null;
	
		try {
			
			boletaSave = boletaService.save(boletaDto);
			
			return new ResponseEntity<>(MensajeResponse.builder()
					.mensaje("Guardado Correctamente")
					.object(BoletaDto.builder()
							.cod_boleta(boletaSave.getCod_boleta())
							.totalPagar(boletaSave.getTotalPagar())
							.usuario(boletaSave.getUsuario())
							.build())
							.build(), HttpStatus.CREATED);
			
		}catch (DataAccessException exDt ){
		
			return new ResponseEntity<>(MensajeResponse.builder()
					.mensaje(exDt.getMessage())
					.object(null)
					.build(), HttpStatus.METHOD_NOT_ALLOWED);
			
		}
		
		
			
	}
	
	@PutMapping("boleta/{id}")
	public ResponseEntity<?> update(@RequestBody BoletaDto boletaDto, @PathVariable Integer id) {

		Boleta boletaUpdate = null;

		try {
			
			//Boleta findBoleta = boletaService.findById(id);
			
			if(boletaService.existsById(id)) {
				boletaDto.setCod_boleta(id);
				
				boletaUpdate = boletaService.save(boletaDto);

				return new ResponseEntity<>(MensajeResponse.builder()
									.mensaje("Actualizado Correctamente")
									.object(BoletaDto.builder()
									.cod_boleta(boletaUpdate.getCod_boleta())
									.totalPagar(boletaUpdate.getTotalPagar())
									.usuario(boletaUpdate.getUsuario())
									
									.build())
									.build(), HttpStatus.CREATED);
			}else {
				
				return new ResponseEntity<>(
						MensajeResponse.builder()
							.mensaje("El registro que intenta actualizar no se encuentra en la base de datos")
							.object(null)
							.build()
						, HttpStatus.NOT_FOUND);	
			}

		} catch (DataAccessException exDt) {

			return new ResponseEntity<>(
					MensajeResponse.builder()
						.mensaje(exDt.getMessage())
						.object(null)
						.build()
					, HttpStatus.METHOD_NOT_ALLOWED);

		}
	}
		
	
	
	
	
	@DeleteMapping("boleta/{id}")		
	public ResponseEntity<?> delete(@PathVariable Integer id) {

		try {
			Boleta boletaDelete = boletaService.findById(id);
			boletaService.delete(boletaDelete);
			return new ResponseEntity<>(boletaDelete, HttpStatus.NO_CONTENT);
			
		}catch (DataAccessException exDt ){
			
			return new ResponseEntity<>(MensajeResponse.builder()
					.mensaje(exDt.getMessage())
					.object(null)
					.build(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}

	
	
	@GetMapping("boleta/{id}")	
	public ResponseEntity<?> showById(@PathVariable Integer id) {
		Boleta boleta = boletaService.findById(id);
		
		
		if(boleta == null) {
			return new ResponseEntity<>(
					MensajeResponse.builder()
						.mensaje("El registro que intenta buscar, no existe")
						.object(null)
						.build()
					,HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(MensajeResponse.builder()
						.mensaje("")
						.object(BoletaDto.builder()
						.cod_boleta(boleta.getCod_boleta())
						.totalPagar(boleta.getTotalPagar())
						.usuario(boleta.getUsuario())
						
						.build())
						.build()
				, HttpStatus.OK);
	}
		
		
}



