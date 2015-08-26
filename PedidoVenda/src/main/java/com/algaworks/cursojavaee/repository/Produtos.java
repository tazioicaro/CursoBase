package com.algaworks.cursojavaee.repository;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import com.algaworks.cursojavaee.model.Produto;
import com.algaworks.cursojavaee.util.jpa.Transactional;

public class Produtos  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;

	@Transactional
	public Produto guardar(Produto produto) {	
	 
		return manager.merge(produto);	
		
	}
	
	public Produto porSku (String sku){
		try {
			return manager.createQuery("From Produto where upper(sku)= :sku", Produto.class)
					.setParameter("sku", sku.toUpperCase()).getSingleResult();
		} catch (NoResultException e) {
			
			return null;
		}
	}
	
	

}
