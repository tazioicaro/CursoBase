package com.algaworks.cursojavaee.util.report;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.ExporterConfiguration;
import net.sf.jasperreports.export.ExporterInput;
import net.sf.jasperreports.export.ExporterOutput;
import net.sf.jasperreports.export.ReportExportConfiguration;

import org.hibernate.jdbc.Work;

public class ExecutorRelatorio implements Work {

	private String caminhorelatorio;

	/*
	 * Necessário para incluir um cabeçário com o nome do arquivo que será feito
	 * o download, incluir no content Type que será arquivo pdf
	 */
	private HttpServletResponse response;
	private Map<String, Object> parametros;
	private String nomeArquivoSaida;

	private boolean relatorioGerado;

	public ExecutorRelatorio(String caminhorelatorio,
			HttpServletResponse response, Map<String, Object> parametros,
			String nomeArquivoSaida) {
		super();
		this.caminhorelatorio = caminhorelatorio;
		this.response = response;
		this.parametros = parametros;
		this.nomeArquivoSaida = nomeArquivoSaida;

		// Para que a exportação para pdf fique em portugues
		this.parametros.put(JRParameter.REPORT_LOCALE, new Locale("pt", "BR"));
	}

	@SuppressWarnings("deprecation")
	@Override
	public void execute(Connection connection) throws SQLException {
		/*
		 * O getResourceAsStream serve par pegar um fluxo de entrada (bytes do
		 * relatório jasper) de dados de um arquivo que está dentro do projeto
		 */
		try {
			InputStream relatorioStream = this.getClass().getResourceAsStream(
					this.caminhorelatorio);

			// resultado do relatório
			JasperPrint print = JasperFillManager.fillReport(relatorioStream,
					this.parametros, connection);
			
			/**
			 * Verificar se o relatório foi gerado
			 */
			this.relatorioGerado = print.getPages().size()>0;
			
			if (this.relatorioGerado){

			// Exportação para PDF
			@SuppressWarnings({ "deprecation", "unused" })
			JRExporter exportador = new JRPdfExporter();
			exportador.setParameter(JRExporterParameter.OUTPUT_STREAM,
					response.getOutputStream());
			exportador.setParameter(JRExporterParameter.JASPER_PRINT, print);

			// Informar ao brownser que é pdf
			response.setContentType("application/pdf");

			// forçar o download do arquivo
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ this.nomeArquivoSaida + "\"");

			// Gerar e enviar para o response outpuntStream
			exportador.exportReport();
			}

		} catch (Exception e) {
			// Utilizando e SQLException prq o método execute tem que lançar um
			// SQLException

			throw new SQLException("Erro ao executar relatório"
					+ this.caminhorelatorio, e);
		}
		

	}

	public boolean isRelatorioGerado() {
		return relatorioGerado;
	}

}
