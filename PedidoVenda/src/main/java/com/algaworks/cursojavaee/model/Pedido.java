package com.algaworks.cursojavaee.model;



import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "pedido")
public class Pedido implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_criacao", nullable = false)
	private Date dataCriacao;
	
	@Column(columnDefinition = "text")
	private String observacao;
	
	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name = "data_entrega", nullable = false)
	private Date dataEntrega;
	
	@NotNull
	@Column(name = "valor_frete", nullable = false, precision = 10, scale = 2)
	private BigDecimal valorFrete = BigDecimal.ZERO;
	
	@NotNull
	@Column(name = "valor_desconto", nullable = false, precision = 10, scale = 2)
	private BigDecimal valorDesconto = BigDecimal.ZERO;
	
	@NotNull
	@Column(name = "valor_total", nullable = false, precision = 10, scale = 2)
	private BigDecimal valorTotal = BigDecimal.ZERO;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	private StatusPedido tatus = StatusPedido.ORCAMENTO;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "forma_pagamento", nullable = false, length = 20)
	private FormaPagamento formaPagamento;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "vendedor_id", nullable = false)
	private Usuario vendedor;
	

	@NotNull
	@ManyToOne
	@JoinColumn(name = "cliente_id", nullable = false)
	private Cliente cliente;
	
	@Embedded
	private EnderecoEntregra enderecoEntregra;
	
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<ItemPedido> itens = new ArrayList<>();

	
	

	public void recalcularValorTotal() {
		BigDecimal total = BigDecimal.ZERO;
		
		total = total.add(this.getValorFrete()).subtract(this.getValorDesconto());
		
		for (ItemPedido item : this.getItens()) {
			if (item.getProduto() != null && item.getProduto().getId() != null) {
				total = total.add(item.getValorTotal());
			}
		}
		
		this.setValorTotal(total);
	}
	
	/**  Adicionar um produto para que fique uma linha editável na página
	 *  ao gerar um novo pedido
	 */

	public void adicionarItemVazio() {
		if (this.isOrcamento()) {
			Produto produto = new Produto();
			
			ItemPedido item = new ItemPedido();
			item.setProduto(produto);
			item.setPedido(this);
			
			this.getItens().add(0, item);
		}
	}
	
	/**
	 * Removendo a linha editável antes de salvar os items do pedido
	 */

	public void removerItemVazio() {
		ItemPedido primeiroItem = this.getItens().get(0);
		
		if (primeiroItem != null && primeiroItem.getProduto().getId() == null) {
			this.getItens().remove(0);
		}
	}
	
	@Transient
	public boolean isNovo() {
		return getId() == null;
	}
	
	@Transient
	public boolean isExistente() {
		return !isNovo();
	}
	
	
	@Transient
	public BigDecimal getValorSubtotal() {
		return this.getValorTotal().subtract(this.getValorFrete()).add(this.getValorDesconto());
	}
	
	
	@Transient
	public boolean isOrcamento() {
		return StatusPedido.ORCAMENTO.equals(this.getTatus());
	}

	@Transient
	public boolean isValorTotalNegativo() {
		
		// Se o valor total for menor que 0 é negativo o método retorna	algo.
		return this.getValorTotal().compareTo(BigDecimal.ZERO) < 0;
	}

	@Transient
	public boolean isEmitido() {
		
		// Verifica se o Status do pedido é Emitido		
		return StatusPedido.EMITIDO.equals(this.getTatus());
	}

	@Transient
	public boolean isNaoEmissivel() {
		return !this.isEmissivel();
	}

	@Transient
	public boolean isEmissivel() {
		return this.isExistente() && this.isOrcamento();
	}

	//Produto cancelável quando for existente e não pode está cancelado já
	@Transient
	public boolean isCancelavel() {
		return this.isExistente() && !this.isCancelado();
	}

	//Retorna true se for status cancelado o status do pedido
	@Transient
	private boolean isCancelado() {
		return StatusPedido.CANCELADO.equals(this.getTatus());
	}

	@Transient
	public boolean isNaoCancelavel() {
		return !this.isCancelavel();
	}

	@Transient
	public boolean isAlteravel() {
		return this.isOrcamento();
	}
	
	@Transient
	public boolean isNaoAlteravel() {
		return !this.isAlteravel();
	}
	
	@Transient
	public boolean isNaoEnviavelPorEmail() {
		return this.isNovo() || this.isCancelado();
	}
	
	
	//G&S	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}	
	public Date getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}	
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}	
	public Date getDataEntrega() {
		return dataEntrega;
	}
	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}	
	public BigDecimal getValorFrete() {
		return valorFrete;
	}
	public void setValorFrete(BigDecimal valorFrete) {
		this.valorFrete = valorFrete;
	}	
	public BigDecimal getValorDesconto() {
		return valorDesconto;
	}
	public void setValorDesconto(BigDecimal valorDesconto) {
		this.valorDesconto = valorDesconto;
	}	
	public BigDecimal getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}	
	public StatusPedido getTatus() {
		return tatus;
	}
	public void setTatus(StatusPedido tatus) {
		this.tatus = tatus;
	}	
	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}
	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}	
	public Usuario getVendedor() {
		return vendedor;
	}
	public void setVendedor(Usuario vendedor) {
		this.vendedor = vendedor;
	}
	public Cliente getCliente() {
		return cliente;
	}	
	public EnderecoEntregra getEnderecoEntregra() {
		return enderecoEntregra;
	}	
	public void setEnderecoEntregra(EnderecoEntregra enderecoEntregra) {
		this.enderecoEntregra = enderecoEntregra;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}	
	public List<ItemPedido> getItens() {
		return itens;
	}
	public void setItens(List<ItemPedido> itens) {
		this.itens = itens;
	}	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
