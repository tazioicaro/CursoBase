package com.algaworks.cursojavaee.controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.cursojavaee.model.Pedido;
import com.algaworks.cursojavaee.util.jsf.FacesUtil;
import com.algaworks.cursojavaee.util.mail.Mailer;
import com.outjected.email.api.MailMessage;

@Named
@RequestScoped
public class EnvioPedidoEmailBean implements Serializable {
	
	private static final long serialVersionUID = 1L;	
	
	@Inject
	private Mailer mailer;
	
	@Inject
	@PedidoEdicao
	private Pedido pedido;
	
	
	
	
	public void enviarPedido(){
		
		MailMessage message = mailer.novaMensagem();
		
		message.to(this.pedido.getCliente().getEmail()).subject("Pedido" + this.pedido.getId())
		.bodyHtml("<strong> Valor total:</strong>" + this.pedido.getValorTotal())
		.send();
		FacesUtil.addInforMessage("Pedido enviado por email com sucesso!");
	}

}
