package com.algaworks.cursojavaee.model.Teste;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.algaworks.cursojavaee.model.Cliente;
import com.algaworks.cursojavaee.model.Endereco;
import com.algaworks.cursojavaee.model.TipoPessoa;

public class Teste {
	
	public static void main(String[] args) {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("PedidoPU");
		EntityManager manager = factory.createEntityManager();
		
		EntityTransaction trx = manager.getTransaction();
		trx.begin();
		
		Cliente cliente = new Cliente();
		
		cliente.setNome("João das Couves dois");
		cliente.setEmail("blablabla@");
		cliente.setDocumntoReceitaFederal("123.123.123-12");
		cliente.setTipo(TipoPessoa.FISICA);
		
		Endereco endereco = new Endereco();
		
		endereco.setLogradouro("Ruas das Aboboras Vermelhas");
		endereco.setNumero("111");
		endereco.setCidade("Uberlandia");
		endereco.setUf("MG");
		endereco.setCep("22502-222");
		
		//Adicionando o cliente ao endereço
		endereco.setCliente(cliente);
		
		//Adicionando o endereço na lista de endereços do cliente (Graças ao Cascade)
		cliente.getEnderecos().add(endereco);
		
		manager.persist(cliente);
		
		trx.commit();
	}

}
