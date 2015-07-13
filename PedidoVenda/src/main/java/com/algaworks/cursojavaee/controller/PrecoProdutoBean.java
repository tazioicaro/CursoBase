package com.algaworks.cursojavaee.controller;

import javax.faces.bean.SessionScoped;
import javax.inject.Named;

@Named("meuBean")
@SessionScoped
public class PrecoProdutoBean {

	
	public double getPreco(){
		return 10.45;
	}
}
