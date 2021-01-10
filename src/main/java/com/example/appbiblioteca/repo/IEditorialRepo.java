package com.example.appbiblioteca.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.appbiblioteca.model.Editorial;

public interface IEditorialRepo extends JpaRepository<Editorial, Integer> {

}
