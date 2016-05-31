package com.algaworks.cursojavaee.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.LineChartModel;

@Named
@RequestScoped
public class GraficosPedidosCriadosBean {
	
	//
	private LineChartModel model;
	
	
	public void preRender(){
		initCategoryModel();
		
	}

	private void adcionarSerie(String rotulo) {
		ChartSeries series = new ChartSeries(rotulo);
		series.set("1", Math.random()*1000);
		series.set("2", Math.random()*1000);
		series.set("3", Math.random()*1000);
		series.set("4", Math.random()*1000);
		series.set("5", Math.random()*1000);	
		this.model.addSeries(series);
		
	}
	
	private void  initCategoryModel(){		
		model = new LineChartModel();
		
		this.model.setTitle("Pedidos Criados");
		this.model.setLegendPosition("e");
		this.model.setShowPointLabels(true);
		this.model.getAxes().put(AxisType.X, new CategoryAxis("Vendedor"));	
        this.model.setAnimate(true);
        this.model.setMouseoverHighlight(true);
		
        
		adcionarSerie("Todos os pedidos");
		adcionarSerie("Meus os pedidos");
		
		
	}

	public CartesianChartModel getModel() {
		return model;
	}

	

}
