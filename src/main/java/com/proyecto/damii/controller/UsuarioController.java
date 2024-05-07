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

import com.proyecto.damii.model.dto.UsuarioDto;
import com.proyecto.damii.model.entity.Usuario;
import com.proyecto.damii.model.payload.MensajeResponse;
import com.proyecto.damii.service.IUsuarioService;

@RestController
@RequestMapping("/api/v1")

public class UsuarioController {
	
	@Autowired
	private IUsuarioService usuarioService;
	
	
	@GetMapping("usuarios")	
	public ResponseEntity<?> showAll() {
		List<Usuario> getList = usuarioService.listAll();
		
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
	
	
	
	
	
	@PostMapping("usuario")
	public ResponseEntity<?> create(@RequestBody UsuarioDto usuarioDto) {	
		
		Usuario usuarioSave = null;
	
		try {
			
			usuarioSave = usuarioService.save(usuarioDto);
			
			return new ResponseEntity<>(MensajeResponse.builder()
					.mensaje("Guardado Correctamente")
					.object(UsuarioDto.builder()
							.cod_usuario(usuarioSave.getCod_usuario())
							.nombre(usuarioSave.getNombre())
							.dni(usuarioSave.getDni())
							.telefono(usuarioSave.getTelefono())
							.correo(usuarioSave.getCorreo())
							.pwd(usuarioSave.getPwd())
							.build())
							.build(), HttpStatus.CREATED);
			
		}catch (DataAccessException exDt ){
		
			return new ResponseEntity<>(MensajeResponse.builder()
					.mensaje(exDt.getMessage())
					.object(null)
					.build(), HttpStatus.METHOD_NOT_ALLOWED);
			
		}
		
		
			
	}
	
	@PutMapping("usuario/{id}")
	public ResponseEntity<?> update(@RequestBody UsuarioDto usuarioDto, @PathVariable Integer id) {

		Usuario usuarioUpdate = null;

		try {
			
			//Usuario findUsuario = usuarioService.findById(id);
			
			if(usuarioService.existsById(id)) {
				usuarioDto.setCod_usuario(id);
				
				usuarioUpdate = usuarioService.save(usuarioDto);

				return new ResponseEntity<>(MensajeResponse.builder()
									.mensaje("Actualizado Correctamente")
									.object(UsuarioDto.builder()
									.cod_usuario(usuarioUpdate.getCod_usuario())
									.nombre(usuarioUpdate.getNombre())
									.dni(usuarioUpdate.getDni())
									.telefono(usuarioUpdate.getTelefono())
									.correo(usuarioUpdate.getCorreo())
									.pwd(usuarioUpdate.getPwd())
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
		
	
	
	@DeleteMapping("usuario/{id}")		
	public ResponseEntity<?> delete(@PathVariable Integer id) {

		try {
			Usuario usuarioDelete = usuarioService.findById(id);
			usuarioService.delete(usuarioDelete);
			return new ResponseEntity<>(usuarioDelete, HttpStatus.NO_CONTENT);
			
		}catch (DataAccessException exDt ){
			
			return new ResponseEntity<>(MensajeResponse.builder()
					.mensaje(exDt.getMessage())
					.object(null)
					.build(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}

	
	
	@GetMapping("usuario/{id}")	
	public ResponseEntity<?> showById(@PathVariable Integer id) {
		Usuario usuario = usuarioService.findById(id);
		
		
		if(usuario == null) {
			return new ResponseEntity<>(
					MensajeResponse.builder()
						.mensaje("El registro que intenta buscar, no existe")
						.object(null)
						.build()
					,HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(MensajeResponse.builder()
						.mensaje("")
						.object(UsuarioDto.builder()
						.cod_usuario(usuario.getCod_usuario())
						.nombre(usuario.getNombre())
						.dni(usuario.getDni())
						.telefono(usuario.getTelefono())
						.correo(usuario.getCorreo())
						.pwd(usuario.getPwd())
						.build())
						.build()
				, HttpStatus.OK);
	}
		
		
}



