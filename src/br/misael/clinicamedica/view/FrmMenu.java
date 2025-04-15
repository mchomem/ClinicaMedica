package br.misael.clinicamedica.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

public class FrmMenu extends JFrame {

	private static final long serialVersionUID = -1531658634942162353L;
	private JPanel pnl;
	private JMenuBar mnbMenu;
	private JDesktopPane desktopPane;
	private JMenu mnSistema;
	private JMenu mnCadastros;
	private JMenu mnConsultas;
	private JMenuItem mntmSair;
	private JMenuItem mntmPaciente;
	private JMenuItem mntmMedicamento;
	private JMenuItem mntmAgendamento;
	private JSeparator separator;
	private JMenuItem mntmRegistroDeConsulta;
	private JMenuItem mntmHistrico;
	private Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

	/**
	 * Create the frame.
	 */
	public FrmMenu() {
		
		setTitle("Cl\u00EDnica m\u00E9dica");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, dimension.width, dimension.height);
		pnl = new JPanel();
		pnl.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pnl);
		pnl.setLayout(null);
		
		desktopPane = new JDesktopPane();
		desktopPane.setBounds(0, 30, this.getWidth(), this.getHeight());
		desktopPane.setBackground(Color.GRAY);
		pnl.add(desktopPane);
		
		mnbMenu = new JMenuBar();
		mnbMenu.setBounds(0, 0, this.getWidth(), 30);
		pnl.add(mnbMenu);
		
		mnSistema = new JMenu("Sistema");
		mnbMenu.add(mnSistema);
		
		mntmSair = new JMenuItem("Sair");
		mnSistema.add(mntmSair);
		
		mnCadastros = new JMenu("Cadastros");
		mnbMenu.add(mnCadastros);
		
		mntmPaciente = new JMenuItem("Paciente");
		mnCadastros.add(mntmPaciente);
		
		mntmMedicamento = new JMenuItem("Medicamento");
		mnCadastros.add(mntmMedicamento);
		
		mntmAgendamento = new JMenuItem("Agendamento");
		mnCadastros.add(mntmAgendamento);
		
		separator = new JSeparator();
		mnCadastros.add(separator);
		
		mntmRegistroDeConsulta = new JMenuItem("Registro de Consulta");
		mnCadastros.add(mntmRegistroDeConsulta);
		
		mnConsultas = new JMenu("Consultas");
		mnbMenu.add(mnConsultas);
		
		mntmHistrico = new JMenuItem("Hist\u00F3rico");
		mnConsultas.add(mntmHistrico);
		
		setVisible(true);
	}

	public JPanel getPnl() {
		return pnl;
	}

	public void setPnl(JPanel pnl) {
		this.pnl = pnl;
	}

	public JMenuBar getMnbMenu() {
		return mnbMenu;
	}

	public void setMnbMenu(JMenuBar mnbMenu) {
		this.mnbMenu = mnbMenu;
	}

	public JDesktopPane getDesktopPane() {
		return desktopPane;
	}

	public void setDesktopPane(JDesktopPane desktopPane) {
		this.desktopPane = desktopPane;
	}

	public JMenu getMnSistema() {
		return mnSistema;
	}

	public void setMnSistema(JMenu mnSistema) {
		this.mnSistema = mnSistema;
	}

	public JMenu getMnCadastros() {
		return mnCadastros;
	}

	public void setMnCadastros(JMenu mnCadastros) {
		this.mnCadastros = mnCadastros;
	}

	public JMenu getMnConsultas() {
		return mnConsultas;
	}

	public void setMnConsultas(JMenu mnConsultas) {
		this.mnConsultas = mnConsultas;
	}

	public JMenuItem getMntmSair() {
		return mntmSair;
	}

	public void setMntmSair(JMenuItem mntmSair) {
		this.mntmSair = mntmSair;
	}

	public JMenuItem getMntmPaciente() {
		return mntmPaciente;
	}

	public void setMntmPaciente(JMenuItem mntmPaciente) {
		this.mntmPaciente = mntmPaciente;
	}

	public JMenuItem getMntmMedicamento() {
		return mntmMedicamento;
	}

	public void setMntmMedicamento(JMenuItem mntmMedicamento) {
		this.mntmMedicamento = mntmMedicamento;
	}

	public JMenuItem getMntmAgendamento() {
		return mntmAgendamento;
	}

	public void setMntmAgendamento(JMenuItem mntmAgendamento) {
		this.mntmAgendamento = mntmAgendamento;
	}

	public JSeparator getSeparator() {
		return separator;
	}

	public void setSeparator(JSeparator separator) {
		this.separator = separator;
	}

	public JMenuItem getMntmRegistroDeConsulta() {
		return mntmRegistroDeConsulta;
	}

	public void setMntmRegistroDeConsulta(JMenuItem mntmRegistroDeConsulta) {
		this.mntmRegistroDeConsulta = mntmRegistroDeConsulta;
	}

	public JMenuItem getMntmHistrico() {
		return mntmHistrico;
	}

	public void setMntmHistrico(JMenuItem mntmHistrico) {
		this.mntmHistrico = mntmHistrico;
	}

}
