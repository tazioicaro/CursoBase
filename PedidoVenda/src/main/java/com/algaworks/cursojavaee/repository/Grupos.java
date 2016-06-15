package com.algaworks.cursojavaee.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.algaworks.cursojavaee.model.Grupo;

public class Grupos implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	
	public List<Grupo> porGrupos(){		
			return this.manager.createQuery("from Grupo", Grupo.class).getResultList();		
		
	}


	public Grupo porId(Long id) {
		
		return this.manager.find(Grupo.class, id);
	}
}
