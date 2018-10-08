package tablas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import acceso_ficheros.LecturaUsuariosFicheros;
import clases.Usuario;
import inicio_sesion.PanelInicio;

@SuppressWarnings("serial")
public class PanelTablaAdmin extends JPanel implements ActionListener{
	JFrame ventana;
	ModeloTablaAdmin tablaModel;
	ModeloColumnasAdmin columnas;
	JTable tabla;


	public PanelTablaAdmin(JFrame ventana){
		super(new BorderLayout());
		
		this.ventana = ventana;
		this.columnas = new ModeloColumnasAdmin();
	
		
		this.add(crearPanelNorte(), BorderLayout.NORTH);
		this.add(crearPanelTablaLista(), BorderLayout.CENTER);
		
		this.ventana.setResizable(true);
		this.ventana.setContentPane(this);
		this.ventana.setVisible(true);
		this.ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private Component crearPanelNorte() {
		JPanel panel = new JPanel(new BorderLayout());
		JButton botonAtras = new JButton(new ImageIcon("icons/izda.png"));
		JLabel label = new JLabel("Usuarios Registrados");
		
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setFont(new Font("Arial", 40, 40));
		
		
		botonAtras.setHorizontalAlignment(JButton.CENTER);
		botonAtras.setActionCommand("atras");
		botonAtras.addActionListener(this);
		
		
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		
		panel.add(label, BorderLayout.CENTER);
		panel.add(botonAtras, BorderLayout.WEST);
		
		return panel;
	}



	private List<Usuario> leerTodosUsuarios(){
		File folder = new File("files");
		File[] listOfFiles = folder.listFiles();
		List<Usuario> usuarios = new ArrayList<>();
		
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				LecturaUsuariosFicheros fichero = new LecturaUsuariosFicheros(listOfFiles[i].getPath());
				usuarios.add(fichero.leer());
			}
		}
		
		return usuarios;
	}
	
	

	
	private Component crearPanelTablaLista() {
		this.tablaModel = new ModeloTablaAdmin(columnas, leerTodosUsuarios());
		tabla = new JTable(tablaModel, columnas);
		JScrollPane panel = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		
		panel.setViewportView(tabla);
		
		return panel;
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("atras")){
			@SuppressWarnings("unused")
			PanelInicio inicio = new PanelInicio(this.ventana);
		}
	}
}
