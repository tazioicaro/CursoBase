package com.algaworks.cursojavaee.util.jpa;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.interceptor.InterceptorBinding;


/**
 * 
 * @author tazio.fernandes
 * Sempre que houver uma transação dentro de método e houver
 * a tag @Trasnsational este método será chamado
 *O interceptado é o transactionalInterceptor que intercepat inicia , finaliza
 *ou cancela a transação
 */
@InterceptorBinding
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Transactional {

}
