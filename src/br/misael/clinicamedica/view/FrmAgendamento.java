package br.misael.clinicamedica.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.TitledBorder;

import br.misael.clinicamedica.model.FormUtils;
import br.misael.clinicamedica.model.MascaraCampo;

public class FrmAgendamento extends JInternalFrame {

	private static final long serialVersionUID = 2247090366186548838L;
	private JFormattedTextField ftxtData;
	private JTable table;
	private JTextField txtFiltro;
	private JToolBar toolBar;
	private JButton btnInicializar;
	private JButton btnGravar;
	private JButton btnExcluir;
	private JButton btnRelatorio;
	private JPanel pnlDados;
	private JLabel lblPaciente;
	private JComboBox<Object> cbPaciente;
	private JLabel lblData;
	private JPanel pnlRegistros;
	private JScrollPane scrollPane;
	private JButton btnFiltrar;
	private Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

	/**
	 * Create the frame.
	 */
	public FrmAgendamento() {
		setClosable(true);
		setTitle("Agendamento");
		setBounds(0, 0, 500, 500);
		// setLocation((dimension.width - this.getWidth()) / 2, (dimension.height -
		// this.getHeight()) / 2 );
		setLocation((dimension.width - this.getWidth()) / 2, 50);
		getContentPane().setLayout(null);

		toolBar = new JToolBar();
		toolBar.setBounds(0, 0, 484, 30);
		toolBar.setFloatable(false);
		getContentPane().add(toolBar);

		btnInicializar = new JButton("");
		btnInicializar.setBorderPainted(false);
		btnInicializar.setIcon(new ImageIcon(FrmAgendamento.class.getResource("/resource/img/page_white.png")));
		toolBar.add(btnInicializar);

		btnGravar = new JButton("");
		btnGravar.setBorderPainted(false);
		btnGravar.setIcon(new ImageIcon(FrmAgendamento.class.getResource("/resource/img/disk.png")));
		toolBar.add(btnGravar);

		btnExcluir = new JButton("");
		btnExcluir.setBorderPainted(false);
		btnExcluir.setIcon(new ImageIcon(FrmAgendamento.class.getResource("/resource/img/cross.png")));
		toolBar.add(btnExcluir);

		btnRelatorio = new JButton("");
		btnRelatorio.setBorderPainted(false);
		btnRelatorio.setIcon(new ImageIcon(FrmAgendamento.class.getResource("/resource/img/page.png")));
		toolBar.add(btnRelatorio);

		pnlDados = new JPanel();
		pnlDados.setBounds(10, 40, 460, 160);
		pnlDados.setBorder(new TitledBorder(null, "Dados", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		getContentPane().add(pnlDados);
		pnlDados.setLayout(null);

		lblPaciente = new JLabel("Paciente:");
		lblPaciente.setBounds(20, 30, 130, 25);
		pnlDados.add(lblPaciente);

		cbPaciente = new JComboBox<Object>();
		cbPaciente.setEditable(true);
		cbPaciente.setBounds(20, 60, 346, 25);
		pnlDados.add(cbPaciente);

		lblData = new JLabel("Data do Agendamento:");
		lblData.setBounds(20, 90, 130, 25);
		pnlDados.add(lblData);

		ftxtData = new JFormattedTextField(FormUtils.formatarMascaraCampos(ftxtData, MascaraCampo.DATAHORA));
		ftxtData.setBounds(20, 120, 130, 25);
		pnlDados.add(ftxtData);
		ftxtData.setColumns(10);

		pnlRegistros = new JPanel();
		pnlRegistros.setBorder(
				new TitledBorder(null, "Registros", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		pnlRegistros.setBounds(10, 210, 460, 240);
		getContentPane().add(pnlRegistros);
		pnlRegistros.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 63, 420, 160);
		pnlRegistros.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		txtFiltro = new JTextField();
		txtFiltro.setBounds(20, 30, 220, 25);
		pnlRegistros.add(txtFiltro);
		txtFiltro.setColumns(10);

		btnFiltrar = new JButton("");
		btnFiltrar.setIcon(new ImageIcon(FrmAgendamento.class.getResource("/resource/img/find.png")));
		btnFiltrar.setBounds(245, 29, 23, 25);
		pnlRegistros.add(btnFiltrar);
		setVisible(true);
	}

	public JFormattedTextField getFtxtData() {
		return ftxtData;
	}

	public void setFtxtData(JFormattedTextField ftxtData) {
		this.ftxtData = ftxtData;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public JTextField getTxtFiltro() {
		return txtFiltro;
	}

	public void setTxtFiltro(JTextField txtFiltro) {
		this.txtFiltro = txtFiltro;
	}

	public JToolBar getToolBar() {
		return toolBar;
	}

	public void setToolBar(JToolBar toolBar) {
		this.toolBar = toolBar;
	}

	public JButton getBtnInicializar() {
		return btnInicializar;
	}

	public void setBtnInicializar(JButton btnInicializar) {
		this.btnInicializar = btnInicializar;
	}

	public JButton getBtnGravar() {
		return btnGravar;
	}

	public void setBtnGravar(JButton btnGravar) {
		this.btnGravar = btnGravar;
	}

	public JButton getBtnExcluir() {
		return btnExcluir;
	}

	public void setBtnExcluir(JButton btnExcluir) {
		this.btnExcluir = btnExcluir;
	}

	public JButton getBtnRelatorio() {
		return btnRelatorio;
	}

	public void setBtnRelatorio(JButton btnRelatorio) {
		this.btnRelatorio = btnRelatorio;
	}

	public JPanel getPnlDados() {
		return pnlDados;
	}

	public void setPnlDados(JPanel pnlDados) {
		this.pnlDados = pnlDados;
	}

	public JLabel getLblPaciente() {
		return lblPaciente;
	}

	public void setLblPaciente(JLabel lblPaciente) {
		this.lblPaciente = lblPaciente;
	}

	public JComboBox<Object> getCbPaciente() {
		return cbPaciente;
	}

	public void setCbPaciente(JComboBox<Object> cbPaciente) {
		this.cbPaciente = cbPaciente;
	}

	public JLabel getLblData() {
		return lblData;
	}

	public void setLblData(JLabel lblData) {
		this.lblData = lblData;
	}

	public JPanel getPnlRegistros() {
		return pnlRegistros;
	}

	public void setPnlRegistros(JPanel pnlRegistros) {
		this.pnlRegistros = pnlRegistros;
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}

	public JButton getBtnFiltrar() {
		return btnFiltrar;
	}

	public void setBtnFiltrar(JButton btnFiltrar) {
		this.btnFiltrar = btnFiltrar;
	}

}
