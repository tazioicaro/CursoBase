package com.algaworks.cursojavaee.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.algaworks.cursojavaee.model.Produto;
import com.algaworks.cursojavaee.repository.Produtos;
import com.algaworks.cursojavaee.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=Produto.class)
public class ProdutoConverter implements Converter {

	//@Inject Verificar na versão Mojarra 2.3 pronta o funcionamento do  Inject
	private Produtos produtos;
	
	//Para driblar a falta do Inject
	public ProdutoConverter() {
		produtos = CDIServiceLocator.getBean(Produtos.class);
	}
	
	
	//Trabalhando com ID como referencia
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
         Produto retorno = null;
		
         if(value != null){ 	 
        	
        	 Long id = new Long(value);
        	 retorno =  produtos.porID(id);
        	
        		 
         }
		return retorno;
	}

	
	//Recebe Objeto e retorna String
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
	if(value != null){
		
		Produto produto = (Produto) value;
		return  produto.getId() == null ? null :  produto.getId().toString();
	}
		return "";
	}


}
