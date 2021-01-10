package com.example.appbiblioteca.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.appbiblioteca.model.Libro;
import com.example.appbiblioteca.repo.ILibroRepo;
import com.example.appbiblioteca.service.ILibroService;

@Service
public class LibroServiceImpl implements ILibroService {

	@Autowired
	private ILibroRepo repo;
	
	@Override
	public Libro crear(Libro obj) {
		return repo.save(obj);
	}

	@Override
	public Libro modificar(Libro obj) {
		return repo.save(obj);
	}

	@Override
	public List<Libro> listar() {
		return repo.findAll();
	}

	@Override
	public Libro burcarId(Integer id) {
		Optional<Libro> optional = repo.findById(id);
		return optional.isPresent() ? optional.get() : new Libro();
		
	}

	@Override
	public boolean eliminar(Integer id) {
		repo.deleteById(id);
		return true;
	}

}
