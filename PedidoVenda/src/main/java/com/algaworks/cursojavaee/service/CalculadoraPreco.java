package com.algaworks.cursojavaee.service;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;

//Classe de serviços não são acessadas diretamente pelo xhtml, mas sim por classes

@RequestScoped
public class CalculadoraPreco implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@PostConstruct	
	public void init(){
		
		System.out.println("Init Calculadora preÇo");
		
	}

	public double calcularPreco (int quantidade, double precoUnitario){		
		return quantidade * precoUnitario;
		
	}
}
