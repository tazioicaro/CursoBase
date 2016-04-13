package com.algaworks.cursojavaee.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.SessionScoped;
import javax.inject.Named;

import com.algaworks.cursojavaee.Funcionario;

@Named
@SessionScoped
public class FuncionariosBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final String[] nomes = {"João", "Maria", "José", "Eduardo", "Sebastião", "Maria"};
	private static final String[] sobrenomes={"Souza","Silva","Andrade", "Machado", "Júnior"};
	
	private List<Funcionario> funcionarios;
	

	public FuncionariosBean() throws ParseException{
		funcionarios = new ArrayList<Funcionario>();
		
		for(int i=0; i<50; i++){
			adicionarFuncinario();
			
		}
	}
	
	private void adicionarFuncinario(){
		
		String nomeCompleto = getNomeAleatorio() +" "+ getSobrenomeAleatorio()+ " "+ getSobrenomeAleatorio();
		funcionarios.add(new Funcionario(getMatriculaAleatoria(), nomeCompleto, getDataAleatoria(), getSalarioAleatorio()));
	}
	
	private Long getMatriculaAleatoria(){
		return (long) (Math.random()*10000);
	}
	
	private BigDecimal getSalarioAleatorio(){
		return new BigDecimal(1000+(Math.random()*29000));
		
	}
	
	private Date getDataAleatoria(){
		long dezAnosemMillis = 24*60*60*60*1000;
		long periodoSorteadoEmMillis = ((long)(Math.random()*10*365))*dezAnosemMillis;
		return new Date(System.currentTimeMillis() - periodoSorteadoEmMillis);
	}
	
	private String getSobrenomeAleatorio(){
		
		int posicao = (int) Math.round(Math.random()*(sobrenomes.length -1));
		return sobrenomes[posicao];
		
	}
	
	private String getNomeAleatorio(){
		int posicao = (int) Math.round(Math.random()*(nomes.length-1));
		return nomes[posicao];
	}
	

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

}
