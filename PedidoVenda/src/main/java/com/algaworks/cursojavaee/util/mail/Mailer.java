package com.algaworks.cursojavaee.util.mail;



import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import com.outjected.email.api.MailMessage;
import com.outjected.email.api.SessionConfig;
import com.outjected.email.impl.MailMessageImpl;

@RequestScoped
public class Mailer implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Cria objetos Email para ser enviados
	 */

	
	//Configuração da sessão do envio de e-mail
	@Inject
	private SessionConfig config;
	
	public MailMessage novaMensagem() {
		return new MailMessageImpl(this.config);
	}
	
}
