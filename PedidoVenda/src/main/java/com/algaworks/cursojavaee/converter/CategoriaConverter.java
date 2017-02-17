package com.algaworks.cursojavaee.converter;

import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import org.primefaces.convert.ClientConverter;

import com.algaworks.cursojavaee.model.Categoria;
import com.algaworks.cursojavaee.repository.Categorias;

@FacesConverter(forClass=Categoria.class)
public class CategoriaConverter implements Converter, ClientConverter {

	
	//Usando o @Inject	
	@Inject
	private Categorias categorias;
	
	//Para driblar a falta do Inject
//	public CategoriaConverter() {
//		categorias = CDIServiceLocator.getBean(Categorias.class);
//	}
	
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Categoria retorno = null;
		
		if (value != null) {
			Long id = new Long(value);
			retorno = categorias.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			return ((Categoria) value).getId().toString();
		}
		
		return "";
	}

	@Override
	public Map<String, Object> getMetadata() {
		return null;
	}

	@Override
	public String getConverterId() {
		return "com.algaworks.Categoria";
	}


}
