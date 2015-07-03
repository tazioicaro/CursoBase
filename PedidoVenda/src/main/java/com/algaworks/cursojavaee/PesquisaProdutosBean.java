package com.algaworks.cursojavaee;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

@ManagedBean
@ViewScoped
public class PesquisaProdutosBean {
	
	private List<Integer> produtosFiltrados;
	


	public PesquisaProdutosBean(){
		produtosFiltrados = new ArrayList<Integer>();
		for(int i=0; i<50; i++){
			produtosFiltrados.add(i);
		}
	}
	
	
	public List<Integer> getProdutosFiltrados() {
		return produtosFiltrados;
	}

}
