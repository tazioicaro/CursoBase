package com.algaworks.cursojavaee.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.algaworks.cursojavaee.model.Grupo;
import com.algaworks.cursojavaee.model.Usuario;
import com.algaworks.cursojavaee.repository.Grupos;
import com.algaworks.cursojavaee.repository.Usuarios;
import com.algaworks.cursojavaee.repository.filter.UsuarioFilter;
import com.algaworks.cursojavaee.service.NegocioException;
import com.algaworks.cursojavaee.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaUsuariosBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject 
	private Usuarios usuarios;
	
	@Inject
	private Grupos grupos;
	
	private UsuarioFilter filtro;
	//private List<Usuario> usuariosFiltrados;	
    private Usuario usuarioSelecionado;
    
    private LazyDataModel<Usuario> model;
    
    
    public PesquisaUsuariosBean(){
    	filtro = new UsuarioFilter();
    	
    	model = new LazyDataModel<Usuario>(){			
			private static final long serialVersionUID = 1L;
			
			@Override
			public List<Usuario> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {
				
				filtro.setPrimeiroRegistro(first);
				filtro.setQtdeRegistros(pageSize);
				filtro.setPropriedadeOrdenacao(sortField);
				filtro.setAscendente(SortOrder.ASCENDING.equals(sortOrder));
				
				setRowCount(usuarios.quantidadesFiltrados(filtro));
				
				return usuarios.filtrados(filtro);
			}
			
    		
    	};
    }
    
//    public void pesquisar(){
//    	usuariosFiltrados = usuarios.filtrados(filtro);
//    }
    
    
    public List<Grupo> getLitaGrupos(){
    	
    	return grupos.porGrupos();
    	
    }
    
    public void excluir(){
    	try{
    	usuarios.removerUsuario(usuarioSelecionado);
    	FacesUtil.addInforMessage("O usuário " + usuarioSelecionado.getNome() +" foi removido com sucesso!");
    	}catch(NegocioException ne){
    		FacesUtil.addErrorMessage(ne.getMessage());
    		
    	}
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

//	public List<Usuario> getUsuariosFiltrados() {
//		return usuariosFiltrados;
//	}
//
//	public void setUsuariosFiltrados(List<Usuario> usuariosFiltrados) {
//		this.usuariosFiltrados = usuariosFiltrados;
//	}

	public LazyDataModel<Usuario> getModel() {
		return model;
	}

	public void setModel(LazyDataModel<Usuario> model) {
		this.model = model;
	}

}
