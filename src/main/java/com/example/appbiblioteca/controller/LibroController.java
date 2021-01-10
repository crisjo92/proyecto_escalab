package com.example.appbiblioteca.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.appbiblioteca.model.Cliente;
import com.example.appbiblioteca.model.Libro;
import com.example.appbiblioteca.service.ILibroService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/libros")
public class LibroController {

	@Autowired
	private ILibroService libroService;
	
	@ApiOperation(value				= "Obtener todos los Libro",
		    	  notes				= "No necesita parametros de entrada",
		    	  response			= List.class,
		    	  responseContainer	= "Libro")
	@ApiResponses(value = {
			  @ApiResponse(code = 400, message = "Datos no enviados correctamente"),
		      @ApiResponse(code = 404, message = "No encontrado"),
		      @ApiResponse(code = 405, message = "No se encontraron datos en la BD"),
		      @ApiResponse(code = 200, message = "Peticón OK")})
	@GetMapping("/listar")
	public ResponseEntity<List<Libro>> listar(){
		List<Libro> lista = libroService.listar();
		return new ResponseEntity<List<Libro>>(lista, HttpStatus.OK);
	}
	@ApiOperation(value				= "Buscar Libro por ID",
		    	  notes				= "Necesita Id de entrada",
		    	  response			= Libro.class,
		    	  responseContainer	= "Libro")
	@ApiResponses(value = {
			  @ApiResponse(code = 400, message = "Datos no enviados correctamente"),
		      @ApiResponse(code = 404, message = "No encontrado"),
		      @ApiResponse(code = 405, message = "No se encontraron datos en la BD"),
		      @ApiResponse(code = 200, message = "Peticón OK")})
	@GetMapping("/buscar/{id}")
	public ResponseEntity<Libro> listarPorId(@PathVariable("id") Integer id) {
		Libro libr = libroService.burcarId(id);
		if (libr.getIdLibro() == null) {
			//throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
			return new ResponseEntity<Libro>(libr, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Libro>(libr, HttpStatus.OK);
	}
	
	@ApiOperation(value				= "Crea Libro",
		  		  notes				= "Necesita Informacion de Libro",
		  		  response			= Object.class,
		  		  responseContainer	= "Libro")
	@ApiResponses(value = {
			  @ApiResponse(code = 400, message = "Datos no enviados correctamente"),
		      @ApiResponse(code = 404, message = "No encontrado"),
		      @ApiResponse(code = 405, message = "No se encontraron datos en la BD"),
		      @ApiResponse(code = 200, message = "Peticón OK")})
	@PostMapping("/registrar")
	public ResponseEntity<Object> registrar(@RequestBody Libro libro) {
		Libro libr = libroService.crear(libro);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(libro.getIdLibro()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@ApiOperation(value				= "Modificar Libro",
		  		  notes				= "Necesita ID de Libro como parametro de entrada",
		  		  response			= Libro.class,
		  		  responseContainer	= "Libro")
	@ApiResponses(value = {
			  @ApiResponse(code = 400, message = "Datos no enviados correctamente"),
		      @ApiResponse(code = 200, message = "Peticón OK")})
	@PutMapping("/modificar")
	public ResponseEntity<Libro> modificar(@RequestBody Libro libro) {
		Libro libr = libroService.modificar(libro);
		return new ResponseEntity<Libro>(libr, HttpStatus.OK);
	}
	
	@ApiOperation(value				= "Eliminar Libro",
		  		  notes				= "Necesita ID de Libro como parametro de entrada",
		  		  response			= Object.class,
		  		  responseContainer	= "Libro")
	@ApiResponses(value = {
				  @ApiResponse(code = 400, message = "Datos no enviados correctamente"),
			      @ApiResponse(code = 404, message = "No encontrado"),
			      @ApiResponse(code = 405, message = "No se encontraron datos en la BD"),
			      @ApiResponse(code = 200, message = "Peticón OK")})
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id) {
		Libro libr = libroService.burcarId(id);
		if (libr.getIdLibro() == null) {
			//throw new ModeloNotFoundException("ID NO ENCONTRADO" + id);
			return new ResponseEntity<Object>(libr, HttpStatus.NOT_FOUND);
		}
		libroService.eliminar(id);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

}
