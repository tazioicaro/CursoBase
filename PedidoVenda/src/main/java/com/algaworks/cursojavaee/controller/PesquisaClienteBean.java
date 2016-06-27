package com.algaworks.cursojavaee.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.algaworks.cursojavaee.model.Cliente;
import com.algaworks.cursojavaee.model.Usuario;
import com.algaworks.cursojavaee.repository.Clientes;
import com.algaworks.cursojavaee.repository.filter.ClienteFilter;
import com.algaworks.cursojavaee.service.NegocioException;
import com.algaworks.cursojavaee.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaClienteBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Clientes clientes;
	
	private LazyDataModel<Cliente> model;
	
	private Cliente clienteSelecionado;

	private ClienteFilter filtro;
	

	
	public PesquisaClienteBean (){
		filtro = new ClienteFilter();
		
		model = new LazyDataModel<Cliente>(){			
			private static final long serialVersionUID = 1L;
			
			@Override
			public List<Cliente> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {
				
				filtro.setPrimeiroRegistro(first);
				filtro.setQuantidadeRegistros(pageSize);
				filtro.setPropriedadeOrdenacao(sortField);
				filtro.setAscendente(SortOrder.ASCENDING.equals(sortOrder));
				
				setRowCount(clientes.quantidadeFiltrados(filtro));


				return clientes.filtrados(filtro);
				
			}
			
		};
	}
	
	public void excluir(){
		
		try{
			clientes.removerCliente(clienteSelecionado);
			FacesUtil.addInforMessage("O usuário " + clienteSelecionado.getNome() + " excluído com sucesso!");  
		}catch(NegocioException ne){
			FacesUtil.addErrorMessage(ne.getMessage());
		}
		
	}
	
	//G&S
	
	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}

	public void setClienteSelecionado(Cliente clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}

	public LazyDataModel<Cliente> getModel() {
		return model;
	}

	public ClienteFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(ClienteFilter filtro) {
		this.filtro = filtro;
	}
	
	
	
}
