package clases_personalizadas;

import javax.swing.ImageIcon;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class MiBoton extends JButton{
	
	String nombre;

	public MiBoton(String imagen){
		super(new ImageIcon(imagen));
	}
	
}
