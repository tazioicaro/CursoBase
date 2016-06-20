package com.algaworks.cursojavaee.repository.filter;

import java.io.Serializable;
import java.util.List;

import com.algaworks.cursojavaee.model.Grupo;

public class UsuarioFilter implements Serializable {


	private static final long serialVersionUID = 1L;
	
	private String nome;
	private List<Grupo>grupos;
	
	
	
	
	//G&S

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public List<Grupo> getGrupos() {
		return grupos;
	}
	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}

}
