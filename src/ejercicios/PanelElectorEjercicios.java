package ejercicios;

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
import javax.swing.JPanel;

import clases.Usuario;
import clases_personalizadas.MiPanel;
import menu_seleccion.Menu;

@SuppressWarnings("serial")
public class PanelElectorEjercicios extends MiPanel implements ActionListener{
	Usuario usuario;
	JFrame ventana;
	
	Image fondo;					//Panel
	Toolkit t;
	
	JButton boton;

	public PanelElectorEjercicios(JFrame ventana, Usuario usuario) {
		super(Toolkit.getDefaultToolkit().createImage("icons/image3.jpg"));
		this.ventana = ventana;
		this.usuario = usuario;

		crearPanel();
	}
	private void crearPanel() {
		this.ventana.setTitle("Shake&Fit´s User: " + usuario.getNombre());	
		this.ventana.getContentPane().removeAll();  //Borrar todo lo que hay en el JFrame
		
		this.crearPanelVentana();
		this.ventana.setContentPane(this);
		
		this.ventana.setResizable(true);
		this.ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.ventana.setVisible(true);
	}
	
	private void crearPanelVentana() {		
		this.setLayout(new BorderLayout());		
		this.add(crearVuelta(), BorderLayout.NORTH);
		this.add(crearPanelEjercicios(), BorderLayout.CENTER);		
	}
	
	private Component crearPanelEjercicios() {
		JPanel pEjercicios = new JPanel(new GridLayout(2 , 1));
		//pEjercicios.setBorder(BorderFactory.createEmptyBorder(50, 500, 50, 500));
		pEjercicios.setBorder(BorderFactory.createEmptyBorder(50, 400, 50, 400));
		pEjercicios.setOpaque(false);
		
		pEjercicios.add(crearBoton("workout", "icons/workout1.png"));
		pEjercicios.add(crearBoton("libre",   "icons/libre.png"));
		return pEjercicios;
	}
	private Component crearVuelta() {
		JPanel panel = new JPanel(new FlowLayout());
		
		panel.setOpaque(false);
		panel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 1000));
		
		panel.add(crearBoton("vuelta", "icons/izda2.png"));
		
		return panel;
	}
	private Component crearBoton(String tipoBoton, String imagen) {
		boton = new JButton(new ImageIcon(imagen));
		
		boton.setActionCommand(tipoBoton); boton.addActionListener(this);
		boton.setContentAreaFilled(false);	boton.setBorderPainted(false);
		
		return boton;
	}
	@SuppressWarnings("unused")
	@Override
	public void actionPerformed(ActionEvent accion) {
		if(accion.getActionCommand().equals("vuelta")) {
			Menu menu = new Menu(this.ventana, this.usuario);
		}
		if(accion.getActionCommand().equals("workout")) {
			System.out.println("workout");
			PanelWorkOut workOuts = new PanelWorkOut(this.ventana, this.usuario);
		}
		if(accion.getActionCommand().equals("libre")) {
			System.out.println("ejercicios libres");
			PanelSelectorEjerciciosLibres ejerciciosLibres = new PanelSelectorEjerciciosLibres(this.ventana, this.usuario);
		}
	}
}
