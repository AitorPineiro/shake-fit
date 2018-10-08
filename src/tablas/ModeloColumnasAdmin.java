package tablas;

import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;

@SuppressWarnings("serial")
public class ModeloColumnasAdmin extends DefaultTableColumnModel{
	
	public ModeloColumnasAdmin(){
		super();
		this.addColumn(crearColumna("Nombre",0,80));
		this.addColumn(crearColumna("Sexo",1,80));
		this.addColumn(crearColumna("Peso",2,80));
		this.addColumn(crearColumna("Contraseña",3,80));
		this.addColumn(crearColumna("Palabra Clave",4,80));
		this.addColumn(crearColumna("Numero Rutinas Creadas",5,80));
		this.addColumn(crearColumna("Rutinas Hechas",6,80));
		this.addColumn(crearColumna("Ejercicios Hechos",7,80));
		this.addColumn(crearColumna("Ultimo Inicio",8,80));

	}

	private TableColumn crearColumna(String texto, int indice, int ancho) {
		TableColumn columna = new TableColumn(indice,ancho);

		columna.setHeaderValue(texto);
		columna.setPreferredWidth(ancho);
		
		return columna;
	}
}
