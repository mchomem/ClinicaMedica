package br.misael.clinicamedica.view.table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.misael.clinicamedica.model.vo.VoReceituario;

public class TblReceituario extends AbstractTableModel {

	private static final long serialVersionUID  = 4703220926534038861L;
	private static final int COL_ID_MEDICAMENTO = 0;
	private static final int COL_POSOLOGIA      = 1;
	
	private List<VoReceituario> registros;

	public TblReceituario(List<VoReceituario> registros) {
		
		this.registros = registros;
		
	}
	
	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public int getRowCount() {
		
		return this.registros.size();
		
	}
	
	@Override
	public Object getValueAt(int indiceLinha, int indiceColuna) {
		
		VoReceituario voReceituario = registros.get(indiceLinha);
		
		if(indiceColuna == COL_ID_MEDICAMENTO) {
			
			return voReceituario.getVoMedicamento().getNome();
			
		} else if(indiceColuna == COL_POSOLOGIA) {
			
			return voReceituario.getPosologia();
			
		}
		
		return null;
		
	}
	
	@Override
	public String getColumnName(int column) {
		
		String coluna = "";
		
		switch (column) {
		
		case COL_ID_MEDICAMENTO:
			
			coluna = "Medicamento";
			break;
			
		case COL_POSOLOGIA:
			
			coluna = "Posologia";
			break;
			
		default:
			
			throw new IllegalArgumentException("Coluna inválida");
			
		}
		
		return coluna;
		
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {

		if(columnIndex == COL_ID_MEDICAMENTO) {
			
			return String.class;
			
		} else if(columnIndex == COL_POSOLOGIA) {
			
			return String.class;
			
		}
		
		return null;
		
	}
	
	public VoReceituario get(int linha) {
		
		return this.registros.get(linha);
		
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		
		if(columnIndex == 1) {
			return true;
		} else {
			return false;
		}	
		
	}
}
