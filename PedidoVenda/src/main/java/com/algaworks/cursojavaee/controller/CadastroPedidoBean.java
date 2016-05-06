package com.algaworks.cursojavaee.controller;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
//import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

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
import com.algaworks.cursojavaee.util.jsf.FacesUtil;
import com.algaworks.cursojavaee.validation.SKU;

@Named
@ViewScoped
public class CadastroPedidoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Produces
	private Pedido pedido;
	@Inject
	private Usuarios usuarios;
	@Inject
	private Clientes clientes;
	@Inject
	private CadastroPedidoService cadastroPedidoService;
	@Inject
	private Produtos produtos;

	private Produto produtoLinhaEditavel;
	private List<Usuario> vendedores;

	@SKU
	private String sku;

	public CadastroPedidoBean() {
		limpar();

	}

	public void incializar() {

		if (FacesUtil.isNotPostBack()) {
			this.vendedores = this.usuarios.vendedores();

			this.pedido.adicionarItemVazio();
			this.recalcularPedido();
		}

	}

	public void limpar() {
		pedido = new Pedido();
		pedido.setEnderecoEntregra(new EnderecoEntregra());

	}

	public List<Cliente> completarCliente(String nome) {

		return this.clientes.porNome(nome);

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
		if (StringUtils.isNoneEmpty(this.sku)) {
			this.produtoLinhaEditavel = this.produtos.porSku(sku);
			this.carregarProdutoLinhaEditavel();
		}
	}

	// Habilita nova linha para acrescentar itens
	public void carregarProdutoLinhaEditavel() {

		// Pegando o primeiro item da lista. Neste caso é o da linha editável
		ItemPedido item = this.pedido.getItens().get(0);

		if (this.produtoLinhaEditavel != null) {

			if (this.existeItemComProduto(this.produtoLinhaEditavel)) {

				FacesUtil
						.addErrorMessage("Já existe no pedido com o produto informado");

			} else {

				// Adicionando produto e valor unitário no primeiro item da
				// lista
				item.setProduto(this.produtoLinhaEditavel);
				item.setValorUnitario(this.produtoLinhaEditavel
						.getValorUnitario());
				// Adicionando um novo elemento (item vazio) acima deste que
				// editamos
				this.pedido.adicionarItemVazio();
				this.produtoLinhaEditavel = null;
				this.sku = null;
				this.pedido.recalcularValorTotal();
			}
		}

	}

	public void atualizarQuantidade(ItemPedido item, int linha) {

		if (item.getQuantidade() < 1) {
			// Se for a primeira linha, que é a editável, não será excluída
			if (linha == 0) {
				item.setQuantidade(1);
			} else {
				this.getPedido().getItens().remove(linha);

			}
		}
		this.pedido.recalcularValorTotal();
	}

	public List<Produto> completarProduto(String nome) {
		return this.produtos.porNome(nome);
	}

	public boolean isEditando() {
		return this.pedido.getId() != null;
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

	// retornar os valores de todos os pagamentos do Enum
	public FormaPagamento[] getFormasPagamento() {
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

	public Produto getProdutoLinhaEditavel() {
		return produtoLinhaEditavel;
	}

	public void setProdutoLinhaEditavel(Produto produtoLinhaEditavel) {
		this.produtoLinhaEditavel = produtoLinhaEditavel;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

}
