package com.algaworks.cursojavaee.controller;



import java.io.Serializable;
import java.util.Locale;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.velocity.tools.generic.NumberTool;
import org.olap4j.metadata.Property.ContentType;

import com.algaworks.cursojavaee.model.Pedido;
import com.algaworks.cursojavaee.util.jsf.FacesUtil;
import com.algaworks.cursojavaee.util.mail.Mailer;
import com.outjected.email.api.MailMessage;
import com.outjected.email.impl.templating.velocity.VelocityTemplate;

@Named
@RequestScoped
public class EnvioPedidoEmailBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Mailer mailer;
	
	@Inject
	@PedidoEdicao
	private Pedido pedido;
	
	public void enviarPedido() {
		MailMessage message = mailer.novaMensagem();
		
		message.to(this.pedido.getCliente().getEmail())
			.subject("Recebemos seu Pedido de n°: " + this.pedido.getId())
			.bodyHtml(new VelocityTemplate(getClass().getResourceAsStream("/emails/pedido.template")))
			.put("pedido", this.pedido).charset("UTF-8")
			.put("numberTool", new NumberTool()).addHeader("Cabeçalho", "Cabeçalho")
			.put("locale", new Locale("pt", "BR"))
			.send();
		
		FacesUtil.addInforMessage("Pedido enviado por e-mail com sucesso!");
	}
	
	
}
