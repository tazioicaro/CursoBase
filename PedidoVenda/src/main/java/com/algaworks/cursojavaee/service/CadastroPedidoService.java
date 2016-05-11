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
	public Pedido salvar(Pedido pedido) throws NegocioException {

		if (pedido.isNovo()) {
			pedido.setDataCriacao(new Date());
			pedido.setTatus(StatusPedido.ORCAMENTO);
		}
		pedido.recalcularValorTotal();
		
		if( pedido.isNaoAlteravel()){
			throw new NegocioException("Pedido não pode ser alterado no status "+
		 pedido.getTatus().getDescricao() + ".");
		}

		// Antes de salvar forçar o cálculo dos produtos

		if (pedido.getItens().isEmpty()) {
			throw new NegocioException(
					"O pedido deve possuir pelo menos um item.");
		}
		if (pedido.isValorTotalNegativa()) {
			throw new NegocioException(
					"Valor total do pedido não pode ser negativo.");
		}
	
		pedido = this.pedidos.guardar(pedido);
		return pedido;
	}

}
