package com.algaworks.cursojavaee.controller;


import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;


@Named
@SessionScoped
public class PesquisaPedidosBean { 
	
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

}
