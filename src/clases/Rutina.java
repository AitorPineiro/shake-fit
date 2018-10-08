package clases;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class Rutina implements Serializable{

	
	String nombre;
	List<Ejercicio> listaEjercicios;
	String tiempo;
	String fecha;
	
	public Rutina(){
		this.listaEjercicios = new ArrayList<>();
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

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List<Ejercicio> getListaEjercicios() {
		return listaEjercicios;
	}
	public void setListaEjercicios(List<Ejercicio> listaEjercicios) {
		this.listaEjercicios = listaEjercicios;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.nombre;
	}
	
}
