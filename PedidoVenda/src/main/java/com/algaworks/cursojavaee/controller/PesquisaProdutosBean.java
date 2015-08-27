package com.algaworks.cursojavaee.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.cursojavaee.model.Produto;
import com.algaworks.cursojavaee.repository.Produtos;
import com.algaworks.cursojavaee.repository.filter.ProdutoFilter;

@Named
@ViewScoped
public class PesquisaProdutosBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	
	private List<Produto> produtosFiltrados;
	
@Inject
private Produtos produtos;

private ProdutoFilter filtro;


		
	public PesquisaProdutosBean() {
	filtro = new ProdutoFilter();
}

	public List<Produto> getProdutosFiltrados() {
		return produtosFiltrados;
	}
	
	public void pesquisar(){
		
		
		produtosFiltrados = produtos.filtrados(filtro);
	}
	
	
	

	public ProdutoFilter getFiltro() {
		return filtro;
	}



}
