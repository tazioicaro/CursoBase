package com.algaworks.cursojavaee.controller;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import com.algaworks.cursojavaee.model.Categoria;
import com.algaworks.cursojavaee.model.Produto;
import com.algaworks.cursojavaee.repository.Categorias;
import com.algaworks.cursojavaee.service.CadastroProdutoService;
import com.algaworks.cursojavaee.service.NegocioException;
import com.algaworks.cursojavaee.util.jsf.FacesUtil;



@Named
@ViewScoped
public class CadastroProdutoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Categorias categorias;
	
	@Inject
	private CadastroProdutoService cadastroProdutoService;
	
	private Produto produto;
	private Categoria categoriaPai;
	
	//Apenas o  método get é necessário, visto que somente iremos carregar ao site
	private List<Categoria> categoriasRaizes;
	private List<Categoria> subcategorias;
	
	public CadastroProdutoBean() {
		limpar();
	}
	
	public void inicializar() {
		if (this.produto == null) {
			limpar();
		}
		
		categoriasRaizes = categorias.raizes();
		
		if (this.categoriaPai != null) {
			carregarSubcategorias();
		}
	}
	
	public void carregarSubcategorias() {
		subcategorias = categorias.subcategoriasDe(categoriaPai);
	}
	
	private void limpar() {
		produto = new Produto();
		categoriaPai = null;
		subcategorias = new ArrayList<>();
	}
	
	public void salvar() {
		try {
			this.produto = cadastroProdutoService.salvar(this.produto);
			limpar();
			
			FacesUtil.addInforMessage("Produto salvo com sucesso!");
		} catch (NegocioException ne) {
			FacesUtil.addErrorMessage(ne.getMessage());
		}
	}

	public Produto getProduto() {
		return produto;
	}
	
	// irá receber do viewParam o produto preenchido da tela de pesquisaProduto	
	public void setProduto(Produto produto) {
		this.produto = produto;
		
		if (this.produto != null) {
			this.categoriaPai = this.produto.getCategoria().getCategoriaPai();
		}
	}

	public List<Categoria> getCategoriasRaizes() {
		return categoriasRaizes;
	}

	@NotNull
	public Categoria getCategoriaPai() {
		return categoriaPai;
	}

	public void setCategoriaPai(Categoria categoriaPai) {
		this.categoriaPai = categoriaPai;
	}

	public List<Categoria> getSubcategorias() {
		return subcategorias;
	}
	
	public boolean isEditando() {
		return this.produto.getId() != null;
	}

}
