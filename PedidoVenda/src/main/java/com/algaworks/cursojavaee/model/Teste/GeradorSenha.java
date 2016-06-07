package com.algaworks.cursojavaee.model.Teste;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.MessageFormat;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GeradorSenha {
	
	public static  void geradorHash(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		
		/**
		 String hash
         = Hashing.sha256()
         .hashString(password, Charsets.UTF_8).toString();
		 
		 
		  * Quando for cadastrar utilizar da seguinte forma:
		  * usuario.setUsuPassword( GeradorSenha.geradorHash(usuario.getUsuPassword()));
		  */

 String output = MessageFormat.format("{0} hashed to: {1}", password, hashedPassword);
		 
		//return hashedPassword; 

 System.out.println(output);
}

public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
//  you can generate as many as you need ... modify to suite...
	geradorHash("123");
//	geradorHash("eduardo");
//	geradorHash("felipe");
}

}