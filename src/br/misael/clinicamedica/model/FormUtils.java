package br.misael.clinicamedica.model;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

/**
 * Classe utilitária para formulários Windows, contendo métodos para auxílio na interface gráfica do usuário.
 * @author Misael C. Homem
 */
public class FormUtils {
	
	private static Dimension dimensaoMonitor = Toolkit.getDefaultToolkit().getScreenSize();
	
	/**
	 * Define os tipos de máscaras usadas nos campos JFormattedText.
	 * @param controle 
	 * @param tipoMascara
	 * @return MaskFormatter
	 */
	public static MaskFormatter formatarMascaraCampos(JFormattedTextField controle, MascaraCampo tipoMascara) {
		
		MaskFormatter maskFormatter = new MaskFormatter();
		
		try {
			
			switch(tipoMascara) {
			
				case DATA:
					maskFormatter.setMask(MascaraCampo.DATA.getValor());
					maskFormatter.setPlaceholderCharacter('0');
					break;
					
				case HORA:
					maskFormatter.setMask(MascaraCampo.HORA.getValor());
					maskFormatter.setPlaceholderCharacter('0');
					break;
					
				case DATAHORA:
					maskFormatter.setMask(MascaraCampo.DATAHORA.getValor());
					maskFormatter.setPlaceholderCharacter('0');
					break;
					
				case CPF:
					maskFormatter.setMask(MascaraCampo.CPF.getValor());
					maskFormatter.setPlaceholderCharacter('0');
					break;
					
				case CNPJ:
					maskFormatter.setMask(MascaraCampo.CNPJ.getValor());
					maskFormatter.setPlaceholderCharacter('0');
					break;
					
				case RG:
					maskFormatter.setMask(MascaraCampo.RG.getValor());
					maskFormatter.setPlaceholderCharacter('0');
					break;
					
				case TELEFONE:
					maskFormatter.setMask(MascaraCampo.TELEFONE.getValor());
					maskFormatter.setPlaceholderCharacter('0');
					break;
					
				default:
					maskFormatter.setMask("");
					break;

			}			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return maskFormatter;
	}
	
	/**
	 * Método para maximizar o tamanho do formulário para o tamanho da tela do monitor. 
	 * @param form
	 */
	public static void setMaximizarForm(JFrame form) {
		form.setSize(dimensaoMonitor.width, dimensaoMonitor.height);
	}
	
	public static void setCentralizarForm(JFrame form) {
		form.setLocation((dimensaoMonitor.width - form.getWidth()) / 2, (dimensaoMonitor.height - form.getHeight()) / 2);
	}
	
	public static void setCentralizarForm(JDialog form) {
		form.setLocation((dimensaoMonitor.width - form.getWidth()) / 2, (dimensaoMonitor.height - form.getHeight()) / 2);
	}
	
	public static void setCentralizarForm(JInternalFrame internalForm) {
		internalForm.setLocation((dimensaoMonitor.width - internalForm.getWidth()) / 2, (dimensaoMonitor.height - internalForm.getHeight()) / 2);
	}
	
	/**
	 * Método para prevenir o uso de caractere indevido nos campos dos formulários.
	 * @param recipiente Um controle que contém outros controles.
	 * @return Retorna true se encontrado um caractere indevido, que não deve se utilizado.
	 */
	public static boolean verificarCaracterIndevido(Container recipiente) {
		
		Component componentes[] = recipiente.getComponents();
		StringBuilder texto = new StringBuilder();
		
		for (Component componente : componentes) {
			
			if (componente instanceof JTextField) {   
				texto.append(((JTextField)componente).getText());
				
				// Foi encontrado o caracter apóstrofe no texto?
				if(texto.substring(0, texto.length()).equals("'")) {
					
					return true;
					
				}
				
				// Foi encontrado o caracter pipe no texto?
				if(texto.substring(0, texto.length()).equals("|")) {
					
					return true;
					
				}
				
				// Foi encontrado o caracter ponto e vírgulo no texto?
				if(texto.substring(0, texto.length()).equals(";")) {
					
					return true;
					
				}
				
			}
			
			/* Usando o método recursivamente para navegar
			 * em componentes que possuam outros componentes.
			 */
			verificarCaracterIndevido((Container)componente);
			
		}
		
		return false;
		
	}
	
	public static void inicializarCampos(Container recipiente) {
		
		Component componentes[] = recipiente.getComponents();
		
		for (Component componente : componentes) {
			
			if (componente instanceof JTextField) {
				
				((JTextField)componente).setText(null);
				
			}
			
			if(componente instanceof JFormattedTextField) {
				
				((JFormattedTextField)componente).setText(null);
				
			}
			
		}
		
	}
	
	
	/* Adequar para processar em controles dentro containers. */
    public static List<Component> inicializarCampos1(Container recipiente) {
    	
        List<Component> components = new ArrayList<Component>();
    	
        for (Component c : recipiente.getComponents()) {
        	
            components.add(c);
            
            if (c instanceof Container) {
            	
                components.addAll(inicializarCampos1((Container)c));
                
            }
            
        }
    	
        return components;
    }
	
    /**
     * Método para formatar as mensagens de rota de pilha em um JOptionPane 
     * http://www.guj.com.br/java/244038-resolvido-printstacktrace-em-joptionpane 
     * Exemplo de uso:<br><br>
     *  
     * try {<br>
     * int x = 100 / 0;<br>
     * } catch() {<br>
     * JOptionPane.showMessageDialog (null, formataMensagemExcecao(ex));<br>
     * }
     * 
     * @param thr O objeto da exceção disparada.
     * @return Retorna uma string contendo o valor formatado para ser exibido na GUI.
     */
    public static String formatarMensagemExcecao (Throwable thr) {
    	
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter (stringWriter);
        thr.printStackTrace (printWriter);
        
        return stringWriter.toString();
        
    }

}
