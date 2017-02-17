package com.algaworks.cursojavaee.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.algaworks.cursojavaee.model.Cliente;
import com.algaworks.cursojavaee.repository.Clientes;
import com.algaworks.cursojavaee.util.jpa.Transactional;

public class CadastroClienteService implements Serializable {	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Clientes clientes;
	
	@Transactional
	public Cliente salvar(Cliente cliente){
	
		Cliente usuarioExistenteNome = clientes.consultarNome(cliente.getNome());
		Cliente usuarioExistenteEmail = clientes.porEmail(cliente.getEmail());
		Cliente usuarioExistenteDoc = clientes.porDocumntoReceitaFederal(cliente.getDocumntoReceitaFederal());
		
		if(usuarioExistenteNome!=null && usuarioExistenteNome.getNome().equals(cliente.getNome()) )
				{
			throw new NegocioException("Já existe um usuário com o nome informado!");
		} if  (usuarioExistenteEmail!=null && usuarioExistenteEmail.getEmail().equals(cliente.getEmail())){
			
			throw new NegocioException("Já existe um usuário com o e-mail informado!");
		}if (usuarioExistenteDoc !=null && usuarioExistenteDoc.getDocumntoReceitaFederal().equals(cliente.getDocumntoReceitaFederal())){
			//Incluir um if para personalizar mensagem de cpf e cnpj
			throw new NegocioException("Já existe um cliente com o Documento de identitificação informado, consulte a lista de clientes!");
		}
		
		cliente = this.clientes.guardar(cliente);
		return cliente;
	}

}
