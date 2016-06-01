package com.algaworks.cursojavaee.model.Teste;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.commons.lang3.time.DateUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;

import com.algaworks.cursojavaee.model.Pedido;
import com.algaworks.cursojavaee.model.Usuario;
import com.algaworks.cursojavaee.model.vo.DataValor;

public class TesteConsulta {
	
	public static void main (String[] arg){
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("PedidoPU");
		EntityManager manager = factory.createEntityManager();
		Session session = manager.unwrap(Session.class);
		
			Usuario usuario	= new Usuario();
			usuario.setId(11L);
		
		
		Map<Date, BigDecimal> valores = valoresTotaisPorData(15, null, session);
		
		for (Date data : valores.keySet()){
			System.out.println(data + " = " + valores.get(data));
		}
		manager.close();
		
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
		
		Criteria criteria = session.createCriteria(Pedido.class);
		
		/**
		 * Projetando uma coluna usando as somas dos valores
		 * 
		 * Foi necessário utilizaar o sqlGroupProjection, pois não havia no
		 * Hibernate a função Date necessária para trucar as datas
		 * 		
		 * O resultado do criteria é um lista de vetores de objetos, 
		 * onde um será a data e o outro o Bigdecimal Valor. Para tratar esse
		 * retorno foi criado uma classe chamada Datavalor dentro de VO ( Value Object)
		 */
		

		/*
		 * Usa-se Date no sql que é uma função para trucar a data, vindo somente a data pura
		 * 
		 * exemplo de consulta em SQL para entender o codigo acima
		 * select date(data_criacao) as data, sum(valor_total) as valor from
		 * pedido where data_criacao >= :dataInicial and vendedor_id = :criadoPor group by date(data_criacao)
		 */
		
		
		criteria.setProjection(Projections.projectionList()
				.add(Projections.sqlGroupProjection("date(data_criacao) as data", 
                 "date(data_criacao)", new String[]{"data"}, new Type[]{StandardBasicTypes.DATE} ))
                .add(Projections.sum("valorTotal").as("valor"))
                )
                .add(Restrictions.ge("dataCriacao", dataInicial.getTime()));
		
		/**
		 * O if é para que o id do vendedor seja opcional, visto que pode ser
		 * que necessitamos de todas as vendas
		 */
		if(criadoPor != null){
			
			criteria.add(Restrictions.eq("vendedor", criadoPor));
		}
		
		/*O Hibertante irá instaciar a classe e transformar o resultado em objetos DataValor
		 * esta lista já contém todos os dias e valores totais
		 */
		@SuppressWarnings("unchecked")
		List<DataValor> valoresPorData = criteria.setResultTransformer(Transformers.aliasToBean(DataValor.class)).list();
		
		
		//Pegando valores da lista e jogando no Map
		for (DataValor datavalor : valoresPorData){
			resultado.put(datavalor.getData(), datavalor.getValor());
		}
		
		
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
