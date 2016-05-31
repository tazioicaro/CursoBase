package com.algaworks.cursojavaee.model.Teste;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import javax.mail.Session;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.poi.hssf.record.crypto.Biff8DecryptingStream;

import com.algaworks.cursojavaee.model.Usuario;

public class TesteConsulta {
	
	public static void main (String[] arg){
		
//		EntityManagerFactory factory = Persistence.createEntityManagerFactory("PedidoPU");
//		EntityManager manager = factory.createEntityManager();
//		Session session = manager.unwrap(Session.class);
//		
//		
//		
//		
//		manager.close();
		
		Map<Date, BigDecimal> valores = valoresTotaisPorData(15, null, null);
		
		for (Date data : valores.keySet()){
			System.out.println(data + " = " + valores.get(data));
		}
		
	}
	/**
	 * 
	 * @param numeroDeDias
	 * @param criadoPor
	 * @param session
	 * @return um mapa com com valores e usuário por data
	 */
	public static Map<Date, BigDecimal> valoresTotaisPorData (Integer numeroDeDias, Usuario criadoPor, Session session){
	
		Calendar dataInicial = Calendar.getInstance();
		//Deixando apenas os dias do mês, utilizando o truncate do Lang3. Data de Hoje
		dataInicial = DateUtils.truncate(dataInicial, Calendar.DAY_OF_MONTH);
		
		//Subtraindo dias do calendário para montar o range do gráfico
		dataInicial.add(Calendar.DAY_OF_MONTH, numeroDeDias * -1);
		
		//criando o Mapa
		Map<Date, BigDecimal> resultado = criarMapaVazio(numeroDeDias, dataInicial);
		
		return resultado;
	}
	
	//Criando um Mapa vazio para que a linha fique no zero quando não houver item
	
	private static Map<Date, BigDecimal> criarMapaVazio(Integer numeroDeDias,
			Calendar dataInicial) {
		//Clonando Calendar para que o add consiga adicionar e retornar algo
		//Sem alterar o datainicial original
		dataInicial = (Calendar)dataInicial.clone();		
		Map<Date, BigDecimal> mapaInicial = new TreeMap<>();
		
		
		for (int i=0; i <= numeroDeDias; i++){
			mapaInicial.put(dataInicial.getTime(), BigDecimal.ZERO);
			//Acrescentando dias para o gráfico
			dataInicial.add(Calendar.DAY_OF_MONTH, 1);
		}
		return mapaInicial;
	}

}
