package com.algaworks.cursojavaee.controller;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotBlank;


import org.primefaces.event.SelectEvent;

import com.algaworks.cursojavaee.events.PedidoAlteradoEvent;
import com.algaworks.cursojavaee.model.Cliente;
import com.algaworks.cursojavaee.model.EnderecoEntregra;
import com.algaworks.cursojavaee.model.FormaPagamento;
import com.algaworks.cursojavaee.model.ItemPedido;
import com.algaworks.cursojavaee.model.Pedido;
import com.algaworks.cursojavaee.model.Produto;
import com.algaworks.cursojavaee.model.Usuario;
import com.algaworks.cursojavaee.repository.Clientes;
import com.algaworks.cursojavaee.repository.Produtos;
import com.algaworks.cursojavaee.repository.Usuarios;
import com.algaworks.cursojavaee.service.CadastroPedidoService;
import com.algaworks.cursojavaee.service.NegocioException;
import com.algaworks.cursojavaee.util.jsf.FacesUtil;
import com.algaworks.cursojavaee.validation.SKU;



@Named
@ViewScoped
public class CadastroPedidoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Usuarios usuarios;
	
	@Inject
	private Clientes clientes;
	
	@Inject
	private Produtos produtos;
	
	@Inject
	private CadastroPedidoService cadastroPedidoService;
	
	private String sku;
	
	@Produces
	@PedidoEdicao
	private Pedido pedido;
	
	private List<Usuario> vendedores;
	
	private Produto produtoLinhaEditavel;
	
	public CadastroPedidoBean() {
		limpar();
	}
	
	public void inicializar() {
		if (this.pedido == null) {
			limpar();
		}
		
		this.vendedores = this.usuarios.vendedores();
		
		this.pedido.adicionarItemVazio();
		
		this.recalcularPedido();
	}
	
	public void clienteSelecionado(SelectEvent event) {
		pedido.setCliente((Cliente) event.getObject());
	}
	
	private void limpar() {
		pedido = new Pedido();
		pedido.setEnderecoEntregra(new EnderecoEntregra());
	}
	
	
	/**
	 * O @Observes é para que haja a chamada deste método quando houver 
	 * a alteração de um pedido. Assim, o que foi alterado  e persistido será exibido 
	 * da tela em tempo real 
	 */
	
	public void pedidoAlterado(@Observes PedidoAlteradoEvent event) {
		this.pedido = event.getPedido();
	}
	
	/**
	 * Antes de salvar é removida a primeira linha que contém um produto sem 
	 * Id. Após salvar ele é readicionando ao datatable para que possibilite
	 * novas inserções.
	 */
	
	
	public void salvar() {
		this.pedido.removerItemVazio();
		
		try {
			this.pedido = this.cadastroPedidoService.salvar(this.pedido);
		
			FacesUtil.addInforMessage("Pedido salvo com sucesso!");
		} catch (NegocioException ne) {
			FacesUtil.addErrorMessage(ne.getMessage());
		} finally {
			this.pedido.adicionarItemVazio();
		}
	}
	
	public void recalcularPedido() {
		if (this.pedido != null) {
			this.pedido.recalcularValorTotal();
		}
	}
	
	public void carregarProdutoPorSku() {
		if (StringUtils.isNotEmpty(this.sku)) {
			this.produtoLinhaEditavel = this.produtos.porSku(this.sku);
			this.carregarProdutoLinhaEditavel();
		}
	}
	
	// Habilita nova linha para acrescentar itens
	public void carregarProdutoLinhaEditavel() {
		ItemPedido item = this.pedido.getItens().get(0);
		
		if (this.produtoLinhaEditavel != null) {
			if (this.existeItemComProduto(this.produtoLinhaEditavel)) {
				FacesUtil.addErrorMessage("Já existe um item no pedido com o produto informado.");
			} else {
				item.setProduto(this.produtoLinhaEditavel);
				item.setValorUnitario(this.produtoLinhaEditavel.getValorUnitario());
				
				this.pedido.adicionarItemVazio();
				this.produtoLinhaEditavel = null;
				this.sku = null;
				
				this.pedido.recalcularValorTotal();
			}
		}
	}
	
	private boolean existeItemComProduto(Produto produto) {
		boolean existeItem = false;
		
		for (ItemPedido item : this.getPedido().getItens()) {
			if (produto.equals(item.getProduto())) {
				existeItem = true;
				break;
			}
		}
		
		return existeItem;
	}

	public List<Produto> completarProduto(String nome) {
		return this.produtos.porNome(nome);
	}
	
	public void atualizarQuantidade(ItemPedido item, int linha) {
		if (item.getQuantidade() < 1) {
			if (linha == 0) {
				item.setQuantidade(1);
			} else {
				this.getPedido().getItens().remove(linha);
			}
		}
		
		this.pedido.recalcularValorTotal();
	}
	
	public FormaPagamento[] getFormasPagamento() {
		return FormaPagamento.values();
	}
	
	public List<Cliente> completarCliente(String nome) {
		return this.clientes.porNome(nome);
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
	
	public boolean isEditando() {
		return this.pedido.getId() != null;
	}

	public Produto getProdutoLinhaEditavel() {
		return produtoLinhaEditavel;
	}

	public void setProdutoLinhaEditavel(Produto produtoLinhaEditavel) {
		this.produtoLinhaEditavel = produtoLinhaEditavel;
	}

	@SKU
	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}
	
	@NotBlank
	public String getNomeCliente() {
		return pedido.getCliente() == null ? null : pedido.getCliente().getNome();
	}
	
	public void setNomeCliente(String nome) {
	}

}
