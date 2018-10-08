package ejercicios;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import clases.Ejercicio;
import clases.RegistroActividades;
import clases.Usuario;
import clases_personalizadas.RelojSistema;

@SuppressWarnings("serial")
public class PanelEjerciciosEspecificos extends JPanel implements ActionListener{

	JFrame ventana;
	Usuario usuario;
	List<Ejercicio> listaEjercicios;
	DefaultListModel<JButton> modeloLista;
	private final String botonAtras = "icons/izda.png";
	String titulo;

	public PanelEjerciciosEspecificos(JFrame ventana, Usuario usuario, List<Ejercicio> ejercicios, String titulo) {
		super(new BorderLayout());

		this.ventana = ventana;
		this.usuario = usuario;
		this.listaEjercicios = ejercicios;
		this.titulo = titulo;
		
		this.add(this.crearPanelNorte(), BorderLayout.NORTH);
		this.add(this.crearPanelLista(), BorderLayout.CENTER);

		this.ventana.setResizable(true);
		this.ventana.setContentPane(this);
	}

	private Component crearPanelLista() {

		JScrollPane sPane = new JScrollPane();

		JPanel lista = new JPanel(new GridLayout(this.listaEjercicios.size(), 2));
		lista.setBorder(BorderFactory.createEmptyBorder(0, 400, 0, 400));
		sPane.setViewportView(lista);
		JLabel nombreEjercicio;
		JButton botones;

		for(int i = 0; i<this.listaEjercicios.size(); i++){

			botones = new JButton(new ImageIcon(this.listaEjercicios.get(i).getImagen()));
			botones.setPreferredSize(new Dimension(250,250));
			
			botones.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4, true));
			
			
			botones.addActionListener(this);
			botones.setActionCommand(String.valueOf(i));
			
			nombreEjercicio = new JLabel(this.listaEjercicios.get(i).getNombre());
			nombreEjercicio.setFont(new Font("Cambria", 30, 30));
			
			
			lista.add(botones);
			lista.add(nombreEjercicio);
			
		}
		return sPane;		
	}

	private Component crearPanelNorte() {
		JPanel panelNorte = new JPanel(new BorderLayout());
		JLabel label = new JLabel(this.titulo);
		
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setFont(new Font("Cambria", 70, 70));
		
		
		JButton botonAtras = new JButton(new ImageIcon(this.botonAtras));
		botonAtras.setContentAreaFilled(false); botonAtras.setBorderPainted(false);
		botonAtras.addActionListener(this);
		botonAtras.setActionCommand("atras");
		
		panelNorte.add(botonAtras, BorderLayout.WEST);
		panelNorte.add(label, BorderLayout.CENTER);
		
		return panelNorte;		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("atras")){
			@SuppressWarnings("unused")
			PanelSelectorEjerciciosLibres ejerciciosLibres = new PanelSelectorEjerciciosLibres(this.ventana, this.usuario);
		}else{ //Cuando selecciona un ejercicio de la lista.
			DialogoRepeticiones dRep = new DialogoRepeticiones(this.ventana, this.usuario);
			if(dRep.getRepeticiones() != 0){				
				
				List<Ejercicio> ejercicio = new ArrayList<>();
				ejercicio.add(new Ejercicio(this.listaEjercicios.get(Integer.valueOf(e.getActionCommand())).getNombre(),
											this.listaEjercicios.get(Integer.valueOf(e.getActionCommand())).getGif(),
											this.listaEjercicios.get(Integer.valueOf(e.getActionCommand())).getImagen(),
											dRep.getRepeticiones()));
				
				long start_time, final_time;
				RelojSistema fecha = new RelojSistema();
				start_time = System.currentTimeMillis();
				
				DialogoHaciendoEjercicio ej = new DialogoHaciendoEjercicio(this.ventana, this.usuario, ejercicio);
				final_time = System.currentTimeMillis();
				ej.dialogoCompletado();
				this.usuario.getRegistroEjercicios().add(new RegistroActividades(ejercicio.get(0).getNombre(), String.valueOf((final_time-start_time)/1000), fecha.getFechaa(), ej.getCompletado()));
			}
		}		
	}
}
