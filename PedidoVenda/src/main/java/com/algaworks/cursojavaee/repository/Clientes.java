package com.algaworks.cursojavaee.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.algaworks.cursojavaee.model.Cliente;
import com.algaworks.cursojavaee.repository.filter.ClienteFilter;
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
		return this.manager.createQuery("from Cliente where upper(nome)like =:nome", Cliente.class)
				.setParameter("nome", nome.toUpperCase() + "%").getResultList();
	}
	
	public Cliente consultarNome (String nome){
		try {
			return this.manager.createQuery("from Cliente where lower(nome) =:nome", Cliente.class)
				.setParameter("nome", nome.toLowerCase()).getSingleResult();
			} catch (NoResultException e){
					
				}
		return null;
	}
	
	@Transactional
	public Cliente guardar (Cliente cliente){
		return manager.merge(cliente);
	}

	public Cliente porEmail(String email) {
		try{
			return this.manager.createQuery("from Cliente where upper(email) =:email", Cliente.class)
					.setParameter("email", email.toUpperCase()).getSingleResult();
		}catch (NoResultException e) {
			
		}
		return null;
	}
	
	public Cliente porDocumntoReceitaFederal(String documntoReceitaFederal){
		try{
			return this.manager.createQuery("from Cliente where lower(doc_receita_federal) =:doc", Cliente.class)
					.setParameter("doc", documntoReceitaFederal.toLowerCase()).getSingleResult();
		}catch(NoResultException e){
			
		}
		return null;
	}

	
	private Criteria criarCriteriosParaFiltro(ClienteFilter filtro){
		Session session = manager.unwrap(Session.class);
		
		Criteria criteria = session.createCriteria(Cliente.class);
		
		if (StringUtils.isNotBlank(filtro.getNome())){
			criteria.add(Restrictions.ilike("nome", filtro.getNome()));
		}
		
		if (StringUtils.isNotEmpty(filtro.getDocumntoReceitaFederal())){
			criteria.add(Restrictions.eq("doc_receita_federal", filtro.getDocumntoReceitaFederal()));
		}
		
		return criteria;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Cliente> filtrados(ClienteFilter filtro) {
		
		Criteria criteria = criarCriteriosParaFiltro(filtro);
		
		criteria.setFirstResult(filtro.getPrimeiroRegistro());
		criteria.setMaxResults(filtro.getQuantidadeRegistros());
		
		if(filtro.isAscendente() && filtro.getPropriedadeOrdenacao() != null){
			criteria.addOrder(Order.asc(filtro.getPropriedadeOrdenacao()));
		}else if ( filtro.getPropriedadeOrdenacao() != null){
			criteria.addOrder(Order.desc(filtro.getPropriedadeOrdenacao()));
		}
		
	
		return criteria.list();
	}

	public int quantidadeFiltrados(ClienteFilter filtro) {
		
		Criteria criteria = criarCriteriosParaFiltro(filtro);
		
		criteria.setProjection(Projections.rowCount());
		
	
		return ((Number) criteria.uniqueResult()).intValue();
	}

	public void removerCliente(Cliente clienteSelecionado) {
		manager.remove(clienteSelecionado);
		
	}

}
