package ejercicios;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import clases.Usuario;

@SuppressWarnings("serial")
public class DialogoRepeticiones extends JDialog implements ActionListener{
	
	private final int maxRepeticiones = 100;
	JComboBox<Integer> cBox;
	int repeticiones = 0;

	public int getRepeticiones() {
		return repeticiones;
	}

	public DialogoRepeticiones(JFrame ventana, Usuario user){
		super(ventana, "Shake & Fit: Repeticiones", true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		this.setContentPane(this.crearPanelPrincipal());
		this.setSize(400, 400);
		this.setVisible(true);
	}	

	private JPanel crearPanelPrincipal() {
		JPanel panel = new JPanel(new BorderLayout());
		JPanel panelCombo = new JPanel();
		JButton botonFinalizar = new JButton("FIN");
		JLabel repeticiones = new JLabel("Repeticiones");
		
		repeticiones.setHorizontalAlignment(JLabel.CENTER);
		repeticiones.setFont(new Font("Arial", 30, 30));
	
		this.cBox = new JComboBox<>();
		cBox.setFont(new Font("Arial", 50, 50));
		cBox.setPreferredSize(new Dimension(300,250));
		botonFinalizar.addActionListener(this);		
		
		for(int i = 1; i<this.maxRepeticiones; i++){
			this.cBox.addItem(i);
		}
		
		panelCombo.add(this.cBox);
		panel.add(repeticiones, BorderLayout.NORTH);
		panel.add(botonFinalizar, BorderLayout.SOUTH);
		panel.add(panelCombo, BorderLayout.CENTER);
		cBox.setSelectedIndex(9); //Seleccion por defecto.
		
		return panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.repeticiones = (int) this.cBox.getSelectedItem();
		
		this.dispose();		
	}	
}
