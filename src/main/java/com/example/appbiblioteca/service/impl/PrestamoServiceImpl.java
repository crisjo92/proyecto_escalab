package com.example.appbiblioteca.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.appbiblioteca.model.Prestamo;
import com.example.appbiblioteca.repo.IPrestamoRepo;
import com.example.appbiblioteca.service.IPrestamoService;

@Service
public class PrestamoServiceImpl implements IPrestamoService {
	
	@Autowired
	private IPrestamoRepo repo;
	
	@Override
	public Prestamo crear(Prestamo obj) {
		return repo.save(obj);
	}

	@Override
	public Prestamo modificar(Prestamo obj) {
		return repo.save(obj);
	}

	@Override
	public List<Prestamo> listar() {
		return repo.findAll();
	}

	@Override
	public Prestamo burcarId(Integer id) {
		Optional<Prestamo> optional = repo.findById(id);
		return optional.isPresent() ? optional.get() : new Prestamo();
	}

	@Override
	public boolean eliminar(Integer id) {
		repo.deleteById(id);
		return true;
	}

}
