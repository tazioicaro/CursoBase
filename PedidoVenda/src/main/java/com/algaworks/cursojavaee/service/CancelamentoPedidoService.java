package com.algaworks.cursojavaee.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.algaworks.cursojavaee.model.Pedido;
import com.algaworks.cursojavaee.model.StatusPedido;
import com.algaworks.cursojavaee.repository.Pedidos;
import com.algaworks.cursojavaee.util.jpa.Transactional;

public class CancelamentoPedidoService implements Serializable {

	private static final long serialVersionUID = 1L;

	
	@Inject
	private Pedidos pedidos;
	
	@Inject
	private EstoqueService estoqueService;
	
	@Transactional
	public Pedido cancelar(Pedido pedido) {
		//Buscar no db o pedido
		pedido = this.pedidos.porId(pedido.getId());
		
		if(pedido.isNaoCancelavel()){
			throw new NegocioException("Pedido não pode ser Cancelado no Status"
					+ pedido.getTatus().getDescricao() +".");
		}
		
		if (pedido.isEmitido()){
			this.estoqueService.retornarItensEstoque(pedido);
		}
		
		pedido.setTatus(StatusPedido.CANCELADO);
		pedido = this.pedidos.guardar(pedido);
		return pedido;
	}

}
