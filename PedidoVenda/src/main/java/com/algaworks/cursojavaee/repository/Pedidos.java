package com.algaworks.cursojavaee.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.algaworks.cursojavaee.model.Pedido;
import com.algaworks.cursojavaee.repository.filter.PedidoFilter;

public class Pedidos implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	@SuppressWarnings("unchecked")
	public List<Pedido> filtrados(PedidoFilter filtro) {

		Session session = this.manager.unwrap(Session.class);

		Criteria criteria = session.createCriteria(Pedido.class)
		// Fazemos uma associação (join) com cliente e nomeamos como "c"
				.createAlias("cliente", "c")
				/* Fazemos uma associação (Join) com vendedor e nomeamos como
				 "v' */
				.createAlias("vendedor", "v");

		if (filtro.getNumeroDe() != null) {
			/* id deve ser maior ou igual (ge = greater or equals) a
			 filtro.numeroDe */
			criteria.add(Restrictions.ge("id", filtro.getNumeroDe()));
		}

		if (filtro.getNumeroAte() != null) {
			// id deve ser enor ou igual (le = lower or equals) a filtro.nueroDe
			criteria.add(Restrictions.le("id", filtro.getNumeroAte()));
		}
		if (filtro.getDataCriacaoDe() != null) {

			criteria.add(Restrictions.ge("id", filtro.getDataCriacaoDe()));
		}

		if (filtro.getDataCriacaoAte() != null) {

			criteria.add(Restrictions.ge("id", filtro.getDataCriacaoAte()));
		}
		
		if(StringUtils.isNotBlank(filtro.getNomeCliente())){
			
			//Acessamos o nome do cliente associado ao pedido pelo alias "c" criado anteriormente
			criteria.add(Restrictions.ilike("c.nome", filtro.getNomeCliente(), MatchMode.ANYWHERE));
			
		}
		
	if(StringUtils.isNotBlank(filtro.getNomeVendedor())){
			
			//Acessamos o nome do vendedor associado ao pedido pelo alias "v" criado anteriormente
			criteria.add(Restrictions.ilike("v.nome", filtro.getNomeVendedor(), MatchMode.ANYWHERE));
			
		}
	
	if(filtro.getStatuses() != null && filtro.getStatuses().length > 0){
		
		/*adicionamos uma restrição "in" passando um array de constantes da enum StatusPedido
		
		O Status necessita está entre um desses filtros*/
		criteria.add(Restrictions.in("tatus", filtro.getStatuses()));
		
	}
	
	return criteria.addOrder(Order.asc("id")).list();

	}

}
