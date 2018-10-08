package ejercicios;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import javax.swing.ListCellRenderer;

import clases.RegistroActividades;
import clases.Usuario;
import clases_personalizadas.RelojSistema;


@SuppressWarnings("serial")
public class PanelWorkOut extends JPanel implements  ActionListener{

	JFrame ventana;
	Usuario usuario;
	JList<JLabel> listaWorkOuts;
	DefaultListModel<JLabel> modeloLista;
	
	JLabel rutinasDisp;
	
	public PanelWorkOut(JFrame ventana, Usuario usuario){
		super(new BorderLayout());
		this.usuario = usuario;
		this.ventana = ventana;
		this.ventana.getContentPane().removeAll();
		
		this.add(crearPanelNorte(), BorderLayout.NORTH);
		this.add(crearPanelSur(), BorderLayout.SOUTH);
		this.add(crearListaWorkOuts(), BorderLayout.CENTER);
		
		this.ventana.setResizable(true);
		this.ventana.setContentPane(this);
		this.ventana.setVisible(true);
		this.ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
	}
	
	
	private Container crearPanelNorte(){
		JPanel panel = new JPanel(new BorderLayout());
		JButton botonAtras = new JButton(new ImageIcon("icons/izda.png"));
		JButton borrar = new JButton(new ImageIcon("icons/borrar.png"));
		
		Font f = new Font("Cambria", 3, 30);
		
		this.rutinasDisp = new JLabel("Rutinas disponibles: "+ this.usuario.getRutinas().size());	
		
		borrar.addActionListener(this);
		borrar.setActionCommand("borrar");
		borrar.setContentAreaFilled(false); borrar.setBorderPainted(false);		
		
		rutinasDisp.setHorizontalAlignment(JLabel.CENTER);
		rutinasDisp.setFont(f);
		
		panel.add(botonAtras, BorderLayout.WEST);
		botonAtras.setContentAreaFilled(false); botonAtras.setBorderPainted(false);		
		botonAtras.addActionListener(this);
		botonAtras.setActionCommand("atras");
		
		panel.add(rutinasDisp, BorderLayout.CENTER);
		panel.add(borrar, BorderLayout.EAST);
		
		return panel;		
	}
	
	private Container crearPanelSur(){
		JPanel panel = new JPanel(new GridLayout(2,1));
		panel.setBorder(BorderFactory.createEmptyBorder(0, 400, 5, 400));
		JButton crear, empezar;
		
		crear = new JButton(new ImageIcon("icons/add.png"));
		crear.setText("Crear Nueva Rutina");
		crear.addActionListener(this);
		crear.setActionCommand("crear");
		
		crear.setContentAreaFilled(false); crear.setBorderPainted(false);
		
		empezar = new JButton("Empezar");
		empezar.addActionListener(this);
		empezar.setActionCommand("empezar");
		
		empezar.setContentAreaFilled(false); empezar.setBorderPainted(false);
		
		crear.setHorizontalAlignment(JButton.CENTER);
		empezar.setHorizontalAlignment(JButton.CENTER);
		
		panel.add(empezar);
		panel.add(crear);

		return panel;		
	}
	
	
	private Component crearListaWorkOuts(){
		JScrollPane panelScroll = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		class Adaptador implements ListCellRenderer<JLabel>{

			@Override
			public Component getListCellRendererComponent(JList<? extends JLabel> list, JLabel value, int index,
					boolean isSelected, boolean cellHasFocus) {
				
				value.setFont(new Font("Arial", 60, 60));
				value.setHorizontalAlignment(JLabel.CENTER);
				value.setBorder(BorderFactory.createLineBorder(Color.GRAY, 5));
				
				if(isSelected == true){
					value.setBackground(Color.BLUE);
				}else{
					value.setBackground(Color.WHITE);
				}
				
				value.setOpaque(true);

				return value;
			}		
		}
		this.listaWorkOuts = new JList<>();		
		this.modeloLista = new DefaultListModel<>();
		this.listaWorkOuts.setModel(this.modeloLista);
		this.listaWorkOuts.setCellRenderer(new Adaptador());
		
		for(int i=0; i< this.usuario.getRutinas().size();i++){		
			JLabel label = new JLabel(this.usuario.getRutinas().get(i).getNombre());
			this.modeloLista.addElement(label);
		}
		panelScroll.setViewportView(this.listaWorkOuts);
		
		return panelScroll;
	}
	
		 
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("empezar") && this.listaWorkOuts.getSelectedValue() == null){
			JOptionPane.showMessageDialog(this.ventana, "No hay ninguna ruta seleccionada");
		}
		if(e.getActionCommand().equals("empezar") && this.listaWorkOuts.getSelectedValue() != null){
			for(int i = 0; i<this.usuario.getRutinas().size(); i++){
				if(this.usuario.getRutinas().get(i).getNombre().equals(this.listaWorkOuts.getSelectedValue().getText())){	
					long start_time, final_time;
					RelojSistema fecha = new RelojSistema();
					
					start_time = System.currentTimeMillis();
					DialogoHaciendoEjercicio empezar = new DialogoHaciendoEjercicio(this.ventana, this.usuario, this.usuario.getRutinas().get(i).getListaEjercicios());	
					final_time = System.currentTimeMillis();
					empezar.dialogoCompletado();
					this.usuario.getRegistroRutinas().add(new RegistroActividades(this.usuario.getRutinas().get(i).getNombre(), String.valueOf((final_time-start_time)/1000), fecha.getFechaa(), empezar.getCompletado()));
				}
			}
		}else if(e.getActionCommand().equals("crear")){
			@SuppressWarnings("unused")
			PanelCrearRutina dialogoRutina = new PanelCrearRutina(this.ventana, this.usuario);	
			
		}else if (e.getActionCommand().equals("atras")){
			@SuppressWarnings("unused")
			PanelElectorEjercicios elj = new PanelElectorEjercicios(this.ventana, this.usuario);
		}else if (e.getActionCommand().equals("borrar")){
			this.usuario.getRutinas().remove(this.listaWorkOuts.getSelectedIndex());
			this.modeloLista.remove(this.listaWorkOuts.getSelectedIndex());
			this.rutinasDisp.setText("Rutinas disponibles: "+ this.usuario.getRutinas().size()); 
		}		
	}


	



}
