package com.algaworks.cursojavaee.model.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class DataValor implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/*
	 * os nomes do atributos vem coincidetem com o álias do método de projeção
	 * lá na classe TesteConsulta.
	 */
	
	private Date data;
	private BigDecimal valor;
	
	
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	
	
	

}
