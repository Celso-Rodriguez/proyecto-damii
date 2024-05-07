package com.proyecto.damii.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Builder
@Table(name = "tb_usuario")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Usuario {
	
	@Id
	@Column(name= "cod_usuario")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cod_usuario;
	@Column(name= "nombre")
	private String nombre;
	@Column(name= "dni")
	private String dni;
	@Column(name= "telefono")
	private String telefono;
	@Column(name= "correo")
	private String correo;
	@Column(name= "pwd")
	private String pwd;
	
	
	
	

}
