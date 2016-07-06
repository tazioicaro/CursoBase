package com.algaworks.cursojavaee.controller;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.cursojavaee.model.Grupo;
import com.algaworks.cursojavaee.model.Usuario;
import com.algaworks.cursojavaee.repository.Grupos;
import com.algaworks.cursojavaee.security.GeradorSenha;
import com.algaworks.cursojavaee.service.CadastroUsuarioService;
import com.algaworks.cursojavaee.service.NegocioException;
import com.algaworks.cursojavaee.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroUsuarioBean implements Serializable {	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private CadastroUsuarioService cadastroUsuarioService;
	
	private Usuario usuario;
	private List<Grupo> listaGrupos;
	private GeradorSenha geradorSenha;
	

	
	@Inject
    private Grupos repositorioGrupos;
	
		

	public CadastroUsuarioBean() {	
		limpar();
		
	}

	
	public void inicializar() {
		if (this.usuario == null) {
			limpar();
		}
		
		obterGrupos();
	}
	
	

	public void cadastrar(){		

		
		try{	
			this.usuario.setSenha(geradorSenha.geradorHash((this.usuario.getSenha())));
			
		this.usuario = cadastroUsuarioService.salvar(this.usuario);		
		FacesUtil.addInforMessage("Usuário criado com sucesso!");
		limpar();
		
		}catch(NegocioException | NoSuchAlgorithmException | UnsupportedEncodingException ne){
			FacesUtil.addErrorMessage(ne.getMessage());
		} 
	}
	
	
	public List<Grupo> obterGrupos(){
		
		return this.listaGrupos = repositorioGrupos.porGrupos();
	}
			
	
	public void limpar(){
		usuario = new Usuario();
		listaGrupos = new ArrayList<Grupo>();
		geradorSenha = new GeradorSenha();
	
	}
	
	
	public boolean isEditando(){
		return this.usuario.getId() !=null;
	}
	
	
	//G&T

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;		
	}
	

	public List<Grupo> getListaGrupos() {
		return listaGrupos;
	}

	public List<Grupo> setListaGrupos(List<Grupo> listaGrupos) {
		this.listaGrupos = listaGrupos;
		return listaGrupos;
	}
	
	

	

}
