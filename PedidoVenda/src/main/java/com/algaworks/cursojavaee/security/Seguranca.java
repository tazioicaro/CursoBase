package com.algaworks.cursojavaee.security;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Named
@RequestScoped
public class Seguranca {

	/**
	 * @return Classe que vai exibir no nome do usuário nas páginas
	 * 
	 */

	@Inject
	private ExternalContext externalContext;

	public String getNomeUsuario() {

		String nome = null;

		UsuarioSistema usuarioLogado = getUsuarioLogado();

		if (usuarioLogado != null) {
			nome = usuarioLogado.getUsuario().getNome();

		}
		return nome;

	}

	@Produces
	@UsuarioLogado
	public UsuarioSistema getUsuarioLogado() {
		UsuarioSistema usuario = null;

		// Convertendo o usuário logado JSF para Spring

		UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) FacesContext
				.getCurrentInstance().getExternalContext().getUserPrincipal();

		if (auth != null && auth.getPrincipal() != null) {

			usuario = (UsuarioSistema) auth.getPrincipal();
		}
		return usuario;
	}

	/**
	 * Garanti para que apenas os Funcionáro com permissão de Vendedores ou
	 * Administradores possam emitir ou cancelar um pedido o perfil de auxiliar
	 * não poderá fazer isso.
	 * 
	 * Utilizado no jsf
	 */

	public boolean isEmitirPedidoPermitido() {

		return externalContext.isUserInRole("ADMINISTRADORES")
				|| externalContext.isUserInRole("VENDEDORES");
	}

	public boolean isCancelarPedidoPermitido() {

		return externalContext.isUserInRole("ADMINISTRADORES")
				|| externalContext.isUserInRole("VENDEDORES");
	}

}
