package br.misael.clinicamedica;

import java.awt.EventQueue;

import br.misael.clinicamedica.controller.CtrLogin;

public class Programa {

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				new CtrLogin();

			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}
