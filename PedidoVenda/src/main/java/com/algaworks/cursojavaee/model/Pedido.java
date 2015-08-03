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
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="pedido")
public class Pedido implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCriacao;
	
	@Column (columnDefinition = "text") //Criar no banco um tipo texto e não um varchar
	private String observacao;
	
	@Column (name="data_entrega", nullable=false)
	@Temporal(TemporalType.DATE)
	private Date dataEntrega;
	
	@Column(nullable=false, precision=10, scale=2, name="valor_frete")
	private BigDecimal valorFrete;
	
	@Column(nullable=false, precision=10, scale=2, name="valor_desconto")
	private BigDecimal valorDesconto;
	
	@Column(nullable=false, precision=10, scale=2, name="valor_total")
	private BigDecimal valorTotal;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false, length=20)
	private StatusPedido tatus;
	
	@Enumerated(EnumType.STRING)
	@Column(name="forma_pagamento", nullable=false, length=20)
	private FormaPagamento formaPagamento;
	
	@ManyToOne
	@JoinColumn(name="vendedor_id", nullable=false)
	private Usuario vendedor;
	
	@ManyToOne
	@JoinColumn(name="cliente_id", nullable=false)
	private Cliente cliente;
	
	
	@Embedded  //Imbutido dentro do pedido
	private EnderecoEntregra enderecoEntregra; //Separado apenas para orientação ao OBJ
	
	/*orphanRemoval remover os itens orfãns
	 */			
	
	@OneToMany (mappedBy="pedido", cascade = CascadeType.ALL, orphanRemoval=true) //Mapado na clase ItemPedido, salva pedidos e seus ítens
	private List<ItemPedido> itens = new ArrayList<>();

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

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public EnderecoEntregra getEnderecoEntregra() {
		return enderecoEntregra;
	}

	public void setEnderecoEntregra(EnderecoEntregra enderecoEntregra) {
		this.enderecoEntregra = enderecoEntregra;
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
