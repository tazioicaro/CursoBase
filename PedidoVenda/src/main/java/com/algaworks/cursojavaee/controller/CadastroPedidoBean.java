package com.algaworks.cursojavaee.controller;


import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class CadastroPedidoBean {
	
	private List<Integer> itens;
	
	public List<Integer> getItens() {
		return itens;
	}

	public CadastroPedidoBean(){
		itens = new ArrayList<Integer>();
		itens.add(1);
	}
	

}
