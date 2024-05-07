package com.proyecto.damii.service;

import java.util.List;

import com.proyecto.damii.model.dto.UsuarioDto;
import com.proyecto.damii.model.entity.Usuario;

public interface IUsuarioService {
	
	List<Usuario> listAll();
	
	Usuario save(UsuarioDto usuario);
	
	Usuario findById(Integer id);
	
	void delete(Usuario usuario);
	
	boolean existsById(Integer id);

}
