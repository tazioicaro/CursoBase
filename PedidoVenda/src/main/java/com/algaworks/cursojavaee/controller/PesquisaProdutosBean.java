package com.algaworks.cursojavaee.controller;



import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.algaworks.cursojavaee.model.Produto;
import com.algaworks.cursojavaee.repository.Produtos;
import com.algaworks.cursojavaee.repository.filter.ProdutoFilter;
import com.algaworks.cursojavaee.service.NegocioException;
import com.algaworks.cursojavaee.util.jsf.FacesUtil;



@Named
@ViewScoped
public class PesquisaProdutosBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Produtos produtos;
	
	private ProdutoFilter filtro;
    //private List<Produto> produtosFiltrados;
	private LazyDataModel<Produto> model;
	
	private Produto produtoSelecionado;
	
	public PesquisaProdutosBean() {
		filtro = new ProdutoFilter();
		
		model = new LazyDataModel<Produto>(){

			private static final long serialVersionUID = 1L;
			
			@Override
			public List<Produto> load(int first, int pageSize,	String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {
				
				filtro.setPrimeiroRegistro(first);
				filtro.setQuantidadeRegistros(pageSize);
				filtro.setPropriedadeOrdenacao(sortField);
				filtro.setAscendente(SortOrder.ASCENDING.equals(sortOrder));
				
				setRowCount(produtos.quantidadesFiltrados(filtro));
				
				return produtos.filtrados(filtro);
			}
			
		};
	}
	
	public void excluir() {
		try {
			produtos.removerProduto(produtoSelecionado);			
			//produtosFiltrados.remove(produtoSelecionado);
			
			FacesUtil.addInforMessage("Produto " + produtoSelecionado.getSku() 
					+ " excluído com sucesso.");
		} catch (NegocioException ne) {
			FacesUtil.addErrorMessage(ne.getMessage());
		}
	}
	
//	public void pesquisar() {
//		produtosFiltrados = produtos.filtrados(filtro);
//	}
//	
//	public List<Produto> getProdutosFiltrados() {
//		return produtosFiltrados;
//	}

	public ProdutoFilter getFiltro() {
		return filtro;
	}

	public Produto getProdutoSelecionado() {
		return produtoSelecionado;
	}

	public void setProdutoSelecionado(Produto produtoSelecionado) {
		this.produtoSelecionado = produtoSelecionado;
	}

	public LazyDataModel<Produto> getModel() {
		return model;
	}		
}
