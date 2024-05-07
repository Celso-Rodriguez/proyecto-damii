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
@Table(name = "tb_menu")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Menu {
	
	@Id
	@Column(name= "cod_menu")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cod_menu;
	
	@Column(name= "nombre")
	private String nombre;
	
	@Column(name= "descripcion")
	private String descripcion;
	
	@Column(name= "precio")
	private Double precio;
	
	@Column(name= "imagen")
	private String imagen;
	
	
	
	

}
