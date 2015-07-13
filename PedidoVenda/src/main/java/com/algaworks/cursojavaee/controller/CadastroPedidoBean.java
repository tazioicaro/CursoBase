package com.algaworks.cursojavaee.controller;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class CadastroPedidoBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private List<Integer> itens;
	
	public List<Integer> getItens() {
		return itens;
	}

	public CadastroPedidoBean(){
		itens = new ArrayList<Integer>();
		itens.add(1);
	}
	

}
