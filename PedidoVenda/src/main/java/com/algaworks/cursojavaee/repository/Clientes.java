package com.algaworks.cursojavaee.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import com.algaworks.cursojavaee.model.Cliente;
import com.algaworks.cursojavaee.util.jpa.Transactional;

public class Clientes implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
		
	public Cliente porID(Long id){
		return manager.find(Cliente.class, id);
	}
	
	/**	 
	 * @param categoriaPai
	 * Está associado ao parâmetro :raiz
	 * Assim é possível fazer um filtro 
	 */
	public List<Cliente> porNome (String nome){
		return this.manager.createQuery("from Cliente where upper(nome) like :nome", Cliente.class)
				.setParameter("nome", nome.toUpperCase() + "%").getResultList();
	}
	
	public Cliente consultarNome (String nome){
		try {return this.manager.createQuery("from Cliente where upper(nome) :nome", Cliente.class)
				.setParameter("nome", nome.toUpperCase() + "%").getSingleResult();} catch (NoResultException e){
					
				}
		return null;
	}
	
	@Transactional
	public Cliente guardar (Cliente cliente){
		return manager.merge(cliente);
	}

	public Cliente porEmail(String email) {
		try{
			return this.manager.createQuery("from Cliente where upper(email) :email", Cliente.class)
					.setParameter("email", email.toUpperCase()).getSingleResult();
		}catch (NoResultException e) {
			
		}
		return null;
	}

}
