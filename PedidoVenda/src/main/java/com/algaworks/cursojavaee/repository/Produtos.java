package com.algaworks.cursojavaee.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.algaworks.cursojavaee.model.Produto;
import com.algaworks.cursojavaee.repository.filter.ProdutoFilter;
import com.algaworks.cursojavaee.service.NegocioException;
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
	
	
	//Devido a diversidade, não foi usado JPQL, usado Criteria do Hibernate
	
	@SuppressWarnings("unchecked")
	public List<Produto> filtrados(ProdutoFilter filter){
		//Session do Hibernate
		Session session = manager.unwrap(Session.class);
		
		Criteria criteria = session.createCriteria(Produto.class);
		if(StringUtils.isNotBlank(filter.getSku())){		
		//Adicionando restrição de igualdade com sku		
		criteria.add(Restrictions.eq("sku", filter.getSku()));
		}
		
		if(StringUtils.isNotBlank(filter.getNome())){
			//ilike é case "insensitive"			
			//MatchMode é o %, que no caso será antes e depois
			criteria.add(Restrictions.ilike("nome", filter.getNome(), MatchMode.ANYWHERE));
		}
		
		//Retornando todos os resultados ordenados por nome
		return criteria.addOrder(Order.asc("nome")).list();
	}

	public Produto porID(Long id) {
		
		return manager.find(Produto.class, id);
	}
	
	//Para remover direto da página, por isso tem a Anotação	
	@Transactional
	public void removerProduto(Produto produto){
		try{
		//Ter certeza que o produto Exite
		produto = porID(produto.getId());
		
		//produto marcado para exclusão (Necessário commit ou flush para deletar)
		manager.remove(produto);
		
		//Chamado o flush dentro do método para que , se houver exception,
		//seja lançado aqui mesmo
		manager.flush();
		} catch(PersistenceException e){
			
			throw new NegocioException("Produto não pode ser excluído");
			
		}
		
	}
	

}
