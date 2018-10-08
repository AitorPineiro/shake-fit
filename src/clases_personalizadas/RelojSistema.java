package clases_personalizadas;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RelojSistema {
	String fecha;
	public RelojSistema() {
		this.fecha = getFechaa();
	}
	public String getFecha() {
		return fecha;
	}

	public String getFechaa() {
		DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date fechaa = new Date();
		return formato.format(fechaa);
	}
}
