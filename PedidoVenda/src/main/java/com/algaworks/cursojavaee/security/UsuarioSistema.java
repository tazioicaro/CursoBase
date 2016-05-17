package com.algaworks.cursojavaee.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.algaworks.cursojavaee.model.Usuario;

/**
 *  @author tazio.fernandes
 *  
 * Controlado Spring para logar no sistema
 * 
 * Esta classe implementa UserDetails, visto que a Classe User implementa
 * UserDetails
 */

public class UsuarioSistema extends User {

	private static final long serialVersionUID = 1L;
	
	private Usuario usuario;
	

	public UsuarioSistema(Usuario usuario,
			Collection<? extends GrantedAuthority> authorities) {
		super(usuario.getEmail(),usuario.getSenha(), authorities);
		
		this.usuario = usuario;
		
	}


	public Usuario getUsuario() {
		return usuario;
	}

	}
