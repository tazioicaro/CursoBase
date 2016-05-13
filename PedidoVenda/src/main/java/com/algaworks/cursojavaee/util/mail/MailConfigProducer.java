package com.algaworks.cursojavaee.util.mail;

import java.io.IOException;
import java.util.Properties;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import com.outjected.email.api.SessionConfig;
import com.outjected.email.impl.SimpleMailConfig;

public class MailConfigProducer {
	
	@Produces
	@ApplicationScoped
	public SessionConfig getMailConfig() throws IOException{
		
		/**
		 * Há um arquivo de configuração com os dados para alimentar
		 * o config do SimpleMailConfig
		 */
		
		
		Properties props = new Properties();
		/**
		 * O getClass() foi utilizado para que fosse buscado o arquivo
		 * a partir da referência da classe indicada pelo fluxo.
		 * Só assim se consegue buscar um arquivo no resource.
		 */
		
		
		
		
			
				props.load(getClass().getResourceAsStream("/mail.properties"));
				
				SimpleMailConfig config = new SimpleMailConfig();
				config.setServerHost(props.getProperty("mail.server.host"));
				config.setServerPort(Integer.parseInt(props.getProperty("mail.server.port")));
				config.setEnableSsl(Boolean.parseBoolean(props.getProperty("mail.enable.ssl")));
				config.setAuth(Boolean.parseBoolean(props.getProperty("mail.auth")));
				config.setUsername(props.getProperty("mail.username"));
				config.setPassword(props.getProperty("mail.password"));
				
				return config;
			}
				

}
