package com.algaworks.cursojavaee.util.jpa;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;



/**
 *  @author tazio.fernandes
 *  As duas anotações informa que ele é um interceptador da
 *  Anotação traansactional já criada
 */
@Interceptor
@Transactional
public class TransactionInterceptor implements Serializable {	
	private static final long serialVersionUID = 1L;
	
	private @Inject EntityManager manager;
	
	@AroundInvoke
	public Object Invoke (InvocationContext context) throws Exception{
		EntityTransaction trx = manager.getTransaction();
		boolean criador = false;
		
		try{
			
			if(!trx.isActive()){
				
				/**
				  * truque para fazer rollback o que já passou
				  *(Sendo, um futuro commit, confirmaria até mesmo operações sem transação)				
				  * 
				  */
								
				
				trx.begin();
				trx.rollback();
				
				//agora sim inicia a transação
				trx.begin();
				
				criador = true;
			}
			
			//Chama o método Salva da Classe CadastroProdutoService 
			return context.proceed();	
			
		}catch(Exception e){
			if(trx != null && criador){
				trx.rollback();
			}
			
			throw e;
		}finally{
			
			if(trx != null && trx.isActive() && criador){
				trx.commit();
			}
			
		}
		
	}

}
