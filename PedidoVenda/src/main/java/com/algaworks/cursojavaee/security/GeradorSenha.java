package com.algaworks.cursojavaee.security;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;

public class GeradorSenha {
	
	public static String geradorHash(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		
		 String hash
         = Hashing.sha256()
         .hashString(password, Charsets.UTF_8).toString();
		 
		 /**
		  * Quando for cadastrar utilizar da seguinte forma:
		  * usuario.setUsuPassword( GeradorSenha.geradorHash(usuario.getUsuPassword()));
		  */

 //String output = MessageFormat.format("{0} hashed to: {1}", password, hash);
		 
		 return hash; 

 //System.out.println(output);
}

public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
 // you can generate as many as you need ... modify to suite...
//	geradorHash("tazio");
//	geradorHash("eduardo");
//	geradorHash("felipe");
}

}