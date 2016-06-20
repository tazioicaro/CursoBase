package com.algaworks.cursojavaee.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.cursojavaee.model.Grupo;
import com.algaworks.cursojavaee.model.Usuario;
import com.algaworks.cursojavaee.repository.Grupos;
import com.algaworks.cursojavaee.repository.Usuarios;
import com.algaworks.cursojavaee.repository.filter.UsuarioFilter;

@Named
@ViewScoped
public class PesquisaUsuariosBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject 
	private Usuarios usuarios;
	
	@Inject
	private Grupos grupos;
	
	private UsuarioFilter filtro;
	private List<Usuario> usuariosFiltrados;
    private Usuario usuarioSelecionado;
    
    
    public PesquisaUsuariosBean(){
    	filtro = new UsuarioFilter();
    }
    
    public void pesquisar(){
    	usuariosFiltrados = usuarios.filtrados(filtro);
    }
    
    
    public List<Grupo> getLitaGrupos(){
    	
    	return grupos.porGrupos();
    	
    }
    
    public void excluir(){
    	
    }
	
	
	//G&S
	public UsuarioFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(UsuarioFilter filtro) {
		this.filtro = filtro;
	}

	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}

	public List<Usuario> getUsuariosFiltrados() {
		return usuariosFiltrados;
	}

	public void setUsuariosFiltrados(List<Usuario> usuariosFiltrados) {
		this.usuariosFiltrados = usuariosFiltrados;
	}

}
