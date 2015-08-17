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

@Named
@ViewScoped
public class CadastroProdutoBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Produto produto;	
	private List<Categoria> categoriaRaizes;
	
	@NotNull
	private Categoria categoriaPai;
	
	@Inject
	private Categorias categorias;
	
	
	public CadastroProdutoBean() {		
		produto = new Produto();
	}


	public void inicializar(){
		
		System.out.println("Inicializando...");		
		categoriaRaizes = categorias.raizes();
		
	}

	public void salvar(){
		
		System.out.println("Categoria Pai Selecionada" + categoriaPai.getDescricao());
		
	}



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



}
