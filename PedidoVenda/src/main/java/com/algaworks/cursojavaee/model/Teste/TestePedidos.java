package com.algaworks.cursojavaee.model.Teste;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.algaworks.cursojavaee.model.Cliente;
import com.algaworks.cursojavaee.model.EnderecoEntregra;
import com.algaworks.cursojavaee.model.FormaPagamento;
import com.algaworks.cursojavaee.model.ItemPedido;
import com.algaworks.cursojavaee.model.Pedido;
import com.algaworks.cursojavaee.model.Produto;
import com.algaworks.cursojavaee.model.StatusPedido;
import com.algaworks.cursojavaee.model.Usuario;

public class TestePedidos {
	
	public static void main(String[] args) {
	
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("PedidoPU");
	EntityManager manager = factory.createEntityManager();
	
	EntityTransaction trx = manager.getTransaction();
	trx.begin();
	
	
	Cliente cliente = manager.find(Cliente.class, 1L); // 1 Long
	Produto produto = manager.find(Produto.class, 1L);
	Usuario vendedor = manager.find(Usuario.class, 1L);
	
	EnderecoEntregra enderecoEntregra = new EnderecoEntregra();
	enderecoEntregra.setLogradouro("Logradouro");
	enderecoEntregra.setNumero("1000");
	enderecoEntregra.setCidade("Uberlandia");
	enderecoEntregra.setUf("MG");
	enderecoEntregra.setCep("26587-151");

	Pedido pedido = new Pedido();
	pedido.setCliente(cliente);
	pedido.setDataCriacao(new Date());
	pedido.setDataEntrega(new Date());
	pedido.setFormaPagamento(FormaPagamento.CARTAO_CREDITO);
	pedido.setObservacao("Aberto das 12 às 15");
	pedido.setTatus(StatusPedido.ORCAMENTO);
	pedido.setValorDesconto(BigDecimal.ZERO);
	pedido.setValorFrete(BigDecimal.ZERO);
	pedido.setValorTotal(new BigDecimal(23.2));
	pedido.setVendedor(vendedor);
	pedido.setEnderecoEntregra(enderecoEntregra);
	
	ItemPedido item = new ItemPedido();
	
	item.setProduto(produto);
	item.setQuantidade(10);
	item.setValorUnitario(new BigDecimal(2.32));
	item.setPedido(pedido);
	
	pedido.getItens().add(item);
	
	manager.persist(pedido); 
	trx.commit();
	
	
	}

}
