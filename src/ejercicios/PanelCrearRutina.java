package ejercicios;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import acceso_ficheros.LeerEjercicios;
import clases.Ejercicio;
import clases.Rutina;
import clases.Usuario;

@SuppressWarnings("serial")
public class PanelCrearRutina extends JPanel implements ActionListener{

	JFrame ventana;
	Usuario usuario;
	
	JList<String> listaEjercicios;
	DefaultListModel<String> modeloLista;
	
	JTextField nombreTextField;
	List<Ejercicio> listaTodosEjercicios;
	
	Rutina rutina;


	public PanelCrearRutina(JFrame ventana, Usuario usuario){
		super(new BorderLayout());	
		this.ventana = ventana;
		this.usuario = usuario;
		this.rutina = new Rutina();
		
		this.crearPanelPrincipal();

		this.ventana.setResizable(true);
		this.ventana.setContentPane(this);
		this.ventana.setVisible(true);
		this.ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void crearPanelPrincipal(){	
		this.add(crearPanelNorte(), BorderLayout.NORTH);
		this.add(crearPanelSur(), BorderLayout.SOUTH);
		this.add(crearPanelSplit(), BorderLayout.CENTER);				
	}

	private Container crearPanelNorte(){
		JPanel panel = new JPanel(new BorderLayout());
		JButton botonAtras = new JButton(new ImageIcon("icons/izda.png"));
		botonAtras.setContentAreaFilled(false); botonAtras.setBorderPainted(false);
		panel.add(botonAtras, BorderLayout.WEST);
		botonAtras.addActionListener(this);
		botonAtras.setActionCommand("atras");

		return panel;
	}

	private Container crearPanelSur(){
		JPanel panel = new JPanel();
		
		JButton fin = new JButton("Finalizar");

		fin.addActionListener(this);
		fin.setActionCommand("finalizar");

		nombreTextField = new JTextField("");
		nombreTextField.setPreferredSize(new Dimension(250,50));
		nombreTextField.setBorder(BorderFactory.createTitledBorder(/*BorderFactory.createLineBorder(Color.BLACK), */"Nombre de la rutina"));

		panel.add(fin);
		panel.add(nombreTextField);

		return panel;
	}
	
	private Container crearPanelSplit(){
		JSplitPane panel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, this.crearPanelLista(), this.crearPanelBotonesEjercicios());
		panel.setDividerLocation(300);

		return panel;
	}	

	private Component crearPanelBotonesEjercicios() {
		JScrollPane panelScroll = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		JPanel panel = new JPanel();		
		JButton boton;
		JLabel nombreEjercicio;
		panelScroll.setViewportView(panel);

		try {
			LeerEjercicios leerEj = new LeerEjercicios();
			listaTodosEjercicios = leerEj.leerTodos();
			
			panel.setLayout(new GridLayout(listaTodosEjercicios.size(), 2));
			
			
			for(int i = 0; i< listaTodosEjercicios.size(); i++){
				nombreEjercicio = new JLabel(this.listaTodosEjercicios.get(i).getNombre());
				nombreEjercicio.setFont(new Font("Cambria", 30, 30));
				
				
				boton = new JButton(new ImageIcon(listaTodosEjercicios.get(i).getImagen()));
				boton.addActionListener(this);
				boton.setActionCommand(String.valueOf(i));
				boton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
				
				panel.add(boton);
				panel.add(nombreEjercicio);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}			
		return panelScroll;
	}

	private Component crearPanelLista() {
		JScrollPane panelScroll = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		JPanel panel = new JPanel(new BorderLayout());
		JButton borrarEjercicioLista = new JButton(new ImageIcon("icons/borrar.png"));

		this.listaEjercicios = new JList<>();
		this.modeloLista = new DefaultListModel<>();
		this.listaEjercicios.setModel(this.modeloLista);
		panelScroll.setViewportView(this.listaEjercicios);

		borrarEjercicioLista.addActionListener(this);
		borrarEjercicioLista.setActionCommand("borrar");
		borrarEjercicioLista.setContentAreaFilled(false); borrarEjercicioLista.setBorderPainted(false);
		panel.add(borrarEjercicioLista, BorderLayout.NORTH);
		panel.add(panelScroll, BorderLayout.CENTER);

		return panel;
	}

	private boolean nombreRutinaEnUso(String nombreRutina){
		for(int i=0;i<this.usuario.getRutinas().size();i++){
			if(this.usuario.getRutinas().get(i).getNombre().equals(nombreRutina)){
				return true;
			}
		}
		
		return false;
	}
	

	@SuppressWarnings("unused")
	@Override
	public void actionPerformed(ActionEvent e) {
		String boton;
		
		if(e.getActionCommand().equals("borrar") && this.listaEjercicios.getSelectedValue() != null){
			this.rutina.getListaEjercicios().remove(this.listaEjercicios.getSelectedIndex());
			this.modeloLista.remove(this.listaEjercicios.getSelectedIndex());
			
		}else if(e.getActionCommand().equals("finalizar")){
			
			if(nombreTextField.getText().equals("") || modeloLista.size() == 0 || nombreRutinaEnUso(nombreTextField.getText()) == true){
				if(nombreTextField.getText().equals("")) {
					JOptionPane.showMessageDialog(ventana, "La rutina debe de tener un nombre");
				}
				else if(modeloLista.size() == 0) {
					JOptionPane.showMessageDialog(ventana, "No se puede crear una rutina sin ejercicios");
				}
				else if(nombreRutinaEnUso(nombreTextField.getText()) == true){
					JOptionPane.showMessageDialog(ventana, "Este nombre de rutina ya esta en uso");
				}
			}
			else {
				this.rutina.setNombre(this.nombreTextField.getText());
				this.usuario.getRutinas().add(this.rutina);
				PanelWorkOut pWorkOut = new PanelWorkOut(this.ventana, this.usuario);
			}

		}else if (e.getActionCommand().equals("atras")){
			PanelWorkOut work = new PanelWorkOut(this.ventana, this.usuario);
		}else{
			for(int i = 0; i < this.listaTodosEjercicios.size(); i++){
				if(e.getActionCommand().equals(String.valueOf(i))){
					DialogoRepeticiones repeticiones = new DialogoRepeticiones(this.ventana, this.usuario);
					if(repeticiones.getRepeticiones() != 0){
						this.modeloLista.addElement(this.listaTodosEjercicios.get(i).getNombre()+ "  " + repeticiones.getRepeticiones()+ " repeticiones");
						
						this.listaTodosEjercicios.get(i).setRepeticiones(repeticiones.getRepeticiones());
						this.rutina.getListaEjercicios().add(this.listaTodosEjercicios.get(i));
											
					}					
				}
			}
		}		
	}
}
