package com.algaworks.cursojavaee.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.algaworks.cursojavaee.model.Categoria;
import com.algaworks.cursojavaee.model.Usuario;
import com.algaworks.cursojavaee.repository.Usuarios;

@FacesConverter(forClass=Usuario.class)
public class UsuarioConverter implements Converter {

	
	//Usando o @Inject	
	@Inject
	private Usuarios usuarios;
	
	//Para driblar a falta do Inject
//	public CategoriaConverter() {
//		categorias = CDIServiceLocator.getBean(Categorias.class);
//	}
	
	
	//Trabalhando com ID como referencia
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
         Usuario retorno = null;
		
         if(value != null){
        	 
        	 Long id = new Long(value);
        	 retorno =  this.usuarios.porID(id);
         }
		return retorno;
	}

	
	//Recebe Objeto e retorna String
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
	if(value != null){
		return ((Usuario) value).getId().toString();
	}
		return null;
	}


}
