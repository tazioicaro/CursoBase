package com.algaworks.cursojavaee.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.algaworks.cursojavaee.model.Categoria;
import com.algaworks.cursojavaee.repository.Categorias;

@FacesConverter(forClass=Categoria.class)
public class CategoriaConverter implements Converter {

	
	//Usando o @Inject	
	@Inject
	private Categorias categorias;
	
	//Para driblar a falta do Inject
//	public CategoriaConverter() {
//		categorias = CDIServiceLocator.getBean(Categorias.class);
//	}
	
	
	//Trabalhando com ID como referencia
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
         Categoria retorno = null;
		
         if(value != null){
        	 
        	 Long id = new Long(value);
        	 retorno =  categorias.porId(id);
         }
		return retorno;
	}

	
	//Recebe Objeto e retorna String
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
	if(value != null){
		Categoria categoria = (Categoria) value;
		
		return  categoria.getId() == null ? null :  categoria.getId().toString();
	}
		return "";
	}


}
