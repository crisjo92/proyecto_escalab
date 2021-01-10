package com.example.appbiblioteca.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.appbiblioteca.model.Editorial;
import com.example.appbiblioteca.repo.IEditorialRepo;
import com.example.appbiblioteca.service.IEditorialService;

@Service
public class EditorialServiceImpl implements IEditorialService {

	@Autowired
	private IEditorialRepo repo;
	
	@Override
	public Editorial crear(Editorial obj) {
		return repo.save(obj);
	}

	@Override
	public Editorial modificar(Editorial obj) {
		// TODO Auto-generated method stub
		return repo.save(obj);
	}

	@Override
	public List<Editorial> listar() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public Editorial burcarId(Integer id) {
		Optional<Editorial> opcional= repo.findById(id);
		return opcional.isPresent() ? opcional.get() : new Editorial();
	}

	@Override
	public boolean eliminar(Integer id) {
		repo.deleteById(id);
		return true;
	}

	

}
