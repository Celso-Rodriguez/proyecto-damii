package com.proyecto.damii.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.damii.model.dao.MenuDao;
import com.proyecto.damii.model.dto.MenuDto;
import com.proyecto.damii.model.entity.Menu;
import com.proyecto.damii.service.IMenuService;



@Service
public class MenuImplService implements IMenuService {

	
	@Autowired
	private MenuDao menuDao;
	
	@Transactional
	@Override
	public Menu save(MenuDto menuDto) {
		Menu menu = Menu.builder()
				.cod_menu(menuDto.getCod_menu())
				.nombre(menuDto.getNombre())
				.descripcion(menuDto.getDescripcion())
				.precio(menuDto.getPrecio())
				.imagen(menuDto.getImagen())
				.build();
		return menuDao.save(menu);
	}
	

	@Transactional(readOnly = true)
	@Override
	public Menu findById(Integer id) {
		return menuDao.findById(id).orElse(null);
	}

	
	@Transactional
	@Override
	public void delete(Menu menu) {
		menuDao.delete(menu);
		
	}


	@Override
	public boolean existsById(Integer id) {
		// TODO Auto-generated method stub
		return menuDao.existsById(id);
	}


	@Override
	public List<Menu> listAll() {
		// TODO Auto-generated method stub
		return (List) menuDao.findAll();
	} 
	
	

}
