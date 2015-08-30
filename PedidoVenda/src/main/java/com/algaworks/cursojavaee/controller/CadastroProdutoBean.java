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
import com.algaworks.cursojavaee.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroProdutoBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Produto produto;	
	private List<Categoria> categoriaRaizes;	
	private List<Categoria> subCategorias;
	
	@NotNull
	private Categoria categoriaPai;
	
	@Inject
	private Categorias categorias;
	
	@Inject
	private CadastroProdutoService cadastroProdutoService;

	
	
	public CadastroProdutoBean() {		
		limpar();
	}


	public void inicializar(){
		
		//Se não for PostBack (Se o salvar não foi pressionado)
		if(FacesUtil.isNotPostBack()){
		categoriaRaizes = categorias.raizes();
		
		if(categoriaPai != null){
			carregarSubCategorias();
		}
		}
	}
	
	
	public void carregarSubCategorias(){
		
		subCategorias = categorias.subCategoriasDe(categoriaPai);
		
	}

	public void salvar(){
		
		
		this.produto =  cadastroProdutoService.salvar(this.produto);				
		FacesUtil.addInforMessage("Produto Salvo com sucesso");
		
		limpar();
				
	}
	
	private void limpar(){
		
		produto = new Produto();
		categoriaPai= null;
		subCategorias= new ArrayList<Categoria>();
	}

	
	
	//G&S

	public Produto getProduto() {
		return produto;
	}

	

//irá receber do viewParam o produto preenchido da tela de pesquisaProduto
	public void setProduto(Produto produto) {
		this.produto = produto;
		
		if(this.produto != null){
			this.categoriaPai = this.produto.getCategoria().getCategoriaPai();
		}
	}


	public List<Categoria> getCategoriaRaizes() {
		return categoriaRaizes;
	}


	public Categoria getCategoriaPai() {
		return categoriaPai;
	}


	public void setCategoriaPai(Categoria categoriaPai) {
		this.categoriaPai = categoriaPai;
	}


	
	public List<Categoria> getSubCategorias() {
		return subCategorias;
	}




}
