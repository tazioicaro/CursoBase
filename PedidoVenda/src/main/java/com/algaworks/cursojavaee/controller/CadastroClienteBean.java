package com.algaworks.cursojavaee.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.cursojavaee.model.Cliente;
import com.algaworks.cursojavaee.model.TipoPessoa;
import com.algaworks.cursojavaee.service.CadastroClienteService;
import com.algaworks.cursojavaee.service.NegocioException;
import com.algaworks.cursojavaee.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroClienteBean implements Serializable {	
	private static final long serialVersionUID = 1L;
	

	private Cliente cliente;
	
	@Inject
	private CadastroClienteService cadastroClienteService;
	
	
	
	public CadastroClienteBean() {
		super();
		cliente = new Cliente();
		
	}
	
	private boolean cpf = false; 
	private boolean cnpj = false; 

	public void cadastrar(){
		try{
			
		this.cliente = cadastroClienteService.salvar(this.cliente);		
		FacesUtil.addInforMessage("Usuário criado com sucesso!");
		
		}catch(NegocioException ne){
			FacesUtil.addErrorMessage(ne.getMessage());
		} 
	}
	
	
	public void carregarInputDocumentoIdentificacao(){
		
		if(this.cliente.getTipo().equals("FISICA")){
			cliente.setTipo(TipoPessoa.FISICA);
			this.cpf=true;
		}else{
			cliente.setTipo(TipoPessoa.JURIDICA);
			this.cnpj = true;
		}
		
	}
	
	
	//Adicionar um inicalizar para que possa haver a edição do Usuário
	
	
	public void inicializar() {
		if (this.cliente == null) {
			limpar();
		}
	}
	
	//Exibir na tela os enuns
	
	public TipoPessoa[] getTipoPessoa(){
		return TipoPessoa.values();
	}
	
	
	public void limpar(){
		cliente = new Cliente();
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
	public boolean isEditando(){
		return this.cliente.getId() !=null;
	}


	public boolean isCnpj() {
		return cnpj;
	}


	public void setCnpj(boolean cnpj) {
		this.cnpj = cnpj;
	}


	public boolean isCpf() {
		return cpf;
	}


	public void setCpf(boolean cpf) {
		this.cpf = cpf;
	}
	
	
	
	

}
