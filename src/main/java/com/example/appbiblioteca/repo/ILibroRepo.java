package com.example.appbiblioteca.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.appbiblioteca.model.Libro;

public interface ILibroRepo extends JpaRepository<Libro, Integer> {

}
