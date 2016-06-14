package com.algaworks.cursojavaee.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.algaworks.cursojavaee.model.Cliente;
import com.algaworks.cursojavaee.repository.Clientes;
import com.algaworks.cursojavaee.util.jpa.Transactional;
import com.algaworks.cursojavaee.util.jsf.FacesUtil;

public class CadastroClienteService implements Serializable {	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Clientes clientes;
	
	@Transactional
	public Cliente salvar(Cliente cliente){
	
		Cliente usuarioExistenteNome = clientes.consultarNome(cliente.getNome());
		Cliente usuarioExistenteEmail = clientes.porEmail(cliente.getEmail());
		
		if(usuarioExistenteNome!=null && usuarioExistenteNome.equals(cliente) )
				{
			throw new NegocioException("Já existe um usuário com o nome informado!");
		} else if  (usuarioExistenteEmail!=null && usuarioExistenteEmail.equals(cliente)){
			
			throw new NegocioException("Já existe um usuário com o email informado!");
		}
		
		return clientes.guardar(cliente);
	}

}
