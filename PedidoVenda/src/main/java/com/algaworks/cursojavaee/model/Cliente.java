package com.algaworks.cursojavaee.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="cliente")
public class Cliente  implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(nullable= false, length = 100) //Not null de tamanho 100
	private String nome;
	
	@NotBlank
	@Column(nullable= false)
	private String email;
	
	@Column (nullable=true, length = 11)
	private String telefone;
	
	@Column (nullable=true, length = 6)
	private String ramal;	
	
	@Column(name="doc_receita_federal", nullable=false, length=14)
	private String documntoReceitaFederal;
	
	@Enumerated(EnumType.STRING) //Salvar o texto do Enum
	@Column ( nullable=false, length = 12)
	private TipoPessoa tipo;
	
	//Informando que este relacionamento é o inverso do que foi 
	//mapeado no Endereço com o nome do atributo cliente. Mapeamento Bidirecional
	@OneToMany(mappedBy="cliente", cascade= CascadeType.ALL) //Quando salvar o cliente, Persisitir o seu endereço 
	private List<Endereco> enderecos = new ArrayList<>();
	
		
	@Transient
	public boolean isCPF(){
		return TipoPessoa.FISICA.equals(this.tipo);
	}
	
	@Transient
	public boolean isCNPJ(){
		return TipoPessoa.JURIDICA.equals(this.tipo);
	}
		
	//G&S	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDocumntoReceitaFederal() {
		return documntoReceitaFederal;
	}
	public void setDocumntoReceitaFederal(String documntoReceitaFederal) {
		this.documntoReceitaFederal = documntoReceitaFederal;
	}
	public TipoPessoa getTipo() {
		return tipo;
	}
	public void setTipo(TipoPessoa tipo) {
		this.tipo = tipo;
	}
	public List<Endereco> getEnderecos() {
		return enderecos;
	}
	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}	
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}	
	public String getRamal() {
		return ramal;
	}
	public void setRamal(String ramal) {
		this.ramal = ramal;
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
