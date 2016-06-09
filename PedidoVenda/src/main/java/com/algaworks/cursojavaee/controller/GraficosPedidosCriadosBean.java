package com.algaworks.cursojavaee.controller;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import com.algaworks.cursojavaee.model.Usuario;
import com.algaworks.cursojavaee.repository.Pedidos;
import com.algaworks.cursojavaee.security.UsuarioLogado;
import com.algaworks.cursojavaee.security.UsuarioSistema;

@Named
@RequestScoped
public class GraficosPedidosCriadosBean {

	private static DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM");

	private LineChartModel model;

	@Inject
	private Pedidos pedidos;

	@Inject
	@UsuarioLogado
	private UsuarioSistema usuarioLogado;

	public void preRender() {
	    initCategoryModel();

	}

	 private void adcionarSerie(String rotulo, Usuario criadoPor) {
	 Map<Date, BigDecimal> valoresPorData =
	 this.pedidos.valoresTotaisPorData(15, criadoPor);
	
	 LineChartSeries series = new LineChartSeries(rotulo);
	
	 for(Date data : valoresPorData.keySet()){
	 series.set(DATE_FORMAT.format(data) , valoresPorData.get(data));
	 }
	
	 this.model.addSeries(series);
	
	 }
	
	 private void initCategoryModel(){
	 model = new LineChartModel();
	
	 this.model.setTitle("Pedidos Criados");
	 this.model.setLegendPosition("e");
	 this.model.setShowPointLabels(true);
	 this.model.getAxes().put(AxisType.X, new CategoryAxis("Dias do mês"));
	 this.model.setAnimate(true);
	 this.model.setMouseoverHighlight(true);
	
	
	 adcionarSerie("Todos os pedidos", null);
	 adcionarSerie("Meus os pedidos", usuarioLogado.getUsuario());
	
	
	 }
	
	 public LineChartModel getModel() {
	 return model;
	 }


}
