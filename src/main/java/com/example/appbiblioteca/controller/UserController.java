package com.example.appbiblioteca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.appbiblioteca.exception.ModeloNotFoundException;
import com.example.appbiblioteca.model.Prestamo;
import com.example.appbiblioteca.model.Usuario;
import com.example.appbiblioteca.service.IUsuarioService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/usuarios")
public class UserController {

	@Autowired
	private IUsuarioService userService;
	
	@ApiOperation(value				= "Obtener todos los Usuario",
		    	  notes				= "No necesita parametros de entrada",
		    	  response			= List.class,
		    	  responseContainer	= "Usuario")
	@ApiResponses(value = {
			  @ApiResponse(code = 400, message = "Datos no enviados correctamente"),
		      @ApiResponse(code = 404, message = "No encontrado"),
		      @ApiResponse(code = 405, message = "No se encontraron datos en la BD"),
		      @ApiResponse(code = 200, message = "Peticón OK")})
	@GetMapping
	public ResponseEntity<List<Usuario>> listar(){
		List<Usuario> lista = userService.listar();
		return new ResponseEntity<List<Usuario>>(lista, HttpStatus.OK);
	}
	
	@ApiOperation(value				= "Buscar Usuario por ID",
		    	  notes				= "Necesita Id de entrada",
		    	  response			= Usuario.class,
		    	  responseContainer	= "Usuario")
	@ApiResponses(value = {
			  @ApiResponse(code = 400, message = "Datos no enviados correctamente"),
		      @ApiResponse(code = 404, message = "No encontrado"),
		      @ApiResponse(code = 405, message = "No se encontraron datos en la BD"),
		      @ApiResponse(code = 200, message = "Peticón OK")})
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> listarPorId(@PathVariable("id") Integer id){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
			Usuario obj = userService.burcarId(id);
			if(obj.getIdUsuario() == null) {
				throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
			}
			return new ResponseEntity<Usuario>(obj, HttpStatus.OK);
		}else {
			return new ResponseEntity<Usuario>(new Usuario(), HttpStatus.UNAUTHORIZED);
		}
		
	}
}
