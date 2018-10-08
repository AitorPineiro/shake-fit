package acceso_ficheros;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import clases.Ejercicio;

public class LeerEjercicios {		
	
	@SuppressWarnings("unused")
	private final String [] gif = {"icons/ejercicios/gif/estandar", "icons/ejercicios/gif/fuerza", "icons/ejercicios/gif/resistencia"};
	private final String [] img = {"icons/ejercicios/img/estandar", "icons/ejercicios/img/fuerza", "icons/ejercicios/img/resistencia"};
	
	
	//Para leer ejercicios de un tipo concreto. (Estandar, fuerza, resistencia).
	public List<Ejercicio> leerEjercicios(String rutaEjercicios) throws IOException{
		List<Ejercicio> lista = new ArrayList<>();

		File folder = new File(rutaEjercicios);
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				String nombre = listOfFiles[i].getName().replaceAll(".PNG", "");
				String imagen = listOfFiles[i].getPath();
				String gif = listOfFiles[i].getPath().replace(".PNG", ".gif").replace("img", "gif");
				lista.add(new Ejercicio(nombre, gif, imagen, 0));
			}
		}
		return lista;
	}
	
	public List<Ejercicio> leerTodos() throws IOException{
		List<Ejercicio> lista = new ArrayList<>();
		
		for(int i = 0; i< this.img.length; i++){
			List<Ejercicio> aux = new ArrayList<>();
			aux = this.leerEjercicios(img[i]);
			
			for(int x = 0; x<aux.size(); x++){
				lista.add(aux.get(x));
			}			
		}			
		return lista;
	}
	
	public String getImagenesEstandar() {
		return img[0];
	}

	public String getImagenesFuerza() {
		return img[1];
	}

	public String getImagenesResistencia() {
		return img[2];
	}
	
}
