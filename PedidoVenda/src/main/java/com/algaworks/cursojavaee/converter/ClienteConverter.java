package com.algaworks.cursojavaee.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;

import com.algaworks.cursojavaee.model.Cliente;
import com.algaworks.cursojavaee.repository.Clientes;

@FacesConverter(forClass=Cliente.class)
public class ClienteConverter implements Converter {

	
	
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
		
         if(StringUtils.isNotEmpty(value)){
        	 
        	 Long id = new Long(value);
        	 retorno =  this.clientes.porID(id);
         }
		return retorno;
	}

	
	//Recebe Objeto e retorna String
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
	if(value != null){
		Cliente cliente = (Cliente)value;
		return cliente.getId() == null ? null : cliente.getId().toString();
	}
		return "";
	}


}
