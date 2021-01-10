package com.example.appbiblioteca.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.appbiblioteca.model.Usuario;
import com.example.appbiblioteca.repo.IUsuarioRepo;
import com.example.appbiblioteca.service.IUsuarioService;

@Service
public class UsuarioServiceImpl  implements UserDetailsService, IUsuarioService{
	
	@Autowired
	private IUsuarioRepo userRepo;
	
	@Override
	public Usuario crear(Usuario obj) {
		return userRepo.save(obj);
	}

	@Override
	public Usuario modificar(Usuario obj) {
		return userRepo.save(obj);
	}

	@Override
	public List<Usuario> listar() {
		return userRepo.findAll();
	}

	@Override
	public Usuario burcarId(Integer id) {
		Optional<Usuario> optional = userRepo.findById(id);
		return optional.isPresent() ? optional.get() : new Usuario(); 
	}

	@Override
	public boolean eliminar(Integer id) {
		userRepo.deleteById(id);
		return true;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario usuario = userRepo.findOneByUsername(username);
		
		if(usuario == null) {
			throw new UsernameNotFoundException(String.format("Usuario no existe", username));
		}
		
		List<GrantedAuthority> roles = new ArrayList<>();
		roles.add(new SimpleGrantedAuthority("ADMIN")); //Solo maneja Admin
		
		UserDetails ud = new User(usuario.getUsername(), usuario.getPassword(), roles);

		return ud;
	}	
}

