package inicio_sesion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import acceso_ficheros.LecturaUsuariosFicheros;
import clases.Usuario;
import clases_personalizadas.MiPanel;
import clases_personalizadas.Reproductor;
import menu_seleccion.Menu;
import tablas.PanelTablaAdmin;

public class PanelLogin  implements ActionListener{
	
	JFrame ventana;					//Le pasamos la misma Ventana desde la clase Inicio
	
	Image fondo;					//Para poner imagen de fondo de un JPanel (Misma imagen que Inicio pero en blanco/negro)
	Toolkit t;
		
	JButton volver, inicioSesion;	//Boton para volver al Inicio y para comprobar si usuario y contraseña son correctos
	
	JTextField nombreUser;			//Campo de texto para introducir el nombre del Usuario
	JPasswordField passwordUser;	//Campo de texto para introducir la contraseña
	JLabel loginIncorrecto;			//Mensaje que aparecerá al introducir mal el usuario o contraseña
	

	public PanelLogin(JFrame ventana) { //Se pasa como atributos el JFrame de Inicio y la 
															   //lista de usuarios que se ha leido del fichero.
		this.ventana = ventana;
		this.ventana.getContentPane().removeAll();
		
		t = Toolkit.getDefaultToolkit();					   //Establecer nueva imagen de fondo, que es igual pero en B y N.
		fondo = t.createImage("icons/fondo2.jpg");
		
		this.ventana.setContentPane(crearPanelVentana());
		
		this.ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.ventana.setVisible(true);
	}
	
	private MiPanel crearPanelVentana() {
		MiPanel panelVentana = new MiPanel(fondo);						//Crear un JPanel cuyo fondo sea la imagen que se le pasa
																	    //como atributo

		panelVentana.setLayout(new BorderLayout());						//Forma para organizar los elementos en el JPanel pVentana
		
		panelVentana.add(crearPanelNorte(), BorderLayout.NORTH);	
		panelVentana.add(crearPanelEscritura(), BorderLayout.CENTER);
		panelVentana.add(crearPanelBotonInicioSesion(), BorderLayout.SOUTH);
				
		return panelVentana;
	}
	
	private Container crearPanelEscritura() {
		JPanel panelEscritura = new JPanel(new GridLayout(3, 1, 50, 50));		//3 filas, 1 columna, 50 hgap, 50 hgap
		
		panelEscritura.setOpaque(false);										//Debe ser opaco para ver el fondo
		
		loginIncorrecto = new JLabel("Usuario o contraseña incorrecto"); loginIncorrecto.setForeground(Color.red); 
		loginIncorrecto.setVisible(false); //Mensaje que aparece cuando se introduce mal el Usuario o Contraseña
		
		Font f = new Font("Cambria", 3, 20);									//Establecer la fuente y el tamaño del campo de texto
		panelEscritura.setBorder(BorderFactory.createEmptyBorder(50, 200, -5, 200));

		nombreUser = new JTextField();
		passwordUser = new JPasswordField();
		
		nombreUser.setFont(f); nombreUser.setBorder(BorderFactory.createTitledBorder("Usuario")); //Establecer Fuente y borde titulado
		passwordUser.setBorder(BorderFactory.createTitledBorder("Contraseña"));					  //Establecer borde titulado
		
		panelEscritura.add(nombreUser);
		panelEscritura.add(passwordUser);
		panelEscritura.add(loginIncorrecto);
		
		return panelEscritura;
	}
	private Container crearPanelBotonVuelta(JPanel pBoton) {
		pBoton.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 650));
		

		volver = new JButton(new ImageIcon("icons/izda2.png"));				  //Imagen que el boton tendrá
		volver.setContentAreaFilled(false);									  //Para no ver el área que abarca el boton
		volver.setActionCommand("volver"); volver.addActionListener(this);
		
		pBoton.add(volver);
		pBoton.setOpaque(false);
		
		return pBoton;
	}
	private Container crearPanelNorte() {
		JPanel panelBoton = new JPanel(new BorderLayout());
		//panelBoton.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 650));
		panelBoton.setOpaque(false);										  //Opaco para ver el fondo
		
		panelBoton.add(crearPanelBotonVuelta(new JPanel(new FlowLayout())), BorderLayout.EAST);
		panelBoton.add(crearPanelImagenLogo (new JPanel(new FlowLayout())), BorderLayout.WEST);
			
		
		return panelBoton;
	}
	
	private Component crearPanelImagenLogo(JPanel panelLogo) {
		JLabel logo = new JLabel(new ImageIcon("icons/logo.png"));
		panelLogo.setBorder(BorderFactory.createEmptyBorder(0, 632, 0, 0));
		
		panelLogo.add(logo);
		panelLogo.setOpaque(false);
		
		return panelLogo;
	}

	private Container crearBoton(String nombreBoton) {
		inicioSesion = new JButton(new ImageIcon("icons/inicio.png")); //El boton tendra la siguiente imagen
		
		inicioSesion.setContentAreaFilled(false); 					   //No se muestra el área que abarca el botón
		
		inicioSesion.setActionCommand(nombreBoton); inicioSesion.addActionListener(this);
		
		return inicioSesion;
	}

	private Container crearPanelBotonInicioSesion() {
		JPanel pBtn = new JPanel(new FlowLayout());
		
		pBtn.setOpaque(false);				//Para que se vea el fondo

		pBtn.add(crearBoton("Validar"));	//Nuevo boton. String nombreBoton = "Validar"
		
		return pBtn;
	}

	@SuppressWarnings("unused")
	@Override
	public void actionPerformed(ActionEvent accion) {
		Usuario user;
		if(accion.getActionCommand().equals("Validar")) {	/*Si se le da al botón de Iniciar Sesion y el tamaño de la lista es distinto de 0,
																							 ya que debe haber usuarios para hacer Login.*/
			
			Reproductor audio = new Reproductor("sounds/sound2.wav");						//Clase Reproductor que reproduce el audio
			if(this.nombreUser.getText().equals("admin") && String.valueOf(this.passwordUser.getPassword()).equals("admin")) {
				PanelTablaAdmin tablaAdmin = new PanelTablaAdmin(this.ventana);
				loginIncorrecto.setVisible(false);
			}
			LecturaUsuariosFicheros leerUser = new LecturaUsuariosFicheros("files/"+this.nombreUser.getText()+".txt");
			user = leerUser.leer();
			if(user != null){
				if(this.nombreUser.getText().equals(user.getNombre()) && String.valueOf(this.passwordUser.getPassword()).equals(user.getContraseña())){
					Menu menu = new Menu(this.ventana, user);
				}
			}		
		
			loginIncorrecto.setVisible(true); 	//Mostrar el mensaje de error "Usuario o contraseña incorrecto"
			passwordUser.setText("");		    //Borrar la contraseña introducida del JPasswordField
			} else {
				loginIncorrecto.setVisible(true);	//Mostrar el mensaje de error "Usuario o contraseña incorrecto"
				passwordUser.setText("");			 //Borrar la contraseña introducida del JPasswordField
			}
		
		
		if(accion.getActionCommand().equals("volver")){
			PanelInicio inicio = new PanelInicio(this.ventana);
		}
	}
}
