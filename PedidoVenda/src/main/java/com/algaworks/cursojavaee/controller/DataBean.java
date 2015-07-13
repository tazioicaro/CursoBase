package com.algaworks.cursojavaee.controller;

import java.io.Serializable;
import java.util.Date;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class DataBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public Date getDataAtual(){
		
		return new Date();
	}

}
