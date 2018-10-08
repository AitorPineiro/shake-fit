package clases;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Ejercicio implements Serializable {

	String nombre;
	String imagen; 
	String gif;
	int repeticiones;
	
	public Ejercicio(String nombre, String gif, String imagen, int repeticiones){
		this.nombre = nombre;
		this.imagen = imagen;
		this.gif = gif;
		this.repeticiones = repeticiones;
	}

	public int getRepeticiones() {
		return repeticiones;
	}

	public void setRepeticiones(int repeticiones) {
		this.repeticiones = repeticiones;
	}

	public String getNombre() {
		return nombre;
	}

	public String getImagen() {
		return imagen;
	}

	public String getGif() {
		return gif;
	}
	
	
	
}
