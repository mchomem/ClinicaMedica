package br.misael.clinicamedica.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import br.misael.clinicamedica.view.FrmMenu;

public class CtrMenu implements ActionListener, ComponentListener {
	
	private FrmMenu frmMenu;
	
	public CtrMenu() {
		
		this.ativarTemaNimbus();
		
		this.frmMenu = new FrmMenu();
		this.frmMenu.getMntmSair().addActionListener(this);
		this.frmMenu.getMntmPaciente().addActionListener(this);
		this.frmMenu.getMntmMedicamento().addActionListener(this);
		this.frmMenu.getMntmAgendamento().addActionListener(this);
		this.frmMenu.getMntmRegistroDeConsulta().addActionListener(this);
		this.frmMenu.getMntmHistrico().addActionListener(this);
		this.frmMenu.addComponentListener(this);
		
	}
	
    private void ativarTemaNimbus() {
        
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
            JFrame.setDefaultLookAndFeelDecorated(true);
            JDialog.setDefaultLookAndFeelDecorated(true);
        } catch(UnsupportedLookAndFeelException|ClassNotFoundException|InstantiationException|IllegalAccessException e) {
        	
        	try {
        		String dir = "d:\\Clinica Médica\\Log";
        	    File directory = new File(dir);
        	    if (! directory.exists()){
        	        directory.mkdirs();
        	    }
        		
				FileWriter fw = new FileWriter(dir + "\\Log.txt", true);
				PrintWriter pw = new PrintWriter(fw);
				
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
				LocalDateTime now = LocalDateTime.now();  
				
				pw.println("[" + dtf.format(now) + "]: Não foi possível carregar o tema Ninbus.");
				pw.close();
				
			} catch (IOException err) {
				err.printStackTrace();
			}
        } finally {
            JFrame.setDefaultLookAndFeelDecorated(true);
            JDialog.setDefaultLookAndFeelDecorated(true);
        }
            
    }

	@Override
	public void actionPerformed(ActionEvent e) {
	
		if(e.getSource() == this.frmMenu.getMntmSair()) {
			
			this.finalizar();
			
		}else if(e.getSource() == this.frmMenu.getMntmPaciente()) {
			
			// Chamada do formúlário atraves do Controlador.
			new CtrPaciente(this.frmMenu);
			
		} else if(e.getSource() == this.frmMenu.getMntmMedicamento()) {
			
			new CtrMedicamento(this.frmMenu);
			
		} else if(e.getSource() == this.frmMenu.getMntmAgendamento()) {
			
			new CtrAngendamento(this.frmMenu);
			
		} else if(e.getSource() == this.frmMenu.getMntmRegistroDeConsulta()) {
			
			new CtrRegistroConsulta(this.frmMenu);
			
		} else if(e.getSource() == this.frmMenu.getMntmHistrico()) {
			
			new CtrHistorico(this.frmMenu);
			
		}
		
	}

	@Override
	public void componentHidden(ComponentEvent e) {
		
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		
	}

	@Override
	public void componentResized(ComponentEvent e) {
		
		this.frmMenu.getMnbMenu().setSize(this.frmMenu.getWidth(), 30);
		this.frmMenu.getDesktopPane().setSize(this.frmMenu.getWidth(), this.frmMenu.getHeight());
		
	}

	@Override
	public void componentShown(ComponentEvent e) {
		
	}
	
	private void finalizar() {
		
		System.exit(0);
		
	}

}
