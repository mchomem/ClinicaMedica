package br.misael.clinicamedica.view.table;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.misael.clinicamedica.model.vo.VoPaciente;

public class TblPaciente extends AbstractTableModel {

	private static final long serialVersionUID   = 1609363612656315431L;
	private static final int COL_ID_PACIENTE     = 0;
	private static final int COL_NOME            = 1;
	private static final int COL_DATA_NASCIMENTO = 2;
	private static final int COL_SEXO            = 3;
	private static final int COL_ENDERECO        = 4;
	private static final int COL_TELEFONE        = 5;
	private static final int COL_ATIVO           = 6;
	
	private List<VoPaciente> registros;

	public TblPaciente(List<VoPaciente> registros) {
		
		this.registros = registros;
		
	}

	@Override
	public int getColumnCount() {
		return 7;
	}

	@Override
	public int getRowCount() {
		
		return this.registros.size();
		
	}

	@Override
	public Object getValueAt(int indiceLinha, int indiceColuna) {
		
		VoPaciente voPaciente = registros.get(indiceLinha);
		
		if(indiceColuna == COL_ID_PACIENTE) {
			
			return voPaciente.getIdPaciente();
			
		} else if(indiceColuna == COL_NOME) {
			
			return voPaciente.getNome();
			
		} else if(indiceColuna == COL_DATA_NASCIMENTO) {
			
			return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(voPaciente.getDataNascimento());
			
		} else if(indiceColuna == COL_SEXO) {
			
			return voPaciente.getSexo();
			
		} else if(indiceColuna == COL_ENDERECO) {
			
			return voPaciente.getEndereco();
			
		} else if(indiceColuna == COL_TELEFONE) {
			
			String telefone = voPaciente.getTelefone();
			
			telefone = "(" + telefone.substring(0, 2)
					+ ") " + telefone.substring(2, 6)
					+ "-" + telefone.substring(6, 10);
			
			return telefone;
			
		} else if(indiceColuna == COL_ATIVO) {
			
			return (voPaciente.isAtivo() ? "Sim" : "Não" );
			
		}
		
		return null;
		
	}
	
	@Override
	public String getColumnName(int column) {
		
		String coluna = "";
		
		switch (column) {
		
		case COL_ID_PACIENTE:
			
			coluna = "Código";
			break;
			
		case COL_NOME:
			
			coluna = "Nome Paciente";
			break;
			
		case COL_DATA_NASCIMENTO:
			
			coluna = "Data. Nascimento";
			break;
			
		case COL_SEXO:
			
			coluna = "Sexo";
			break;
			
		case COL_ENDERECO:
			
			coluna = "Endereço";
			break;
			
		case COL_TELEFONE:
			
			coluna = "Telefone";
			break;
			
		case COL_ATIVO:
			
			coluna = "Ativo?";
			break;

		default:
			
			throw new IllegalArgumentException("Coluna inválida");
			
		}
		
		return coluna;
		
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {

		if(columnIndex == COL_ID_PACIENTE) {
			
			return Long.class;
			
		} else if(columnIndex == COL_NOME) {
			
			return String.class;
			
		} else if(columnIndex == COL_DATA_NASCIMENTO) {
			
			return String.class;
			
		} else if(columnIndex == COL_SEXO) {
			
			return String.class;
			
		} else if(columnIndex == COL_ENDERECO) {
			
			return String.class;
			
		} else if(columnIndex == COL_TELEFONE) {
			
			return String.class;
			
		} else if(columnIndex == COL_ATIVO) {
			
			return String.class;
			
		}
		
		return null;
		
	}
	
	public VoPaciente get(int linha) {
		
		return this.registros.get(linha);
		
	}
	
}
