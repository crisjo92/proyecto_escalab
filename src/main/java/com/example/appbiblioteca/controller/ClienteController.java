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
import com.example.appbiblioteca.model.Cliente;
import com.example.appbiblioteca.service.IClienteService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private IClienteService clienteService;
	
	@ApiOperation(value				= "Obtener todos los Clientes",
		    	  notes				= "No necesita parametros de entrada",
		    	  response			= List.class,
		    	  responseContainer	= "Cliente")
	@ApiResponses(value = {
			  @ApiResponse(code = 400, message = "Datos no enviados correctamente"),
		      @ApiResponse(code = 404, message = "No encontrado"),
		      @ApiResponse(code = 405, message = "No se encontraron datos en la BD"),
		      @ApiResponse(code = 200, message = "Peticón OK")})
	@GetMapping("/listar")
	public ResponseEntity<List<Cliente>> listar(){
		List<Cliente> lista = clienteService.listar();
		return new ResponseEntity<List<Cliente>>(lista, HttpStatus.OK);
	}
	
	@ApiOperation(value				= "Buscar Cliente por ID",
		    	  notes				= "Necesita Id de entrada",
		    	  response			= Cliente.class,
		    	  responseContainer	= "Cliente")
	@ApiResponses(value = {
			  @ApiResponse(code = 400, message = "Datos no enviados correctamente"),
		      @ApiResponse(code = 404, message = "No encontrado"),
		      @ApiResponse(code = 405, message = "No se encontraron datos en la BD"),
		      @ApiResponse(code = 200, message = "Peticón OK")})
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> listarPorId(@PathVariable("id") Integer id) {
		Cliente client = clienteService.burcarId(id);
		if (client.getIdCliente() == null) {
			//throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
			return new ResponseEntity<Cliente>(client, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Cliente>(client, HttpStatus.OK);
	}
	
	@ApiOperation(value				= "Crea Cliente",
	  	  		  notes				= "Necesita Informacion de Cliente",
	  	  		  response			= Object.class,
	  	  		  responseContainer	= "Cliente")
	@ApiResponses(value = {
			  @ApiResponse(code = 400, message = "Datos no enviados correctamente"),
		      @ApiResponse(code = 404, message = "No encontrado"),
		      @ApiResponse(code = 405, message = "No se encontraron datos en la BD"),
		      @ApiResponse(code = 200, message = "Peticón OK")})
	@PostMapping("/registrar")
	public ResponseEntity<Object> registrar(@RequestBody Cliente cliente) {
		clienteService.crear(cliente);		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getIdCliente()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@ApiOperation(value				= "Modificar Cliente",
		  		  notes				= "Necesita ID de Autor como parametro de entrada",
		  		  response			= Cliente.class,
		  		  responseContainer	= "Cliente")
	@ApiResponses(value = {
			  @ApiResponse(code = 400, message = "Datos no enviados correctamente"),
		      @ApiResponse(code = 200, message = "Peticón OK")})
	@PutMapping("/modificar")
	public ResponseEntity<Cliente> modificar(@RequestBody Cliente cliente) {
		Cliente client = clienteService.modificar(cliente);
		return new ResponseEntity<Cliente>(client, HttpStatus.OK);
	}
	
	@ApiOperation(value				= "Eliminar Cliente",
		  		  notes				= "Necesita ID de Cliente como parametro de entrada",
		  		  response			= Object.class,
		  		  responseContainer	= "Cliente")
	@ApiResponses(value = {
				  @ApiResponse(code = 400, message = "Datos no enviados correctamente"),
			      @ApiResponse(code = 404, message = "No encontrado"),
			      @ApiResponse(code = 405, message = "No se encontraron datos en la BD"),
			      @ApiResponse(code = 200, message = "Peticón OK")})
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id) {
		Cliente client = clienteService.burcarId(id);
		if (client.getIdCliente() == null) {
			//throw new ModeloNotFoundException("ID NO ENCONTRADO" + id);
			return new ResponseEntity<Object>(client, HttpStatus.NOT_FOUND);
		}
		clienteService.eliminar(id);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
}
