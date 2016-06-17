package com.algaworks.cursojavaee.repository.filter;

import java.io.Serializable;

public class ClienteFilter implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String nome;
	private String email;
	private String documntoReceitaFederal;
	
	
	//G&S
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDocumntoReceitaFederal() {
		return documntoReceitaFederal;
	}
	public void setDocumntoReceitaFederal(String documntoReceitaFederal) {
		this.documntoReceitaFederal = documntoReceitaFederal;
	}
	
	

}
