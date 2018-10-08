package acceso_ficheros;



import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import clases.Usuario;

public class LecturaUsuariosFicheros {
	String fichero;
	Usuario user;
	
	public LecturaUsuariosFicheros(String fichero) {
		this.fichero = fichero;
	}
	
	public Usuario leer() {
	
		ObjectInputStream in = null;
		
		try {
			in = new ObjectInputStream(new FileInputStream(fichero));
			
			try {
				this.user = (Usuario) in.readObject();
				in.close();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			return null;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}		
		
		return this.user;
	}	
	
}
