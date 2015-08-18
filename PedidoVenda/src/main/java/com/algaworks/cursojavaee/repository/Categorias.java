package com.algaworks.cursojavaee.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.algaworks.cursojavaee.model.Categoria;

public class Categorias implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public List<Categoria> raizes(){
		
		return manager.createQuery("from Categoria where categoriaPai is null", Categoria.class).getResultList(); //todas os objetos do tipo categoria, mapeado no banco
		
	}
	
	public Categoria porID(Long id){
		return manager.find(Categoria.class, id);
	}
	
	public List<Categoria> subCategoriasDe (Categoria categoriaPai){
		return manager.createQuery("from Categoria where categoriaPai = :raiz", Categoria.class)
				.setParameter("raiz", categoriaPai).getResultList();
	}

}
