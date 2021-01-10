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
import com.example.appbiblioteca.model.Editorial;
import com.example.appbiblioteca.service.IEditorialService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/editoriales")
public class EditorialController {

	@Autowired
	private IEditorialService editoService;
	
	@ApiOperation(value				= "Obtener todos los Editorial",
		    	  notes				= "No necesita parametros de entrada",
		    	  response			= List.class,
		    	  responseContainer	= "Editorial")
	@ApiResponses(value = {
			  @ApiResponse(code = 400, message = "Datos no enviados correctamente"),
		      @ApiResponse(code = 404, message = "No encontrado"),
		      @ApiResponse(code = 405, message = "No se encontraron datos en la BD"),
		      @ApiResponse(code = 200, message = "Peticón OK")})
	@GetMapping("/listar")
	public ResponseEntity<List<Editorial>> listar(){
		List<Editorial> lista = editoService.listar();
		return new ResponseEntity<List<Editorial>>(lista, HttpStatus.OK);
	}
	
	@ApiOperation(value				= "Buscar Editorial por ID",
		    	  notes				= "Necesita Id de entrada",
		    	  response			= Editorial.class,
		    	  responseContainer	= "Editorial")
	@ApiResponses(value = {
			  @ApiResponse(code = 400, message = "Datos no enviados correctamente"),
		      @ApiResponse(code = 404, message = "No encontrado"),
		      @ApiResponse(code = 405, message = "No se encontraron datos en la BD"),
		      @ApiResponse(code = 200, message = "Peticón OK")})
	@GetMapping("/buscar/{id}")
	public ResponseEntity<Editorial> listarPorId(@PathVariable("id") Integer id) {
		Editorial client = editoService.burcarId(id);
		if (client.getIdEditorial() == null) {
			//throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
			return new ResponseEntity<Editorial>(client, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Editorial>(client, HttpStatus.OK);
	}
	
	@ApiOperation(value				= "Crea Editorial",
		  		  notes				= "Necesita Informacion de Editorial",
		  		  response			= Object.class,
		  		  responseContainer	= "Editorial")
	@ApiResponses(value = {
			  @ApiResponse(code = 400, message = "Datos no enviados correctamente"),
		      @ApiResponse(code = 404, message = "No encontrado"),
		      @ApiResponse(code = 405, message = "No se encontraron datos en la BD"),
		      @ApiResponse(code = 200, message = "Peticón OK")})
	@PostMapping("/registrar")
	public ResponseEntity<Object> registrar(@RequestBody Editorial editorial) {
		Editorial client = editoService.crear(editorial);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(editorial.getIdEditorial()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@ApiOperation(value				= "Modificar Editorial",
		  		  notes				= "Necesita ID de Autor como parametro de entrada",
		  		  response			= Editorial.class,
		  		  responseContainer	= "Editorial")
	@ApiResponses(value = {
			  @ApiResponse(code = 400, message = "Datos no enviados correctamente"),
		      @ApiResponse(code = 200, message = "Peticón OK")})
	@PutMapping("/modificar")
	public ResponseEntity<Editorial> modificar(@RequestBody Editorial editorial) {
		Editorial client = editoService.modificar(editorial);
		return new ResponseEntity<Editorial>(client, HttpStatus.OK);
	}
	
	@ApiOperation(value				= "Eliminar Editorial",
		  		  notes				= "Necesita ID de Cliente como parametro de entrada",
		  		  response			= Object.class,
		  		  responseContainer	= "Editorial")
	@ApiResponses(value = {
				  @ApiResponse(code = 400, message = "Datos no enviados correctamente"),
			      @ApiResponse(code = 404, message = "No encontrado"),
			      @ApiResponse(code = 405, message = "No se encontraron datos en la BD"),
			      @ApiResponse(code = 200, message = "Peticón OK")})
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id) {
		Editorial client = editoService.burcarId(id);
		if (client.getIdEditorial() == null) {
			//throw new ModeloNotFoundException("ID NO ENCONTRADO" + id);
			return new ResponseEntity<Object>(client, HttpStatus.NOT_FOUND);
		}
		editoService.eliminar(id);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

}
