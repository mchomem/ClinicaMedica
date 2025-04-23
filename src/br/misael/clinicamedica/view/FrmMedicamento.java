package br.misael.clinicamedica.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

public class FrmMedicamento extends JInternalFrame {

	private static final long serialVersionUID = -5961540043166924987L;
	private JTextField txtNome;
	private JTable table;
	private JToolBar toolBar;
	private JButton btnInicializar;
	private JButton btnGravar;
	private JButton btnExcluir;
	private JButton btnRelatorio;
	private JPanel pnlDados;
	private JLabel lblNome;
	private JPanel pnlRegistros;
	private JScrollPane scrollPane;
	private JButton btnFiltrar;
	private JTextField txtFiltro;
	private Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

	/**
	 * Create the frame.
	 */
	public FrmMedicamento() {
		setClosable(true);
		setTitle("Medicamento");
		setBounds(0, 0, 466, 422);
		// setLocation((dimension.width - this.getWidth()) / 2, (dimension.height -
		// this.getHeight()) / 2 );
		setLocation((dimension.width - this.getWidth()) / 2, 50);
		getContentPane().setLayout(null);

		toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.setBounds(0, 0, 450, 30);
		getContentPane().add(toolBar);

		btnInicializar = new JButton("");
		btnInicializar.setBorderPainted(false);
		btnInicializar.setIcon(new ImageIcon(FrmMedicamento.class.getResource("/resource/img/page_white.png")));
		toolBar.add(btnInicializar);

		btnGravar = new JButton("");
		btnGravar.setBorderPainted(false);
		btnGravar.setIcon(new ImageIcon(FrmMedicamento.class.getResource("/resource/img/disk.png")));
		toolBar.add(btnGravar);

		btnExcluir = new JButton("");
		btnExcluir.setBorderPainted(false);
		btnExcluir.setIcon(new ImageIcon(FrmMedicamento.class.getResource("/resource/img/cross.png")));
		toolBar.add(btnExcluir);

		btnRelatorio = new JButton("");
		btnRelatorio.setBorderPainted(false);
		btnRelatorio.setIcon(new ImageIcon(FrmMedicamento.class.getResource("/resource/img/page.png")));
		toolBar.add(btnRelatorio);

		pnlDados = new JPanel();
		pnlDados.setBorder(new TitledBorder(null, "Dados", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		pnlDados.setBounds(10, 40, 430, 100);
		getContentPane().add(pnlDados);
		pnlDados.setLayout(null);

		lblNome = new JLabel("Nome:");
		lblNome.setBounds(20, 30, 46, 25);
		pnlDados.add(lblNome);

		txtNome = new JTextField();
		txtNome.setBounds(20, 60, 360, 25);
		pnlDados.add(txtNome);
		txtNome.setColumns(10);

		pnlRegistros = new JPanel();
		pnlRegistros.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Registros",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		pnlRegistros.setBounds(10, 150, 430, 234);
		getContentPane().add(pnlRegistros);
		pnlRegistros.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 63, 390, 160);
		pnlRegistros.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		txtFiltro = new JTextField();
		txtFiltro.setBounds(20, 30, 220, 25);
		pnlRegistros.add(txtFiltro);
		txtFiltro.setColumns(10);

		btnFiltrar = new JButton("");
		btnFiltrar.setIcon(new ImageIcon(FrmMedicamento.class.getResource("/resource/img/find.png")));
		btnFiltrar.setBounds(245, 29, 23, 25);
		pnlRegistros.add(btnFiltrar);
		setVisible(true);
	}

	public JTextField getTxtNome() {
		return txtNome;
	}

	public void setTxtNome(JTextField txtNome) {
		this.txtNome = txtNome;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
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

	public JLabel getLblNome() {
		return lblNome;
	}

	public void setLblNome(JLabel lblNome) {
		this.lblNome = lblNome;
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

	public JTextField getTxtFiltro() {
		return txtFiltro;
	}

	public void setTxtFiltro(JTextField txtFiltro) {
		this.txtFiltro = txtFiltro;
	}

}
