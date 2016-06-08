package com.algaworks.cursojavaee.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.cursojavaee.model.Cliente;
import com.algaworks.cursojavaee.service.CadastroClienteService;
import com.algaworks.cursojavaee.service.NegocioException;
import com.algaworks.cursojavaee.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroClienteBean implements Serializable {	
	private static final long serialVersionUID = 1L;
	

	private Cliente cliente = new Cliente();;
	
	@Inject
	private CadastroClienteService cadastroClienteService;
	
	
	
	public CadastroClienteBean() {
		super();
		
	}

	public void cadastrar(){
		try{
			
		this.cliente = cadastroClienteService.salvar(this.cliente);		
		FacesUtil.addInforMessage("Usuário criado com sucesso!");
		
		}catch(NegocioException ne){
			FacesUtil.addErrorMessage(ne.getMessage());
		} 
	}
	
	//Adicionar um inicalizar para que possa haver a edição do Usuário
	
	
	public void inicializar() {
		if (this.cliente == null) {
			limpar();
		}
	}
		
	
	public void limpar(){
		cliente = new Cliente();
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setUsuario(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
	public boolean isEditando(){
		return this.cliente.getId() !=null;
	}
	
	
	
	

}
