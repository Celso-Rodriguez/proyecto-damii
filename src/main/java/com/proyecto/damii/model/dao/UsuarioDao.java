package com.proyecto.damii.model.dao;

import com.proyecto.damii.model.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioDao extends CrudRepository<Usuario, Integer> {

}
