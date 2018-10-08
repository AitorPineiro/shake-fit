package menu_seleccion;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import clases.Usuario;

@SuppressWarnings("serial")
public class GraficoPeso extends JFrame {
	
	Usuario usuario;

	public GraficoPeso(Usuario usuario) {	
		this.usuario = usuario;
		this.setTitle("Historial Del Peso");
        this.setSize(730,480);
        this.setLocation(50, 50);
        
        ImageIcon icono = new  ImageIcon("icons/estadistica.png");   	//Icono de la Ventana
		this.setIconImage(icono.getImage());
		
        this.setVisible(true);
        this.setResizable(false);
        this.setContentPane(init(usuario));       
	}


	public Container init(Usuario usuario){		
			JPanel panel = new JPanel();
			panel.setBackground(Color.WHITE);
	        getContentPane().add(panel);
	        // Fuente de Datos
	        DefaultCategoryDataset line_chart_dataset = new DefaultCategoryDataset();
	      
	        for(int i = 0; i < usuario.getPeso().size(); i++) {
	        	line_chart_dataset.addValue(usuario.getPeso().get(i).getValor(), "Peso", usuario.getPeso().get(i).getFecha());
	        }	 
	        // Creando el Grafico
	        JFreeChart chart = ChartFactory.createLineChart("Evolución de: "+usuario.getNombre(),
	                "Fecha","Peso",line_chart_dataset,PlotOrientation.VERTICAL,
	                true,true,false);  
	        
	        // Mostrar Grafico
	        ChartPanel chartPanel = new ChartPanel(chart);
	        
	    	Font f = new Font("Cambria", 3, 30);
	        chartPanel.setFont(f);
	        panel.add(chartPanel);
	        
	        return panel;		
	}
}
