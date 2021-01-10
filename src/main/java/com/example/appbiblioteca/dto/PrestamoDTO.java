package com.example.appbiblioteca.dto;

import org.springframework.hateoas.ResourceSupport;

import com.example.appbiblioteca.model.Cliente;
import com.example.appbiblioteca.model.Libro;


public class PrestamoDTO extends ResourceSupport {

	private Integer idPrestamo;
	private Libro libro;
	private Cliente cliente;
	
	public Integer getIdPrestamo() {
		return idPrestamo;
	}
	public void setIdPrestamo(Integer idPrestamo) {
		this.idPrestamo = idPrestamo;
	}
	public Libro getLibro() {
		return libro;
	}
	public void setLibro(Libro libro) {
		this.libro = libro;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
}
