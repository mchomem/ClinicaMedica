package br.misael.clinicamedica.view.table;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.misael.clinicamedica.model.vo.VoAgendamento;

public class TblAgendamento extends AbstractTableModel {

	private static final long serialVersionUID = -7046527408157877767L;
	private static final int COL_CONSULTA = 0;
	private static final int COL_NOME_PACIENTE = 1;
	private static final int COL_DATA_CONSULTA = 2;

	private List<VoAgendamento> registros;

	public TblAgendamento(List<VoAgendamento> registros) {

		this.registros = registros;

	}

	@Override
	public int getColumnCount() {

		return 3;
	}

	@Override
	public int getRowCount() {

		return this.registros.size();
	}

	@Override
	public Object getValueAt(int indiceLinha, int indiceColuna) {

		VoAgendamento voAgendamento = registros.get(indiceLinha);

		if (indiceColuna == COL_CONSULTA) {

			return voAgendamento.getIdConsulta();

		} else if (indiceColuna == COL_NOME_PACIENTE) {

			return voAgendamento.getPaciente();

		} else if (indiceColuna == COL_DATA_CONSULTA) {

			return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(voAgendamento.getDataConsulta());

		}

		return null;

	}

	@Override
	public String getColumnName(int column) {

		String coluna = "";

		switch (column) {

		case COL_CONSULTA:

			coluna = "Código";
			break;

		case COL_NOME_PACIENTE:

			coluna = "Nome Paciente";
			break;

		case COL_DATA_CONSULTA:

			coluna = "Data/Hora Consulta";
			break;

		default:

			throw new IllegalArgumentException("Coluna inválida");

		}

		return coluna;

	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {

		if (columnIndex == COL_CONSULTA) {

			return Long.class;

		} else if (columnIndex == COL_NOME_PACIENTE) {

			return String.class;

		} else if (columnIndex == COL_DATA_CONSULTA) {

			return String.class;

		}

		return null;

	}

	public VoAgendamento get(int linha) {

		return this.registros.get(linha);

	}

}
