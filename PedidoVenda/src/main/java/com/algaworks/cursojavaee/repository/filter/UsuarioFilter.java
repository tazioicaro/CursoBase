package com.algaworks.cursojavaee.repository.filter;

import java.io.Serializable;
import java.util.List;

import com.algaworks.cursojavaee.model.Grupo;

public class UsuarioFilter implements Serializable {


	private static final long serialVersionUID = 1L;
	
	private String nome;
	private List<Grupo>grupos;
	
	private int primeiroRegistro;
	private int qtdeRegistros;
	private String propriedadeOrdenacao;
	private boolean ascendente;
	
	
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
	public int getPrimeiroRegistro() {
		return primeiroRegistro;
	}
	public void setPrimeiroRegistro(int primeiroRegistro) {
		this.primeiroRegistro = primeiroRegistro;
	}
	public int getQtdeRegistros() {
		return qtdeRegistros;
	}
	public void setQtdeRegistros(int qtdeRegistros) {
		this.qtdeRegistros = qtdeRegistros;
	}
	public String getPropriedadeOrdenacao() {
		return propriedadeOrdenacao;
	}
	public void setPropriedadeOrdenacao(String propriedadeOrdenacao) {
		this.propriedadeOrdenacao = propriedadeOrdenacao;
	}
	public boolean isAscendente() {
		return ascendente;
	}
	public void setAscendente(boolean ascendente) {
		this.ascendente = ascendente;
	}
	
	

}
