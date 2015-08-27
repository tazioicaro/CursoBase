package com.algaworks.cursojavaee.repository.filter;

import java.io.Serializable;

import com.algaworks.cursojavaee.validation.SKU;

//Classe para padronizar os filtros para o produto
public class ProdutoFilter  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String sku;
	private String nome;
	
	
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
	
	
	

}
