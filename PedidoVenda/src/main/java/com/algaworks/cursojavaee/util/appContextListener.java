package com.algaworks.cursojavaee.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

//tem o @WebListener, mas não achei, auala 13.1

@WebListener
public class appContextListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Quando o Contexto for inicializa o ContextInitialized vai
	 * ser chamado ( Quando o Tomcat Subir)
	 */
	@Override
	public void contextInitialized(ServletContextEvent event) {
		
		//Não é para o apache tentar converter para  zero as EL
		System.setProperty("org.apache.el.parse.COERCE_TO_ZERO", "false");
		
	}
	
}