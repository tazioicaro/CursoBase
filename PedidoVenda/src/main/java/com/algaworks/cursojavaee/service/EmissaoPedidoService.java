package com.algaworks.cursojavaee.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.algaworks.cursojavaee.model.Pedido;
import com.algaworks.cursojavaee.model.StatusPedido;
import com.algaworks.cursojavaee.repository.Pedidos;
import com.algaworks.cursojavaee.util.jpa.Transactional;

public class EmissaoPedidoService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	@Inject
	private CadastroPedidoService cadastroPedidoService;
	
	@Inject
	private Pedidos pedidos;
	
	@Inject
	private EstoqueService estoqueService;
	
	@Transactional
	public Pedido emitir (Pedido pedido){
		
		pedido = this.cadastroPedidoService.salvar(pedido);
		
		if(pedido.isNaoEmissivel()){
			throw new NegocioException("Pedido não pode ser emitido com status" 
		+ pedido.getTatus().getDescricao() + ".");
		}
		//Dar baixo no estoque
		this.estoqueService.baixarItensEstoque(pedido);
		
		pedido.setTatus(StatusPedido.EMITIDO);		
		pedido = this.pedidos.guardar(pedido);		
		return pedido;
		
	}

}
