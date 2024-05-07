package com.proyecto.damii.model.dto;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;


@Data
@ToString
@Builder

public class MenuDto implements Serializable {
	

	private Integer cod_menu;
	private String nombre;
	private String descripcion;
	private Double precio;
	private String imagen;
	
	
	
	

}
