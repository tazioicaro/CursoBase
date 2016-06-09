package com.algaworks.cursojavaee.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.algaworks.cursojavaee.model.Cliente;
import com.algaworks.cursojavaee.repository.Clientes;

public class CadastroClienteService implements Serializable {	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Clientes clientes;
	
	public Cliente salvar(Cliente cliente){
		/**
		 * Inserir as restrição para salvamento
		 */
		return clientes.guardar(cliente);
	}

}
