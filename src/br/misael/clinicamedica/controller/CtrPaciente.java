package br.misael.clinicamedica.controller;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import br.misael.clinicamedica.model.bo.BoPaciente;
import br.misael.clinicamedica.model.dao.DaoPaciente;
import br.misael.clinicamedica.model.vo.VoPaciente;
import br.misael.clinicamedica.view.FrmMenu;
import br.misael.clinicamedica.view.FrmPaciente;
import br.misael.clinicamedica.view.table.CelTblPaciente;
import br.misael.clinicamedica.view.table.TblPaciente;
import br.misael.clinicamedica.view.uc.UCImageLoader;

public class CtrPaciente extends Component implements ActionListener, KeyListener, InternalFrameListener, ListSelectionListener {
	
	private static final long serialVersionUID = -6874841730858750940L;
	private FrmPaciente      frmPaciente;
	private VoPaciente       voPaciente;
	private BoPaciente       boPaciente;
	private DaoPaciente      daoPaciente;
	private List<VoPaciente> voPacientes;
	
	public CtrPaciente(FrmMenu frmMenu) {		
		
		this.frmPaciente = new FrmPaciente();
		this.voPaciente  = new VoPaciente();
		this.boPaciente  = new BoPaciente();
		this.daoPaciente = new DaoPaciente();
		
		this.frmPaciente.getTxtNome().addKeyListener(this);
		this.frmPaciente.getTxtEndereco().addKeyListener(this);
		this.frmPaciente.getTxtFiltro().addKeyListener(this);
		
		this.frmPaciente.getBtnInicializar().addActionListener(this);
		this.frmPaciente.getBtnGravar().addActionListener(this);
		this.frmPaciente.getBtnExcluir().addActionListener(this);
		this.frmPaciente.getBtnRelatorio().addActionListener(this);
		this.frmPaciente.getBtnFiltrar().addActionListener(this);
		this.frmPaciente.getBtnCarregar().addActionListener(this);
		this.frmPaciente.getTable().getSelectionModel()
			.addListSelectionListener(this);
		
		this.frmPaciente.addInternalFrameListener(this);
		
		frmMenu.getDesktopPane().add(this.frmPaciente); // Adiciona o JIternalFrame no DesktopPane da janela principal.
		frmMenu.getDesktopPane().selectFrame(true);     // Coloca o foco na JInternalFrame quando ela é aberta.
				
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == this.frmPaciente.getBtnInicializar()) {
			
			this.inicializar();
			
		} else if(e.getSource() == this.frmPaciente.getBtnGravar()) {
			
			this.gravar();
			
		} else if(e.getSource() == this.frmPaciente.getBtnExcluir()) {
			
			this.excluir();
			
		}  else if(e.getSource() == this.frmPaciente.getBtnRelatorio()) {
			
			this.gerarRelatorioLista();
			
		} else if(e.getSource() == this.frmPaciente.getBtnFiltrar()) {
			
			this.atualizarJTable();
			
		} else if(e.getSource() == this.frmPaciente.getBtnCarregar()) {
			
			this.carregarFoto();
			
		}
		
	}	

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {
		
		/* Limitando a digitação de carateres em campos de texto
		 * anexando aos mesmos, o evento de liberação de tecla.
		 */
		
		String campo = "";
		int maximo   = 0;
		
		if(e.getSource() == this.frmPaciente.getTxtNome()) {
			
			campo  = this.frmPaciente.getTxtNome().getText();
			maximo = 100;
			
			if(campo.length() > maximo) {
				
				campo = campo.substring(0, maximo);
				this.frmPaciente.getTxtNome().setText(campo);
				
			}
			
		} else if(e.getSource() == this.frmPaciente.getTxtEndereco()) {
			
			campo  = this.frmPaciente.getTxtEndereco().getText();
			maximo = 100;
			
			if(campo.length() > maximo) {
				
				campo = campo.substring(0, maximo);
				this.frmPaciente.getTxtEndereco().setText(campo);
				
			}
			
		} else if(e.getSource() == this.frmPaciente.getTxtFiltro()) {
			
			campo  = this.frmPaciente.getTxtFiltro().getText();
			maximo = 100;
			
			if(campo.length() > maximo) {
				
				campo = campo.substring(0, maximo);
				this.frmPaciente.getTxtFiltro().setText(campo);
				
			}
			
		}
		
	}
	
	private void carregarFoto() {
		
		try {
			
			JFileChooser jfc = new JFileChooser();
			int resultado    = jfc.showOpenDialog(this.frmPaciente);
			
			// Operação cancelada ou abortada?
			if(resultado == JFileChooser.CANCEL_OPTION
				|| resultado == JFileChooser.ABORT) {
				return;
			}
			
			UCImageLoader imageLoader = new UCImageLoader();
			imageLoader.load(jfc.getSelectedFile());
			
			JPanel painel = this.frmPaciente.getPnlFoto();
			int h         = painel.getHeight();
			int w         = painel.getWidth();
			
			// TODO remover esta regra daqui, regras devem ficar nas classes do pacote Business
			if(imageLoader.getImageHeight() > h
				|| imageLoader.getImageWidth() > w) {
				
				JOptionPane.showMessageDialog(this.frmPaciente
						, "A imagem não deve exceder as dimenssões de "
						 + h + " x " + w + " pixels."
						, "Alerta"
						, JOptionPane.WARNING_MESSAGE);
				
				return;
				
			}
			
			this.frmPaciente.getPnlFoto().removeAll();
			this.frmPaciente.getPnlFoto().add(imageLoader);
			this.frmPaciente.getPnlFoto().revalidate();
			this.frmPaciente.getPnlFoto().repaint();
			
		} catch(Exception e) {
			
			JOptionPane.showMessageDialog(this.frmPaciente
					, "Falha ao carregar imagem. Causado por: "
						+ e.getMessage()
					, "Erro"
					, JOptionPane.ERROR_MESSAGE);
			
		}
		
	}
	
	private byte[] obterFotoDoPainel() throws Exception {
		
		// Obter as dimenssões do JPanel.
		JPanel painel = this.frmPaciente.getPnlFoto();
		int h         = painel.getHeight();
		int w         = painel.getWidth();
		
		// Carrega o objeto BufferedImage com a figura/imagem.
		BufferedImage bufferedImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		Graphics2D g                = bufferedImage.createGraphics();
		painel.paint(g);
		
		// Exporta a figura/imagem para um array de bytes.
		ByteArrayOutputStream baos  = new ByteArrayOutputStream();
		ImageIO.write(bufferedImage, "jpg", baos);
		baos.flush();
		baos.close();

		return baos.toByteArray();
		
	}
	
	private void removerFoto() {
		
		this.frmPaciente.getPnlFoto().removeAll();
		this.frmPaciente.getPnlFoto().revalidate();
		this.frmPaciente.getPnlFoto().repaint();
	}
	
	@Override
	public void internalFrameActivated(InternalFrameEvent e) {
		
		this.atualizarJTable();
		
	}

	@Override
	public void internalFrameClosed(InternalFrameEvent e) {}

	@Override
	public void internalFrameClosing(InternalFrameEvent e) {}

	@Override
	public void internalFrameDeactivated(InternalFrameEvent e) {}

	@Override
	public void internalFrameDeiconified(InternalFrameEvent e) {}

	@Override
	public void internalFrameIconified(InternalFrameEvent e) {}

	@Override
	public void internalFrameOpened(InternalFrameEvent e) {}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {

		int indiceLinha = this.frmPaciente.getTable().getSelectedRow();
		
		if(indiceLinha == -1) {
			return;
		}
		
		this.voPaciente = new TblPaciente(voPacientes).get(indiceLinha);
		this.frmPaciente.getTxtNome().setText(voPaciente.getNome());
		this.frmPaciente
			.getFtxtDataNascimento()
				.setText(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
					.format(this.voPaciente.getDataNascimento()));
		
		if(voPaciente.getSexo().equals("M")) {
			
			this.frmPaciente.getRdbtnMasculino().setSelected(true);
			
		} else if(voPaciente.getSexo().equals("F")) {
			
			this.frmPaciente.getRdbtnFeminino().setSelected(true);
			
		}
		
		this.frmPaciente.getTxtEndereco().setText(voPaciente.getEndereco());
		this.frmPaciente.getFtxtTelefone().setText(voPaciente.getTelefone());
		this.frmPaciente.getChckbxAtivo().setSelected(voPaciente.isAtivo());
		
		try {
			
			UCImageLoader imageLoader = new UCImageLoader();
			imageLoader.load(this.voPaciente.getFoto());
			
			this.frmPaciente.getPnlFoto().removeAll();
			this.frmPaciente.getPnlFoto().add(imageLoader);
			this.frmPaciente.getPnlFoto().revalidate();
			this.frmPaciente.getPnlFoto().repaint();
			
		} catch(Exception ex) {
			
			JOptionPane.showMessageDialog(this.frmPaciente
					, "Falha ao carregar a foto. Causa: " + ex.getMessage()
					, "Erro"
					, JOptionPane.ERROR_MESSAGE);
			
		}
		
	}

	private void inicializar() {
		
		this.frmPaciente.getTxtNome().setText("");
		this.frmPaciente.getFtxtDataNascimento().setText("");
		this.frmPaciente.getRdbtnMasculino().setSelected(true);
		this.frmPaciente.getTxtEndereco().setText("");
		this.frmPaciente.getFtxtTelefone().setText("");		
		this.frmPaciente.getChckbxAtivo().setSelected(false);
		this.frmPaciente.getTxtFiltro().setText("");
		this.voPaciente.setIdPaciente(0);
		this.removerFoto();
				
	}
	
	private void gravar() {
		
		String nome              = this.frmPaciente.getTxtNome().getText();
		Timestamp dataNascimento = null;
		String sexo              = this.frmPaciente.getRdbtnMasculino().isSelected() ? "M" : "F";
		String endereco          = this.frmPaciente.getTxtEndereco().getText();
		String telefone          = this.frmPaciente.getFtxtTelefone().getText();
		boolean ativo            = this.frmPaciente.getChckbxAtivo().isSelected();

		try {
			
			DateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			dataNascimento =
					new Timestamp(formatoData.parse(this.frmPaciente.getFtxtDataNascimento().getText()).getTime());
			
		} catch (ParseException e) {
			
			JOptionPane.showMessageDialog(this.frmPaciente
					, "Data inválida."
					, "Erro"
					, JOptionPane.ERROR_MESSAGE);
			return;
			
		}		
		
		int respostaDialogo =
				JOptionPane.showConfirmDialog(
						this.frmPaciente
						, "Confirma gravação?"
						, ""
						, JOptionPane.YES_NO_OPTION
						, JOptionPane.QUESTION_MESSAGE);

		// Confirmar operação antes de mais nada.
		if(respostaDialogo == JOptionPane.NO_OPTION) {
			return;
		}
		
		// Consistência de dados.
		
		// Campos requisitados.
		if(nome.length() == 0
			|| dataNascimento == null //TODO corrigir: a dataNascimento nunca é nula se não informada
			|| endereco.length() == 0) {
			
			JOptionPane.showMessageDialog(this.frmPaciente
					, "Informe os campos requisitados."
					, "Aviso"
					, JOptionPane.WARNING_MESSAGE);
			return;
			
		}
		
		try {
		
			voPaciente.setNome(nome);
			voPaciente.setDataNascimento(dataNascimento);
			voPaciente.setSexo(sexo);
			voPaciente.setEndereco(endereco);
			// Atribui o valor do telefone já removendo a máscara.
			voPaciente.setTelefone(
					telefone.replace("(", "")
						.replace(")", "")
							.replace("-", "")
								.replace(" ", ""));
			voPaciente.setAtivo(ativo);
			voPaciente.setFoto(this.obterFotoDoPainel());
				
			String resultado = "";
			
			// Novo registro ou alteração?
			if(voPaciente.getIdPaciente() == 0) {
				
				boPaciente.validarIdade(voPaciente);
				daoPaciente.inserir(voPaciente);
				resultado = "Registro gravado.";
				
			} else {
				
				boPaciente.validarIdade(voPaciente);
				daoPaciente.alterar(voPaciente);
				resultado = "Registro alterado.";
				voPaciente.setIdPaciente(0);
				
			}
			
			this.inicializar();
			this.atualizarJTable();
			
			JOptionPane.showMessageDialog(
					this.frmPaciente
					, resultado
					, ""
					, JOptionPane.INFORMATION_MESSAGE);
			
		} catch(ClassNotFoundException e) {
			
			JOptionPane.showMessageDialog(
					this.frmPaciente
					, "Erro ao gravar.\n\nDetalhes: " + e.getMessage()
					, "Erro"
					, JOptionPane.ERROR_MESSAGE);
			
		} catch(SQLException e) {
			
			JOptionPane.showMessageDialog(
					this.frmPaciente
					, "Erro ao gravar.\n\nDetalhes: " + e.getMessage()
					, "Erro"
					, JOptionPane.ERROR_MESSAGE);
			
		} catch(Exception e) {
			
			JOptionPane.showMessageDialog(
					this.frmPaciente
					, "Erro ao gravar.\n\nDetalhes: " + e.getMessage()
					, "Erro"
					, JOptionPane.ERROR_MESSAGE);
			
		}
		
	}
	
	private void excluir() {
		
		int respostaDialogo =
				JOptionPane.showConfirmDialog(
						this.frmPaciente
						, "Confirma exclusão?"
						, ""
						, JOptionPane.YES_NO_OPTION
						, JOptionPane.QUESTION_MESSAGE);
		
		if(respostaDialogo == JOptionPane.NO_OPTION) {
			
			return;
			
		}
		
		if(voPaciente.getIdPaciente() == 0) {
			
			JOptionPane.showMessageDialog(
					this.frmPaciente
					, "Selecione um registro da tabela para excluir."
					, ""
					, JOptionPane.WARNING_MESSAGE);
			
			return;
			
		}
		
		try {
			
			int agendamentos = boPaciente.existeAgendamento(voPaciente);
			
			if(agendamentos != 0) {
				
				JOptionPane.showMessageDialog(
						this.frmPaciente
						, "O paciente " + voPaciente.getNome()
							+ " possui " + agendamentos
							+ " " + (agendamentos > 1 ? "agendamentos" : "agendamento") + ".\n"
							+ "Não é possível excluir o registro."
						, "Alerta"
						, JOptionPane.WARNING_MESSAGE);
				
				return;
				
			}
			
			daoPaciente.excluir(voPaciente);
			this.inicializar();
			this.atualizarJTable();
			
			JOptionPane.showMessageDialog(
					this.frmPaciente
					, "Registro excluido."
					, ""
					, JOptionPane.INFORMATION_MESSAGE);
			
		} catch(ClassNotFoundException e) {
			
			JOptionPane.showMessageDialog(
					this.frmPaciente
					, "Erro ao excluir.\n\nDetalhes: " + e.getMessage()
					, "Erro"
					, JOptionPane.ERROR_MESSAGE);
			
		} catch(SQLException e) {
			
			JOptionPane.showMessageDialog(
					this.frmPaciente
					, "Erro ao excluir.\n\nDetalhes: " + e.getMessage()
					, "Erro"
					, JOptionPane.ERROR_MESSAGE);
			
		} catch(Exception e) {
			
			JOptionPane.showMessageDialog(
					this.frmPaciente
					, "Erro ao excluir.\n\nDetalhes: " + e.getMessage()
					, "Erro"
					, JOptionPane.ERROR_MESSAGE);
			
		}
		
	}
	
	private void atualizarJTable() {
		
		try {
			
			voPacientes = daoPaciente.consultar();
			
			this.frmPaciente.getLblContadorRegistros().setText(voPacientes.size() + " registros(s)");
			
			String filtroNome = this.frmPaciente.getTxtFiltro().getText().toUpperCase();
			List<VoPaciente> pacientesFiltrados = new ArrayList<VoPaciente>();
			
			if(this.frmPaciente.getRdbtnAtivo().isSelected()) {
				
				voPacientes.stream()
								.filter(p -> p.getNome().contains(filtroNome)
										&& p.isAtivo() == true)
									.forEach(p -> {
										pacientesFiltrados.add(p);
									});
				
			} else if(this.frmPaciente.getRdbtnInativo().isSelected()) {
				
				voPacientes.stream()
								.filter(p -> p.getNome().contains(filtroNome)
										&& p.isAtivo() == false)
									.forEach(p -> {
										pacientesFiltrados.add(p);
									});
				
			} else {
				
				voPacientes.stream()
								.filter(p -> p.getNome().contains(filtroNome)
										&& (p.isAtivo() == false
										|| p.isAtivo() == true) )
									.forEach(p -> {
										pacientesFiltrados.add(p);
									});
				
			}
			
			voPacientes = pacientesFiltrados;
			
			// Lista retornada.
			if(voPacientes != null) {
				
				this.frmPaciente
					.getTable()
						.setModel(new TblPaciente(voPacientes));
				
				this.frmPaciente
					.getTable()
						.setDefaultRenderer(Object.class,  new CelTblPaciente());
				
			}
			
		} catch(ClassNotFoundException e) {
			
			JOptionPane.showMessageDialog(
					this.frmPaciente
					, "Erro ao consultar.\n\nDetalhes: " + e.getMessage()
					, "Erro"
					, JOptionPane.ERROR_MESSAGE);
			
		} catch(SQLException e) {
			
			JOptionPane.showMessageDialog(
					this.frmPaciente
					, "Erro ao consultar.\n\nDetalhes: " + e.getMessage()
					, "Erro"
					, JOptionPane.ERROR_MESSAGE);
			
		}
		
	}
	
	private void gerarRelatorioLista() {
		
		// Para utilizar um filtro para relatório ou aplicar os filtros antes, alimentar a lista e passa-la para o JasperReport.
		// Se usar o parâmetro de relatório, substituir o valor null 
		// Map parametrosRel = new HashMap();
		// parametrosRel.put("variavel", variavel);
		
		try {
			
			if(this.voPacientes == null) {
				
				JOptionPane.showMessageDialog(
						this.frmPaciente
						, "Execute uma consulta antes."
						, "Aviso"
						, JOptionPane.WARNING_MESSAGE);
				return;
				
			}
			
			if(this.voPacientes.size() == 0) {
				
				JOptionPane.showMessageDialog(
						this.frmPaciente
						, "Não há registros para exibir o relatório."
						, "Aviso"
						, JOptionPane.WARNING_MESSAGE);
				return;
				
			}
			
			JRBeanCollectionDataSource jrbcds = new JRBeanCollectionDataSource(this.voPacientes);
			JasperPrint print                 = JasperFillManager.fillReport("relatorios/RelPaciente.jasper", null, jrbcds);
			JasperViewer jv                   = new JasperViewer(print, false);
			jv.setZoomRatio(0.75F);
			jv.setVisible(true);
			
		} catch (JRException ex) {
				
				JOptionPane.showMessageDialog(
						this.frmPaciente
						, "Não foi possível abrir o relatório.\n\n" + ex.getMessage()
						, "Erro"
						, JOptionPane.ERROR_MESSAGE);
				ex.printStackTrace();
				
		}
		
	}

}
