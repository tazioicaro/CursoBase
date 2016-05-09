package com.algaworks.cursojavaee.controller;
import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.cursojavaee.model.Pedido;
import com.algaworks.cursojavaee.service.EmissaoPedidoService;
import com.algaworks.cursojavaee.util.jsf.FacesUtil;

@Named
@ViewScoped
public class EmissaoPedidoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	@PedidoEdicao
	private Pedido pedido;

	@Inject
	private EmissaoPedidoService emissaoPedidoService;

	/*
	 * A referência do Pedido será atraves da uma anotação
	 */
	public void emitirPedido() {

		this.pedido.removerItemVazio();

		try {

			this.pedido = this.emissaoPedidoService.emitir(this.pedido);

			FacesUtil.addInforMessage("Pedido emitido com sucesso!");

		} finally {
			this.pedido.adicionarItemVazio();
		}
	}

}
