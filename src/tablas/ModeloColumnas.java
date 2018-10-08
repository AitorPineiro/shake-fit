package tablas;

import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;

import tablas.PanelTablas.Trazador;

@SuppressWarnings("serial")
public class ModeloColumnas extends DefaultTableColumnModel{
	Trazador trazador;
	
	public ModeloColumnas(Trazador trazador){
		super();
		this.trazador = trazador;
		this.addColumn(crearColumna("Nombre",0,80));
		this.addColumn(crearColumna("Tiempo (Segundos)",1,80));
		this.addColumn(crearColumna("Fecha",2,80));
		this.addColumn(crearColumna("Completado",3,80));
	}

	private TableColumn crearColumna(String texto, int indice, int ancho) {
		TableColumn columna = new TableColumn(indice,ancho);

		columna.setHeaderValue(texto);
		columna.setPreferredWidth(ancho);
		columna.setCellRenderer(trazador);
		
		return columna;
	}

}
