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

public class FrmHistorico extends JInternalFrame {

	private static final long serialVersionUID = 3089016848116712919L;
	private Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	private JPanel panel;
	private JScrollPane scrollPane;
	private JTable table;
	private JTextField txtFiltro;
	private JButton btnFiltrar;
	private JRadioButton rdbtnFinalizada;
	private JRadioButton rdbtnNaoFinalizada;
	private JRadioButton rdbtnTodos;
	private ButtonGroup buttonGroupFinalizada;

	/**
	 * Create the frame.
	 */
	public FrmHistorico() {

		setClosable(true);
		setTitle("Hist\u00F3rico");
		setBounds(100, 100, 968, 600);
		// setLocation((dimension.width - this.getWidth()) / 2, (dimension.height -
		// this.getHeight()) / 2 );
		setLocation((dimension.width - this.getWidth()) / 2, 50);
		getContentPane().setLayout(null);

		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Registros", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		panel.setBounds(10, 11, 932, 548);
		getContentPane().add(panel);
		panel.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 66, 912, 471);
		panel.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		txtFiltro = new JTextField();
		txtFiltro.setBounds(10, 31, 205, 25);
		panel.add(txtFiltro);
		txtFiltro.setColumns(10);

		btnFiltrar = new JButton("");
		btnFiltrar.setIcon(new ImageIcon(FrmHistorico.class.getResource("/resource/img/find.png")));
		btnFiltrar.setBounds(899, 31, 23, 25);
		panel.add(btnFiltrar);

		rdbtnFinalizada = new JRadioButton("Finalizada");
		rdbtnFinalizada.setBounds(274, 32, 109, 23);
		panel.add(rdbtnFinalizada);

		rdbtnNaoFinalizada = new JRadioButton("N\u00E3o finalizada");
		rdbtnNaoFinalizada.setBounds(412, 32, 109, 23);
		panel.add(rdbtnNaoFinalizada);

		rdbtnTodos = new JRadioButton("Todos");
		rdbtnTodos.setSelected(true);
		rdbtnTodos.setBounds(545, 32, 109, 23);
		panel.add(rdbtnTodos);
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
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
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

	public JTextField getTxtFiltro() {
		return txtFiltro;
	}

	public void setTxtFiltro(JTextField txtFiltro) {
		this.txtFiltro = txtFiltro;
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
