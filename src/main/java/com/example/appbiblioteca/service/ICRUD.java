package com.example.appbiblioteca.service;

import java.util.List;

public interface ICRUD<T> {
	
	T crear(T obj);
	T modificar(T obj);
	List<T> listar();
	T burcarId(Integer id);
	boolean eliminar(Integer id);
}
