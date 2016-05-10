package com.algaworks.cursojavaee.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.algaworks.cursojavaee.model.ItemPedido;
import com.algaworks.cursojavaee.model.Pedido;
import com.algaworks.cursojavaee.repository.Pedidos;
import com.algaworks.cursojavaee.util.jpa.Transactional;

public class EstoqueService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Pedidos pedidos;

	@Transactional
	public void baixarItensEstoque(Pedido pedido) {
		pedido = this.pedidos.porId(pedido.getId());

		for (ItemPedido item : pedido.getItens()) {
			item.getProduto().baixarEstoque(item.getQuantidade());
		}

	}

	public void retornarItensEstoque(Pedido pedido) {
		pedido = this.pedidos.porId(pedido.getId());
		
		for(ItemPedido item: pedido.getItens()){
			
			//Devolvendo a quantidade ao estoque
			item.getProduto().adicionarEstoque(item.getQuantidade());
		}
		
	}

}
