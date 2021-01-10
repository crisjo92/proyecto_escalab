package com.example.appbiblioteca.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.appbiblioteca.model.Autor;
import com.example.appbiblioteca.repo.IAutorRepo;
import com.example.appbiblioteca.service.IAutorService;

@Service
public class AutorServiceImpl implements IAutorService{

	@Autowired
	private IAutorRepo repo;
	
	@Override
	public Autor crear(Autor obj) {
		return repo.save(obj);
	}

	@Override
	public Autor modificar(Autor obj) {
		return repo.save(obj);
	}

	@Override
	public List<Autor> listar() {
		return repo.findAll();
	}

	@Override
	public Autor burcarId(Integer id) {
		Optional<Autor> opcional = repo.findById(id);
		return opcional.isPresent() ? opcional.get() : new Autor();
	}

	@Override
	public boolean eliminar(Integer id) {
		repo.deleteById(id);
		return true;
	}

}
