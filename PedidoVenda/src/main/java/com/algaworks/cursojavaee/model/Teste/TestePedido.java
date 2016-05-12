//package com.algaworks.cursojavaee.model.Teste;
//
//import java.math.BigDecimal;
//import java.util.Date;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.EntityTransaction;
//import javax.persistence.Persistence;
//
//import com.algaworks.cursojavaee.model.Cliente;
//import com.algaworks.cursojavaee.model.EnderecoEntregra;
//import com.algaworks.cursojavaee.model.FormaPagamento;
//import com.algaworks.cursojavaee.model.ItemPedido;
//import com.algaworks.cursojavaee.model.Pedido;
//import com.algaworks.cursojavaee.model.Produto;
//import com.algaworks.cursojavaee.model.StatusPedido;
//import com.algaworks.cursojavaee.model.Usuario;
//
//public class TestePedido {
//	
//	public static void main(String[] args) {
//		EntityManagerFactory factory = Persistence.createEntityManagerFactory("PedidoPU");
//		EntityManager manager = factory.createEntityManager();
//		
//		EntityTransaction trx = manager.getTransaction();
//		trx.begin();
//		
//		Cliente cliente = manager.find(Cliente.class, 1L);
//		Produto produto = manager.find(Produto.class, 1L);
//		Usuario vendedor = manager.find(Usuario.class, 1L);
//		
//		EnderecoEntregra enderecoEntrega = new EnderecoEntregra();
//		enderecoEntrega.setLogradouro("Rua dos Mercados");
//		enderecoEntrega.setNumero("1000");
//		enderecoEntrega.setCidade("Uberlândia");
//		enderecoEntrega.setUf("MG");
//		enderecoEntrega.setCep("38400-123");
//		
//		Pedido pedido = new Pedido();
//		pedido.setCliente(cliente);
//		pedido.setDataCriacao(new Date());
//		pedido.setDataEntrega(new Date());
//		pedido.setFormaPagamento(FormaPagamento.CARTAO_CREDITO);
//		pedido.setObservacao("Aberto das 08 às 18h");
//		pedido.setTatus(StatusPedido.ORCAMENTO);
//		pedido.setValorDesconto(BigDecimal.ZERO);
//		pedido.setValorFrete(BigDecimal.ZERO);
//		pedido.setValorTotal(new BigDecimal(23.2));
//		pedido.setVendedor(vendedor);
//		pedido.setEnderecoEntregra(enderecoEntrega);
//		
//		
//		//Vai salvar também o ItemPedido devido ao Cascade inserido no classe ItemPedido
//		ItemPedido item = new ItemPedido();
//		item.setProduto(produto);
//		item.setQuantidade(10);
//		item.setValorUnitario(new BigDecimal(2.32));
//		item.setPedido(pedido);
//		
//		pedido.getItens().add(item);
//		
//		manager.persist(pedido);
//		
//		trx.commit();
//	}
//	
//
//}
