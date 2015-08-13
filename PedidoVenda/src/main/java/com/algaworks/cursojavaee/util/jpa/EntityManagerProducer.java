package com.algaworks.cursojavaee.util.jpa;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/*
 * Produtor de EntityManager
 */
@ApplicationScoped
public class EntityManagerProducer {
	
	private EntityManagerFactory factory;

	public EntityManagerProducer() {	
		 factory= Persistence.createEntityManagerFactory("PedidoPU");

	}
	
	@Produces @RequestScoped
	public EntityManager createEntityManager(){		
		return factory.createEntityManager();
		
	}
	
	//Quando finalaizar a requisição ele será executado
	public void closeEntityManager(@Disposes EntityManager manager){
		manager.close();
	}
}
