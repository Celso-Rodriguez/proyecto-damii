package com.proyecto.damii.service;

import java.util.List;

import com.proyecto.damii.model.dto.MenuDto;
import com.proyecto.damii.model.entity.Menu;

public interface IMenuService {
	
	List<Menu> listAll();
	
	Menu save(MenuDto menu);
	
	Menu findById(Integer id);
	
	void delete(Menu menu);
	
	boolean existsById(Integer id);

}
