package com.algaworks.cursojavaee.security;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Named
@RequestScoped
public class Seguranca {
	
	/**
	 *  @return
	 *  Classe que vai exibir no nome do usuário nas páginas
	 */
	
	public String getNomeUsuario(){
		
		String nome = null;
		
		UsuarioSistema usuarioLogado = getUsuarioLogado();
		
		if( usuarioLogado != null){
		nome = usuarioLogado.getUsuario().getNome();
		
		}
		return nome;
		
	}

	private UsuarioSistema getUsuarioLogado() {
		UsuarioSistema usuario = null;
		
		//Convertendo o usuário logado JSF para Spring
		
		UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken)
		FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
		
		if (auth != null && auth.getPrincipal() != null){
			
			usuario = (UsuarioSistema) auth.getPrincipal();
		}
		return usuario;
	}

}
