package com.algaworks.cursojavaee.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author tazio.fernandes
 * 
 * Necessário usar a classe FacesContext, HTTP Request e Response
 * Por isso será abstraído em outra classe essas depedência
 * 
 * Criado uma classe  produtor FacesProducer
 *
 */

@Named
@SessionScoped
public class LoginBean implements Serializable {


	private static final long serialVersionUID = 1L;
	
	@Inject
	private FacesContext facesContext;
	
	@Inject
	private HttpServletRequest request;
	
	@Inject
	private HttpServletResponse response;
	
	private String email;
	
	
	//Bean despachar para o endereço abaixo
	
	public void login() throws ServletException, IOException{
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/j_spring_security_check");
		dispatcher.forward(request, response);
		
		//Interropendo o ciclo de vida do JSF. Depois disso é o Spring que fará o resto da autenticação
		facesContext.responseComplete();
	}
	
	
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	

}
