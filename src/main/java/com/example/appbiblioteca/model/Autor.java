package com.example.appbiblioteca.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Información del Autor")
@Entity
@Table(name="autor")
public class Autor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idAutor;
	
	@ApiModelProperty(notes = "Nombres del Autor")
	@Size(min = 4, message = "Nombres debe tener minimo 4 caracteres")
	@Column(name = "nombres", nullable = false, length = 100)
	private String nombres;
	
	@ApiModelProperty(notes = "Apellidos del Autor")
	@Size(min = 4, message = "Apellidos debe tener minimo 4 caracteres")
	@Column(name = "apellidos", nullable = false, length = 100)
	private String apellidos;
	
	@ApiModelProperty(notes = "Nacionalidas del Autor")
	@Size( message = "Si no encuentra nacionalidad colocar Sin Nacionalidad")
	@Column(name = "nacionalidad", nullable = false, length = 100)
	private String nacionalidad;
	
	@OneToMany(mappedBy = "autor", cascade = { CascadeType.ALL }, orphanRemoval = true)
	private List<Libro> libro;
	
	public List<Libro> getLibro() {
		return libro;
	}

	public void setLibro(List<Libro> libro) {
		this.libro = libro;
	}

	public Integer getIdAutor() {
		return idAutor;
	}

	public void setIdAutor(Integer idAutor) {
		this.idAutor = idAutor;
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

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	
}
