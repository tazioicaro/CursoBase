package com.algaworks.cursojavaee.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.cursojavaee.model.Usuario;
import com.algaworks.cursojavaee.service.CadastroUsuarioService;
import com.algaworks.cursojavaee.service.NegocioException;
import com.algaworks.cursojavaee.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroUsuarioBean implements Serializable {	
	private static final long serialVersionUID = 1L;
	

	private Usuario usuario;
	
	@Inject
	private CadastroUsuarioService cadastroUsuarioService;
	
	
	
	public void cadastrar(){
		try{
			
		this.usuario = cadastroUsuarioService.salvar(this.usuario);		
		FacesUtil.addInforMessage("Usuário criado com sucesso!");
		
		}catch(NegocioException ne){
			FacesUtil.addErrorMessage(ne.getMessage());
		} 
	}
	
	//Adicionar um inicalizar para que possa haver a edição do Usuário
	
	
	public void inicializar() {
		if (this.usuario == null) {
			limpar();
		}
	}
		
	
	public void limpar(){
		usuario = new Usuario();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	public boolean isEditando(){
		return this.usuario.getId() !=null;
	}
	
	
	
	

}
