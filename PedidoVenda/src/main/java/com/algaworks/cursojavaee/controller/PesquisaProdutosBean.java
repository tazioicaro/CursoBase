package com.algaworks.cursojavaee.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.cursojavaee.model.Produto;
import com.algaworks.cursojavaee.repository.Produtos;
import com.algaworks.cursojavaee.repository.filter.ProdutoFilter;
import com.algaworks.cursojavaee.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaProdutosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Produtos produtos;
	private Produto produtosSelecionados;
	private ProdutoFilter filtro;
	private List<Produto> produtosFiltrados;

	public PesquisaProdutosBean() {
		filtro = new ProdutoFilter();
	}

	public List<Produto> getProdutosFiltrados() {
		return produtosFiltrados;
	}

	public void pesquisar() {

		produtosFiltrados = produtos.filtrados(filtro);
	}
	
	public void excluir(){
		
		produtos.removerProduto(produtosSelecionados);
		
		//Excluíndo produtos da lista, sem precisar chamar a tela novamente
		produtosFiltrados.remove(produtosSelecionados);
		
		FacesUtil.addInforMessage("Proputo" + produtosSelecionados.getSku() 
				+" excluído com sucesso.");
		
	}

	// G&S

	public ProdutoFilter getFiltro() {
		return filtro;
	}

	public Produto getProdutosSelecionados() {
		return produtosSelecionados;
	}

	public void setProdutosSelecionados(Produto produtosSelecionados) {
		this.produtosSelecionados = produtosSelecionados;
	}

}
