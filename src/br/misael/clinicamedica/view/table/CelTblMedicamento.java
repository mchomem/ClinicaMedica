package br.misael.clinicamedica.view.table;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class CelTblMedicamento extends DefaultTableCellRenderer {

	private static final long serialVersionUID = -3780980761633932969L;
	
	@Override
	public Component getTableCellRendererComponent(JTable table
													, Object value
													, boolean isSelected
													, boolean hasFocus
													, int row
													, int column) {
		
		super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		
		// Se a linha é par.
		if(row % 2 == 0) {
			
			// Aplicar cor em tom de cinza.
			setBackground(new Color(220, 220, 220));
			
		} else {
			
			// Não aplicar cor.
			setBackground(null);
			
		}
		
		if(isSelected) {
			
			setBackground(new Color(10, 10, 190));
			setForeground(Color.WHITE);
			setFont(new Font(null, Font.BOLD, 12));
			
		} else {
			
			setForeground(Color.BLACK);
			
		}
		
		table.getColumnModel().getColumn(0).setMaxWidth(50);
		table.getColumnModel().getColumn(1).setMaxWidth(340);
		
		return this;
		
	}
	

}
