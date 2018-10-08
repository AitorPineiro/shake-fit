package tablas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import clases.Usuario;
import menu_seleccion.Menu;

@SuppressWarnings("serial")
public class PanelTablas extends JPanel implements ActionListener{
	JFrame ventana;
	Usuario usuario;
	Trazador trazador;
	ModeloTabla tablaModel;
	ModeloColumnas columnas;
	JTable tabla;
	
	public PanelTablas(JFrame ventana, Usuario usuario){
		super(new BorderLayout());
		
		this.ventana = ventana;
		this.usuario = usuario;
		
		this.trazador = new Trazador();
		
		
		this.columnas = new ModeloColumnas (trazador);
	
		
		this.add(crearPanelNorte(), BorderLayout.NORTH);
		this.add(crearPanelSplit(), BorderLayout.CENTER);
		
		this.ventana.setResizable(true);
		this.ventana.setContentPane(this);
		this.ventana.setVisible(true);
		this.ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private Component crearPanelNorte() {
		JPanel panel = new JPanel();
		JButton botonAtras = new JButton(new ImageIcon("icons/izda.png"));
		
		botonAtras.setHorizontalAlignment(JButton.CENTER);
		botonAtras.setActionCommand("atras");
		botonAtras.addActionListener(this);
		
		panel.add(botonAtras);
		
		return panel;
	}

	private Component crearPanelSplit(){
		JSplitPane panel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		panel.setDividerLocation(680);

		panel.setLeftComponent(crearPaneIzq());
		panel.setRightComponent( crearPaneDer());

		return panel;
	}
	

	private Component crearPaneDer(){
		JPanel panel = new JPanel(new BorderLayout());
		JLabel titulo = new JLabel("Registro de ejercicios");
		
		titulo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		titulo.setFont(new Font("Arial", 30,30));
		titulo.setHorizontalAlignment(JLabel.CENTER);
		
		
		panel.add(titulo, BorderLayout.NORTH);
		panel.add(crearPanelTablaEjercicios(), BorderLayout.CENTER);

		return panel;
	}
	
	
	private Component crearPaneIzq(){
		JPanel panel = new JPanel(new BorderLayout());
		JLabel titulo = new JLabel("Registro de rutinas");
		
		titulo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		titulo.setFont(new Font("Arial", 30,30));
		titulo.setHorizontalAlignment(JLabel.CENTER);
		
		
		panel.add(titulo, BorderLayout.NORTH);
		panel.add(crearPanelTablaRutinas(), BorderLayout.CENTER);
		
		return panel;
	}
	

	
	private Component crearPanelTablaEjercicios() {
		this.tablaModel = new ModeloTabla(columnas, this.usuario.getRegistroEjercicios());
		tabla = new JTable(tablaModel, columnas);
		JScrollPane panel = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panel.setViewportView(tabla);
		
		return panel;
	}
	
	private Component crearPanelTablaRutinas() {
		this.tablaModel = new ModeloTabla(columnas, this.usuario.getRegistroRutinas());
		tabla = new JTable(tablaModel, columnas);
		JScrollPane panel = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panel.setViewportView(tabla);
		
		return panel;
	}
	
	
	
	public class Trazador extends DefaultTableCellRenderer{
		@Override
		public Component getTableCellRendererComponent(JTable table, Object valor,
				boolean isSelected, boolean hasFocus, int fila, int columna) {
			
			super.getTableCellRendererComponent(table, valor, isSelected, hasFocus, fila, columna);
				switch (columna){
				case 0: 
				case 1: 
				case 2: this.setHorizontalAlignment(CENTER);break; 
				case 3: if((boolean)valor == true){
							JLabel texto = new JLabel("SI");
							texto.setForeground(Color.GREEN);
							return texto;
						}else{
							JLabel texto = new JLabel("NO");
							texto.setForeground(Color.RED);
							return texto;
						}
				}
			
			return this;
		}
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("atras")){
			@SuppressWarnings("unused")
			Menu men = new Menu(this.ventana, this.usuario);
		}
	}

}
