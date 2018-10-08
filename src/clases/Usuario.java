package clases;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import clases_personalizadas.RelojSistema;

@SuppressWarnings("serial")
public class Usuario implements Serializable{
	final boolean DEFAULT_SOUND = true;
	String nombre;
	String contraseña;
	String palabraClave;
	String ultimoInicio = "";
	String sexo;
	
	List <Peso> pesoLista;
	boolean sonido;
	
	List<Rutina> rutinas;
	List<RegistroActividades> registroEjercicios;
	List<RegistroActividades> registroRutinas;
	

	
	public Usuario( String nombre, String contraseña, 
					String palabraClave, double peso, 
					String sexo) {
		this.nombre = nombre;
		this.contraseña = contraseña;
		this.palabraClave = palabraClave;
		
		this.pesoLista = new ArrayList<>();
		
		this.rutinas = new ArrayList<>();
		this.registroEjercicios = new ArrayList<>();
		this.registroRutinas = new ArrayList<>();		
		
		RelojSistema reloj = new RelojSistema();
		Peso nuevoPeso = new Peso(peso, reloj.getFechaa());
		this.pesoLista.add(nuevoPeso);		
		
		this.sexo = sexo;
		this.sonido = DEFAULT_SOUND;
	}
	
	public boolean getSonido() {
		return sonido;
	}

	public void setSonido(boolean sonido) {
		this.sonido = sonido;
	}

	public void setPeso(Peso peso) {
		this.pesoLista.add(peso);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public String getPalabraClave() {
		return palabraClave;
	}

	public void setPalabraClave(String palabraClave) {
		this.palabraClave = palabraClave;
	}

	public String getUltimoInicio() {
		return ultimoInicio;
	}

	public void setUltimoInicio(String ultimoInicio) {
		this.ultimoInicio = ultimoInicio;
	}

	public List<Peso> getPeso() {
		return pesoLista;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	public void setEjercicioTerminado(RegistroActividades ejercicio) {
		this.registroEjercicios.add(ejercicio);
	}
	
	public List<Peso> getPesoLista() {
		return pesoLista;
	}

	public void setPesoLista(List<Peso> pesoLista) {
		this.pesoLista = pesoLista;
	}

	public List<Rutina> getRutinas() {
		return rutinas;
	}

	public void setRutinas(List<Rutina> rutinas) {
		this.rutinas = rutinas;
	}

	public List<RegistroActividades> getRegistroEjercicios() {
		return registroEjercicios;
	}

	public void setRegistroEjercicios(List<RegistroActividades> registroEjercicios) {
		this.registroEjercicios = registroEjercicios;
	}

	public List<RegistroActividades> getRegistroRutinas() {
		return registroRutinas;
	}

	public void setRegistroRutinas(List<RegistroActividades> registroRutinas) {
		this.registroRutinas = registroRutinas;
	}
	
	
	@Override
	public String toString(){
		return nombre;
	}
	
	public Object getFieldAt(int columna) {
		switch (columna){
		case 0: return this.nombre;
		case 1: return this.sexo;
		case 2: return this.pesoLista.get(this.pesoLista.size()-1);
		case 3: return this.contraseña;
		case 4: return this.palabraClave;
		case 5: return this.rutinas.size();
		case 6: return this.registroRutinas.size();
		case 7: return this.registroEjercicios.size();
		case 8: return this.ultimoInicio;
		default: return null; 
		}
		
	}
	
}