package motivador;

import java.util.Random;

public class Motivador {
	
	String [] motivaciones = {"icons/motivacion/1.jpg", "icons/motivacion/2.png", 
				 				"icons/motivacion/3.png","icons/motivacion/4.jpg" , "icons/motivacion/5.jpg",
				 				"icons/motivacion/6.jpg", "icons/motivacion/7.png", "icons/motivacion/8.jpg", "icons/motivacion/9.jpg",
				 				"icons/motivacion/10.jpg", "icons/motivacion/11.png", "icons/motivacion/12.jpg", "icons/motivacion/13.png",
				 				"icons/motivacion/14.png", "icons/motivacion/15.png"};
	
	Random generador = new Random();
	String mensaje = "";
	public Motivador() {
		int numero = generador.nextInt(motivaciones.length);
		generarMotivacion(numero);
	}
	public void generarMotivacion(int numero) {
		this.mensaje =  motivaciones[numero];
	}
	public String verMotivacion() {
		return this.mensaje;
	}
}
