package br.misael.clinicamedica.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import br.misael.clinicamedica.model.dao.DaoAgendamento;
import br.misael.clinicamedica.model.dao.DaoMedicamento;
import br.misael.clinicamedica.model.dao.DaoReceituario;
import br.misael.clinicamedica.model.dao.DaoRegistroConsulta;
import br.misael.clinicamedica.model.vo.VoAgendamento;
import br.misael.clinicamedica.model.vo.VoMedicamento;
import br.misael.clinicamedica.model.vo.VoReceituario;
import br.misael.clinicamedica.model.vo.VoRegistroConsulta;
import br.misael.clinicamedica.view.FrmMenu;
import br.misael.clinicamedica.view.FrmRegistroConsulta;
import br.misael.clinicamedica.view.table.CelTblReceituario;
import br.misael.clinicamedica.view.table.CelTblRegistroConsulta;
import br.misael.clinicamedica.view.table.TblReceituario;
import br.misael.clinicamedica.view.table.TblRegistroConsulta;

public class CtrRegistroConsulta implements ActionListener, KeyListener, InternalFrameListener, ListSelectionListener {
	
	private FrmRegistroConsulta      frmRegistroConsulta;
	private VoRegistroConsulta       voRegistroConsulta;
	private DaoRegistroConsulta      daoRegistroConsulta;
	private DaoAgendamento           daoAgendamento;
	private DaoMedicamento           daoMedicamento;
	private DaoReceituario           daoReceituario;
	private List<VoRegistroConsulta> voRegistroConsultas;
	private List<VoAgendamento>      voAgendamentos;
	private List<VoMedicamento>      voMedicamentos;
	private List<VoReceituario>      voReceituarios;
	
	public CtrRegistroConsulta(FrmMenu frmMenu) {
		
		this.frmRegistroConsulta = new FrmRegistroConsulta();
		this.voRegistroConsulta  = new VoRegistroConsulta();
		this.daoRegistroConsulta = new DaoRegistroConsulta();
		this.daoAgendamento      = new DaoAgendamento();
		this.daoMedicamento      = new DaoMedicamento();
		this.daoReceituario      = new DaoReceituario();
		
		voReceituarios           = new ArrayList<VoReceituario>();
		
		this.frmRegistroConsulta.getTxtaProntuario().addKeyListener(this);
		
		this.frmRegistroConsulta.getBtnInicializar().addActionListener(this);
		this.frmRegistroConsulta.getBtnGravar().addActionListener(this);
		this.frmRegistroConsulta.getBtnExcluir().addActionListener(this);
		this.frmRegistroConsulta.getBtnFiltrar().addActionListener(this);
		
		this.frmRegistroConsulta.getBtnAdicionar().addActionListener(this);
		this.frmRegistroConsulta.getBtnRemover().addActionListener(this);
		
		this.frmRegistroConsulta.getTableReceituario().getSelectionModel().addListSelectionListener(this);
		this.frmRegistroConsulta.getTable().getSelectionModel().addListSelectionListener(this);
		
		this.frmRegistroConsulta.addInternalFrameListener(this);
		
		frmMenu.getDesktopPane().add(this.frmRegistroConsulta);
		frmMenu.getDesktopPane().selectFrame(true);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == this.frmRegistroConsulta.getBtnInicializar()) {
			
			this.inicializar();
			
		} else if(e.getSource() == this.frmRegistroConsulta.getBtnGravar()) {
			
			this.gravar();
			
		} else if(e.getSource() == this.frmRegistroConsulta.getBtnExcluir()) {
			
			this.excluir();
			
		} else if(e.getSource() == this.frmRegistroConsulta.getBtnAdicionar()) {
			
			this.adicionarMedicamento();
			
		} else if(e.getSource() == this.frmRegistroConsulta.getBtnRemover()) {
			
			this.removerMedicamento();
			
		} else if(e.getSource() == this.frmRegistroConsulta.getBtnFiltrar()) {
			
			this.atualizarJTable();
			
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) { }

