package com.algaworks.cursojavaee.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.algaworks.cursojavaee.model.Usuario;
import com.algaworks.cursojavaee.repository.Usuarios;
import com.algaworks.cursojavaee.util.jpa.Transactional;

public class CadastroUsuarioService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Usuarios repositorioUsuarios;
	

	@Transactional
	public Usuario salvar(Usuario usuario) throws NegocioException {
		Usuario consulta = null;
		if (repositorioUsuarios.porEmail(usuario.getEmail())!= null){
			
			throw new NegocioException("O e-mail" + usuario.getEmail() + " já existe. Necessário informar um outro.");
			
		}
		
		if (repositorioUsuarios.porNome(usuario.getNome())!=null){
			throw new NegocioException("O nome " + usuario.getNome() + " já existe. Necessário informar um outro.");
			
		}
		
		 usuario = this.repositorioUsuarios.guardar(usuario);
		 
		 return usuario;
		
	}

}
