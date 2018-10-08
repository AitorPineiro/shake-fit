package configuracion;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import clases.Usuario;
import clases_personalizadas.MiPanel;

@SuppressWarnings("serial")
public class DialogoChangePassword extends JDialog implements ActionListener{

	
	JFrame ventana;
	Usuario usuario;
	
	Image fondo;											//Imagen de fondo
	Toolkit t;
	
	JPasswordField passwordField1, passwordField2, passwordField3;
	JTextField textField;
	
	MiPanel jp;	
	
	JButton boton;
	
	int contador = 0;
	
	public DialogoChangePassword(JFrame ventana, Usuario usuario) {
		super(ventana, "Configuración - Cambiar Contraseña", true);
		
		this.usuario = usuario;
		
		t = Toolkit.getDefaultToolkit();			//Para introducir una imagen animada de fondo del panel
		fondo = t.createImage("icons/dialog.gif");
		
		jp = new MiPanel(fondo);					//Nuevo panel con imagen animada de fondo
		
		jp.setLayout(new BorderLayout());
		
		this.setLocation(430, 150);
		this.setSize(400, 400);
		
		this.crearPanelDialog();
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
		
	}

	private void crearPanelDialog() {
		String nombre = "";				
		String direccion = "";
		
		this.setContentPane(this.jp);
		
		this.jp.removeAll(); //Borrar todos los componentes del panel;
		this.jp.updateUI();  //Refrescar el panel;

		
		if(contador < 1) {						//Si no es el último dato a pedir, el boton será "Siguiente"
			nombre = "Siguiente";
			direccion = "icons/siguiente.png";
		}
		if(contador == 1){					//Si es el último dato a pedir, el botón será "Finalizar"
			nombre = "Finalizar";
			direccion = "icons/finalizar.png";
		}

		jp.add(crearPanelDatos(), BorderLayout.CENTER);
		jp.add(crearPanelBoton(nombre, direccion), BorderLayout.SOUTH); 
		
	}

	private Component crearPanelBoton(String nombre, String direccion) {

		JPanel panel = new JPanel();
		
		boton = new JButton(new ImageIcon(direccion));					//Dependiendo del contador tomará una imagen u otra.
																		//Siguiente / Finalizar
				
		panel.setOpaque(false);											//Para que se vea la animación de fondo
		
		boton.setContentAreaFilled(false);								//No ver el área del botón
		boton.setBorderPainted(false); 									//Para no ver el borde del boton pintado
		
		boton.setActionCommand(nombre); boton.addActionListener(this);
		
		panel.add(boton);

		return panel;
	}

	private JPanel crearPanelDatos() {
		this.jp.removeAll(); //Borrar todos los componentes del panel;
		this.jp.updateUI(); 
		
		switch(contador) {
		case 0: return crearPanelSeguridad(new JPanel(new GridLayout(2, 1)));    
		case 1: return crearPanelPasswordField(new JPanel(new GridLayout(2, 1))); 
		default: break;
		}
		return null;
	}

	private JPanel crearPanelPasswordField(JPanel jPanel) {
		jPanel.setBorder(BorderFactory.createEmptyBorder(100,25,100,25));			//Crear un borde
		
		jPanel.setOpaque(false);													//Para que se vea la animacion de fondo
		
		this.passwordField1 = new JPasswordField();
		this.passwordField2 = new JPasswordField();
		
		this.passwordField1.setBorder(BorderFactory.createTitledBorder("Establezca una nueva Contraseña")); //Crear un borde con titulo
		this.passwordField2.setBorder(BorderFactory.createTitledBorder("Repita la Contraseña"));      //Crear un borde con titulo
		
		jPanel.add(passwordField1);
		jPanel.add(passwordField2);
		
		jPanel.setVisible(true);
		
		return jPanel;
	}

	private JPanel crearPanelSeguridad(JPanel jp) {
		jp.setBorder(BorderFactory.createEmptyBorder(100,25,100,25));	
		jp.setOpaque(false);
		
		this.passwordField3 = new JPasswordField();
		this.textField = new JTextField();
		
		this.passwordField3.setBorder(BorderFactory.createTitledBorder("Introduzca la Contraseña actual")); //Crear un borde con titulo
		this.textField.setBorder(BorderFactory.createTitledBorder("Introduzca la palabra Clave")); 
		
		
		jp.add(textField);
		jp.add(passwordField3);
		
		return jp;
	}

	@Override
	public void actionPerformed(ActionEvent accion) {
		if(accion.getActionCommand().equals("Siguiente")) {
			if(contador == 0) {
				if(this.usuario.getPalabraClave().equals(this.textField.getText()) && 
				String.valueOf(passwordField3.getPassword()).equals(this.usuario.getContraseña())) {
					contador++;
					this.crearPanelDialog();
				}
				else {
					JOptionPane.showConfirmDialog(this, "No coinciden con los datos guardados","Error",
							JOptionPane.CLOSED_OPTION);
					textField.setText("");
					passwordField3.setText("");
				}
			}
		}
		if(accion.getActionCommand().equals("Finalizar")) {
			if(String.valueOf(passwordField1.getPassword()).equals( 
					String.valueOf(passwordField2.getPassword()))){ 
				//Si ambas Contraseñas son iguales
					
					this.usuario.setContraseña(String.valueOf(passwordField1.getPassword()));
					this.dispose();
				}
			else {
				JOptionPane.showConfirmDialog(this, "No coinciden las dos contraseñas","Error",
						JOptionPane.CLOSED_OPTION);
				passwordField1.setText("");
				passwordField2.setText("");
			}	
		}
	}
}
