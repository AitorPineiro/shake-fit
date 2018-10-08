package menu_seleccion;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import acceso_ficheros.EscrituraUsuarioFicheros;
import clases.Usuario;
import clases_personalizadas.RelojSistema;
import configuracion.PanelConfiguracion;
import ejercicios.PanelElectorEjercicios;
import inicio_sesion.PanelInicio;
import motivador.DialogoGeneradorMotivacion;
import tablas.PanelTablas;

public class Menu implements ActionListener, WindowListener{

	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	JFrame ventana;						//Se le pasa el JFrame del Login, se cambia el tamaño	
	Usuario usuario;					//Se le pasa el usuario con el que se ha Iniciado Sesiaon

	//Coge la resolución de la pantalla que tengas, sea cual sea
	
	int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;		//Ancho
	int alto  = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;	//Alto			

	public Menu(JFrame ventana, Usuario usuario) {		
		this.ventana = ventana;	
		this.usuario = usuario;		
		crearPanel();
	}

	public void crearPanel() {
		this.ventana.getContentPane().removeAll();  //Borrar todo lo que hay en el JFrame
		this.ventana.addWindowListener(this);		
		
		System.out.println(this.usuario.getPeso().get(0).getValor());
		System.out.println(this.usuario.getPeso().get(0).getFecha());
		
		PanelSlider<JFrame> deslizante = new PanelSlider<JFrame>(this.ventana, this.usuario);
		JPanel jPanel = deslizante.getBasePanel();
		
		deslizante.addComponent(crearBoton("start",      "icons/start2.png"));        //Boton de Ejercicios	
		deslizante.addComponent(crearBoton("calendario", "icons/calendario.png"));	  //Boton de Evolucion
		deslizante.addComponent(crearBoton("peso",       "icons/peso2.png"));	  //Boton de Estadísticas
		deslizante.addComponent(crearBoton("conf",       "icons/conf2.png"));		  //Boton de Configuración
		deslizante.addComponent(crearBoton("out",        "icons/out.png"));			  //Boton para Cerrar Sesion	

		this.ventana.setContentPane(jPanel);
		
		this.ventana.setSize(ancho - 200, alto -100);
		this.ventana.setResizable(true);
		this.ventana.setLocationRelativeTo(null);
		this.ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.ventana.setVisible(true);

	}
	
	public Component crearBoton(String nombre, String directorio) {
		JButton boton = new JButton(new ImageIcon(directorio));	
		
		if(nombre == "start") boton.setEnabled(true);
		boton.setActionCommand(nombre);boton.addActionListener(this);
		//boton.setContentAreaFilled(false);									//Para que no se vea el área del botón
		
		return boton;
	}
	
	@SuppressWarnings("unused")
	@Override
	public void actionPerformed(ActionEvent accion) {
		
		if(accion.getActionCommand().equals("conf")) {
			System.out.println("Has pulsado el boton de configuracion");			
			PanelConfiguracion conf = new PanelConfiguracion(this.ventana, this.usuario);
		}
		if(accion.getActionCommand().equals("peso")) {
			System.out.println("Has pulsado el boton del calendario");
			GraficoPeso grafico = new GraficoPeso(this.usuario);
			grafico.setVisible(true);			
		}
		if(accion.getActionCommand().equals("calendario")) {			
			System.out.println("Has pulsado el boton del calendario");
			PanelTablas tablaPanel = new PanelTablas(this.ventana, this.usuario);
		}
		if(accion.getActionCommand().equals("start")) {
			RelojSistema sistem = new RelojSistema();
			String fechaActual = sistem.getFechaa();
			
			//Que aparezca un mensaje motivador
			if(!fechaActual.equals(this.usuario.getUltimoInicio())) {
				DialogoGeneradorMotivacion motivacion = new DialogoGeneradorMotivacion(this.ventana, this.usuario, fechaActual);
			}
			//Tipo de ejercicio que se quiere hacer(Workout o Ejercicios Libres)
			PanelElectorEjercicios ej = new PanelElectorEjercicios(this.ventana, this.usuario);
			
		}
		if(accion.getActionCommand().equals("out")) {
			System.out.println("Has pulsado el boton de cerrar sesion");
			PanelInicio in = new PanelInicio(this.ventana);
			this.windowClosing(null);			
		}

	}



	//Window listener methods.
	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {	
		RelojSistema h = new RelojSistema();
		String dia = h.getFechaa();
		this.usuario.setUltimoInicio(dia);
		System.out.println(this.usuario.getUltimoInicio());
		File fich = new File("files/"+this.usuario.getNombre()+".txt");
		if(fich.exists()){ //Si el usuario ha sido borrado, para que no vuelva a guardar.
			EscrituraUsuarioFicheros escribirUser = new EscrituraUsuarioFicheros("files/"+this.usuario.getNombre()+".txt", this.usuario);
			escribirUser.escribir();
		}		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


}
