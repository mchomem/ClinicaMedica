package br.misael.clinicamedica.view.table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.misael.clinicamedica.model.vo.VoMedicamento;

public class TblMedicamento extends AbstractTableModel {

	private static final long serialVersionUID = -3448304592599397603L;
	private static final int COL_ID_MEDICAMENTO = 0;
	private static final int COL_NOME = 1;

	private List<VoMedicamento> registros;

	public TblMedicamento(List<VoMedicamento> registros) {

		this.registros = registros;

	}

	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public int getRowCount() {

		return registros.size();

	}

	@Override
	public Object getValueAt(int indiceLinha, int indiceColuna) {

		VoMedicamento voMedicamento = registros.get(indiceLinha);

		if (indiceColuna == COL_ID_MEDICAMENTO) {

			return voMedicamento.getIdMedicamento();

		} else if (indiceColuna == COL_NOME) {

			return voMedicamento.getNome();

		}

		return null;

	}

	@Override
	public String getColumnName(int column) {

		String coluna = "";

		switch (column) {

		case COL_ID_MEDICAMENTO:

			coluna = "Código";
			break;

		case COL_NOME:

			coluna = "Nome Medicamento";
			break;

		default:

			throw new IllegalArgumentException("Coluna inválida");

		}

		return coluna;

	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {

		if (columnIndex == COL_ID_MEDICAMENTO) {

			return Long.class;

		} else if (columnIndex == COL_NOME) {

			return String.class;

		}

		return null;

	}

	public VoMedicamento get(int linha) {

		return registros.get(linha);

	}

}
