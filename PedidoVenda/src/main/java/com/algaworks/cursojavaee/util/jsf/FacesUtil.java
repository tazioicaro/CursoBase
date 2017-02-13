package com.algaworks.cursojavaee.util.jsf;

import java.math.BigDecimal;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

//Classe utilitária para tratar exceções para as telas JSF
public class FacesUtil {

	public static void addErrorMessage(String message) {

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message));

	}

	// Verificar se é PostBack

	public static boolean isPostback() {
		return FacesContext.getCurrentInstance().isPostback();
	}

	public static boolean isNotPostBack() {

		return !isPostback();

	}

	public static void addInforMessage(String message) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, message, message));

	}

	public static void addInforProductSucess(String produto, BigDecimal valor) {

		String msg = "Produto '" + produto + "' cadastrado com sucesso com preço R$ " + valor + "!";
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));

	}

}
