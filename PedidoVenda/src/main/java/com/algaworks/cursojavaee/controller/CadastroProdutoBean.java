package com.algaworks.cursojavaee.controller;


import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import com.algaworks.cursojavaee.model.Categoria;
import com.algaworks.cursojavaee.model.Produto;
import com.algaworks.cursojavaee.repository.Categorias;
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
	
	
	public CadastroProdutoBean() {		
		produto = new Produto();
	}


	public void inicializar(){
		
		//Se não for PostBack (Se o salvar não foi pressionado)
		if(FacesUtil.isNotPostBack()){
		categoriaRaizes = categorias.raizes();
		}
	}
	
	
	public void carregarSubCategorias(){
		
		subCategorias = categorias.subCategoriasDe(categoriaPai);
		
	}

	public void salvar(){
		
		System.out.println("Categoria Selecionada: " +categoriaPai.getDescricao());
		
		System.out.println("SubCategoria Selecionada: " +produto.getCategoria().getDescricao());
		
		
		
	}

	
	
	//G&S

	public Produto getProduto() {
		return produto;
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
