package com.algaworks.cursojavaee.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.algaworks.cursojavaee.model.Usuario;

public class Usuarios implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
		
	public Usuario porID(Long id){
		return manager.find(Usuario.class, id);
	}
	

	public List<Usuario> vendedores (){
		
		//TODO Filtrar apenas os vendedores no futuro por grupo
		return this.manager.createQuery("from Usuario", Usuario.class)
				.getResultList();
	}

}
