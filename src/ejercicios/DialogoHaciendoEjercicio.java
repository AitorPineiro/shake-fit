package ejercicios;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import clases.Ejercicio;
import clases.Usuario;
import clases_personalizadas.MiPanel;

@SuppressWarnings("serial")
public class DialogoHaciendoEjercicio extends JDialog implements ActionListener{
	
	JFrame ventana;
	Usuario usuario;
	MiPanel panel;
	List<Ejercicio> ejercicios;
	boolean completado;
	
	private int contador = 0;
	
	public DialogoHaciendoEjercicio(JFrame ventana, Usuario usuario, List<Ejercicio> ejercicios){
		super(ventana, "Registro", true);
		
		this.completado = false;
		this.usuario = usuario;
		this.ventana = ventana;	
		this.ejercicios = ejercicios;
		
		this.setContentPane(crearPanelGif());
				
		this.setSize(600, 600);
		//this.setLocation(this.ventana.getLocation());
		this.setLocation(200, 100);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);		
	}
	
	private Container crearPanelGif(){
		JPanel panelPrinci = new JPanel(new BorderLayout());
		
		this.panel = new MiPanel(Toolkit.getDefaultToolkit().createImage(this.ejercicios.get(this.contador).getGif()));
		this.contador++;
		panelPrinci.add(panel, BorderLayout.CENTER);
		panelPrinci.add(crearPanelFinalizar(), BorderLayout.SOUTH);
	
		return panelPrinci;
	}
	
	private Container crearPanelFinalizar(){
		JPanel panel = new JPanel();
		JButton finalizar = new JButton("Finalizar");
		
		finalizar.setHorizontalAlignment(JButton.CENTER);
		finalizar.addActionListener(this);
		finalizar.setActionCommand("finalizar");
		//finalizar.setPreferredSize(new Dimension(150,150));
		panel.add(finalizar);
		
		return panel;
	}

	public boolean getCompletado(){
		
		return this.completado;
	}
	
	public void dialogoCompletado(){
		if(this.completado == false){
			JOptionPane.showMessageDialog(ventana, "No te rindas tan pronto, la proxima vez a completar!!");
		}else{
			JOptionPane.showMessageDialog(ventana, "Muy bien, has finalizado!!");
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("finalizar")){
			if(this.ejercicios.size() > this.contador){	
				this.panel.setImagen(Toolkit.getDefaultToolkit().createImage(this.ejercicios.get(this.contador).getGif()));		
				this.contador++;
			}else{
				this.dispose();
				completado = true;
			}
	
		}		
	}	
}
