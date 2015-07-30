package com.algaworks.cursojavaee.model.Teste;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.algaworks.cursojavaee.model.Cliente;
import com.algaworks.cursojavaee.model.TipoPessoa;

public class Teste {
	
	public static void main(String[] args) {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("PedidoPU");
		EntityManager manager = factory.createEntityManager();
		
		EntityTransaction trx = manager.getTransaction();
		trx.begin();
		
		Cliente cliente = new Cliente();
		
		cliente.setNome("João das Couves");
		cliente.setEmail("blablabla@");
		cliente.setDocumntoReceitaFederal("123.123.123-12");
		cliente.setTipo(TipoPessoa.FISICA);
		
		manager.persist(cliente);
		
		trx.commit();
	}

}
