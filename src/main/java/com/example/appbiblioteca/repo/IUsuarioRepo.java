package com.example.appbiblioteca.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.appbiblioteca.model.Usuario;

public interface IUsuarioRepo extends JpaRepository<Usuario, Integer>{

	Usuario findOneByUsername(String username);
}
