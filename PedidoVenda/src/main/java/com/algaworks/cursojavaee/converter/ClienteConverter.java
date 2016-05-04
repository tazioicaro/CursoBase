package com.algaworks.cursojavaee.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.algaworks.cursojavaee.model.Categoria;
import com.algaworks.cursojavaee.model.Cliente;
import com.algaworks.cursojavaee.repository.Clientes;

@FacesConverter(forClass=Categoria.class)
public class ClienteConverter implements Converter {

	
	//Usando o @Inject	
	@Inject
	private Clientes clientes;
	
	//Para driblar a falta do Inject
//	public CategoriaConverter() {
//		categorias = CDIServiceLocator.getBean(Categorias.class);
//	}
	
	
	//Trabalhando com ID como referencia
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
         Cliente retorno = null;
		
         if(value != null){
        	 
        	 Long id = new Long(value);
        	 retorno =  this.clientes.porID(id);
         }
		return retorno;
	}

	
	//Recebe Objeto e retorna String
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
	if(value != null){
		return ((Cliente) value).getId().toString();
	}
		return null;
	}


}
