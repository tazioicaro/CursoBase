package com.algaworks.cursojavaee.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.algaworks.cursojavaee.model.Produto;
import com.algaworks.cursojavaee.repository.Produtos;
import com.algaworks.cursojavaee.util.jpa.Transactional;

//Regras de negócio do Cadastro de produtos (Bean CDI)
public class CadastroProdutoService  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Produtos produtos;
	
	
	@Transactional
	public Produto salvar (Produto produto){
		Produto produtoExistente = produtos.porSku(produto.getSku());
		
		/*Se existe um produto com o mesmo SKU e se ele não é o mesmo produto em questão
		*Assim será possível editar um produto sem alterar seu SKU
		*/
		if(produtoExistente !=null && !produtoExistente.equals(produto)){
			throw new NegocioException("Já existe um produto com o sku informado");
		}
		
		return produtos.guardar(produto);
	}

}
