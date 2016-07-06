package com.algaworks.cursojavaee.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.algaworks.cursojavaee.model.Usuario;
import com.algaworks.cursojavaee.repository.filter.UsuarioFilter;
import com.algaworks.cursojavaee.util.jpa.Transactional;

public class Usuarios implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Usuario porID(Long id) {
		return manager.find(Usuario.class, id);
	}

	public List<Usuario> vendedores() {

		// TODO Filtrar apenas os vendedores no futuro por grupo
		return this.manager.createQuery("from Usuario", Usuario.class)
				.getResultList();
	}

	public Usuario porEmail(String email) {
		Usuario usuario = null;
		try {
			usuario = this.manager.createQuery("from Usuario where lower(email) = :email", Usuario.class)
					.setParameter("email", email.toLowerCase())
					.getSingleResult();

		} catch (NoResultException e) {
			// Nenhuma usuário encontrado com e-mail informado
		}
		return usuario;
	}

	public Usuario porNome(String nome) {
		Usuario usuario = null;
		try {
			usuario = this.manager.createQuery("from Usuario where lower(nome) =:nome", Usuario.class)
					.setParameter("nome", nome.toLowerCase()).getSingleResult();
		} catch (NoResultException e) {

		}
		return usuario;
	}

	@Transactional
	public Usuario guardar(Usuario usuario) {
		return this.manager.merge(usuario);
	}
	
public void removerUsuario(Usuario usuarioSelecionado){
		
		manager.remove(usuarioSelecionado);
	}

	
	
	private Criteria criarCriteriaParaFiltro (UsuarioFilter filtro){
		

		Session session = manager.unwrap(Session.class);
		
		Criteria criteria = session.createCriteria(Usuario.class).createAlias("grupos", "gp");
		
		
		if (StringUtils.isNotBlank(filtro.getNome())){
			criteria.add(Restrictions.eq("nome", filtro.getNome()));
		}
		if (filtro.getGrupos()!= null && filtro.getGrupos().size()>0 ){
			criteria.add(Restrictions.in("gp.descricao", filtro.getGrupos()));
		}
		return criteria;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> filtrados(UsuarioFilter filtro) {
		
		Criteria criteria = criarCriteriaParaFiltro(filtro);
		criteria.setFirstResult(filtro.getPrimeiroRegistro());
		criteria.setMaxResults(filtro.getQtdeRegistros());
		
		if(filtro.isAscendente() && filtro.getPropriedadeOrdenacao() != null){
			criteria.addOrder(Order.asc(filtro.getPropriedadeOrdenacao()));
		}else if(filtro.getPropriedadeOrdenacao() !=null){
			criteria.addOrder(Order.desc(filtro.getPropriedadeOrdenacao()));
		}
		return criteria.list();
	}

	public int quantidadesFiltrados(UsuarioFilter filtro) {
		Criteria criteria = criarCriteriaParaFiltro(filtro);
		criteria.setProjection(Projections.rowCount());
		return ((Number) criteria.uniqueResult()).intValue();
	}
	
	
}
