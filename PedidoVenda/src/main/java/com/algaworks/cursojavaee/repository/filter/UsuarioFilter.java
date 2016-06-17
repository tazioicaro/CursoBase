package com.algaworks.cursojavaee.repository.filter;

import java.io.Serializable;

public class UsuarioFilter implements Serializable {


	private static final long serialVersionUID = 1L;
	
	private String nome;
	private String tipoAcesso;
	
	
	
	
	//G&S
	
	public String getTipoAcesso() {
		return tipoAcesso;
	}
	public void setTipoAcesso(String tipoAcesso) {
		this.tipoAcesso = tipoAcesso;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

}
