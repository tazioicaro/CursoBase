package com.algaworks.cursojavaee.service;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Inject;

import com.algaworks.cursojavaee.model.Pedido;
import com.algaworks.cursojavaee.model.StatusPedido;
import com.algaworks.cursojavaee.repository.Pedidos;
import com.algaworks.cursojavaee.util.jpa.Transactional;

public class CadastroPedidoService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Pedidos pedidos;
	
	
	@Transactional
	public Pedido salvar (Pedido pedido){
		
		if(pedido.isNovo()){
			pedido.setDataCriacao(new Date());
			pedido.setTatus(StatusPedido.ORCAMENTO);
		}
		
		pedido = this.pedidos.guardar(pedido);
		
		return pedido;
	}

}
