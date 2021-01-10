package com.example.appbiblioteca.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Información del Cliente")
@Entity
@Table(name = "cliente")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCliente;
	
	@ApiModelProperty(notes = "Nombres del Cliente")
	@Size(min = 4, message = "Nombres debe tener minimo 4 caracteres")
	@Column(name = "nombres", nullable = false, length = 100)
	private String nombres;
	
	@ApiModelProperty(notes = "Apellidos del Cliente")
	@Size(min = 4, message = "Apellidos debe tener minimo 4 caracteres")
	@Column(name = "apellidos", nullable = false, length = 100)
	private String apellidos;
	
	@ApiModelProperty(notes = "Direccion del Cliente")
	@Size(min = 4, message = "Minimo 4 caracteres")
	@Column(name = "direccion", nullable = false, length = 100)
	private String direccion;
	
	@ApiModelProperty(notes = "Telefono del Cliente")
	@Size(min = 6, message = "Minimo 6 caracteres")
	@Column(name = "telefono", nullable = false, length = 15)
	private String telefono;
	
	

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	
}
