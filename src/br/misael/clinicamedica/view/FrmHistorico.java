package br.misael.clinicamedica.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.ButtonGroup;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.JLabel;

public class FrmHistorico extends JInternalFrame {

	private static final long serialVersionUID = 3089016848116712919L;
	private Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	private JPanel panelFiltros; 
	private JPanel panelRegistros;
	private JScrollPane scrollPane;
	private JTable table;
	private JLabel labelNomePaciente;
	private JTextField textNomePaciente;
	private JButton btnFiltrar;
	private JRadioButton rdbtnFinalizada;
	private JRadioButton rdbtnNaoFinalizada;
	private JRadioButton rdbtnTodos;
	private ButtonGroup buttonGroupFinalizada;
	
	public FrmHistorico() {
		setClosable(true);
		setTitle("Hist\u00F3rico");
		setBounds(100, 100, 968, 600);
		setLocation((dimension.width - this.getWidth()) / 2, 50);
		getContentPane().setLayout(null);

		panelFiltros = new JPanel();
		panelFiltros.setBorder(new TitledBorder(null, "Filtros", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		panelFiltros.setBounds(10, 11, 932, 100);
		getContentPane().add(panelFiltros);
		panelFiltros.setLayout(null);
		
		panelRegistros = new JPanel();
		panelRegistros.setBorder(new TitledBorder(null, "Registros", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		panelRegistros.setBounds(10, 122, 932, 437);
		getContentPane().add(panelRegistros);
		panelRegistros.setLayout(null);
		
		 labelNomePaciente = new JLabel("Nome paciente:");
		labelNomePaciente.setBounds(10, 35, 97, 14);
		panelFiltros.add(labelNomePaciente);
		
		textNomePaciente = new JTextField();
		textNomePaciente.setColumns(10);
		textNomePaciente.setBounds(133, 30, 257, 25);
		panelFiltros.add(textNomePaciente);
		
		rdbtnFinalizada = new JRadioButton("Finalizada");
		rdbtnFinalizada.setBounds(443, 30, 109, 23);
		panelFiltros.add(rdbtnFinalizada);
		
		rdbtnNaoFinalizada = new JRadioButton("N\u00E3o finalizada");
		rdbtnNaoFinalizada.setBounds(581, 30, 109, 23);
		panelFiltros.add(rdbtnNaoFinalizada);
		
		rdbtnTodos = new JRadioButton("Todos");
		rdbtnTodos.setSelected(true);
		rdbtnTodos.setBounds(714, 30, 109, 23);
		panelFiltros.add(rdbtnTodos);
		
		btnFiltrar = new JButton("");
		btnFiltrar.setIcon(new ImageIcon(FrmHistorico.class.getResource("/resource/img/find.png")));
		btnFiltrar.setBounds(897, 31, 23, 25);
		panelFiltros.add(btnFiltrar);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 29, 912, 397);
		panelRegistros.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		setVisible(true);

		buttonGroupFinalizada = new ButtonGroup();
		buttonGroupFinalizada.add(rdbtnFinalizada);
		buttonGroupFinalizada.add(rdbtnNaoFinalizada);
		buttonGroupFinalizada.add(rdbtnTodos);
	}

	public Dimension getDimension() {
		return dimension;
	}

	public void setDimension(Dimension dimension) {
		this.dimension = dimension;
	}

	public JPanel getPanel() {
		return panelRegistros;
	}

	public void setPanel(JPanel panel) {
		this.panelRegistros = panel;
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}
	
	public JLabel getLabelNomePaciente() {
		return labelNomePaciente;
	}
	
	public void  setLabelNomePaciente(JLabel labelNomePaciente) {
		this.labelNomePaciente = labelNomePaciente;
	}

	public JTextField getTextNomePaciente() {
		return textNomePaciente;
	}

	public void setTextNomePaciente(JTextField txtFiltro) {
		this.textNomePaciente = txtFiltro;
	}

	public JButton getBtnFiltrar() {
		return btnFiltrar;
	}

	public void setBtnFiltrar(JButton btnFiltrar) {
		this.btnFiltrar = btnFiltrar;
	}

	public JRadioButton getRdbtnFinalizada() {
		return rdbtnFinalizada;
	}

	public void setRdbtnFinalizada(JRadioButton rdbtnFinalizada) {
		this.rdbtnFinalizada = rdbtnFinalizada;
	}

	public JRadioButton getRdbtnNaoFinalizada() {
		return rdbtnNaoFinalizada;
	}

	public void setRdbtnNaoFinalizada(JRadioButton rdbtnNaoFinalizada) {
		this.rdbtnNaoFinalizada = rdbtnNaoFinalizada;
	}

	public JRadioButton getRdbtnTodos() {
		return rdbtnTodos;
	}

	public void setRdbtnTodos(JRadioButton rdbtnTodos) {
		this.rdbtnTodos = rdbtnTodos;
	}
}
