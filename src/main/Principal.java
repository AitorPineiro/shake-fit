package main;



import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import inicio_sesion.PanelInicio;

public class Principal{

	private final static String titulo = "Shake & Fitness";
	
	
	JFrame ventana;
	
	public Principal() {		
		this.ventana = new JFrame(titulo);				
		loginUser();
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void loginUser() {									//Nuevo Inicio, para hacer Login o Registrarse
		@SuppressWarnings("unused")
		PanelInicio init = new PanelInicio(this.ventana);
	}

	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "No se encuentra el look&Feel");
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		Principal ej = new Principal();
	}
}
