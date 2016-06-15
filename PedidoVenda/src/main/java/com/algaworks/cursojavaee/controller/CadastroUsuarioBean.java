package com.algaworks.cursojavaee.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.cursojavaee.model.Grupo;
import com.algaworks.cursojavaee.model.Usuario;
import com.algaworks.cursojavaee.repository.Grupos;
import com.algaworks.cursojavaee.service.CadastroUsuarioService;
import com.algaworks.cursojavaee.service.NegocioException;
import com.algaworks.cursojavaee.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroUsuarioBean implements Serializable {	
	private static final long serialVersionUID = 1L;
	

	private Usuario usuario;
	
	@Inject
	private CadastroUsuarioService cadastroUsuarioService;
	
	@Inject
    private Grupos repositorioGrupos;
	
	private List<Grupo> listaGrupos = null;
	
	
	
	

	public CadastroUsuarioBean() {
		super();
		limpar();
		
	}
	
	public List<Grupo> obterGrupos(){
		
		return this.listaGrupos = repositorioGrupos.porGrupos();
	}

	public void cadastrar(){
		
		for (Grupo grupos : this.usuario.getGrupos()){
			
			System.out.println("Grupos selecionados: " + grupos.getDescricao());
			
		}
		
		
		try{
			
		this.usuario = cadastroUsuarioService.salvar(this.usuario);		
		FacesUtil.addInforMessage("Usuário criado com sucesso!");
		
		}catch(NegocioException ne){
			FacesUtil.addErrorMessage(ne.getMessage());
		} 
	}
	
	//Adicionar um inicalizar para que possa haver a edição do Usuário
	
	
	public void inicializar() {
		if (this.usuario == null) {
			limpar();
		}
		
		obterGrupos();
	}
		
	
	public void limpar(){
		usuario = new Usuario();		
		setListaGrupos(new ArrayList<Grupo>());
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	public boolean isEditando(){
		return this.usuario.getId() !=null;
	}

	public List<Grupo> getListaGrupos() {
		return listaGrupos;
	}

	public List<Grupo> setListaGrupos(List<Grupo> listaGrupos) {
		this.listaGrupos = listaGrupos;
		return listaGrupos;
	}
	
	

}
