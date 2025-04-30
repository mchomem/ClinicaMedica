package br.misael.clinicamedica.controller;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import br.misael.clinicamedica.model.Utils;
import br.misael.clinicamedica.model.dao.DaoUsuario;
import br.misael.clinicamedica.model.vo.VoUsuario;
import br.misael.clinicamedica.view.FrmLogin;

public class CtrLogin implements ActionListener, KeyListener {
	private FrmLogin frmLogin;
	private DaoUsuario daoUsuario;

	public CtrLogin() {
		this.daoUsuario = new DaoUsuario();
		this.frmLogin = new FrmLogin();
		this.frmLogin.setVisible(true);
		this.frmLogin.getBtnAcessar().addActionListener(this);
		this.frmLogin.getBtnCancelar().addActionListener(this);
		this.frmLogin.getTextFieldLogin().addKeyListener(this);
		this.frmLogin.getPasswordField().addKeyListener(this);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getSource() == this.frmLogin.getTextFieldLogin() || e.getSource() == this.frmLogin.getPasswordField()) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				this.autorizar();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.frmLogin.getBtnAcessar()) {
			this.autorizar();
		}

		if (e.getSource() == this.frmLogin.getBtnCancelar()) {
			System.out.println("[" + new Utils().getDataHora() + "]: Saída do sistema.");
			System.exit(0);
		}
	}

	private void autorizar() {
		String login = this.frmLogin.getTextFieldLogin().getText();
		char[] password = this.frmLogin.getPasswordField().getPassword();

		String passwordTratado = new String(password);

		if (login.length() == 0 && passwordTratado.length() == 0) {
			JOptionPane.showMessageDialog(this.frmLogin, "Informe o Login e a senha", "", JOptionPane.WARNING_MESSAGE);
			return;
		}

		try {
			VoUsuario usuario = daoUsuario.consultarLogin(login, passwordTratado);

			if (usuario == null) {
				JOptionPane.showMessageDialog(this.frmLogin, "O login ou senha não conferem.", "",
						JOptionPane.WARNING_MESSAGE);

				EventQueue.invokeLater(() -> {
					this.frmLogin.getTextFieldLogin().requestFocusInWindow();
				});

				return;
			}

			if (!usuario.isAtivo()) {
				JOptionPane.showMessageDialog(this.frmLogin,
						"Esta conta está desativada, contate o administrador de sistemas..", "",
						JOptionPane.WARNING_MESSAGE);
				return;
			}

			new CtrMenu();

			this.frmLogin.dispose();

		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(this.frmLogin,
					"Não foi possível consultar os dados do usuário.\n\nDetalhes: " + e.getMessage(), "Erro",
					JOptionPane.ERROR_MESSAGE);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this.frmLogin,
					"Não foi possível consultar os dados do usuário.\n\nDetalhes: " + e.getMessage(), "Erro",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
