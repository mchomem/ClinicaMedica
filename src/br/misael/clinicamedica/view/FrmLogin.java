package br.misael.clinicamedica.view;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class FrmLogin extends JFrame {
	private static final long serialVersionUID = 1L;
	private Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	private JPanel contentPane;
	private JLabel labelImage;
	private JLabel lblLogin;
	private JLabel lblPassword;
	private JTextField textFieldLogin;
	private JPasswordField passwordField;
	private JButton btnAcessar;
	private JButton btnCancelar;

	public FrmLogin() {
		setTitle("Clínica Médica - Login");
		setAlwaysOnTop(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		this.setLocation((this.dimension.width - this.getWidth()) / 2, (this.dimension.height - this.getHeight()) / 2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		super.setContentPane(contentPane);
		contentPane.setLayout(null);

		lblLogin = new JLabel("Login:");
		lblLogin.setBounds(93, 154, 46, 14);
		contentPane.add(lblLogin);

		lblPassword = new JLabel("Senha:");
		lblPassword.setBounds(93, 185, 46, 14);
		contentPane.add(lblPassword);

		passwordField = new JPasswordField();
		passwordField.setBounds(139, 182, 195, 20);
		contentPane.add(passwordField);

		textFieldLogin = new JTextField();
		textFieldLogin.setBounds(139, 151, 195, 20);
		contentPane.add(textFieldLogin);
		textFieldLogin.setColumns(10);

		btnAcessar = new JButton("Acessar");
		btnAcessar.setBounds(93, 210, 111, 23);
		contentPane.add(btnAcessar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(223, 210, 111, 23);
		contentPane.add(btnCancelar);

		labelImage = new JLabel("");
		labelImage.setIcon(new ImageIcon(FrmLogin.class.getResource("/resource/img/login.png")));
		labelImage.setBounds(10, 11, 414, 120);
		contentPane.add(labelImage);
	}

	public JPanel getContentPane() {
		return contentPane;
	}

	public void setContentPane(JPanel contentPane) {
		this.contentPane = contentPane;
	}

	public JLabel getLabelImage() {
		return this.labelImage;
	}

	public void setLabelImage(JLabel labelImage) {
		this.labelImage = labelImage;
	}

	public JLabel getLblLogin() {
		return lblLogin;
	}

	public void setLblLogin(JLabel lblLogin) {
		this.lblLogin = lblLogin;
	}

	public JLabel getLblPassword() {
		return lblPassword;
	}

	public void setLblPassword(JLabel lblPassword) {
		this.lblPassword = lblPassword;
	}

	public JTextField getTextFieldLogin() {
		return textFieldLogin;
	}

	public void setTextFieldLogin(JTextField textFieldLogin) {
		this.textFieldLogin = textFieldLogin;
	}

	public JPasswordField getPasswordField() {
		return passwordField;
	}

	public void setPasswordField(JPasswordField passwordField) {
		this.passwordField = passwordField;
	}

	public JButton getBtnAcessar() {
		return btnAcessar;
	}

	public void setBtnAcessar(JButton btnAcessar) {
		this.btnAcessar = btnAcessar;
	}

	public JButton getBtnCancelar() {
		return this.btnCancelar;
	}

	public void setBtnCancelar(JButton btnCancelar) {
		this.btnCancelar = btnCancelar;
	}
}
