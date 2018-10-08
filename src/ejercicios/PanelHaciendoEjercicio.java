package ejercicios;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Date;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import clases.Usuario;
import clases_personalizadas.MiPanel;

@SuppressWarnings("serial")
public class PanelHaciendoEjercicio extends JPanel implements ActionListener{
	JFrame ventana;
	Usuario usuario;
	String ejercicio;
	Date tiempoInicio, tiempoFinal;
	

	
	public PanelHaciendoEjercicio(JFrame ventana, Usuario usuario, String ejercicio){
		super(new BorderLayout());
		this.usuario = usuario;
		this.ventana = ventana;
		this.ventana.getContentPane().removeAll();
		this.ejercicio = ejercicio;
		
		this.add(crearPanelGif(), BorderLayout.CENTER);
		this.tiempoInicio = new Date();
		
		this.ventana.setResizable(true);
		this.ventana.setContentPane(this);
		this.ventana.setVisible(true);
		this.ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	
	}
	
	private Container crearPanelGif(){
		JPanel panelPrinci = new JPanel(new BorderLayout());
		Toolkit t = Toolkit.getDefaultToolkit();
		
		Image gif = t.createImage(this.ejercicio);
		
		System.out.println(this.ejercicio);
		MiPanel panel = new MiPanel(gif);
		
		panelPrinci.add(panel, BorderLayout.CENTER);
		panelPrinci.add(crearPanelFinalizar(), BorderLayout.SOUTH);
	
		return panelPrinci;
	}
	
	private Container crearPanelFinalizar(){
		JPanel panel = new JPanel();
		JButton finalizar = new JButton("Finalizar");
		
		finalizar.setHorizontalAlignment(JButton.CENTER);
		finalizar.addActionListener(this);
		finalizar.setActionCommand("finalizar");
		//finalizar.setPreferredSize(new Dimension(150,150));
		panel.add(finalizar);
		
		return panel;
	}
	


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("finalizar")){
			this.tiempoFinal = new Date();
			
			//this.usuario.setEjercicioTerminado(new RegistroActividades(this.usuario.getNombre(), String.valueOf(tiempoFinal.getTime()-tiempoInicio.getTime()),));
			@SuppressWarnings("unused")
			
			PanelSelectorEjerciciosLibres ej = new PanelSelectorEjerciciosLibres(this.ventana, this.usuario);
		}
		
	}
	
	
}
