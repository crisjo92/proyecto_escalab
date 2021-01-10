package com.example.appbiblioteca.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.appbiblioteca.model.Autor;

public interface IAutorRepo extends JpaRepository<Autor, Integer>{

}