	@Override
	public void keyPressed(KeyEvent e) { }

	@Override
	public void keyReleased(KeyEvent e) {
		
		String campo = "";
		int maximo   = 0;
		
		if(e.getSource() == this.frmRegistroConsulta.getTxtaProntuario()) {
			
			campo = this.frmRegistroConsulta.getTxtaProntuario().getText();
			maximo = 2000;
			
			if(campo.length() > maximo) {
				
				campo = campo.substring(0, maximo);
				this.frmRegistroConsulta.getTxtaProntuario().setText(campo);
				
				JOptionPane.showMessageDialog(
						this.frmRegistroConsulta
						, "Máximo de 2000 caracteres atingido."
						, "Alerta"
						, JOptionPane.WARNING_MESSAGE);
				
			}
			
		}
		
	}
		
	@Override
	public void internalFrameActivated(InternalFrameEvent e) {

		this.popularComboAgendamentos();
		this.popularComboMedicamentos();
		this.atualizarJTable();
		
	}
	
	@Override
	public void internalFrameClosed(InternalFrameEvent e) {}

	@Override
	public void internalFrameClosing(InternalFrameEvent e) {}

	@Override
	public void internalFrameDeactivated(InternalFrameEvent e) {}

	@Override
	public void internalFrameDeiconified(InternalFrameEvent e) {}

	@Override
	public void internalFrameIconified(InternalFrameEvent e) {}

	@Override
	public void internalFrameOpened(InternalFrameEvent e) {}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		
		// TODO verificar erro, seleção e filtro apresentam divergência.
		
		int indiceLinha = this.frmRegistroConsulta.getTable().getSelectedRow();
		
		if(indiceLinha == -1) {
			return;
		}
		
		this.voRegistroConsulta = new TblRegistroConsulta(this.voRegistroConsultas).get(indiceLinha);
		
