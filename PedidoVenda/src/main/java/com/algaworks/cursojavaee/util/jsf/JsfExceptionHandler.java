package com.algaworks.cursojavaee.util.jsf;

import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.util.Iterator;

import javax.faces.FacesException;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

//Trata exceções
public class JsfExceptionHandler  extends ExceptionHandlerWrapper{

	
	private ExceptionHandler wrapped;
	
	
	
	public JsfExceptionHandler(ExceptionHandler wrapped) {
		super();
		this.wrapped = wrapped;
	}



	@Override
	public ExceptionHandler getWrapped() {
		// TODO Auto-generated method stub
		return this.wrapped;
	}
	
	//Metodo resposnavel por tratar as exceções capuradas pelo JSF
	@Override
	public void handle() throws FacesException {
		//Todos os evento de exceções enfileirados
		Iterator<ExceptionQueuedEvent> events = getUnhandledExceptionQueuedEvents().iterator();
		
		while(events.hasNext()){
			ExceptionQueuedEvent event = events.next();
			ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event.getSource();
		
			//pego a excessão
		    Throwable exception = context.getException();
		    
		    try{
		    if(exception instanceof ViewExpiredException){
		    	redirect("/");
		    }
		    }finally{
		    	//removendo a axceção do Iterate
		    	events.remove();
		    }
		}
		
		//Repassando para o Faces continuar tratando as exceções que não foram pegos
		getWrapped().handle();
	}
	
	private void redirect (String page){
		try{
		FacesContext facesContext = FacesContext.getCurrentInstance();
		
		ExternalContext externalContext = facesContext.getExternalContext();
		String contextPath = externalContext.getRequestContextPath(); //No caso é o PedidoVenda
		
		
		externalContext.redirect(contextPath +page);
		facesContext.responseComplete();
		} catch(IOException e){
			throw new FacesException(e);
		}
	}

}
