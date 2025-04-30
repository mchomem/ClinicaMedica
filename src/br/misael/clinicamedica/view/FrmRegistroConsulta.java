package br.misael.clinicamedica.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;

public class FrmRegistroConsulta extends JInternalFrame {

	private static final long serialVersionUID = -8226510820116669329L;
	private JPanel pnlDados;
	private JPanel pnlRegistros;
	private JToolBar toolBar;
	private JButton btnInicializar;
	private JButton btnGravar;
	private JButton btnExcluir;
	private JButton btnAdicionar;
	private JButton btnRemover;
	private JButton btnFiltrar;
	private JTable table;
	private JTable tableReceituario;
	private JLabel lblAgendamento;
	private JComboBox<Object> cbAgedamento;
	private JComboBox<Object> cbMedicamento;
	private JLabel lblProntuario;
	private JScrollPane scrollPaneProntuario;
	private JScrollPane scrollPaneTable;
	private JTextArea txtaProntuario;
	private JCheckBox chckbxConsultaFinalizada;
	private JLabel lblConsultaFinalizada;
	private Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	private JTextField txtFiltro;
	private JLabel lblReceituario;
	private JLabel lblMedicamento;

	/**
	 * Create the frame.
	 */
	public FrmRegistroConsulta() {

		setClosable(true);
		setTitle("Registro de Consulta");
		setBounds(100, 100, 929, 622);
		// setLocation((dimension.width - this.getWidth()) / 2, (dimension.height -
		// this.getHeight()) / 2 );
		setLocation((dimension.width - this.getWidth()) / 2, 50);
		getContentPane().setLayout(null);

		toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.setBounds(0, 0, 913, 30);
		getContentPane().add(toolBar);

		btnInicializar = new JButton("");
		btnInicializar.setBorderPainted(false);
		btnInicializar.setIcon(new ImageIcon(FrmRegistroConsulta.class.getResource("/resource/img/page_white.png")));
		toolBar.add(btnInicializar);

		btnGravar = new JButton("");
		btnGravar.setIcon(new ImageIcon(FrmRegistroConsulta.class.getResource("/resource/img/disk.png")));
		btnGravar.setBorderPainted(false);
		toolBar.add(btnGravar);

		btnExcluir = new JButton("");
		btnExcluir.setBorderPainted(false);
		btnExcluir.setIcon(new ImageIcon(FrmRegistroConsulta.class.getResource("/resource/img/cross.png")));
		toolBar.add(btnExcluir);

		pnlDados = new JPanel();
		pnlDados.setBorder(new TitledBorder(null, "Dados", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		pnlDados.setBounds(10, 41, 893, 283);
		getContentPane().add(pnlDados);
		pnlDados.setLayout(null);

		lblAgendamento = new JLabel("Agendamento:");
		lblAgendamento.setBounds(10, 30, 104, 25);
		pnlDados.add(lblAgendamento);

		cbAgedamento = new JComboBox<Object>();
		cbAgedamento.setEditable(true);
		cbAgedamento.setBounds(124, 30, 383, 25);
		pnlDados.add(cbAgedamento);

		lblProntuario = new JLabel("Prontu\u00E1rio:");
		lblProntuario.setBounds(10, 70, 104, 25);
		pnlDados.add(lblProntuario);

		scrollPaneProntuario = new JScrollPane();
		scrollPaneProntuario.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneProntuario.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPaneProntuario.setBounds(124, 70, 678, 76);
		pnlDados.add(scrollPaneProntuario);

		txtaProntuario = new JTextArea();
		scrollPaneProntuario.setViewportView(txtaProntuario);

		chckbxConsultaFinalizada = new JCheckBox("");
		chckbxConsultaFinalizada.setBounds(662, 30, 21, 25);
		pnlDados.add(chckbxConsultaFinalizada);

		lblConsultaFinalizada = new JLabel("Consulta Finalizada?");
		lblConsultaFinalizada.setBounds(537, 30, 120, 25);
		pnlDados.add(lblConsultaFinalizada);

		lblReceituario = new JLabel("Receitu\u00E1rio:");
		lblReceituario.setBounds(398, 165, 87, 25);
		pnlDados.add(lblReceituario);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(491, 165, 311, 107);
		pnlDados.add(scrollPane);

		tableReceituario = new JTable();
		scrollPane.setViewportView(tableReceituario);

		lblMedicamento = new JLabel("Medicamento:");
		lblMedicamento.setBounds(13, 165, 101, 25);
		pnlDados.add(lblMedicamento);

		cbMedicamento = new JComboBox<Object>();
		cbMedicamento.setBounds(124, 163, 151, 28);
		pnlDados.add(cbMedicamento);

		btnAdicionar = new JButton("");
		btnAdicionar.setIcon(new ImageIcon(FrmRegistroConsulta.class.getResource("/resource/img/add.png")));
		btnAdicionar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAdicionar.setMargin(new Insets(0, 0, 0, 0));
		btnAdicionar.setBounds(288, 165, 25, 23);
		pnlDados.add(btnAdicionar);

		btnRemover = new JButton("");
		btnRemover.setIcon(new ImageIcon(FrmRegistroConsulta.class.getResource("/resource/img/delete.png")));
		btnRemover.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnRemover.setMargin(new Insets(0, 0, 0, 0));
		btnRemover.setBounds(323, 165, 25, 23);
		pnlDados.add(btnRemover);

		pnlRegistros = new JPanel();
		pnlRegistros.setBorder(
				new TitledBorder(null, "Registros", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		pnlRegistros.setBounds(10, 335, 893, 246);
		getContentPane().add(pnlRegistros);
		pnlRegistros.setLayout(null);

		scrollPaneTable = new JScrollPane();
		scrollPaneTable.setBounds(20, 56, 852, 179);
		pnlRegistros.add(scrollPaneTable);

		table = new JTable();
		scrollPaneTable.setViewportView(table);

		btnFiltrar = new JButton("");
		btnFiltrar.setIcon(new ImageIcon(FrmRegistroConsulta.class.getResource("/resource/img/find.png")));
		btnFiltrar.setBounds(205, 25, 23, 23);
		pnlRegistros.add(btnFiltrar);

		txtFiltro = new JTextField();
		txtFiltro.setBounds(20, 25, 175, 25);
		pnlRegistros.add(txtFiltro);
		txtFiltro.setColumns(10);

		setVisible(true);

	}

	public JPanel getPnlDados() {
		return pnlDados;
	}

	public void setPnlDados(JPanel pnlDados) {
		this.pnlDados = pnlDados;
	}

	public JPanel getPnlRegistros() {
		return pnlRegistros;
	}

	public void setPnlRegistros(JPanel pnlRegistros) {
		this.pnlRegistros = pnlRegistros;
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

	public JButton getBtnAdicionar() {
		return btnAdicionar;
	}

	public void setBtnAdicionar(JButton btnAdicionar) {
		this.btnAdicionar = btnAdicionar;
	}

	public JButton getBtnRemover() {
		return btnRemover;
	}

	public void setBtnRemover(JButton btnRemover) {
		this.btnRemover = btnRemover;
	}

	public JButton getBtnFiltrar() {
		return btnFiltrar;
	}

	public void setBtnFiltrar(JButton btnFiltrar) {
		this.btnFiltrar = btnFiltrar;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public JTable getTableReceituario() {
		return tableReceituario;
	}

	public void setTableReceituario(JTable tableReceituario) {
		this.tableReceituario = tableReceituario;
	}

	public JLabel getLblAgendamento() {
		return lblAgendamento;
	}

	public void setLblAgendamento(JLabel lblAgendamento) {
		this.lblAgendamento = lblAgendamento;
	}

	public JComboBox<Object> getCbAgedamento() {
		return cbAgedamento;
	}

	public void setCbAgedamento(JComboBox<Object> cbAgedamento) {
		this.cbAgedamento = cbAgedamento;
	}

	public JComboBox<Object> getCbMedicamento() {
		return cbMedicamento;
	}

	public void setCbMedicamento(JComboBox<Object> cbMedicamento) {
		this.cbMedicamento = cbMedicamento;
	}

	public JLabel getLblProntuario() {
		return lblProntuario;
	}

	public void setLblProntuario(JLabel lblProntuario) {
		this.lblProntuario = lblProntuario;
	}

	public JScrollPane getScrollPaneProntuario() {
		return scrollPaneProntuario;
	}

	public void setScrollPaneProntuario(JScrollPane scrollPaneProntuario) {
		this.scrollPaneProntuario = scrollPaneProntuario;
	}

	public JScrollPane getScrollPaneTable() {
		return scrollPaneTable;
	}

	public void setScrollPaneTable(JScrollPane scrollPaneTable) {
		this.scrollPaneTable = scrollPaneTable;
	}

	public JTextArea getTxtaProntuario() {
		return txtaProntuario;
	}

	public void setTxtaProntuario(JTextArea txtaProntuario) {
		this.txtaProntuario = txtaProntuario;
	}

	public JCheckBox getChckbxConsultaFinalizada() {
		return chckbxConsultaFinalizada;
	}

	public void setChckbxConsultaFinalizada(JCheckBox chckbxConsultaFinalizada) {
		this.chckbxConsultaFinalizada = chckbxConsultaFinalizada;
	}

	public JLabel getLblConsultaFinalizada() {
		return lblConsultaFinalizada;
	}

	public void setLblConsultaFinalizada(JLabel lblConsultaFinalizada) {
		this.lblConsultaFinalizada = lblConsultaFinalizada;
	}

	public JTextField getTxtFiltro() {
		return txtFiltro;
	}

	public void setTxtFiltro(JTextField txtFiltro) {
		this.txtFiltro = txtFiltro;
	}
}
