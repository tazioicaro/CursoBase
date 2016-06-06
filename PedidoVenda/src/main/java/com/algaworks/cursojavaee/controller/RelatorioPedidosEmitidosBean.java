package com.algaworks.cursojavaee.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

import org.hibernate.Session;

import com.algaworks.cursojavaee.util.jsf.FacesUtil;
import com.algaworks.cursojavaee.util.report.ExecutorRelatorio;

@Named
@RequestScoped
public class RelatorioPedidosEmitidosBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotNull
	private Date dataInicio;
	private Date dataFim;
	
	@Inject
	private FacesContext facesContext;
	
	@Inject
	private HttpServletResponse response;

	@Inject
	private EntityManager manager; //Necessário para conectar ao db para o relatório
	
	public void emitir(){
		Map<String, Object> parametros = new HashMap<>();
		
		/*Os paramentros data_inicio e data_fim devem ser iguais
		 * ao parametros já criados no Ireport para que o relatório compilado
		 * jasper possar buscar os valores achando os nomes corretos 
		 */
		parametros.put("data_inicio", this.dataInicio);
		parametros.put("data_fim", this.dataFim);		
		
		ExecutorRelatorio executor = new ExecutorRelatorio("/relatorios/relatorio_pedidos_emitidos.jasper",
				this.response, parametros, "Pedidos emitidos.pdf");
		
		Session session = manager.unwrap(Session.class);
		//Fornecendo uma conexao pela classe implementadora em util
		session.doWork(executor);
	
		/**
		 * Se for positivo será terminado o ciclo de vido do jsf
		 */
		if( executor.isRelatorioGerado()){
		//Após a execução do relatório o ciclo de vida do jsf é completado,
		//não renderiza mais a página
		facesContext.responseComplete();
		} else{
			FacesUtil.addErrorMessage("Execução do relatório não retornou dados. ");
		}
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
	
}
