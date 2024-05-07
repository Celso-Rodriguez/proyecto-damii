package com.proyecto.damii.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.damii.model.dao.UsuarioDao;
import com.proyecto.damii.model.dto.UsuarioDto;
import com.proyecto.damii.model.entity.Usuario;
import com.proyecto.damii.service.IUsuarioService;



@Service
public class UsuarioImplService implements IUsuarioService {

	
	@Autowired
	private UsuarioDao usuarioDao;
	
	@Transactional
	@Override
	public Usuario save(UsuarioDto usuarioDto) {
		Usuario usuario = Usuario.builder()
				.cod_usuario(usuarioDto.getCod_usuario())
				.nombre(usuarioDto.getNombre())
				.dni(usuarioDto.getDni())
				.telefono(usuarioDto.getTelefono())
				.correo(usuarioDto.getCorreo())
				.pwd(usuarioDto.getPwd())
				.build();
		return usuarioDao.save(usuario);
	}
	

	@Transactional(readOnly = true)
	@Override
	public Usuario findById(Integer id) {
		return usuarioDao.findById(id).orElse(null);
	}

	
	@Transactional
	@Override
	public void delete(Usuario usuario) {
			usuarioDao.delete(usuario);
		
	}


	@Override
	public boolean existsById(Integer id) {
		// TODO Auto-generated method stub
		return usuarioDao.existsById(id);
	}


	@Override
	public List<Usuario> listAll() {
		// TODO Auto-generated method stub
		return (List) usuarioDao.findAll();
	} 
	
	

}
