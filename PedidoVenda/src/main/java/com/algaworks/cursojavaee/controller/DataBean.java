package com.algaworks.cursojavaee.controller;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class DataBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public Date getDataAtual(){
		
		return new Date();
	}

}
