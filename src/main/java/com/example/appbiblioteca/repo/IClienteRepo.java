package com.example.appbiblioteca.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.appbiblioteca.model.Cliente;

public interface IClienteRepo extends JpaRepository<Cliente, Integer>{

	
}
