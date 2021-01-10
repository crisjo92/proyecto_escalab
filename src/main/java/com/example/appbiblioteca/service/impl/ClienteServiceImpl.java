package com.example.appbiblioteca.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.appbiblioteca.model.Cliente;
import com.example.appbiblioteca.repo.IClienteRepo;
import com.example.appbiblioteca.service.IClienteService;

@Service
public class ClienteServiceImpl implements IClienteService{

	@Autowired
	private IClienteRepo repo;
	@Override
	public Cliente crear(Cliente obj) {
		return repo.save(obj);
	}

	@Override
	public Cliente modificar(Cliente obj) {
		return repo.save(obj);
	}

	@Override
	public List<Cliente> listar() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public Cliente burcarId(Integer id) {
		Optional<Cliente> opcional = repo.findById(id);
		return opcional.isPresent() ? opcional.get() : new Cliente();
	}

	@Override
	public boolean eliminar(Integer id) {
		repo.deleteById(id);
		return true;
	}

}
