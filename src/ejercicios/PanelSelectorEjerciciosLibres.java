package ejercicios;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import acceso_ficheros.LeerEjercicios;
import clases.Ejercicio;
import clases.Usuario;
import clases_personalizadas.MiPanel;

@SuppressWarnings("serial")
public class PanelSelectorEjerciciosLibres extends JPanel implements ActionListener{

	private final String botonEstandar = "icons/ejercicios/estandar.png";
	private final String botonFuerza = "icons/ejercicios/fuerza.png";
	private final String botonResistencia = "icons/ejercicios/resistencia.png";
	
	private final String botonAtras = "icons/izda2.png";

	JFrame ventana;
	Usuario usuario;	

	Image fondo;					//Panel
	Toolkit t;

	public PanelSelectorEjerciciosLibres (JFrame ventana, Usuario usuario){
		super(new BorderLayout());
		//this.ventana.getContentPane().removeAll();
		this.ventana = ventana;
		this.usuario = usuario;
		
		t = Toolkit.getDefaultToolkit();					//Establecer una imagen como Background de un JPanel
		fondo = t.createImage("icons/madera.jpg");
		
		MiPanel jPanel = new MiPanel(fondo);
		jPanel.setLayout(new BorderLayout());
		
		jPanel.add(crearPanelNorte(), BorderLayout.NORTH);
		jPanel.add(crearPanelCentro(), BorderLayout.CENTER);

	
		this.ventana.setResizable(false);
		this.ventana.setContentPane(jPanel);			
	}

	private Component crearPanelNorte() {
		JPanel panelNorte = new JPanel(new GridLayout(1,2));
		panelNorte.setOpaque(false);
		JButton botonAtras = new JButton(new ImageIcon(this.botonAtras));
		
				
		JPanel jp1 = new JPanel(new FlowLayout());
		JPanel jp2 = new JPanel(new FlowLayout());
		
		
		jp1.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 430)); jp1.setOpaque(false);
		jp2.setBorder(BorderFactory.createEmptyBorder(-10, 0, 0, -400));jp2.setOpaque(false);
		

		botonAtras.setContentAreaFilled(false); botonAtras.setBorderPainted(false);
		botonAtras.addActionListener(this); botonAtras.setActionCommand("atras");
			
		jp1.add(botonAtras);
		jp2.add(new JLabel(new ImageIcon("icons/logo.png")));		
		
		panelNorte.add(jp1);
		panelNorte.add(jp2);	

		return panelNorte;		
	}

	private Component crearPanelCentro() {
		JPanel panelCentro = new JPanel(new GridLayout(1, 3));	
		panelCentro.setOpaque(false);
		panelCentro.setBorder(BorderFactory.createEmptyBorder(100, 50, 100, 50));
		JButton botones;

		botones = new JButton(new ImageIcon(this.botonEstandar));
		botones.addActionListener(this);
		//botones.setBorderPainted(false);
		//botones.setContentAreaFilled(false);
		botones.setActionCommand("estandar");
		botones.setContentAreaFilled(false); botones.setBorderPainted(false);
		panelCentro.add(botones);

		botones = new JButton(new ImageIcon(this.botonFuerza));
		botones.addActionListener(this);
		botones.setActionCommand("fuerza");
		botones.setContentAreaFilled(false); botones.setBorderPainted(false);
		panelCentro.add(botones);

		botones = new JButton(new ImageIcon(this.botonResistencia));
		botones.addActionListener(this);
		botones.setActionCommand("resistencia");
		botones.setContentAreaFilled(false); botones.setBorderPainted(false);
		panelCentro.add(botones);	

		return panelCentro;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getActionCommand().equals("atras")){
			@SuppressWarnings("unused")
			PanelElectorEjercicios electorEjercicios = new PanelElectorEjercicios(this.ventana, this.usuario);
		}else{
			LeerEjercicios leer = new LeerEjercicios();
			
			switch (e.getActionCommand()) {
			case "estandar":
				try {
					List<Ejercicio> listaEstandar = leer.leerEjercicios(leer.getImagenesEstandar());
					@SuppressWarnings("unused")
					PanelEjerciciosEspecificos ejerciciosEstandar = new PanelEjerciciosEspecificos(this.ventana, this.usuario, listaEstandar, "Estandar");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				break;

			case "fuerza":
				try {
					List<Ejercicio> listaFuerza = leer.leerEjercicios(leer.getImagenesFuerza());
					@SuppressWarnings("unused")
					PanelEjerciciosEspecificos ejerciciosEstandar = new PanelEjerciciosEspecificos(this.ventana, this.usuario, listaFuerza, "Fuerza");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				break;

			case "resistencia":
				try {
					List<Ejercicio> listaResistencia = leer.leerEjercicios(leer.getImagenesResistencia());
					@SuppressWarnings("unused")
					PanelEjerciciosEspecificos ejerciciosEstandar = new PanelEjerciciosEspecificos(this.ventana, this.usuario, listaResistencia, "Resistencia");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				break;
			}
		}
	}
}
