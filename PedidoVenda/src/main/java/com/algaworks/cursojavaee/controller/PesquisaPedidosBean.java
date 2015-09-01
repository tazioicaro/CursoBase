package com.algaworks.cursojavaee.controller;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;


@Named
@ViewScoped
public class PesquisaPedidosBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	private List<Integer> pedidosFiltrados;	
	
	
	
	public List<Integer> getPedidosFiltrados() {
		return pedidosFiltrados;
	}

	public PesquisaPedidosBean(){
		pedidosFiltrados = new ArrayList<Integer>();
		
		for(int i =0; i <50; i++){
			pedidosFiltrados.add(i);
		}
		
		
	}
	
	//G&S

	
}
