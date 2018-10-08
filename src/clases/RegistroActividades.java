package clases;

import java.io.Serializable;

@SuppressWarnings("serial")
public class RegistroActividades implements Serializable{

	String nombre;
	String tiempo;
	String fecha;
	boolean completado;
	
	public RegistroActividades(String nombre, String tiempo, String fecha, boolean completado){
		this.nombre = nombre;
		this.tiempo = tiempo;
		this.fecha = fecha;
		this.completado = completado;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTiempo() {
		return tiempo;
	}
	public void setTiempo(String tiempo) {
		this.tiempo = tiempo;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	public Object getFieldAt(int columna) {
		switch (columna){
		case 0: return nombre;
		case 1: return tiempo;
		case 2: return fecha;
		case 3: return completado;
		default: return null; 
		}
		
	}
	
}
