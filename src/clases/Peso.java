package clases;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Peso implements Serializable{
	double valor;
	String fecha;
	
	public Peso(double valor, String fecha) {
		this.valor = valor;
		this.fecha = fecha;
	}

	public String getFecha() {
		return fecha;
	}

	public double getValor() {
		return valor;
	}

	@Override
	public String toString() {
		return String.valueOf(valor);
	}
}
