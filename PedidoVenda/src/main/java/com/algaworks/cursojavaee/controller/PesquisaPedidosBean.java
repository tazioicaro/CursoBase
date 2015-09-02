package com.algaworks.cursojavaee.controller;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.cursojavaee.model.Pedido;
import com.algaworks.cursojavaee.model.StatusPedido;
import com.algaworks.cursojavaee.repository.Pedidos;
import com.algaworks.cursojavaee.repository.filter.PedidoFilter;


@Named
@ViewScoped
public class PesquisaPedidosBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Pedidos pedidos;
	
	private List<Pedido> pedidosFiltrados;	
	private PedidoFilter filtro;
		

	public PesquisaPedidosBean(){
		setPedidosFiltrados(new ArrayList<Pedido>());		
		filtro = new PedidoFilter();
		
		
	}
	
	public void pesquisar(){
		pedidosFiltrados = pedidos.filtrados(filtro);
	}
	
	public StatusPedido[] getStatuses(){
		return StatusPedido.values();
	}
	

	//G&S
	public List<Pedido> getPedidosFiltrados() {
		return pedidosFiltrados;
	}


	public void setPedidosFiltrados(List<Pedido> pedidosFiltrados) {
		this.pedidosFiltrados = pedidosFiltrados;
	}

	public PedidoFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(PedidoFilter filtro) {
		this.filtro = filtro;
	}
	

	
	

	
}
