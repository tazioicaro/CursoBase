package com.algaworks.cursojavaee.controller;

import java.io.Serializable;

import javax.faces.component.html.HtmlSelectOneRadio;
import javax.faces.event.ValueChangeEvent;
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

	private boolean cpf;
	private boolean cnpj;
	private HtmlSelectOneRadio radio;

	public CadastroClienteBean() {
		limpar();

	}

	// Adicionar um inicalizar para que possa haver a edição do Usuário
	public void inicializar() {
		if (this.cliente == null) {

		}
	}

	public void cadastrar() {
		try {

			this.cliente = cadastroClienteService.salvar(this.cliente);
			FacesUtil.addInforMessage("Usuário criado com sucesso!");
			limpar();

		} catch (NegocioException ne) {
			FacesUtil.addErrorMessage(ne.getMessage());
		}
	}

	public void carregarInputDocumentoIdentificacao() {

		if (this.radio.getValue().toString().equals("FISICA")) {
			cliente.setTipo(TipoPessoa.FISICA);
			this.cpf = true;
			this.cnpj = false;
		} else if (this.radio.getValue().toString().equals("JURIDICA")) {
			this.cliente.setTipo(TipoPessoa.JURIDICA);
			this.cnpj = true;
			this.cpf = false;
		}

	}

	// Pegando o valor da radiobutton e colocando numa variável
	public void pegarDadosEscolhido(ValueChangeEvent event) {
		radio = (HtmlSelectOneRadio) event.getComponent();

		// String nome = radio.getValue().toString();
		// System.out.println("Valor selecionado foi: " + nome);

	}

	// Exibir na tela os enuns

	public TipoPessoa[] getTipoPessoa() {
		return TipoPessoa.values();
	}

	public void limpar() {
		cliente = new Cliente();
		cpf = false;
		cnpj = false;
		radio = null;
	}

	public boolean isEditando() {
		if (cliente.getId() != null){
		if (this.cliente.getDocumntoReceitaFederal().length() <= 14
				&& isMaiorZero()) {
			this.cpf = true;
			this.cnpj = false;
		} else if (this.cliente.getDocumntoReceitaFederal().length()> 14 && 
				isMaiorZero()) {
			this.cpf = false;
			this.cnpj = true;
		}
		
		return true;
	}
		return false;
	}	

	public boolean isMaiorZero() {
		return this.cliente.getDocumntoReceitaFederal().length() > 0;
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

	// G&S
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public HtmlSelectOneRadio getRadio() {
		return radio;
	}

	public void setRadio(HtmlSelectOneRadio radio) {
		this.radio = radio;
	}

}
