package br.misael.clinicamedica.view.table;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.misael.clinicamedica.model.vo.VoRegistroConsulta;

public class TblHistorico extends AbstractTableModel {

	private static final long serialVersionUID = 7627639554505994423L;
	private static final int COL_ID_CONSULTA = 0;
	private static final int COL_CONSULTA = 1;
	private static final int COL_PRONTUARIO = 2;
	private static final int COL_CONSULTA_FINALIZADA = 3;
	private static final int COL_DATA_FIM_CONSULTA = 4;

	private List<VoRegistroConsulta> registros;

	public TblHistorico(List<VoRegistroConsulta> registros) {

		this.registros = registros;

	}

	@Override
	public int getColumnCount() {

		return 5;

	}

	@Override
	public int getRowCount() {

		return this.registros.size();

	}

	@Override
	public Object getValueAt(int indiceLinha, int indiceColuna) {

		VoRegistroConsulta voRegistroConsulta = registros.get(indiceLinha);

		if (indiceColuna == COL_ID_CONSULTA) {

			return voRegistroConsulta.getVoAgendamento().getIdConsulta();

		} else if (indiceColuna == COL_CONSULTA) {

			return voRegistroConsulta.getVoAgendamento();

		} else if (indiceColuna == COL_PRONTUARIO) {

			return voRegistroConsulta.getProntuario();

		} else if (indiceColuna == COL_CONSULTA_FINALIZADA) {

			return (voRegistroConsulta.isConsultaFinalizada() ? "Sim" : "Não");

		} else if (indiceColuna == COL_DATA_FIM_CONSULTA) {

			String dataHora;

			if (voRegistroConsulta.getDataFimConsulta() == null) {
				dataHora = "00/00/0000 00:00:00";
			} else {
				dataHora = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(voRegistroConsulta.getDataFimConsulta());
			}

			return dataHora;

		}

		return null;

	}

	@Override
	public String getColumnName(int column) {

		String coluna = "";

		switch (column) {

		case COL_ID_CONSULTA:

			coluna = "Código";
			break;

		case COL_CONSULTA:

			coluna = "Agendamento";
			break;

		case COL_PRONTUARIO:

			coluna = "Prontuário";
			break;

		case COL_CONSULTA_FINALIZADA:

			coluna = "Consulta Finalizada?";
			break;

		case COL_DATA_FIM_CONSULTA:

			coluna = "Data Fim Consulta";
			break;

		default:

			throw new IllegalArgumentException("Coluna inválida");

		}

		return coluna;

	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {

		if (columnIndex == COL_ID_CONSULTA) {

			return Long.class;

		} else if (columnIndex == COL_CONSULTA) {

			return String.class;

		} else if (columnIndex == COL_PRONTUARIO) {

			return String.class;

		} else if (columnIndex == COL_CONSULTA_FINALIZADA) {

			return String.class;

		} else if (columnIndex == COL_DATA_FIM_CONSULTA) {

			return String.class;

		}

		return null;

	}

	public VoRegistroConsulta get(int linha) {

		return this.registros.get(linha);

	}

}
