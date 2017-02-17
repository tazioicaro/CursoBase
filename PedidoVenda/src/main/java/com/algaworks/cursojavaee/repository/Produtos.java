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
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.algaworks.cursojavaee.model.Produto;
import com.algaworks.cursojavaee.repository.filter.ProdutoFilter;
import com.algaworks.cursojavaee.repository.filter.UsuarioFilter;
import com.algaworks.cursojavaee.service.NegocioException;
import com.algaworks.cursojavaee.util.jpa.Transactional;

public class Produtos implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	// @Transactional
	public Produto guardar(Produto produto) {

		return manager.merge(produto);

	}

	// Para remover direto da página, por isso tem a Anotação
	@Transactional
	public void removerProduto(Produto produto) {
		try {
			// Ter certeza que o produto Exite
			produto = porID(produto.getId());

			// produto marcado para exclusão (Necessário commit ou flush para
			// deletar)
			manager.remove(produto);

			// Chamado o flush dentro do método para que , se houver exception,
			// seja lançado aqui mesmo e todas pendências sejam executadas
			// (inluindo o commit)
			// Se o objetivo estiver sendo usado por outro DB é lançadu uma
			// exceção.
			manager.flush();
		} catch (PersistenceException e) {

			throw new NegocioException("Produto não pode ser excluído");

		}

	}

	public Produto porSku(String sku) {
		try {
			return manager.createQuery("From Produto where upper(sku)= :sku", Produto.class)
					.setParameter("sku", sku.toUpperCase()).getSingleResult();
		} catch (NoResultException e) {

			return null;
		}
	}

	private Criteria criarCriteriosParaFiltro(ProdutoFilter filtro) {
		// Desempacotar Session do Hibernate e colocar na variável Session do
		// Hibernate
		Session session = manager.unwrap(Session.class);

		/*
		 * Criteria do Hibernate faz de forma dinâmica Criando meios de
		 * adicionar criterios a Entidade produto
		 */

		Criteria criteria = session.createCriteria(Produto.class);

		/*
		 * Common Lang3 disponibiliza o StringUtils Aqui está verificando se o
		 * parametro SKU não está em Branco
		 */
		if (StringUtils.isNotBlank(filtro.getSku())) {

			// Adicionando restrição de igualdade com sku
			criteria.add(Restrictions.eq("sku", filtro.getSku()));
		}

		if (StringUtils.isNotBlank(filtro.getNome())) {
			// ilike é case "insensitive"
			// MatchMode é o %, que no caso será antes e depois
			criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
		}

		return criteria;
	}

	// Devido a diversidade, não foi usado JPQL, usado Criteria do Hibernate

	@SuppressWarnings("unchecked")
	public List<Produto> filtrados(ProdutoFilter filter) {

		Criteria criteria = criarCriteriosParaFiltro(filter);

		criteria.setFirstResult(filter.getPrimeiroRegistro());
		criteria.setMaxResults(filter.getQuantidadeRegistros());

		if (filter.isAscendente() && filter.getPropriedadeOrdenacao() != null) {
			criteria.addOrder(Order.asc(filter.getPropriedadeOrdenacao()));
		} else if (filter.getPropriedadeOrdenacao() != null) {
			criteria.addOrder(Order.desc(filter.getPropriedadeOrdenacao()));
		}

		// Retornando todos os resultados ordenados
		return criteria.list();
	}

	// Retorna a quantidade de linhas capturadas no banco
	public int quantidadesFiltrados(ProdutoFilter filtro) {

		Criteria criteria = criarCriteriosParaFiltro(filtro);

		criteria.setProjection(Projections.rowCount());

		return ((Number) criteria.uniqueResult()).intValue();

	}

	public Produto porID(Long id) {

		return manager.find(Produto.class, id);
	}

	public List<Produto> porNome(String nome) {
		// Usando JPQL
		// Concatenando no final do nome com um %
		return this.manager.createQuery("from Produto where upper(nome)like :nome", Produto.class)
				.setParameter("nome", nome.toUpperCase() + "%").getResultList();

	}

}
