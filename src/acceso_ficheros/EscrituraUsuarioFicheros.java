package acceso_ficheros;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import clases.Usuario;

public class EscrituraUsuarioFicheros {
	String fichero;
	Usuario user;
	
	public EscrituraUsuarioFicheros(String fichero, Usuario user) {
		this.fichero = fichero;
		this.user = user;
	}
	public void escribir() {
		ObjectOutputStream out = null;
		
		try {
			
				out = new ObjectOutputStream(new FileOutputStream(fichero));				
	            out.writeObject(this.user);	
	            out.close();				
				
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {			
		}
	}
}
