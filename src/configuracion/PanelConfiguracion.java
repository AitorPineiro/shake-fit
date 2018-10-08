package configuracion;

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
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import clases.Peso;
import clases.Usuario;
import clases_personalizadas.MiPanel;
import clases_personalizadas.RelojSistema;
import clases_personalizadas.Reproductor;
import inicio_sesion.PanelInicio;
import menu_seleccion.Menu;

@SuppressWarnings("serial")
public class PanelConfiguracion extends JPanel implements ActionListener{

	private final String soundON  = "icons/ON.png";
	private final String soundOFF = "icons/OFF.png";

	JFrame ventana;

	Usuario usuario;

	JButton volver;	
	JButton changePass, delete, changeWeight;
	JButton sonido;

	ImageIcon icono;				//Icono de la Ventana

	Image fondo;					//Panel
	Toolkit t;

	public PanelConfiguracion(JFrame ventana, Usuario usuario) {
		super(new BorderLayout());
		this.ventana = ventana;

		this.ventana.getContentPane().removeAll();

		this.usuario = usuario;

		this.ventana.setContentPane(crearPanelPrincipal());

		this.ventana.setVisible(true);
	}

	public Container crearPanelPrincipal() {	


		t = Toolkit.getDefaultToolkit();					//Establecer una imagen como Background de un JPanel
		fondo = t.createImage("icons/image2.jpg");

		MiPanel panel = new MiPanel(fondo);

		panel.setLayout(new BorderLayout());

		panel.add(crearPanelBotonVuelta(), BorderLayout.NORTH);
		panel.add(crearPanelSplit(),        BorderLayout.CENTER);
		return panel;
	}

