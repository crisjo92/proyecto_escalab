package com.example.appbiblioteca.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.appbiblioteca.model.Prestamo;

public interface IPrestamoRepo extends JpaRepository<Prestamo, Integer> {

}
