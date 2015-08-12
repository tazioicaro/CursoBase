package com.algaworks.cursojavaee.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.OverridesAttribute;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;


/*
 * 		2 letras Letras minúsculas e minúsculas os () com ? torna opcional
		\\d{4,18} d são digitos de 0 -9 e que aceita de 4 aé 18 dígito
 */

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy={})
@Pattern(regexp="([a-zA-Z]{2}\\d{4,18})?")
public @interface SKU {
	
	//Fazendo com que a mensagem do  'message' do SKU substitua o message do Pattern 
	@OverridesAttribute(constraint= Pattern.class, name="message")
	
	//Atributos obrigatórios
	
	String message() default "tem formato inválido";
	
	Class<?>[] groups() default{};
	
	Class<? extends Payload>[] payload() default{};
	

}