	private Container crearPanelBotonVuelta() {
		JPanel panelBoton = new JPanel(new GridLayout(1,2));

		panelBoton.setOpaque(false);										  //Opaco para ver el fondo

		//panelBoton.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 600)); //Borde que queda arriba a la izda
		JPanel jp1 = new JPanel(new FlowLayout());
		jp1.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 430));
		jp1.setOpaque(false);
		volver = new JButton(new ImageIcon("icons/izda2.png"));				  //Imagen que el boton tendrá
		volver.setContentAreaFilled(false);									  //Para no ver el área que abarca el boton
		volver.setActionCommand("volver"); volver.addActionListener(this);

		jp1.add(volver);
		JPanel jp2 = new JPanel(new FlowLayout());
		jp2.setBorder(BorderFactory.createEmptyBorder(-10, 0, 0, -400));
		jp2.setOpaque(false);
		jp2.add(new JLabel(new ImageIcon("icons/logo.png")));
		panelBoton.add(jp1);
		panelBoton.add(jp2);

		return panelBoton;
	}

	private Container crearPanelSplit() {
		JSplitPane panelSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT, crearPanelConfGlobal(), crearPanelConfUsuario());
		panelSplit.setOpaque(false);
		return panelSplit;
	}

	private Component crearPanelConfUsuario() {
		JPanel panelUser = new JPanel(new BorderLayout());
		panelUser.setOpaque(false);
		JLabel jl = new JLabel("Configuración Usuario");
		jl.setForeground(Color.WHITE);
		Font f = new Font("Cambria", 3, 20);	
		jl.setFont(f);
		panelUser.add(jl, BorderLayout.NORTH);
		panelUser.add(crearPanelOpcionesUser(), BorderLayout.SOUTH);

		return panelUser;
	}

	private Component crearPanelOpcionesUser() {
		JPanel panelOptionUser = new JPanel(new GridLayout(3, 2));
		panelOptionUser.setOpaque(false);


		this.changeWeight = new JButton(new ImageIcon("icons/peso.png"));
		this.delete     = new JButton(new ImageIcon("icons/delete.png"));
		this.changePass = new JButton(new ImageIcon("icons/change.png"));

		this.changeWeight.setActionCommand("changeWeight"); this.changeWeight.addActionListener(this);
		this.delete.setActionCommand("delete"); this.delete.addActionListener(this);
		this.changePass.setActionCommand("change"); this.changePass.addActionListener(this);

		this.changeWeight.setContentAreaFilled(false); this.changeWeight.setBorderPainted(false);
		this.delete.setContentAreaFilled(false); this.delete.setBorderPainted(false);
		this.changePass.setContentAreaFilled(false); this.changePass.setBorderPainted(false);

		Font f = new Font("Cambria", 3, 15);

		JLabel jl = new JLabel("Actualizar Peso");
		jl.setForeground(Color.WHITE);
		jl.setFont(f);

		panelOptionUser.add(jl);
		panelOptionUser.add(this.changeWeight);

		JLabel jl1 = new JLabel("Cambiar Constraseña");
		jl1.setForeground(Color.WHITE);
		jl1.setFont(f);

		panelOptionUser.add(jl1);
		panelOptionUser.add(this.changePass);

		JLabel jl2 = new JLabel("Borrar Usuario");
		jl2.setForeground(Color.WHITE);
		jl2.setFont(f);

		panelOptionUser.add(jl2);
		panelOptionUser.add(this.delete);

		return panelOptionUser;
	}

	private Component crearPanelConfGlobal() {
		JPanel panelFit = new JPanel(new BorderLayout());
		panelFit.setOpaque(false);
		JLabel jl = new JLabel("Configuración de Shake&Fit");
		Font f = new Font("Cambria", 3, 20);
		jl.setForeground(Color.WHITE);
		jl.setFont(f);

		panelFit.add(jl, BorderLayout.NORTH);
		panelFit.add(crearPanelOpcionesFit(), BorderLayout.CENTER);

		return panelFit;
	}

	private Component crearPanelOpcionesFit() {
		JPanel panelOptionFit = new JPanel(new GridLayout(1 , 2));
		panelOptionFit.setOpaque(false);
		if(this.usuario.getSonido())this.sonido = new JButton(new ImageIcon(soundON));
		else this.sonido = new JButton(new ImageIcon(soundOFF));

		this.sonido.setContentAreaFilled(false); this.sonido.setBorderPainted(false);
		this.sonido.setActionCommand("sonido"); this.sonido.addActionListener(this);

		Font f = new Font("Cambria", 3, 15);

		JLabel jl = new JLabel("Sonido");
		jl.setForeground(Color.WHITE);
		jl.setFont(f);
		panelOptionFit.add(jl);
		panelOptionFit.add(this.sonido);

		return panelOptionFit;
	}

	@SuppressWarnings("unused")
	@Override
	public void actionPerformed(ActionEvent accion) {
		int opcion;

		if(accion.getActionCommand().equals("delete")) {
			opcion = JOptionPane.showConfirmDialog(ventana, "Desea borrar este usuario?",
					"Eliminar usuario", JOptionPane.OK_CANCEL_OPTION);
			switch (opcion){

			case JOptionPane.YES_OPTION:
				File borrar = new File("files/"+this.usuario.getNombre()+".txt");
				borrar.delete();
				PanelInicio inicio = new PanelInicio(this.ventana);
				break;
			}
		}

		if(accion.getActionCommand().equals("change")) {
			DialogoChangePassword ch = new DialogoChangePassword(this.ventana, this.usuario);
		}

		if(accion.getActionCommand().equals("volver")) {
			if(this.usuario.getSonido()) {
				Reproductor audio = new Reproductor("sounds/sound2.wav");
			}
			Menu men = new Menu(this.ventana, this.usuario);						//Volver a la clase Inicio
		}
		if(accion.getActionCommand().equals("changeWeight")) {
			if(this.usuario.getSonido()) {
				Reproductor audio = new Reproductor("sounds/sound2.wav");
			}
			
			SeriekoLineaKontrolatzailea fpga = new SeriekoLineaKontrolatzailea(this.ventana);
			RelojSistema reloj = new RelojSistema();	
			try {
				Double peso = (double) fpga.leerPeso();		
				fpga.close();
				int opcion1 = JOptionPane.showConfirmDialog (getParent(), "El peso recogido es: "+peso+"\n ¿es correcto?","Atención",JOptionPane.YES_NO_OPTION);
				
				switch (opcion1){
				case JOptionPane.YES_OPTION:
					this.usuario.getPeso().add(new Peso(peso, reloj.getFecha()));
					break;
				}				
			} catch (IOException e1) {
				System.out.println("ERROR");
			}
		}

		if(accion.getActionCommand().equals("sonido")) {
			if(usuario.getSonido()) {
				this.sonido.setIcon((new ImageIcon(soundOFF)));
				this.usuario.setSonido(false);
			}
			else {
				this.sonido.setIcon((new ImageIcon(soundON)));
				this.usuario.setSonido(true);
			}

		}
	}
}

