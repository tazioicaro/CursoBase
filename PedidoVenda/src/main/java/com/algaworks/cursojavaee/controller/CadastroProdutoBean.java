package com.algaworks.cursojavaee.controller;


import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.cursojavaee.model.Categoria;
import com.algaworks.cursojavaee.model.Produto;
import com.algaworks.cursojavaee.repository.Categorias;

@Named
@ViewScoped
public class CadastroProdutoBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Produto produto;	
	private List<Categoria> categoriaRaizes;
	
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
		
	}



	public Produto getProduto() {
		return produto;
	}



	public List<Categoria> getCategoriaRaizes() {
		return categoriaRaizes;
	}



}
