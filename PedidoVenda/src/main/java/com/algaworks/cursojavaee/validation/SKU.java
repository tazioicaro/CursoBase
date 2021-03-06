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
 
 * RetentionPolicy.Runtime informa que a anotação pode ser lida em tempo de execução
 */

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy={})
@Pattern(regexp="([a-zA-Z]{2}\\d{4,18})?")
public @interface SKU {
	
	//Fazendo com que a mensagem do  'message' do SKU substitua o message do Pattern //Atributos obrigatórios
	//Além disso, isso possibilita personalizar a mensagem direto na anotação no método da classe, usando o atributo message
	@OverridesAttribute(constraint= Pattern.class, name="message")
	String message() default "{com.algaworks.validator.constraints.SKU.message}";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};
	

}