		// Nota importante: para que valor do controle JComboBox possa ser atualizado, ele deve ser editável.
		// lá na classe view do FrmAgendamento a variável cbPaciente deve ser a sua propriedade setEditable(true);
		this.frmRegistroConsulta.getCbAgedamento().setSelectedItem(this.voRegistroConsulta.getVoAgendamento());
		this.frmRegistroConsulta.getTxtaProntuario().setText(this.voRegistroConsulta.getProntuario());
		this.frmRegistroConsulta.getChckbxConsultaFinalizada().setSelected(this.voRegistroConsulta.isConsultaFinalizada());
		
	}
	
	private void inicializar() {
		
		this.frmRegistroConsulta.getCbAgedamento().setSelectedItem(null);
		this.frmRegistroConsulta.getCbMedicamento().setSelectedItem(null);
		this.frmRegistroConsulta.getTxtaProntuario().setText("");
		this.frmRegistroConsulta.getChckbxConsultaFinalizada().setSelected(false);
		this.frmRegistroConsulta.getTxtFiltro().setText("");
		
		if(this.voRegistroConsulta.getVoAgendamento() != null) {
			
			this.voRegistroConsulta.getVoAgendamento().setIdConsulta(0);
			
		}
		
	}

	private void gravar() {
		
		VoAgendamento voAgendamento = (VoAgendamento) this.frmRegistroConsulta.getCbAgedamento().getSelectedItem();
		String prontuario           = this.frmRegistroConsulta.getTxtaProntuario().getText();
		boolean consultaFinalizada  = this.frmRegistroConsulta.getChckbxConsultaFinalizada().isSelected();
		
		int respostaDialogo = JOptionPane.showConfirmDialog(
				this.frmRegistroConsulta
				, "Confirma gravação?"
				, ""
				, JOptionPane.YES_NO_OPTION
				, JOptionPane.QUESTION_MESSAGE);

		// Confirmar operação.
		if(respostaDialogo == JOptionPane.NO_OPTION
			|| respostaDialogo ==  JOptionPane.CLOSED_OPTION) {
			return;
		}
		
		// Consistência de dados.
		
		// Campos requisitados.
		if(voAgendamento == null
				|| prontuario.length() == 0) {
			JOptionPane.showMessageDialog(
					this.frmRegistroConsulta
					, "Informe os campos requisitados."
					, "Alerta"
					, JOptionPane.WARNING_MESSAGE);
			
			return;
		}
		
		voRegistroConsulta.setVoAgendamento(voAgendamento);
		voRegistroConsulta.setProntuario(prontuario);
		voRegistroConsulta.setConsultaFinalizada(consultaFinalizada);
		
		// Define data/hora do fim da consulta se consulta será finalizada.
		if(consultaFinalizada) {
			voRegistroConsulta.setDataFimConsulta(Timestamp.from(Instant.now()));
		} else {
			voRegistroConsulta.setDataFimConsulta(null);
		}
		
		if(consultaFinalizada
				&& voAgendamento.getDataConsulta().getTime() > Timestamp.from(Instant.now()).getTime()) {
				
				JOptionPane.showMessageDialog(
						this.frmRegistroConsulta
						, "A data do agendamento é maior que a data de finalização."
						, "Aviso"
						, JOptionPane.WARNING_MESSAGE);
				return;
				
			}
	
		try {
			
			String resultado = "";
			
			// Existe a consulta na tabela Registro de Consulta?
			// Como o relacionamento entre a tabela Agendamento e RegistroConsulta é 1:1
			// Então, verifica a existência do registro na tabela RegistroConsulta(ID_CONSULTA)
			// com o ID_CONSULTA da tabela Agendamento.
			List<VoRegistroConsulta> registroConsultas
				= daoRegistroConsulta
					.consultar(voRegistroConsulta.getVoAgendamento().getIdConsulta());
			
			// Se a lista está vazia é novo registro.
			if(registroConsultas.isEmpty()) {
				
				this.daoRegistroConsulta.inserir(this.voRegistroConsulta);
				resultado = "Registro gravado.";
				
			// Se não, altera o registro selecionado.
			} else {
				
				this.daoRegistroConsulta.alterar(this.voRegistroConsulta);
				resultado = "Registro alterado.";
				this.voRegistroConsulta.getVoAgendamento().setIdConsulta(0);
				
			}
			
			if(consultaFinalizada) {
				JOptionPane.showMessageDialog(
						this.frmRegistroConsulta
						, "Ao finalizar a consulta somente será possível consultá-la no Histórico."
						, "Aviso"
						, JOptionPane.WARNING_MESSAGE);
			}
			
			this.inicializar();
			this.atualizarJTable();
			
			JOptionPane.showMessageDialog(
					this.frmRegistroConsulta
					, resultado
					, ""
					, JOptionPane.INFORMATION_MESSAGE);
			
		} catch(ClassNotFoundException e) {
			
			JOptionPane.showMessageDialog(
					this.frmRegistroConsulta
					, "Erro ao gravar.\n\nDetalhes: " + e.getMessage()
					, "Erro"
					, JOptionPane.ERROR_MESSAGE);
			
		} catch(SQLException e) {
			
			JOptionPane.showMessageDialog(
					this.frmRegistroConsulta
					, "Erro ao gravar.\n\nDetalhes: " + e.getMessage()
					, "Erro"
					, JOptionPane.ERROR_MESSAGE);
			
		}	
		
	}
	
	private void excluir() {
		
		VoAgendamento voAgendamento = (VoAgendamento) this.frmRegistroConsulta.getCbAgedamento().getSelectedItem();
		
		int respostaDialogo = JOptionPane.showConfirmDialog(
				this.frmRegistroConsulta
				, "Confirma exclusão?"
				, ""
				, JOptionPane.YES_NO_OPTION
				, JOptionPane.QUESTION_MESSAGE);
		
		if(respostaDialogo == JOptionPane.NO_OPTION) {
			return;
		}
		
		// Consistência de dados.
		
		// Campos requisitados.
		if(voAgendamento == null) {
			
			JOptionPane.showMessageDialog(
					this.frmRegistroConsulta
					, "Informe os campos requisitados."
					, ""
					, JOptionPane.WARNING_MESSAGE);
			return;
			
		}
		
		// TODO objeto não instanciado verificar.
		if(voRegistroConsulta.getVoAgendamento().getIdConsulta() == 0) {
			JOptionPane.showMessageDialog(
					this.frmRegistroConsulta
					, "Seleciona um registro da tabela para excluir."
					, ""
					, JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		try {
			
			daoRegistroConsulta.excluir(voRegistroConsulta);
			this.inicializar();
			this.atualizarJTable();
			
			JOptionPane.showMessageDialog(
					this.frmRegistroConsulta
					, "Registro excluido."
					, ""
					, JOptionPane.INFORMATION_MESSAGE);
			
		} catch(ClassNotFoundException e) {
			
			JOptionPane.showMessageDialog(
					this.frmRegistroConsulta
					, "Erro ao gravar.\n\nDetalhes: " + e.getMessage()
					, "Erro"
					, JOptionPane.ERROR_MESSAGE);
			
		} catch(SQLException e) {
			
			JOptionPane.showMessageDialog(
					this.frmRegistroConsulta
					, "Erro ao gravar.\n\nDetalhes: " + e.getMessage()
					, "Erro"
					, JOptionPane.ERROR_MESSAGE);
			
		}
		
	}
	
	private void adicionarMedicamento() {

		// TODO revisar recurso
		/*
		 * Trocar compoente JComboBox por JList para medicamentos já cadastrados no banco.
		 * A lista de medicamsentos disponívels deve estar a esquerda e não a direita
		 * 
		 */
		
		// TODO Falha ao incerir medicamento na lista, tentativa de edição da célula falhou.		
		try {
		
			int rows = this.frmRegistroConsulta.getTableReceituario().getRowCount() - 1;
			
			if(voReceituarios.size() > 0) {
				
				String posologia = this.frmRegistroConsulta.getTableReceituario().getValueAt(rows, 1).toString();
				voReceituarios.get(voReceituarios.size() - 1).setPosologia(posologia);
				
			}
			
			VoReceituario receituario = new VoReceituario();
			receituario.setVoMedicamento( (VoMedicamento)this.frmRegistroConsulta.getCbMedicamento().getSelectedItem() );
			
			/*if(rows > -1) {
				String posologia = this.frmRegistroConsulta.getTableReceituario().getValueAt(rows, 1).toString();
				receituario.setPosologia(posologia);
			}*/
			
			voReceituarios.add(receituario);
			
			this.frmRegistroConsulta.getTableReceituario().setModel(new TblReceituario(voReceituarios));
			this.frmRegistroConsulta.getTableReceituario().setDefaultRenderer(Object.class, new CelTblReceituario());
		
		} catch(Exception e) {
			
			JOptionPane.showMessageDialog(
					this.frmRegistroConsulta
					, "Erro ao adicionar medicamento.\n\nDetalhes: " + e.getMessage()
					, "Erro"
					, JOptionPane.ERROR_MESSAGE);
			
		}
	}
	
	private void removerMedicamento() {
		
		try {
			
			int index = voReceituarios.size() - 1;
			
			if(index >= 0) {
				voReceituarios.remove(index);
			}
			
			this.frmRegistroConsulta.getTableReceituario().setModel(new TblReceituario(voReceituarios));
			this.frmRegistroConsulta.getTableReceituario().setDefaultRenderer(Object.class, new CelTblReceituario());
			
		} catch(Exception e) {

			JOptionPane.showMessageDialog(
					this.frmRegistroConsulta
					, "Erro ao remover medicamento.\n\nDetalhes: " + e.getMessage()
					, "Erro"
					, JOptionPane.ERROR_MESSAGE);
			
		}
		
	}
	
	private void atualizarJTable() {
		
		try {
			
			if(this.frmRegistroConsulta.getTxtFiltro().getText().length() == 0) {
				
				voRegistroConsultas = daoRegistroConsulta.consultar();
				
			} else {
				
				voRegistroConsultas = daoRegistroConsulta.consultar(this.frmRegistroConsulta.getTxtFiltro().getText());
				
			}
			
			List<VoRegistroConsulta> registroConsultaFiltro = new ArrayList<VoRegistroConsulta>();
			
			/* Exibe somente consultas não finalizadas */
			voRegistroConsultas
				.stream()
					.filter(rc -> rc.getDataFimConsulta() == null)
						.forEach(rc -> {
							registroConsultaFiltro.add(rc);
						});
			
			voRegistroConsultas = registroConsultaFiltro;
			
			if(voRegistroConsultas != null) {
				
				this.frmRegistroConsulta.getTable().setModel(new TblRegistroConsulta(voRegistroConsultas));
				this.frmRegistroConsulta.getTable().setDefaultRenderer(Object.class, new CelTblRegistroConsulta());
				
			}
			
		} catch(ClassNotFoundException e) {
			
			JOptionPane.showMessageDialog(
					this.frmRegistroConsulta
					, "Erro ao consultar.\n\nDetalhes: " + e.getMessage()
					, "Erro"
					, JOptionPane.ERROR_MESSAGE);
			
		} catch(SQLException e) {
			
			JOptionPane.showMessageDialog(
					this.frmRegistroConsulta
					, "Erro ao consultar.\n\nDetalhes: " + e.getMessage()
					, "Erro"
					, JOptionPane.ERROR_MESSAGE);
			
		}
		
	}
	
	private void popularComboAgendamentos() {
		
		try {
			
			DefaultComboBoxModel<VoAgendamento> cmbModelo = ((DefaultComboBoxModel) this.frmRegistroConsulta.getCbAgedamento().getModel());
			
			cmbModelo.removeAllElements();
			cmbModelo.addElement(null);
			
			this.voAgendamentos = this.daoAgendamento.consultar();
			
			for (int linha = 0; linha < this.voAgendamentos.size(); linha++) {
				
				VoAgendamento voAgendamento = this.voAgendamentos.get(linha);
				cmbModelo.addElement(voAgendamento);
				
			}
			
		} catch(ClassNotFoundException e) {
			
			JOptionPane.showMessageDialog(
					this.frmRegistroConsulta
					, "Erro ao consultar.\n\nDetalhes: " + e.getMessage()
					, "Erro"
					, JOptionPane.ERROR_MESSAGE);
			
		} catch(SQLException e) {
			
			JOptionPane.showMessageDialog(
					this.frmRegistroConsulta
					, "Erro ao consultar.\n\nDetalhes: " + e.getMessage()
					, "Erro"
					, JOptionPane.ERROR_MESSAGE);
			
		}
		
	}
	
	private void popularComboMedicamentos() {
		
		try {
			
			DefaultComboBoxModel<VoMedicamento> cmbModelo = ((DefaultComboBoxModel) this.frmRegistroConsulta.getCbMedicamento().getModel());
			
			cmbModelo.removeAllElements();
			cmbModelo.addElement(null);
			
			this.voMedicamentos = this.daoMedicamento.consultar();
			
			for (int linha = 0; linha < this.voMedicamentos.size(); linha++) {
				
				VoMedicamento voMedicamento = this.voMedicamentos.get(linha);
				cmbModelo.addElement(voMedicamento);
				
			}
			
		} catch(ClassNotFoundException e) {
			
			JOptionPane.showMessageDialog(
					this.frmRegistroConsulta
					, "Erro ao consultar.\n\nDetalhes: " + e.getMessage()
					, "Erro"
					, JOptionPane.ERROR_MESSAGE);
			
		} catch(SQLException e) {
			
			JOptionPane.showMessageDialog(
					this.frmRegistroConsulta
					, "Erro ao consultar.\n\nDetalhes: " + e.getMessage()
					, "Erro"
					, JOptionPane.ERROR_MESSAGE);
			
		}
	}
	
}
