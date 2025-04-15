package br.misael.clinicamedica.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import br.misael.clinicamedica.model.FormUtils;
import br.misael.clinicamedica.model.MascaraCampo;

public class FrmPaciente extends JInternalFrame {
	
	private static final long serialVersionUID = -6805869000433143815L;
	private JToolBar toolBar;
	private JLabel lblNome;
	private JLabel lblDataNascimento;
	private JLabel lblSexo;
	private JLabel lblEndereco;
	private JLabel lblTelefone;
	private JLabel lblAtivo;
	private JLabel lblFoto;
	private JLabel lblContadorRegistros;
	private JTextField txtNome;
	private JFormattedTextField ftxtDataNascimento;
	private JTextField txtFiltro;
	private JTextField txtEndereco;
	private JRadioButton rdbtnMasculino;
	private JRadioButton rdbtnFeminino;
	private JRadioButton rdbtnTodos;
	private JRadioButton rdbtnAtivo;
	private JRadioButton rdbtnInativo;
	private ButtonGroup buttonGroupSexo;
	private ButtonGroup buttonGroupAtivo;
	private JFormattedTextField ftxtTelefone;
	private JCheckBox chckbxAtivo;
	private JButton btnInicializar;
	private JButton btnGravar;
	private JButton btnExcluir;
	private JButton btnFiltrar;
	private JButton btnRelatorio;
	private JButton btnCarregar;
	private JTable table;
	private JScrollPane scrollPane;
	private JPanel pnlDados;
	private JPanel pnlRegistros;
	private JPanel pnlFoto;
	private Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	
	/**
	 * Create the frame.
	 */
	public FrmPaciente() {
		
		setClosable(true);
		setTitle("Paciente");
		setBounds(0, 0, 865, 649);
		//setLocation((dimension.width - this.getWidth()) / 2, (dimension.height - this.getHeight()) / 2);
		setLocation((dimension.width - this.getWidth()) / 2, 50);
		
		getContentPane().setLayout(null);
		
		toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.setBounds(0, 0, 849, 30);
		getContentPane().add(toolBar);
		
		btnInicializar = new JButton("");
		btnInicializar.setBorderPainted(false);
		btnInicializar.setIcon(new ImageIcon(FrmPaciente.class.getResource("/resource/img/page_white.png")));
		toolBar.add(btnInicializar);
		
		btnGravar = new JButton("");
		btnGravar.setBorderPainted(false);
		btnGravar.setIcon(new ImageIcon(FrmPaciente.class.getResource("/resource/img/disk.png")));
		toolBar.add(btnGravar);
		
		btnExcluir = new JButton("");
		btnExcluir.setBorderPainted(false);
		btnExcluir.setIcon(new ImageIcon(FrmPaciente.class.getResource("/resource/img/cross.png")));
		toolBar.add(btnExcluir);
		
		btnRelatorio = new JButton("");
		btnRelatorio.setBorderPainted(false);
		toolBar.add(btnRelatorio);
		btnRelatorio.setIcon(new ImageIcon(FrmPaciente.class.getResource("/resource/img/page.png")));
		
		pnlDados = new JPanel();
		pnlDados.setBorder(new TitledBorder(null, "Dados", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		pnlDados.setBounds(10, 41, 829, 242);
		getContentPane().add(pnlDados);
		pnlDados.setLayout(null);
		
		lblNome = new JLabel("Nome:");
		lblNome.setBounds(20, 30, 60, 25);
		pnlDados.add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setBounds(20, 60, 351, 25);
		txtNome.setColumns(10);
		pnlDados.add(txtNome);
		
		chckbxAtivo = new JCheckBox("");
		chckbxAtivo.setHorizontalAlignment(SwingConstants.LEFT);
		chckbxAtivo.setBounds(490, 150, 26, 25);
		pnlDados.add(chckbxAtivo);
		
		lblAtivo = new JLabel("Ativo?");
		lblAtivo.setBounds(450, 150, 46, 25);
		pnlDados.add(lblAtivo);
		
		lblSexo = new JLabel("Sexo:");
		lblSexo.setBounds(20, 90, 60, 25);
		pnlDados.add(lblSexo);
		
		rdbtnMasculino = new JRadioButton("Masculino");
		rdbtnMasculino.setSelected(true);
		rdbtnMasculino.setBounds(20, 120, 109, 25);
		pnlDados.add(rdbtnMasculino);
		
		rdbtnFeminino = new JRadioButton("Feminino");
		rdbtnFeminino.setBounds(130, 120, 109, 25);
		pnlDados.add(rdbtnFeminino);
		
		buttonGroupSexo = new ButtonGroup();
		buttonGroupSexo.add(rdbtnMasculino);
		buttonGroupSexo.add(rdbtnFeminino);

		lblEndereco = new JLabel("Endere\u00E7o:");
		lblEndereco.setBounds(20, 150, 60, 25);
		pnlDados.add(lblEndereco);
		
		txtEndereco = new JTextField();
		txtEndereco.setBounds(20, 180, 351, 25);
		pnlDados.add(txtEndereco);
		txtEndereco.setColumns(10);
		
		lblTelefone = new JLabel("Telefone:");
		lblTelefone.setBounds(450, 90, 60, 25);
		pnlDados.add(lblTelefone);
		
		ftxtTelefone = new JFormattedTextField(FormUtils.formatarMascaraCampos(ftxtTelefone, MascaraCampo.TELEFONE));
		ftxtTelefone.setBounds(450, 120, 100, 25);
		pnlDados.add(ftxtTelefone);
		
		lblDataNascimento = new JLabel("Data Nascimento:");
		lblDataNascimento.setBounds(450, 30, 118, 25);
		pnlDados.add(lblDataNascimento);
		
		ftxtDataNascimento = new JFormattedTextField(FormUtils.formatarMascaraCampos(ftxtDataNascimento, MascaraCampo.DATAHORA));
		ftxtDataNascimento.setBounds(450, 60, 130, 25);
		pnlDados.add(ftxtDataNascimento);
		
		lblFoto = new JLabel("Foto:");
		lblFoto.setBounds(638, 30, 46, 25);
		pnlDados.add(lblFoto);
		
		pnlFoto = new JPanel();
		pnlFoto.setToolTipText("");
		pnlFoto.setBackground(Color.GRAY);
		pnlFoto.setBounds(638, 60, 160, 160);
		pnlDados.add(pnlFoto);
		pnlFoto.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		
		btnCarregar = new JButton("Carregar");
		btnCarregar.setBounds(706, 31, 89, 23);
		pnlDados.add(btnCarregar);
		
		pnlRegistros = new JPanel();
		pnlRegistros.setForeground(Color.BLACK);
		pnlRegistros.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Registros", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		pnlRegistros.setBounds(10, 294, 829, 314);
		getContentPane().add(pnlRegistros);
		pnlRegistros.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 100, 786, 203);
		pnlRegistros.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		btnFiltrar = new JButton("");
		btnFiltrar.setIcon(new ImageIcon(FrmPaciente.class.getResource("/resource/img/find.png")));
		btnFiltrar.setBounds(615, 30, 23, 25);
		pnlRegistros.add(btnFiltrar);
		
		txtFiltro = new JTextField();
		txtFiltro.setBounds(20, 30, 220, 25);
		pnlRegistros.add(txtFiltro);
		txtFiltro.setColumns(10);
		
		rdbtnTodos = new JRadioButton("Todos");
		rdbtnTodos.setBounds(505, 30, 100, 23);
		pnlRegistros.add(rdbtnTodos);
		
		rdbtnAtivo = new JRadioButton("Ativo");
		rdbtnAtivo.setSelected(true);
		rdbtnAtivo.setBounds(268, 30, 100, 23);
		pnlRegistros.add(rdbtnAtivo);
		
		rdbtnInativo = new JRadioButton("Inativo");
		rdbtnInativo.setBounds(387, 30, 100, 23);
		pnlRegistros.add(rdbtnInativo);

		buttonGroupAtivo = new ButtonGroup();
		buttonGroupAtivo.add(rdbtnAtivo);
		buttonGroupAtivo.add(rdbtnInativo);
		buttonGroupAtivo.add(rdbtnTodos);
		
		lblContadorRegistros = new JLabel("0 registro(s)");
		lblContadorRegistros.setBounds(20, 75, 220, 14);
		pnlRegistros.add(lblContadorRegistros);
		
		setVisible(true);
		
	}

	public JToolBar getToolBar() {
		return toolBar;
	}

	public void setToolBar(JToolBar toolBar) {
		this.toolBar = toolBar;
	}

	public JLabel getLblNome() {
		return lblNome;
	}

	public void setLblNome(JLabel lblNome) {
		this.lblNome = lblNome;
	}

	public JLabel getLblDataNascimento() {
		return lblDataNascimento;
	}

	public void setLblDataNascimento(JLabel lblDataNascimento) {
		this.lblDataNascimento = lblDataNascimento;
	}

	public JLabel getLblSexo() {
		return lblSexo;
	}

	public void setLblSexo(JLabel lblSexo) {
		this.lblSexo = lblSexo;
	}

	public JLabel getLblEndereco() {
		return lblEndereco;
	}

	public void setLblEndereco(JLabel lblEndereco) {
		this.lblEndereco = lblEndereco;
	}

	public JLabel getLblTelefone() {
		return lblTelefone;
	}

	public void setLblTelefone(JLabel lblTelefone) {
		this.lblTelefone = lblTelefone;
	}

	public JLabel getLblAtivo() {
		return lblAtivo;
	}

	public void setLblAtivo(JLabel lblAtivo) {
		this.lblAtivo = lblAtivo;
	}

	public JLabel getLblFoto() {
		return lblFoto;
	}

	public void setLblFoto(JLabel lblFoto) {
		this.lblFoto = lblFoto;
	}
	
	public JLabel getLblContadorRegistros() {
		return lblContadorRegistros;
	}
	
	public void setLblContadorRegistros(JLabel lblContadorRegistros) {
		this.lblContadorRegistros = lblContadorRegistros;
	}

	public JTextField getTxtNome() {
		return txtNome;
	}

	public void setTxtNome(JTextField txtNome) {
		this.txtNome = txtNome;
	}

	public JFormattedTextField getFtxtDataNascimento() {
		return ftxtDataNascimento;
	}

	public void setFtxtDataNascimento(JFormattedTextField ftxtDataNascimento) {
		this.ftxtDataNascimento = ftxtDataNascimento;
	}

	public JTextField getTxtFiltro() {
		return txtFiltro;
	}

	public void setTxtFiltro(JTextField txtFiltro) {
		this.txtFiltro = txtFiltro;
	}

	public JTextField getTxtEndereco() {
		return txtEndereco;
	}

	public void setTxtEndereco(JTextField txtEndereco) {
		this.txtEndereco = txtEndereco;
	}

	public JRadioButton getRdbtnMasculino() {
		return rdbtnMasculino;
	}

	public void setRdbtnMasculino(JRadioButton rdbtnMasculino) {
		this.rdbtnMasculino = rdbtnMasculino;
	}

	public JRadioButton getRdbtnFeminino() {
		return rdbtnFeminino;
	}

	public void setRdbtnFeminino(JRadioButton rdbtnFeminino) {
		this.rdbtnFeminino = rdbtnFeminino;
	}

	public ButtonGroup getButtonGroupSexo() {
		return buttonGroupSexo;
	}

	public void setButtonGroupSexo(ButtonGroup buttonGroupSexo) {
		this.buttonGroupSexo = buttonGroupSexo;
	}
	
	public ButtonGroup getButtonGroupAtivo() {
		return buttonGroupAtivo;
	}
	
	public void setButtonGroupAtivo(ButtonGroup buttonGroupAtivo) {
		this.buttonGroupAtivo = buttonGroupAtivo;
	}

	public JFormattedTextField getFtxtTelefone() {
		return ftxtTelefone;
	}

	public void setFtxtTelefone(JFormattedTextField ftxtTelefone) {
		this.ftxtTelefone = ftxtTelefone;
	}

	public JCheckBox getChckbxAtivo() {
		return chckbxAtivo;
	}

	public void setChckbxAtivo(JCheckBox chckbxAtivo) {
		this.chckbxAtivo = chckbxAtivo;
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

	public JButton getBtnFiltrar() {
		return btnFiltrar;
	}

	public void setBtnFiltrar(JButton btnFiltrar) {
		this.btnFiltrar = btnFiltrar;
	}

	public JButton getBtnRelatorio() {
		return btnRelatorio;
	}

	public void setBtnRelatorio(JButton btnRelatorio) {
		this.btnRelatorio = btnRelatorio;
	}
	
	public JButton getBtnCarregar() {
		return btnCarregar;
	}
	
	public void setBtnCarregar(JButton btnCarregar) {
		this.btnCarregar = btnCarregar;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
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

	public JPanel getPnlFoto() {
		return pnlFoto;
	}

	public void setPnlFoto(JPanel pnlFoto) {
		this.pnlFoto = pnlFoto;
	}

	public JRadioButton getRdbtnTodos() {
		return rdbtnTodos;
	}

	public void setRdbtnTodos(JRadioButton rdbtnTodos) {
		this.rdbtnTodos = rdbtnTodos;
	}

	public JRadioButton getRdbtnAtivo() {
		return rdbtnAtivo;
	}

	public void setRdbtnAtivo(JRadioButton rdbtnAtivo) {
		this.rdbtnAtivo = rdbtnAtivo;
	}

	public JRadioButton getRdbtnInativo() {
		return rdbtnInativo;
	}

	public void setRdbtnInativo(JRadioButton rdbtnInativo) {
		this.rdbtnInativo = rdbtnInativo;
	}
}
