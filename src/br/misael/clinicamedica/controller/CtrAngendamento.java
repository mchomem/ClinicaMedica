package br.misael.clinicamedica.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import br.misael.clinicamedica.model.bo.BoAgendamento;
import br.misael.clinicamedica.model.dao.DaoAgendamento;
import br.misael.clinicamedica.model.dao.DaoPaciente;
import br.misael.clinicamedica.model.dao.DaoRegistroConsulta;
import br.misael.clinicamedica.model.vo.VoAgendamento;
import br.misael.clinicamedica.model.vo.VoPaciente;
import br.misael.clinicamedica.view.FrmAgendamento;
import br.misael.clinicamedica.view.FrmMenu;
import br.misael.clinicamedica.view.table.CelTblAgendamento;
import br.misael.clinicamedica.view.table.TblAgendamento;

public class CtrAngendamento implements ActionListener, InternalFrameListener, ListSelectionListener {

	private FrmAgendamento frmAngedamento;
	private VoAgendamento voAgendamento;
	private BoAgendamento boAgendamento;
	private DaoAgendamento daoAgendamento;
	private DaoPaciente daoPaciente;
	private DaoRegistroConsulta daoRegistroConsulta;
	private List<VoAgendamento> voAgendamentos;
	private List<VoPaciente> voPacientes;

	public CtrAngendamento(FrmMenu frmMenu) {

		this.frmAngedamento = new FrmAgendamento();
		this.voAgendamento = new VoAgendamento();
		this.boAgendamento = new BoAgendamento();
		this.daoAgendamento = new DaoAgendamento();
		this.daoRegistroConsulta = new DaoRegistroConsulta();
		this.daoPaciente = new DaoPaciente();

		this.frmAngedamento.getBtnInicializar().addActionListener(this);
		this.frmAngedamento.getBtnGravar().addActionListener(this);
		this.frmAngedamento.getBtnExcluir().addActionListener(this);
		this.frmAngedamento.getBtnRelatorio().addActionListener(this);
		this.frmAngedamento.getBtnFiltrar().addActionListener(this);
		this.frmAngedamento.getTable().getSelectionModel().addListSelectionListener(this);

		this.frmAngedamento.addInternalFrameListener(this);

		frmMenu.getDesktopPane().add(this.frmAngedamento);
		frmMenu.getDesktopPane().selectFrame(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == this.frmAngedamento.getBtnInicializar()) {

			this.inicializar();

		} else if (e.getSource() == this.frmAngedamento.getBtnGravar()) {

			this.gravar();

		} else if (e.getSource() == this.frmAngedamento.getBtnExcluir()) {

			this.excluir();

		} else if (e.getSource() == this.frmAngedamento.getBtnRelatorio()) {

			this.gerarRelatorioLista();

		} else if (e.getSource() == this.frmAngedamento.getBtnFiltrar()) {

			this.atualizarJTable();

		}

	}

	@Override
	public void internalFrameActivated(InternalFrameEvent e) {

		this.popularComboPaciente();
		this.atualizarJTable();

	}

	@Override
	public void internalFrameClosed(InternalFrameEvent e) {

	}

	@Override
	public void internalFrameClosing(InternalFrameEvent e) {

	}

	@Override
	public void internalFrameDeactivated(InternalFrameEvent e) {

	}

	@Override
	public void internalFrameDeiconified(InternalFrameEvent e) {

	}

	@Override
	public void internalFrameIconified(InternalFrameEvent e) {

	}

	@Override
	public void internalFrameOpened(InternalFrameEvent e) {

	}

	@Override
	public void valueChanged(ListSelectionEvent e) {

		int indiceLinha = this.frmAngedamento.getTable().getSelectedRow();

		if (indiceLinha == -1) {
			return;
		}

		this.voAgendamento = new TblAgendamento(this.voAgendamentos).get(indiceLinha);

		// Nota importante: para que valor do controle JComboBox possa ser atualizado,
		// ele deve ser editável.
		// Na classe view do FrmAgendamento a variável cbPaciente deve ser a sua
		// propriedade setEditable(true);
		this.frmAngedamento.getCbPaciente().setSelectedItem(this.voAgendamento.getPaciente());

		this.frmAngedamento.getFtxtData()
				.setText(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(this.voAgendamento.getDataConsulta()));

	}

