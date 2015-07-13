package com.algaworks.cursojavaee.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.cursojavaee.service.CalculadoraPreco;

@Named("meuBean")
@ViewScoped
public class PrecoProdutoBean  implements Serializable{

	private static final long serialVersionUID = 1L;
	@Inject
	private CalculadoraPreco calculadora;
	
	//Utilizar qualquer nome no lugar o init
	@PostConstruct	
	public void init(){
		
		System.out.println("Init PreçoProdutoBean");
		
	}

	
	public double getPreco(){
		return calculadora.calcularPreco(12, 44.55);
	}
}
