package br.misael.clinicamedica.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.util.List;

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
import br.misael.clinicamedica.model.dao.DaoMedicamento;
import br.misael.clinicamedica.model.vo.VoMedicamento;
import br.misael.clinicamedica.view.FrmMedicamento;
import br.misael.clinicamedica.view.FrmMenu;
import br.misael.clinicamedica.view.table.CelTblMedicamento;
import br.misael.clinicamedica.view.table.TblMedicamento;

public class CtrMedicamento implements ActionListener, KeyListener, InternalFrameListener, ListSelectionListener {

	private FrmMedicamento frmMedicamento;
	private VoMedicamento voMedicamento;
	private DaoMedicamento daoMedicamento;
	private List<VoMedicamento> voMedicamentos;

	public CtrMedicamento(FrmMenu frmMenu) {

		this.voMedicamento = new VoMedicamento();
		this.daoMedicamento = new DaoMedicamento();
		this.frmMedicamento = new FrmMedicamento();

		this.frmMedicamento.getTxtNome().addKeyListener(this);
		this.frmMedicamento.getTxtFiltro().addKeyListener(this);

		this.frmMedicamento.getBtnInicializar().addActionListener(this);
		this.frmMedicamento.getBtnGravar().addActionListener(this);
		this.frmMedicamento.getBtnExcluir().addActionListener(this);
		this.frmMedicamento.getBtnRelatorio().addActionListener(this);
		this.frmMedicamento.getBtnFiltrar().addActionListener(this);
		this.frmMedicamento.getTable().getSelectionModel().addListSelectionListener(this);

		this.frmMedicamento.addInternalFrameListener(this);

		frmMenu.getDesktopPane().add(this.frmMedicamento);
		frmMenu.getDesktopPane().selectFrame(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == this.frmMedicamento.getBtnInicializar()) {

			this.inicializar();

		} else if (e.getSource() == this.frmMedicamento.getBtnGravar()) {

			this.gravar();

		} else if (e.getSource() == this.frmMedicamento.getBtnExcluir()) {

			this.excluir();

		} else if (e.getSource() == this.frmMedicamento.getBtnRelatorio()) {

			this.gerarRelatorioLista();

		} else if (e.getSource() == this.frmMedicamento.getBtnFiltrar()) {

			this.atualizarJTable();

		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {

		String campo = "";
		int maximo = 0;

		if (e.getSource() == this.frmMedicamento.getTxtNome()) {

			campo = this.frmMedicamento.getTxtNome().getText();
			maximo = 200;

			if (campo.length() > maximo) {

				campo = campo.substring(0, maximo);
				this.frmMedicamento.getTxtNome().setText(campo);

			}

		} else if (e.getSource() == this.frmMedicamento.getTxtFiltro()) {

			campo = this.frmMedicamento.getTxtFiltro().getText();
			maximo = 200;

			if (campo.length() > maximo) {

				campo = campo.substring(0, maximo);
				this.frmMedicamento.getTxtFiltro().setText(campo);

			}

		}

	}

	@Override
	public void internalFrameActivated(InternalFrameEvent e) {
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

		int indiceLinha = this.frmMedicamento.getTable().getSelectedRow();

		if (indiceLinha == -1) {
			return;
		}

		voMedicamento = new TblMedicamento(voMedicamentos).get(indiceLinha);
		this.frmMedicamento.getTxtNome().setText(voMedicamento.getNome());

	}

	private void inicializar() {

		this.frmMedicamento.getTxtNome().setText("");
		this.voMedicamento.setIdMedicamento(0);

	}

	private void gravar() {

		String nome = this.frmMedicamento.getTxtNome().getText();
		int respostaDialogo = JOptionPane.showConfirmDialog(this.frmMedicamento, "Confirma gravação?", "",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

		// Confirmar operação antes de mais nada.
		if (respostaDialogo == JOptionPane.NO_OPTION) {
			return;
		}

		// Consistência de dados.

		// Campos requisitados.
		if (nome.length() == 0) {
			JOptionPane.showMessageDialog(this.frmMedicamento, "Informe os campos requisitados.", "",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		voMedicamento.setNome(nome);

		try {

			String resultado = "";

			// Novo registro ou alteração?
			if (voMedicamento.getIdMedicamento() == 0) {

				daoMedicamento.inserir(voMedicamento);
				resultado = "Registro gravado.";

			} else {

				daoMedicamento.alterar(voMedicamento);
				resultado = "Registro alterado.";
				voMedicamento.setIdMedicamento(0);

			}

			this.inicializar();
			this.atualizarJTable();
			JOptionPane.showMessageDialog(this.frmMedicamento, resultado, "", JOptionPane.INFORMATION_MESSAGE);

		} catch (ClassNotFoundException e) {

			JOptionPane.showMessageDialog(this.frmMedicamento, "Erro ao gravar.\n\nDetalhes: " + e.getMessage(), "Erro",
					JOptionPane.ERROR_MESSAGE);

		} catch (SQLException e) {

			JOptionPane.showMessageDialog(this.frmMedicamento, "Erro ao gravar.\n\nDetalhes: " + e.getMessage(), "Erro",
					JOptionPane.ERROR_MESSAGE);

		}

	}

	private void excluir() {

		int respostaDialogo = JOptionPane.showConfirmDialog(this.frmMedicamento, "Confirma exclusão?", "",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

		if (respostaDialogo == JOptionPane.NO_OPTION) {
			return;
		}

		if (voMedicamento.getIdMedicamento() == 0) {
			JOptionPane.showMessageDialog(this.frmMedicamento, "Seleciona um registro da tabela para excluir.", "",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		try {

			daoMedicamento.excluir(voMedicamento);
			this.inicializar();
			this.atualizarJTable();
			JOptionPane.showMessageDialog(this.frmMedicamento, "Registro excluido.", "",
					JOptionPane.INFORMATION_MESSAGE);

		} catch (ClassNotFoundException e) {

			JOptionPane.showMessageDialog(this.frmMedicamento, "Erro ao gravar.\n\nDetalhes: " + e.getMessage(), "Erro",
					JOptionPane.ERROR_MESSAGE);

		} catch (SQLException e) {

			JOptionPane.showMessageDialog(this.frmMedicamento, "Erro ao gravar.\n\nDetalhes: " + e.getMessage(), "Erro",
					JOptionPane.ERROR_MESSAGE);

		}

	}

	private void atualizarJTable() {

		try {

			if (this.frmMedicamento.getTxtFiltro().getText().length() == 0) {
				voMedicamentos = daoMedicamento.consultar();
			} else {
				voMedicamentos = daoMedicamento.consultar(this.frmMedicamento.getTxtFiltro().getText());
			}

			if (voMedicamentos != null) {

				this.frmMedicamento.getTable().setModel(new TblMedicamento(voMedicamentos));
				this.frmMedicamento.getTable().setDefaultRenderer(Object.class, new CelTblMedicamento());

			}

		} catch (ClassNotFoundException e) {

			JOptionPane.showMessageDialog(this.frmMedicamento, "Erro ao consultar.\n\nDetalhes: " + e.getMessage(),
					"Erro", JOptionPane.ERROR_MESSAGE);

		} catch (SQLException e) {

			JOptionPane.showMessageDialog(this.frmMedicamento, "Erro ao consultar.\n\nDetalhes: " + e.getMessage(),
					"Erro", JOptionPane.ERROR_MESSAGE);

		}

	}

	private void gerarRelatorioLista() {

		try {

			if (this.voMedicamentos == null) {

				JOptionPane.showMessageDialog(this.frmMedicamento, "Execute uma consulta antes.", "Aviso",
						JOptionPane.WARNING_MESSAGE);
				return;

			}

			if (this.voMedicamentos.size() == 0) {

				JOptionPane.showMessageDialog(this.frmMedicamento, "Não há registros para exibir o relatório.", "Aviso",
						JOptionPane.WARNING_MESSAGE);
				return;

			}

			JRBeanCollectionDataSource jrbcds = new JRBeanCollectionDataSource(this.voMedicamentos);
			JasperPrint print = JasperFillManager.fillReport("relatorios/RelMedicamento.jasper", null, jrbcds);
			JasperViewer jv = new JasperViewer(print, false);
			jv.setZoomRatio(0.75F);
			jv.setVisible(true);

		} catch (JRException e) {

			JOptionPane.showMessageDialog(this.frmMedicamento,
					"Não foi possível abrir o relatório.\n\n" + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();

		}

	}

}