	private void inicializar() {

		this.frmAngedamento.getCbPaciente().setSelectedItem(null);
		this.frmAngedamento.getFtxtData().setText("");
		this.frmAngedamento.getTxtFiltro().setText("");
		this.voAgendamento.setIdConsulta(0);

	}

	private void gravar() {

		VoPaciente voPaciente = (VoPaciente) this.frmAngedamento.getCbPaciente().getSelectedItem();

		// Paciente não está ativo no cadastro?
		if (!this.boAgendamento.verificarPacienteAtivo(voPaciente)) {

			JOptionPane.showMessageDialog(this.frmAngedamento,
					"O paciente " + voPaciente.getNome() + " não está ativo no cadastro.\n"
							+ "Ative o cadastro deste paciente para poder agendar uma consulta.",
					"Aviso", JOptionPane.WARNING_MESSAGE);

			return;

		}

		Timestamp dataConsulta = null;

		try {

			DateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			dataConsulta = new Timestamp(formatoData.parse(this.frmAngedamento.getFtxtData().getText()).getTime());

		} catch (ParseException e) {

			JOptionPane.showMessageDialog(this.frmAngedamento, "Data inválida.", "Erro", JOptionPane.ERROR_MESSAGE);
			return;

		}

		int respostaDialogo = JOptionPane.showConfirmDialog(this.frmAngedamento, "Confirma gravação?", "",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

		// Confirmar operação antes de mais nada.
		if (respostaDialogo == JOptionPane.NO_OPTION) {
			return;
		}

		// Consistência de dados.

		// Campos requisitados.
		if (voPaciente == null || dataConsulta == null) {

			JOptionPane.showMessageDialog(this.frmAngedamento, "Informe os campos requisitados.", "",
					JOptionPane.WARNING_MESSAGE);
			return;

		}

		voAgendamento.setPaciente(voPaciente);
		voAgendamento.setDataConsulta(dataConsulta);

		try {

			String resultado = "";

			if (voAgendamento.getIdConsulta() == 0) {

				this.daoAgendamento.inserir(this.voAgendamento);
				resultado = "Registro gravado.";

			} else {

				this.daoAgendamento.alterar(this.voAgendamento);
				resultado = "Registro alterado.";
				this.voAgendamento.setIdConsulta(0);

			}

			this.inicializar();
			this.atualizarJTable();

			JOptionPane.showMessageDialog(this.frmAngedamento, resultado, "", JOptionPane.INFORMATION_MESSAGE);

		} catch (ClassNotFoundException e) {

			JOptionPane.showMessageDialog(this.frmAngedamento, "Erro ao gravar.\n\nDetalhes: " + e.getMessage(), "Erro",
					JOptionPane.ERROR_MESSAGE);

		} catch (SQLException e) {

			JOptionPane.showMessageDialog(this.frmAngedamento, "Erro ao gravar.\n\nDetalhes: " + e.getMessage(), "Erro",
					JOptionPane.ERROR_MESSAGE);

		}

	}

	private void excluir() {

		int respostaDialogo = JOptionPane.showConfirmDialog(this.frmAngedamento, "Confirma exclusão?", "",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

		if (respostaDialogo == JOptionPane.NO_OPTION) {

			return;

		}

		if (voAgendamento.getIdConsulta() == 0) {

			JOptionPane.showMessageDialog(this.frmAngedamento, "Seleciona um registro da tabela para excluir.", "",
					JOptionPane.WARNING_MESSAGE);
			return;

		}

		try {

			// TODO impedir exclusão quando um agendamento possuir consulta.

			long idConsulta = this.voAgendamento.getIdConsulta();

			if (this.daoRegistroConsulta.consultar(idConsulta).size() > 0) {

				JOptionPane.showMessageDialog(this.frmAngedamento,
						"Este agendamento já foi consolidado com uma consulta. Não é possível a exclusão.", "Aviso",
						JOptionPane.WARNING_MESSAGE);
				return;

			}

			this.daoAgendamento.excluir(voAgendamento);
			this.inicializar();
			this.atualizarJTable();

			JOptionPane.showMessageDialog(this.frmAngedamento, "Registro excluido.", "",
					JOptionPane.INFORMATION_MESSAGE);

		} catch (ClassNotFoundException e) {

			JOptionPane.showMessageDialog(this.frmAngedamento, "Erro ao gravar.\n\nDetalhes: " + e.getMessage(), "Erro",
					JOptionPane.ERROR_MESSAGE);

		} catch (SQLException e) {

			JOptionPane.showMessageDialog(this.frmAngedamento, "Erro ao gravar.\n\nDetalhes: " + e.getMessage(), "Erro",
					JOptionPane.ERROR_MESSAGE);

		}

	}

	private void atualizarJTable() {

		try {

			if (this.frmAngedamento.getTxtFiltro().getText().length() == 0) {

				this.voAgendamentos = daoAgendamento.consultar();

			} else {

				this.voAgendamentos = daoAgendamento.consultar(this.frmAngedamento.getTxtFiltro().getText());

			}

			if (voAgendamentos != null) {

				this.frmAngedamento.getTable().setModel(new TblAgendamento(voAgendamentos));
				this.frmAngedamento.getTable().setDefaultRenderer(Object.class, new CelTblAgendamento());

			}

		} catch (ClassNotFoundException e) {

			JOptionPane.showMessageDialog(this.frmAngedamento, "Erro ao consultar.\n\nDetalhes: " + e.getMessage(),
					"Erro", JOptionPane.ERROR_MESSAGE);

		} catch (SQLException e) {

			JOptionPane.showMessageDialog(this.frmAngedamento, "Erro ao consultar.\n\nDetalhes: " + e.getMessage(),
					"Erro", JOptionPane.ERROR_MESSAGE);

		}

	}

	private void popularComboPaciente() {

		try {

			DefaultComboBoxModel<VoPaciente> cmbModelo = ((DefaultComboBoxModel) this.frmAngedamento.getCbPaciente()
					.getModel());

			cmbModelo.removeAllElements();
			cmbModelo.addElement(null);
			this.voPacientes = this.daoPaciente.consultar();

			for (int linha = 0; linha < this.voPacientes.size(); linha++) {

				VoPaciente voPaciente = this.voPacientes.get(linha);
				cmbModelo.addElement(voPaciente);

			}

		} catch (ClassNotFoundException e) {

			JOptionPane.showMessageDialog(this.frmAngedamento, "Erro ao consultar.\n\nDetalhes: " + e.getMessage(),
					"Erro", JOptionPane.ERROR_MESSAGE);

		} catch (SQLException e) {

			JOptionPane.showMessageDialog(this.frmAngedamento, "Erro ao consultar.\n\nDetalhes: " + e.getMessage(),
					"Erro", JOptionPane.ERROR_MESSAGE);

		}

	}

	private void gerarRelatorioLista() {

		try {

			if (this.voAgendamentos == null) {

				JOptionPane.showMessageDialog(this.frmAngedamento, "Execute uma consulta antes.", "Aviso",
						JOptionPane.WARNING_MESSAGE);
				return;

			}

			if (this.voAgendamentos.size() == 0) {

				JOptionPane.showMessageDialog(this.frmAngedamento, "Não há registros para exibir o relatório.", "Aviso",
						JOptionPane.WARNING_MESSAGE);
				return;

			}

			JRBeanCollectionDataSource jrbcds = new JRBeanCollectionDataSource(this.voAgendamentos);

			JasperPrint print = JasperFillManager.fillReport("relatorios/RelAgendamento.jasper", null, jrbcds);

			JasperViewer jv = new JasperViewer(print, false);
			jv.setZoomRatio(0.75F);
			jv.setVisible(true);

		} catch (JRException e) {

			JOptionPane.showMessageDialog(this.frmAngedamento,
					"Não foi possível abrir o relatório.\n\n" + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();

		}

	}

}
