package com.algaworks.cursojavaee.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.algaworks.cursojavaee.model.Produto;
import com.algaworks.cursojavaee.repository.Produtos;
import com.algaworks.cursojavaee.util.jpa.Transactional;

//Regras de negócio do Cadastro de produtos (Ben CDI)
public class CadastroProdutoService  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Produtos produtos;
	
	
	@Transactional
	public Produto salvar (Produto produto){
		Produto produtoExistente = produtos.porSku(produto.getSku());
		
		if(produtoExistente !=null){
			throw new NegocioException("Já existe um produto com o sku informado");
		}
		
		return produtos.guardar(produto);
	}

}
