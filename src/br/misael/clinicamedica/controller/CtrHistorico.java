package br.misael.clinicamedica.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import br.misael.clinicamedica.model.dao.DaoRegistroConsulta;
import br.misael.clinicamedica.model.vo.VoRegistroConsulta;
import br.misael.clinicamedica.view.FrmHistorico;
import br.misael.clinicamedica.view.FrmMenu;
import br.misael.clinicamedica.view.table.CelTblHistorico;
import br.misael.clinicamedica.view.table.TblHistorico;

public class CtrHistorico implements ActionListener, InternalFrameListener {

	private FrmHistorico frmHistorico;
	private List<VoRegistroConsulta> voRegistroConsultas;
	private DaoRegistroConsulta daoRegistroConsulta;

	public CtrHistorico(FrmMenu frmMenu) {
		this.frmHistorico = new FrmHistorico();
		this.daoRegistroConsulta = new DaoRegistroConsulta();
		this.frmHistorico.getBtnFiltrar().addActionListener(this);
		this.frmHistorico.addInternalFrameListener(this);
		frmMenu.getDesktopPane().add(this.frmHistorico);
		frmMenu.getDesktopPane().selectFrame(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.frmHistorico.getBtnFiltrar()) {
			this.atualizarJTable();
		}
	}

	@Override
	public void internalFrameOpened(InternalFrameEvent e) {
	}

	@Override
	public void internalFrameClosing(InternalFrameEvent e) {
	}

	@Override
	public void internalFrameClosed(InternalFrameEvent e) {
	}

	@Override
	public void internalFrameIconified(InternalFrameEvent e) {
	}

	@Override
	public void internalFrameDeiconified(InternalFrameEvent e) {
	}

	@Override
	public void internalFrameActivated(InternalFrameEvent e) {
		this.atualizarJTable();
	}

	@Override
	public void internalFrameDeactivated(InternalFrameEvent e) {
	}

	private void atualizarJTable() {
		try {

			voRegistroConsultas = daoRegistroConsulta.consultar();

			String filtroNome = this.frmHistorico.getTextNomePaciente().getText().toUpperCase();
			List<VoRegistroConsulta> registroConsultaFiltrado = new ArrayList<VoRegistroConsulta>();

			if (this.frmHistorico.getRdbtnFinalizada().isSelected()) {

				voRegistroConsultas.stream()
						.filter(rc -> rc.getVoAgendamento().getPaciente().getNome().contains(filtroNome)
								&& rc.isConsultaFinalizada() == true)
						.forEach(rc -> {
							registroConsultaFiltrado.add(rc);
						});

			} else if (this.frmHistorico.getRdbtnNaoFinalizada().isSelected()) {

				voRegistroConsultas.stream()
						.filter(rc -> rc.getVoAgendamento().getPaciente().getNome().contains(filtroNome)
								&& rc.isConsultaFinalizada() == false)
						.forEach(rc -> {
							registroConsultaFiltrado.add(rc);
						});

			} else {

				voRegistroConsultas.stream()
						.filter(rc -> rc.getVoAgendamento().getPaciente().getNome().contains(filtroNome)
								&& (rc.isConsultaFinalizada() == true || rc.isConsultaFinalizada() == false))
						.forEach(rc -> {
							registroConsultaFiltrado.add(rc);
						});

			}

			if (registroConsultaFiltrado != null) {
				this.frmHistorico.getTable().setModel(new TblHistorico(registroConsultaFiltrado));
				this.frmHistorico.getTable().setDefaultRenderer(Object.class, new CelTblHistorico());
			}
			
			int total = registroConsultaFiltrado.size();
			String mensagem = "";
			
			if(total == 0) {
				mensagem = "Nenhum registro localizado";
			} else if(total > 1) {
				mensagem = "Foram localizados " + total + " registros";
			} else {
				mensagem = "Foi localizado " + total +" registro";
			}
			
			this.frmHistorico.getLabelTotalRegistros().setText(mensagem);

		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(this.frmHistorico, "Erro ao consultar.\n\nDetalhes: " + e.getMessage(),
					"Erro", JOptionPane.ERROR_MESSAGE);

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this.frmHistorico, "Erro ao consultar.\n\nDetalhes: " + e.getMessage(),
					"Erro", JOptionPane.ERROR_MESSAGE);
		}
	}
}
