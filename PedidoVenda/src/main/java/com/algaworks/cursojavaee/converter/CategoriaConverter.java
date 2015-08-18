package com.algaworks.cursojavaee.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;
import javax.faces.convert.Converter;

import com.algaworks.cursojavaee.model.Categoria;
import com.algaworks.cursojavaee.repository.Categorias;
import com.algaworks.cursojavaee.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=Categoria.class)
public class CategoriaConverter implements Converter {

	//@Inject Verificar na versão Mojarra 2.3 pronta o funcionamento do  Inject
	private Categorias categorias;
	
	//Para driblar a falta do Inject
	public CategoriaConverter() {
		categorias = CDIServiceLocator.getBean(Categorias.class);
	}
	
	
	//Trabalhando com ID como referencia
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
         Categoria retorno = null;
		
         if(value != null){
        	 
        	 Long id = new Long(value);
        	 retorno =  categorias.porID(id);
         }
		return retorno;
	}

	
	//Recebe Objeto e retorna String
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
	if(value != null){
		return ((Categoria) value).getId().toString();
	}
		return null;
	}


}
