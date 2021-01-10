package com.example.appbiblioteca.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import com.example.appbiblioteca.dto.PrestamoDTO;
import com.example.appbiblioteca.exception.ModeloNotFoundException;
import com.example.appbiblioteca.model.Cliente;
import com.example.appbiblioteca.model.Prestamo;
import com.example.appbiblioteca.service.IPrestamoService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/prestamos")
public class PrestamoController {

	@Autowired
	private IPrestamoService prestamoService;
	
	@ApiOperation(value				= "Obtener todos los Prestamo",
		    	  notes				= "No necesita parametros de entrada",
		    	  response			= List.class,
		    	  responseContainer	= "Prestamo")
	@ApiResponses(value = {
			  @ApiResponse(code = 400, message = "Datos no enviados correctamente"),
		      @ApiResponse(code = 404, message = "No encontrado"),
		      @ApiResponse(code = 405, message = "No se encontraron datos en la BD"),
		      @ApiResponse(code = 200, message = "Peticón OK")})
	@GetMapping("/listar")
	public ResponseEntity<List<Prestamo>> listar(){
		List<Prestamo> lista = prestamoService.listar();
		return new ResponseEntity<List<Prestamo>>(lista, HttpStatus.OK);
	}
	
	@ApiOperation(value				= "Buscar Prestamo por ID",
		    	  notes				= "Necesita Id de entrada",
		    	  response			= Prestamo.class,
		    	  responseContainer	= "Prestamo")
	@ApiResponses(value = {
			  @ApiResponse(code = 400, message = "Datos no enviados correctamente"),
		      @ApiResponse(code = 404, message = "No encontrado"),
		      @ApiResponse(code = 405, message = "No se encontraron datos en la BD"),
		      @ApiResponse(code = 200, message = "Peticón OK")})
	@GetMapping("/buscar/{id}")
	public ResponseEntity<Prestamo> listarPorId(@PathVariable("id") Integer id) {
		Prestamo prest = prestamoService.burcarId(id);
		if (prest.getIdPrestamo() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
		}
		return new ResponseEntity<Prestamo>(prest, HttpStatus.OK);
	}
	
	@ApiOperation(value				= "Crea Prestamo",
		  		  notes				= "Necesita Informacion de Prestamo",
		  		  response			= Object.class,
		  		  responseContainer	= "Prestamo")
	@ApiResponses(value = {
			  @ApiResponse(code = 400, message = "Datos no enviados correctamente"),
		      @ApiResponse(code = 404, message = "No encontrado"),
		      @ApiResponse(code = 405, message = "No se encontraron datos en la BD"),
		      @ApiResponse(code = 200, message = "Peticón OK")})
	@PostMapping("/registrar")
	public ResponseEntity<Object> registrar(@RequestBody Prestamo prestamo) {
		Prestamo prest = prestamoService.crear(prestamo);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(prestamo.getIdPrestamo()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@ApiOperation(value				= "Modificar Prestamo",
		  		  notes				= "Necesita ID de Prestamo como parametro de entrada",
		  		  response			= Prestamo.class,
		  		  responseContainer	= "Prestamo")
	@ApiResponses(value = {
			  @ApiResponse(code = 400, message = "Datos no enviados correctamente"),
		      @ApiResponse(code = 200, message = "Peticón OK")})
	@PutMapping("/modificar")
	public ResponseEntity<Prestamo> modificar(@RequestBody Prestamo prestamo) {
		Prestamo prest = prestamoService.modificar(prestamo);
		return new ResponseEntity<Prestamo>(prest, HttpStatus.OK);
	}
	
	@ApiOperation(value				= "Eliminar Prestamo",
		  		  notes				= "Necesita ID de Prestamo como parametro de entrada",
		  		  response			= Object.class,
		  		  responseContainer	= "Prestamo")
	@ApiResponses(value = {
				  @ApiResponse(code = 400, message = "Datos no enviados correctamente"),
			      @ApiResponse(code = 404, message = "No encontrado"),
			      @ApiResponse(code = 405, message = "No se encontraron datos en la BD"),
			      @ApiResponse(code = 200, message = "Peticón OK")})
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id) {
		Prestamo prest = prestamoService.burcarId(id);
		if (prest.getIdPrestamo() == null) {
			//throw new ModeloNotFoundException("ID NO ENCONTRADO" + id);
			return new ResponseEntity<Object>(prest, HttpStatus.NOT_FOUND);
		}
		prestamoService.eliminar(id);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	@ApiOperation(value				= "Listar Prestamo Hateos",
		  		  notes				= "No necesita datos de entrada",
		  		  response			= List.class,
		  		  responseContainer	= "PrestamoDTO")
	@ApiResponses(value = {
				  @ApiResponse(code = 400, message = "Datos no enviados correctamente"),
			      @ApiResponse(code = 404, message = "No encontrado"),
			      @ApiResponse(code = 405, message = "No se encontraron datos en la BD"),
			      @ApiResponse(code = 200, message = "Peticón OK")})
	@GetMapping(value = "/hateoas", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PrestamoDTO> listarHateoas() {
		List<Prestamo> prestamos = new ArrayList<>();
		List<PrestamoDTO> prestamosDTO = new ArrayList<>();
		prestamos = prestamoService.listar();
		
		for (Prestamo presta : prestamos) {
			PrestamoDTO preDTO = new PrestamoDTO();
			preDTO.setIdPrestamo(presta.getIdPrestamo());
			preDTO.setLibro(presta.getLibro());
			preDTO.setCliente(presta.getCliente());

			ControllerLinkBuilder linkTo = linkTo(methodOn(PrestamoController.class).listarPorId((presta.getIdPrestamo())));
			preDTO.add(linkTo.withSelfRel());
			prestamosDTO.add(preDTO);
			
			ControllerLinkBuilder linkTo1 = linkTo(methodOn(LibroController.class).listarPorId((presta.getLibro().getIdLibro())));
			preDTO.add(linkTo1.withSelfRel());
			prestamosDTO.add(preDTO);

			ControllerLinkBuilder linkTo2 = linkTo(methodOn(ClienteController.class).listarPorId((presta.getCliente().getIdCliente())));
			preDTO.add(linkTo2.withSelfRel());
			prestamosDTO.add(preDTO);
		}
		return prestamosDTO;
	}

}
