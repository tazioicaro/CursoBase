package com.algaworks.cursojavaee.controller;



import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;


import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.algaworks.cursojavaee.model.Pedido;
import com.algaworks.cursojavaee.model.StatusPedido;
import com.algaworks.cursojavaee.repository.Pedidos;
import com.algaworks.cursojavaee.repository.filter.PedidoFilter;



@Named
@ViewScoped
public class PesquisaPedidosBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Pedidos pedidos;
	
	private PedidoFilter filtro;
	private LazyDataModel<Pedido> model;
	
	public PesquisaPedidosBean() {
		filtro = new PedidoFilter();
		
		model = new LazyDataModel<Pedido>() {

			private static final long serialVersionUID = 1L;
			
			@Override
			public List<Pedido> load(int first, int pageSize, String sortField, SortOrder sortOrder, 
					Map<String, Object> filters) {
				
				filtro.setPrimeiroRegistro(first);
				filtro.setQuantidadeRegistros(pageSize);
				filtro.setPropriedadeOrdenacao(sortField);
				filtro.setAscendente(SortOrder.ASCENDING.equals(sortOrder));
				
				setRowCount(pedidos.quantidadeFiltrados(filtro));
				
				return pedidos.filtrados(filtro);
			}
			
		};
	}

//	public void posProcessarXls(Object documento) {
//		HSSFWorkbook planilha = (HSSFWorkbook) documento;
//		HSSFSheet folha = planilha.getSheetAt(0);
//		HSSFRow cabecalho = folha.getRow(0);
//		HSSFCellStyle estiloCelula = planilha.createCellStyle();
//		Font fonteCabecalho = planilha.createFont();
//		
//		fonteCabecalho.setColor(IndexedColors.WHITE.getIndex());
//		fonteCabecalho.setBold(true);
//		fonteCabecalho.setFontHeightInPoints((short) 16);
//		
//		estiloCelula.setFont(fonteCabecalho);
//		estiloCelula.setFillForegroundColor(IndexedColors.BLACK.getIndex());
//		estiloCelula.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//		
//		for (int i = 0; i < cabecalho.getPhysicalNumberOfCells(); i++) {
//			cabecalho.getCell(i).setCellStyle(estiloCelula);
//		}
//	}
	
	public StatusPedido[] getStatuses() {
		return StatusPedido.values();
	}
	
	public PedidoFilter getFiltro() {
		return filtro;
	}

	public LazyDataModel<Pedido> getModel() {
		return model;
	}
	
}
