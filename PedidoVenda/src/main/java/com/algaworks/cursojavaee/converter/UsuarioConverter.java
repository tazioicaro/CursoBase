package com.algaworks.cursojavaee.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import com.algaworks.cursojavaee.model.Pedido;
import com.algaworks.cursojavaee.model.Usuario;
import com.algaworks.cursojavaee.repository.Usuarios;

@FacesConverter(forClass = Usuario.class)
public class UsuarioConverter implements Converter {

	// Usando o @Inject
	@Inject
	private Usuarios usuarios;

	// Para driblar a falta do Inject
	// public CategoriaConverter() {
	// categorias = CDIServiceLocator.getBean(Categorias.class);
	// }

	// Trabalhando com ID como referencia
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,	String value) {
		Usuario retorno = null;

		if (StringUtils.isNotEmpty(value)) {

			Long id = new Long(value);
			retorno = usuarios.porID(id);
		}
		return retorno;
	}

	// Recebe Objeto e retorna String
	@Override
	public String getAsString(FacesContext context, UIComponent component,	Object value) {
		if (value != null) {
			Usuario usuario = (Usuario) value;
			return usuario.getId() == null ? null : usuario.getId().toString();
		}
		return "";
	}

}
