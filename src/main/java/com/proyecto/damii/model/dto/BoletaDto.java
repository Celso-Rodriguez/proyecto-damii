package com.proyecto.damii.model.dto;

import java.io.Serializable;

import com.proyecto.damii.model.entity.Usuario;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;


@Data
@ToString
@Builder

public class BoletaDto implements Serializable {
	

	private Integer cod_boleta;
	private Double totalPagar;
	private Usuario usuario;

	
	
	
	

}
