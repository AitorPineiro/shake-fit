package tablas;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import clases.RegistroActividades;
import tablas.PanelTablas.Trazador;

@SuppressWarnings("serial")
public class ModeloTabla  extends AbstractTableModel{
	Trazador trazador;
	ModeloColumnas columnas;
	List<RegistroActividades> lista;
		
	public ModeloTabla(ModeloColumnas columnas, List<RegistroActividades> lista){
		super();
			
		this.lista = lista;
		this.columnas = columnas;
			
	}
		

	@Override
	public int getColumnCount() {
		return columnas.getColumnCount();
	}

	@Override
	public int getRowCount() {
		return lista.size();
	}

	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
	
		return false;
	}
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		
		return getValueAt(0,columnIndex).getClass();
	}
	

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		RegistroActividades registro = lista.get(rowIndex);
		return registro.getFieldAt(columnIndex);
		
	}

}
