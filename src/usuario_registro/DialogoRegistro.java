package usuario_registro;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import acceso_ficheros.EscrituraUsuarioFicheros;
import clases.Usuario;
import clases_personalizadas.MiPanel;

@SuppressWarnings("serial")
public class DialogoRegistro extends JDialog implements ActionListener{

	JFrame ventana;											//Es el JFrame de la clase Inicio
	
	MiPanel jp;												//Clase que crea un JPanel con una imagen de fondo
	PanelSelectorPeso pPeso;	
	
	JTextField textField;									//Campo de texto para introducir user/palabra_clave/peso
	JPasswordField passwordField1, passwordField2;			/*Campos para introducir contraseña 1. Contraseña 2 para confirmar password*/
	JRadioButton radioButtonHombre, radioButtonMujer;		//Sexo del user
	
	int contador = 0;										//Contador para saber en qué dato está
	
	JButton boton;											//Boton de Siguiente o finalizar, dependiendo de los valores del contador
	
	String [] datosUsuario;
		
	public DialogoRegistro(JFrame ventana) {
		
		super(ventana, "Registro", true);			//Para que bloquee las acciones fuera de ese dialog de Registro
		
		this.datosUsuario = new String [5];         //Numero de datos que se piden rellenar
		
		this.ventana = ventana;		
		
		jp = new MiPanel(Toolkit.getDefaultToolkit().createImage("icons/dialog.gif"));					//Nuevo panel con imagen animada de fondo
		
		jp.setLayout(new BorderLayout());
		
		this.setLocation(430, 150);
		this.setSize(400, 400);
		
		this.crearPanelDialog();
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
	
	private void nuevoUsuario() {
		Usuario nuevoUsuario = new Usuario(datosUsuario[0], datosUsuario[1], datosUsuario[2],  
										Double.parseDouble(datosUsuario[3]), datosUsuario[4]); 	//Nuevo usuario. Clase Usuario
		//Crear fichero.
		File nuevoFile = new File("files/"+nuevoUsuario.getNombre()+".txt");		
		try {
			nuevoFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		EscrituraUsuarioFicheros escrituraUser = new EscrituraUsuarioFicheros("files/"+nuevoUsuario.getNombre()+".txt", nuevoUsuario);
		escrituraUser.escribir();		
	}

	public boolean comprobarExistenciaUsuario(String nombreUser){		    //Comprueba si el nombre del usuario existe en la lista
		File fichero = new File("files/"+nombreUser+".txt");
		if(fichero.exists()){
			return true;
		}		
		return false;														//Si no existe devuelve FALSE
	}

	private void crearPanelDialog() {
		
		String nombre = "";				
		String direccion = "";
		
		this.setContentPane(this.jp);
		
		this.jp.removeAll(); //Borrar todos los componentes del panel;
		this.jp.updateUI();  //Refrescar el panel;

		
		if(contador < 4) {						//Si no es el último dato a pedir, el boton será "Siguiente"
			nombre = "Siguiente";
			direccion = "icons/siguiente.png";
		}
		else if(contador == 4){					//Si es el último dato a pedir, el botón será "Finalizar"
			nombre = "Finalizar";
			direccion = "icons/finalizar.png";
		}

		jp.add(crearPanelDatos(), BorderLayout.CENTER);
		jp.add(crearPanelBoton(nombre, direccion), BorderLayout.SOUTH);
	
	}
	
	private JPanel crearPanelDatos() {			
		switch(contador) {
		case 0: return crearPanelTextField(new JPanel(new BorderLayout()),       "Nombre");
		case 1: return crearPanelPasswordField(new JPanel(new GridLayout(2, 1)), "Contraseña"); 
		case 2: return crearPanelTextField(new JPanel(new BorderLayout()),       "Palabra Clave");
		case 3: return this.pPeso = new PanelSelectorPeso(); // Peso
		case 4: return crearPanelRadioButton(new JPanel(new GridLayout(2, 1)),   "Sexo");		
		default: break;
		}
		return null;		
	}
	
	private JPanel crearPanelTextField(JPanel panel, String tipoDeDato) {
		this.textField = new JTextField();
		
		panel.setBorder(BorderFactory.createEmptyBorder(140,25,105,25));
		panel.setOpaque(false);											 		 //Para que se vea la animacion de fondo
		
		Font f = new Font("Cambria", 3, 16);							 		 //Escoger fuente y tamaño de letra
		
		this.textField.setBorder(BorderFactory.createTitledBorder(tipoDeDato));  /*Escribe nombre, palabra clave, peso, dependiendo
																					del valor del contador*/
		this.textField.setFont(f);												 //Establecer fuente
		
		panel.add(textField, BorderLayout.CENTER);
		
		return panel;
	}
	
	private JPanel crearPanelPasswordField(JPanel jPanel, String str) {				//El String es un identificador, no se usa
		jPanel.setBorder(BorderFactory.createEmptyBorder(100,25,100,25));			//Crear un borde
		
		jPanel.setOpaque(false);													//Para que se vea la animacion de fondo
		
		this.passwordField1 = new JPasswordField();
		this.passwordField2 = new JPasswordField();
		
		this.passwordField1.setBorder(BorderFactory.createTitledBorder("Establezca una Contraseña")); //Crear un borde con titulo
		this.passwordField2.setBorder(BorderFactory.createTitledBorder("Repita la Contraseña"));      //Crear un borde con titulo
		
		jPanel.add(passwordField1);
		jPanel.add(passwordField2);
		
		return jPanel;
	}
	
	private JPanel crearPanelRadioButton(JPanel jPanel, String str) {				//El String es un identificador, no se usa
		
		ButtonGroup grupoRadioButton = new ButtonGroup();							//Nuevo ButtonGroup para gestionar Mujer/Hombre 					
		
		jPanel.setOpaque(false);													//Para que se vea la animación de fondo
		jPanel.setBorder(BorderFactory.createEmptyBorder(130,130,130,130));			//Borde vacío
		
		this.radioButtonHombre = new JRadioButton("Hombre");
		this.radioButtonMujer = new JRadioButton("Mujer");
		
		grupoRadioButton.add(this.radioButtonMujer);
		grupoRadioButton.add(this.radioButtonHombre);
		
		this.radioButtonMujer.setSelected(true);							//El boton de la Mujer estará por defecto seleccionado
		this.radioButtonHombre.setSelected(false);							//El boton de la Hombre no estará por defecto seleccionado
		
		jPanel.add(this.radioButtonMujer);									//El boton de mujer primero ya que estadísticamente
		jPanel.add(this.radioButtonHombre);									//hay más mujeres que hombre en el mundo.
		
		return jPanel;
	}
	
	private JPanel crearPanelBoton(String nombre, String direccion) {
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
	
	@Override
	public void actionPerformed(ActionEvent accion) {
		if(accion.getActionCommand().equals("Siguiente")) {
			
			if(contador == 0 || contador == 2){                   //Nombre, Palabra Clave, Peso
				
				if(!textField.getText().equals("")){							   // Comprueba si se ha introducido algo o no
					//Para comprobar si el usuario ya existe de antemano.
					if(contador == 0 && textField.getText().equals("admin")) {
						JOptionPane.showConfirmDialog(this, "No se puede crear el usuario admin","Error",
								JOptionPane.CLOSED_OPTION); //Nuevo dialog con error.
							textField.setText("");
					}
					if(contador == 0 && comprobarExistenciaUsuario(textField.getText()) == true){ /*Si el contador es 0 (Nombre)
					 																				y el nombre SI está la lista*/
						JOptionPane.showConfirmDialog(this, "El usuario: " +textField.getText() +" ya existe!","Error",
																	JOptionPane.CLOSED_OPTION); //Nuevo dialog con error.
						textField.setText("");
					} else{ 
						datosUsuario[contador] = textField.getText(); //Guarda el texto en el String [] datosUsuarios
						contador++;
						
						this.crearPanelDialog();					  //Con un nuevo contador, un dialog distinto
					}
				}
				
			}else if(contador == 1){ //Contraseña
				
				if(!String.valueOf(passwordField1.getPassword()).equals("") && 
						!String.valueOf(passwordField2.getPassword()).equals("") ){ //Ningun PasswordField debe estar vacío
					
					
					if(String.valueOf(passwordField1.getPassword()).equals( 
						String.valueOf(passwordField2.getPassword()))){ 			//Si ambas Contraseñas son iguales
						
							datosUsuario[contador] = String.valueOf(passwordField1.getPassword()); /*Guarda el texto en el 
																									String [] datosUsuarios*/
							//String.valueOf --> Hay que poner porque no se puede acceder directamente al texto de un JPasswordField
							contador++;
							this.crearPanelDialog();							//Con un nuevo contador, un dialog distinto
					}
					else {
						JOptionPane.showConfirmDialog(this, "Las dos contraeñas no coinciden.","Error",
																	JOptionPane.CLOSED_OPTION); //Nuevo Dialog con error, no coinciden
						passwordField1.setText("");
						passwordField2.setText("");
					}
				}
			}else if(contador == 3){
				datosUsuario[contador] = String.valueOf(pPeso.getPeso());
				this.contador++;
				this.crearPanelDialog();
			}
			
		}else if(accion.getActionCommand().equals("Finalizar")){		//Finalizar

			if (radioButtonHombre.isSelected()) datosUsuario[contador] = "Hombre";  //Hombre --> Introducir al String
			else datosUsuario[contador] = "Mujer";									//Mujer  --> Introducir al String 
			
			this.nuevoUsuario();	
			
			this.dispose();				//Cierra el JDialog para que se pueda usar de nuevo el JFrame de la clase Inicio						
		}		
	}
}
