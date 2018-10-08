package motivador;

import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import clases.Usuario;

@SuppressWarnings("serial")
public class DialogoGeneradorMotivacion extends JDialog{
	Usuario usuario;
	String fechaActual;
	JFrame ventana;
	public DialogoGeneradorMotivacion(JFrame ventana, Usuario usuario, String fechaActual) {
		super(ventana, "Frase del día", true);
		this.usuario = usuario;
		this.fechaActual = fechaActual;
		this.crearVentana();
	}
	
	private void crearVentana() {
		this.setSize(500, 500);
		this.setLocation(300, 100);
		this.setContentPane(crear());
		this.usuario.setUltimoInicio(fechaActual);
		this.setVisible(true);
	}
	
	private Container crear() {
		Motivador mensaje = new Motivador();
		JPanel panel = new JPanel(new FlowLayout());
		JLabel jl = new JLabel(new ImageIcon(mensaje.verMotivacion()));

		panel.add(jl);
		jl.setVisible(true);

		return panel;
	}
}
