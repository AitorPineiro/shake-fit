package usuario_registro;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class PanelSelectorPeso extends JPanel implements ActionListener{
	
	private final int Centenas = 0;
	private final int Decenas = 1;
	private final int Unidades = 2;
	private final int Decimales = 3;
	
	List<JLabel> numeros;
	List<JButton> botones;
	int cont = 0;
	double peso = 0;
	
	String boton = "";
	String [] titulo = {"Centenas", "Decenas", "Unidades", "Decimales"};
	double [] valor = {0, 0, 0, 0};
	

	public PanelSelectorPeso(){
		super(new GridLayout(3,4));
		this.numeros = new ArrayList<>();
		this.botones = new ArrayList<>();
		
		añadirComponentes();
	}

	private void añadirComponentes() {
		for(int i = 0; i<=3; i++){
			if(cont < 3) boton = "icons/arriba.png";
			else if(cont == 3)  boton = "icons/arriba2.png";
			this.add(this.crearBotones(boton, this.cont));
			cont++;
		}
		for(int i = 0; i<=3; i++){
			this.add(this.crearNumeros(titulo[i]));
		}
		for(int i = 0; i<=3; i++){
			if(cont < 7 && cont > 3) boton = "icons/abajo.png";
			else if(cont == 7)  boton = "icons/abajo2.png";
			this.add(this.crearBotones(boton, this.cont));
			cont++;
		}
	}
	
	public Component crearBotones(String operacion, int listener){
		JButton boton = new JButton(new ImageIcon(operacion));
		boton.setActionCommand(String.valueOf(listener));
		boton.addActionListener(this);
		boton.setContentAreaFilled(false); boton.setBorderPainted(false);
		this.botones.add(boton);
		return boton;
	}
	
	public Component crearNumeros(String titulo){
		JLabel numero = new JLabel("0", SwingConstants.CENTER);	
		Font f = new Font("Cambria", 3, 80);			
		numero.setBorder(BorderFactory.createTitledBorder(titulo));
		numero.setFont(f);
		this.numeros.add(numero);
		return numero;
	}

	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		switch (e.getActionCommand()){
		case "0":
			this.numeros.get(Centenas).setText(String.valueOf(Integer.valueOf(this.numeros.get(Centenas).getText())+1));
			this.valor[Centenas]++;
			break;
		case "1":
			this.numeros.get(Decenas).setText(String.valueOf(Integer.valueOf(this.numeros.get(Decenas).getText())+1));
			this.valor[Decenas]++;
			break;
		case "2":
			this.numeros.get(Unidades).setText(String.valueOf(Integer.valueOf(this.numeros.get(Unidades).getText())+1));
			this.valor[Unidades]++;
			break;
		case "3":
			this.numeros.get(Decimales).setText(String.valueOf(Integer.valueOf(this.numeros.get(Decimales).getText())+1));
			this.valor[Decimales]++;
			break;
		case "4":
			this.numeros.get(Centenas).setText(String.valueOf(Integer.valueOf(this.numeros.get(Centenas).getText())-1));
			this.valor[Centenas]--;
			break;
		case "5":
			this.numeros.get(Decenas).setText(String.valueOf(Integer.valueOf(this.numeros.get(Decenas).getText())-1));
			this.valor[Decenas]--;
			break;
		case "6": 
			this.numeros.get(Unidades).setText(String.valueOf(Integer.valueOf(this.numeros.get(Unidades).getText())-1));
			this.valor[Unidades]--;
			break;
		case "7":
			this.numeros.get(Decimales).setText(String.valueOf(Integer.valueOf(this.numeros.get(Decimales).getText())-1));
			this.valor[Decimales]--;
			break;
		}
		
		for(int i = 0; i<=3; i++){
			if(Integer.valueOf(this.numeros.get(i).getText()) < 0){
				this.numeros.get(i).setText("9");
				this.valor[i] = 9;
			}
			if(Integer.valueOf(this.numeros.get(i).getText()) > 9){
				this.numeros.get(i).setText("0");
				this.valor[i] = 0;
			}
		}
		
		this.peso = this.valor[Centenas]*100 + this.valor[Decenas]*10 + this.valor[Unidades] + this.valor[Decimales]/10;
		System.out.println(this.peso);
	}

	public double getPeso() {
		return peso;
	}
	
}
