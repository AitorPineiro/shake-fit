package tablas;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import clases.Usuario;

@SuppressWarnings("serial")
public class ModeloTablaAdmin extends AbstractTableModel{
	ModeloColumnasAdmin columnas;
	List<Usuario> lista;
		
	public ModeloTablaAdmin(ModeloColumnasAdmin columnas, List<Usuario> lista){
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
		Usuario user = lista.get(rowIndex);
		return user.getFieldAt(columnIndex);
		
	}

}
