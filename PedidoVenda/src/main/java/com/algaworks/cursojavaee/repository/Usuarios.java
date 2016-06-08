package com.algaworks.cursojavaee.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import com.algaworks.cursojavaee.model.Usuario;

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
			usuario = this.manager
					.createQuery("from Usuario where lower(email) = :email",
							Usuario.class)
					.setParameter("email", email.toLowerCase())
					.getSingleResult();

		} catch (NoResultException e) {
			// Nenhuma usuário encontrado com e-mail informado
		}
		return usuario;
	}
	
	public Usuario guardar (Usuario usuario){
		return this.manager.merge(usuario);
	}
	
	public Usuario porNome (String nome){
		Usuario usuario = null;
		try{
			usuario = this.manager.createQuery("from Usuario where lower(nome) =:nome", Usuario.class)
					.setParameter("nome", nome.toLowerCase())
					.getSingleResult();
		}catch (NoResultException e){
			
		}
		return usuario;
	}

}
