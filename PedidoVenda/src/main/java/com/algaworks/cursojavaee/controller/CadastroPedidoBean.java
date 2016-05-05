package com.algaworks.cursojavaee.controller;


import java.io.Serializable;
import java.util.List;

import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
//import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.cursojavaee.model.Cliente;
import com.algaworks.cursojavaee.model.EnderecoEntregra;
import com.algaworks.cursojavaee.model.FormaPagamento;
import com.algaworks.cursojavaee.model.Pedido;
import com.algaworks.cursojavaee.model.Usuario;
import com.algaworks.cursojavaee.repository.Clientes;
import com.algaworks.cursojavaee.repository.Usuarios;
import com.algaworks.cursojavaee.service.CadastroPedidoService;
import com.algaworks.cursojavaee.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroPedidoBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Produces
	private Pedido pedido;		
	@Inject
	private Usuarios usuarios;
	@Inject
	private Clientes clientes;	
	@Inject
	private CadastroPedidoService cadastroPedidoService;
	
		
	private List<Usuario> vendedores;
	
	
	public CadastroPedidoBean(){
		limpar();
		
	
	}
	
	public void incializar(){
		
		if(FacesUtil.isNotPostBack()){
			this.vendedores = this.usuarios.vendedores();
		}
		
	}
	
	public void limpar(){
		pedido = new Pedido();
		pedido.setEnderecoEntregra(new EnderecoEntregra());
		
	}
	
	public List<Cliente> completarCliente (String nome){
		
		return this.clientes.porNome(nome);
		
	}
	

	
	public void salvar(){
		//É atriuído ao pedido o objeto pedido completo com ID e outro objetos dependentes
		this.pedido = this.cadastroPedidoService.salvar(this.pedido);
		
		FacesUtil.addInforMessage("Pedido salvo com sucesso!");
	}
	
	public void recalcularPedido (){
		this.pedido.recalcularValorTotal();
	}
	
	public boolean isEditando(){
		return this.pedido.getId() !=null;
	}
	
	//retornar os valores de todos os pagamentos do Enum
	public FormaPagamento[] getFormasPagamento(){
		return FormaPagamento.values();
	}

	
	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public List<Usuario> getVendedores() {
		return vendedores;
	}

	

}
