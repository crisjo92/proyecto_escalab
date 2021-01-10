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

import com.example.appbiblioteca.model.Autor;
import com.example.appbiblioteca.service.IAutorService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/autores")
public class AutorController {
	
	@Autowired
	private IAutorService  autorService;
	
	@ApiOperation(value				= "Obtener todos los Autores",
		    	  notes				= "No necesita parametros de entrada",
		    	  response			= List.class,
		    	  responseContainer	= "Autor")
	@ApiResponses(value = {
			  @ApiResponse(code = 400, message = "Datos no enviados correctamente"),
		      @ApiResponse(code = 404, message = "No encontrado"),
		      @ApiResponse(code = 405, message = "No se encontraron pacientes en la BD"),
		      @ApiResponse(code = 200, message = "Peticón OK")})
	
	@GetMapping("/listar")
	public ResponseEntity<List<Autor>> listar(){
		List<Autor> lista = autorService.listar();
		return new ResponseEntity<List<Autor>>(lista, HttpStatus.OK);
	}
	
	@ApiOperation(value				= "Busca Autor por ID",
	    	  	  notes				= "Necesita Id de entrada",
	    	  	  response			= Autor.class,
	    	  	  responseContainer	= "Autor")
	@ApiResponses(value = {
			  @ApiResponse(code = 400, message = "Datos no enviados correctamente"),
		      @ApiResponse(code = 404, message = "No encontrado"),
		      @ApiResponse(code = 405, message = "No se encontraron paciente en la BD"),
		      @ApiResponse(code = 200, message = "Peticón OK")})
	@GetMapping("/buscar/{id}")
	public ResponseEntity<Autor> listarPorId(@PathVariable("id") Integer id) {
		Autor aut = autorService.burcarId(id);
		if (aut.getIdAutor() == null) {
			//throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
			return new ResponseEntity<Autor>(aut, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Autor>(aut, HttpStatus.OK);
	}
	
	@ApiOperation(value				= "Crea Autor",
  	  	  		  notes				= "Necesita Informacion de Autor",
  	  	  		  response			= Object.class,
  	  	  		  responseContainer	= "Autor")
	@ApiResponses(value = {
			  @ApiResponse(code = 400, message = "Datos no enviados correctamente"),
		      @ApiResponse(code = 404, message = "No encontrado"),
		      @ApiResponse(code = 405, message = "No se encontraron paciente en la BD"),
		      @ApiResponse(code = 200, message = "Peticón OK")})
	@PostMapping("/registrar")
	public ResponseEntity<Object> registrar(@RequestBody Autor autor) {
		Autor aut = autorService.crear(autor);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(autor.getIdAutor()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@ApiOperation(value				= "Modificar Autor",
	  	  		  notes				= "Necesita ID de Autor como parametro de entrada",
	  	  		  response			= Autor.class,
	  	  		  responseContainer	= "Autor")
	@ApiResponses(value = {
			  @ApiResponse(code = 400, message = "Datos no enviados correctamente"),
		      @ApiResponse(code = 200, message = "Peticón OK")})
	@PutMapping("/modificar")
	public ResponseEntity<Autor> modificar(@RequestBody Autor autor) {
		Autor aut = autorService.modificar(autor);
		return new ResponseEntity<Autor>(aut, HttpStatus.OK);
	}
	
	@ApiOperation(value				= "Eliminar Autor",
		  		  notes				= "Necesita ID de Autor como parametro de entrada",
		  		  response			= Object.class,
		  		  responseContainer	= "Autor")
	@ApiResponses(value = {
				  @ApiResponse(code = 400, message = "Datos no enviados correctamente"),
			      @ApiResponse(code = 404, message = "No encontrado"),
			      @ApiResponse(code = 405, message = "No se encontraron datos en la BD"),
			      @ApiResponse(code = 200, message = "Peticón OK")})
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id) {
		Autor aut = autorService.burcarId(id);
		if (aut.getIdAutor() == null) {
			//throw new ModeloNotFoundException("ID NO ENCONTRADO" + id);
			return new ResponseEntity<Object>(aut, HttpStatus.NOT_FOUND);
		}
		autorService.eliminar(id);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
}
