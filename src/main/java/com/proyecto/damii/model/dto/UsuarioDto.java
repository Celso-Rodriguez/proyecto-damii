package com.proyecto.damii.model.dto;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;


@Data
@ToString
@Builder

public class UsuarioDto implements Serializable {
	

	private Integer cod_usuario;
	private String nombre;
	private String dni;
	private String telefono;
	private String correo;
	private String pwd;
	
	
	
	

}
