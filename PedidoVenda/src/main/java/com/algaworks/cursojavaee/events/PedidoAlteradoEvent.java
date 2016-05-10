package com.algaworks.cursojavaee.events;

import com.algaworks.cursojavaee.model.Pedido;

/**
 * @author tazio.fernandes
 *
 *Este evento será chamado assim que houver uma alteração no 
 *pedido e encapsulará o objeto pedido para que tanto o pedido buscado
 *do banco de dados quando o pedido existente no tela que pesquisaPedidos 
 *possam ser unificado para que hala a informação correta e atualizada na
 *página de alteração do pedido.
 */

public class PedidoAlteradoEvent {
	
	private Pedido pedido;
	
	public PedidoAlteradoEvent (Pedido pedido) {
		
		this.pedido = pedido;
		
		
	}

	public Pedido getPedido() {
		return pedido;
	}

}
