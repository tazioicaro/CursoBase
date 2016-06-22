package com.algaworks.cursojavaee.repository.filter;

import java.io.Serializable;

import com.algaworks.cursojavaee.validation.SKU;

/**
 * @author tazio.fernandes
 *Classe para padronizar e dinamizar os filtros para o produto
 *visto que pode-se fazer incrementos das pesquisas com diferentes dados
 */
public class ProdutoFilter  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String sku;
	private String nome;
	
	private int primeiroRegistro;
	private int quantidadeRegistros;
	private String propriedadeOrdenacao;
	private boolean ascendente;
	
	
	
	@SKU
	public String getSku() {
		return sku;
	}
	
	//Veriricar se é nulo antes de transformar em maiúscula
	public void setSku(String sku) {
		this.sku = sku == null ? null : sku.toUpperCase() ;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getPrimeiroRegistro() {
		return primeiroRegistro;
	}

	public void setPrimeiroRegistro(int primeiroRegistro) {
		this.primeiroRegistro = primeiroRegistro;
	}

	public int getQuantidadeRegistros() {
		return quantidadeRegistros;
	}

	public void setQuantidadeRegistros(int quantidadeRegistros) {
		this.quantidadeRegistros = quantidadeRegistros;
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
