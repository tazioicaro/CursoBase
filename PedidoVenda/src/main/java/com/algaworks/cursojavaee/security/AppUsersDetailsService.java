package com.algaworks.cursojavaee.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.algaworks.cursojavaee.model.Grupo;
import com.algaworks.cursojavaee.model.Usuario;
import com.algaworks.cursojavaee.repository.Usuarios;
import com.algaworks.cursojavaee.util.cdi.CDIServiceLocator;


public class AppUsersDetailsService implements UserDetailsService {

	/**
	 * Não é uma classe CDI, pois é um bean do Spring
	 * Classe para fornecer os detalhes dos usuários
	 * 
	 * A classe UsuarioSistema é um tipo UserDetails
	 */
	
	@Autowired
	   private BCryptPasswordEncoder encoder;	
	
	@Override
	public UserDetails loadUserByUsername(String email)
			throws UsernameNotFoundException {
		
		 Usuarios usuarios = CDIServiceLocator.getBean(Usuarios.class);
		 
		 Usuario usuario = usuarios.porEmail(email);
		 
		 UsuarioSistema user = null;
		 
		 if(usuario !=null){
			user =  new UsuarioSistema(usuario, getGrupos(usuario));
		 }
		 
			 return user;
			
	}

	private Collection<? extends GrantedAuthority> getGrupos(Usuario usuario) {
      //Criar um grupo  de autorizações
		 List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		 
		 for ( Grupo grupo : usuario.getGrupos()){
			 //Adicionando os nomes dos grupos que o usuário pertence na lista de permissao
			 authorities.add(new SimpleGrantedAuthority(grupo.getNome().toUpperCase()));
		 }

		return authorities;
	}

}
