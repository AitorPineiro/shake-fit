package clases_personalizadas;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MiPanel extends JPanel{
	
	Image imagen;
	public MiPanel(Image imagen) {
		if (imagen != null) {
		 this.imagen = imagen;
		}
	}

	public void setImagen(Image nuevaImagen) {
		this.imagen = nuevaImagen;
		repaint();
	}

	@Override
	public void paint(Graphics g) {
		
		if (imagen != null) {
			g.drawImage(imagen, 0, 0, getWidth(), getHeight(),this);
			setOpaque(false);
		} else {
			setOpaque(true);
		}	
		
		super.paint(g);
	}
}