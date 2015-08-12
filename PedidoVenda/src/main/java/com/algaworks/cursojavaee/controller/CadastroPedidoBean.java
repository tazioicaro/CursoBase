package com.algaworks.cursojavaee.controller;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.algaworks.cursojavaee.model.EnderecoEntregra;
import com.algaworks.cursojavaee.model.Pedido;
import com.algaworks.cursojavaee.service.NegocioException;

@Named
@ViewScoped
public class CadastroPedidoBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Pedido pedido;
	
	
	private List<Integer> itens;
	


	public CadastroPedidoBean(){
		
		pedido = new Pedido();
		pedido.setEnderecoEntregra(new EnderecoEntregra ());
		itens = new ArrayList<Integer>();
		itens.add(1);
	}
	
	public void salvar(){
		
		throw new NegocioException("Pedido não pode ser Salvo, pois ainda não foi implementado");
	}

	
	public List<Integer> getItens() {
		return itens;
	}
	
	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	

}
