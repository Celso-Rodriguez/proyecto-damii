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

import com.proyecto.damii.model.dto.MenuDto;
import com.proyecto.damii.model.entity.Menu;
import com.proyecto.damii.model.payload.MensajeResponse;
import com.proyecto.damii.service.IMenuService;

@RestController
@RequestMapping("/api/v1")

public class MenuController {
	
	@Autowired
	private IMenuService menuService;
	
	
	@GetMapping("menus")	
	public ResponseEntity<?> showAll() {
		List<Menu> getList = menuService.listAll();
		
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
	
	
	
	
	
	@PostMapping("menu")
	public ResponseEntity<?> create(@RequestBody MenuDto menuDto) {	
		
		Menu menuSave = null;
	
		try {
			
			menuSave = menuService.save(menuDto);
			
			return new ResponseEntity<>(MensajeResponse.builder()
					.mensaje("Guardado Correctamente")
					.object(MenuDto.builder()
							.cod_menu(menuSave.getCod_menu())
							.nombre(menuSave.getNombre())
							.descripcion(menuSave.getDescripcion())
							.precio(menuSave.getPrecio())
							.imagen(menuSave.getImagen())
							.build())
							.build(), HttpStatus.CREATED);
			
		}catch (DataAccessException exDt ){
		
			return new ResponseEntity<>(MensajeResponse.builder()
					.mensaje(exDt.getMessage())
					.object(null)
					.build(), HttpStatus.METHOD_NOT_ALLOWED);
			
		}
		
		
			
	}
	
	@PutMapping("menu/{id}")
	public ResponseEntity<?> update(@RequestBody MenuDto menuDto, @PathVariable Integer id) {

		Menu menuUpdate = null;

		try {
			
			//Menu findMenu = menuService.findById(id);
			
			if(menuService.existsById(id)) {
				menuDto.setCod_menu(id);
				
				menuUpdate = menuService.save(menuDto);

				return new ResponseEntity<>(MensajeResponse.builder()
									.mensaje("Actualizado Correctamente")
									.object(MenuDto.builder()
									.cod_menu(menuUpdate.getCod_menu())
									.nombre(menuUpdate.getNombre())
									.descripcion(menuUpdate.getDescripcion())
									.precio(menuUpdate.getPrecio())
									.imagen(menuUpdate.getImagen())
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
		
	
	
	@DeleteMapping("menu/{id}")		
	public ResponseEntity<?> delete(@PathVariable Integer id) {

		try {
			Menu menuDelete = menuService.findById(id);
			menuService.delete(menuDelete);
			return new ResponseEntity<>(menuDelete, HttpStatus.NO_CONTENT);
			
		}catch (DataAccessException exDt ){
			
			return new ResponseEntity<>(MensajeResponse.builder()
					.mensaje(exDt.getMessage())
					.object(null)
					.build(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}

	
	
	@GetMapping("menu/{id}")	
	public ResponseEntity<?> showById(@PathVariable Integer id) {
		Menu menu = menuService.findById(id);
		
		
		if(menu == null) {
			return new ResponseEntity<>(
					MensajeResponse.builder()
						.mensaje("El registro que intenta buscar, no existe")
						.object(null)
						.build()
					,HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(MensajeResponse.builder()
						.mensaje("")
						.object(MenuDto.builder()
						.cod_menu(menu.getCod_menu())
						.nombre(menu.getNombre())
						.descripcion(menu.getDescripcion())
						.precio(menu.getPrecio())
						.imagen(menu.getImagen())
						.build())
						.build()
				, HttpStatus.OK);
	}
		
		
}



