package com.algaworks.cursojavaee.util.mail;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import com.outjected.email.api.MailMessage;
import com.outjected.email.api.SessionConfig;
import com.outjected.email.impl.MailMessageImpl;

@RequestScoped
public class Mailer  implements Serializable{

	
	private static final long serialVersionUID = 1L;
	/**
	 * SessionConfig é configuração da sessão de envio de e-mail,
	 * contém a configuração de servido ftp, usário e senha e etc.
	 * 
	 * Para usar o @Injet vou necessário fazer um produtor cdi chamado
	 * MailConfigProducer
	 */
	
	@Inject
	private SessionConfig config;
	
	
	public MailMessage novaMensagem(){
		
		return new MailMessageImpl(this.config);
		
	}

}
