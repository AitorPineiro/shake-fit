package inicio_sesion;


import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import clases_personalizadas.MiPanel;
import clases_personalizadas.Reproductor;
import usuario_registro.DialogoRegistro;

public class PanelInicio implements ActionListener{
	JFrame ventana; 				//Ventana
	
	ImageIcon icono;				//Icono de la Ventana
	
	Image fondo;					//Panel
	Toolkit t;
	
	JButton login, registro;		//Botones
		
	public PanelInicio(JFrame ventana) {
		this.ventana = ventana;		
		
		this.ventana.getContentPane().removeAll();			//Borrar todo lo que hay en el JFrame
		
		
		this.ventana.setSize(783, 531);  //Resolucion de la imagen de fondo
		this.ventana.setLocation(300 , 50);
		this.ventana.setTitle("Shake & Fit");
		
		
		icono = new  ImageIcon("icons/fitness1.png");   	//Icono de la Ventana
		this.ventana.setIconImage(icono.getImage());

		t = Toolkit.getDefaultToolkit();					//Establecer una imagen como Background de un JPanel
		fondo = t.createImage("icons/fondo1.jpg");
		
		this.ventana.setContentPane(crearPanelPrincipal());
		this.ventana.setResizable(false);
		this.ventana.setVisible(true);
		this.ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	private MiPanel crearPanelPrincipal() {
		MiPanel panel = new MiPanel(fondo); 				//Crear un panel con imagen de fondo. Para ello, 
															//llama la clase MiPanel y se le pasa el la ubicación de la imagen
		
		panel.setLayout(new BorderLayout());
		panel.add(this.crearPanelLogo(), BorderLayout.NORTH);
		panel.add(this.crearPanelBotones(), BorderLayout.SOUTH);
		
		return panel;
	}
	
	private Component crearPanelLogo() {
		JPanel panelLogo = new JPanel(new FlowLayout());
		JLabel logo = new JLabel(new ImageIcon("icons/logo.png"));
		
		panelLogo.setOpaque(false);
		panelLogo.setBorder(BorderFactory.createEmptyBorder(0, 650, 0, 0));
		panelLogo.add(logo);
		
		return panelLogo;
	}
	
	private Component crearPanelBotones() {
		JPanel jp = new JPanel(new GridLayout(1 , 2));
		JPanel panelLogin;
		panelLogin = new JPanel(new FlowLayout());
		
		jp.setOpaque(false);								//Convertir en opaco para poder ver la imagen de fondo
		jp.setBorder(BorderFactory.createEmptyBorder(600,280,0,40));
		
		login = new JButton(new ImageIcon("icons/btnLogin.png"));login.setActionCommand("login"); login.addActionListener(this);
		registro   = new JButton(new ImageIcon("icons/btnReg.png")); registro.setActionCommand("registro"); registro.addActionListener(this);
		
		login.setOpaque(false); login.setBorderPainted(false); login.setContentAreaFilled(false);
		//Opaco, ver fondo		//No ver el borde del boton    //No ver en blanco el fondo del boton
		registro.setOpaque(true);    registro.setBorderPainted(false);   registro.setContentAreaFilled(false);
		
		login.setBorder(BorderFactory.createEmptyBorder(0, 150, 0, 0));
		
		panelLogin.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 60));
		panelLogin.setOpaque(false);
		panelLogin.add(login);
		jp.add(panelLogin); 											//Añadir los botones al JPanel jp
		jp.add(registro);
		
		return jp;
	}
	
	@SuppressWarnings("unused")
	@Override
	public void actionPerformed(ActionEvent accion) {
		if(accion.getActionCommand().equals("login")) {			//Si el boton de Login se pulsa
			
			Reproductor audio = new Reproductor("sounds/sound2.wav"); //LLamada a la clase reproductor, que recibe la 
																	  //direccion del audio y lo reproduce
			
			PanelLogin log = new PanelLogin(this.ventana);   //Entrar en Login (Introducir User&Password)
			
		}else if(accion.getActionCommand().equals("registro")) {		//Si el boton de Registrarse se pulsa
			
			Reproductor audio = new Reproductor("sounds/sound2.wav");
			DialogoRegistro registro = new DialogoRegistro(ventana); //Entrar en RegistroDialog, que pide
																				 //los datos del nuevo usuario.
		}
		
	}	
}
