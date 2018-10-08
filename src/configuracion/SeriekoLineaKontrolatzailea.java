package configuracion;

import java.io.IOException;
import java.io.InputStream;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

public class SeriekoLineaKontrolatzailea {
private static final String PUERTO = "COM";
	
	private JFrame ventana;
	private InputStream in;
	private SerialPort serialPort;

	public SeriekoLineaKontrolatzailea(JFrame ventana){
		this.ventana = ventana;
		iniciar();
	}

	private void iniciar(){
		
		boolean salir = false;
		int i = 0;
		
		while(!salir){
			if(i > 20){
				salir = true;
				JOptionPane.showMessageDialog(ventana,"La la placa no esta conectada.","ERROR",JOptionPane.ERROR_MESSAGE);
			}
			try {
				conectar(PUERTO+i);
				salir = true;
				System.out.println("Puerto: "+PUERTO+i);
			} catch (Exception e) {
				System.out.println("El puerto "+PUERTO+i+" no existe.");
				i++;
			}
		}
		
	}

	private void conectar(String puerto) throws Exception{
		CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(puerto);
		
		if ( portIdentifier.isCurrentlyOwned() ){
			JOptionPane.showMessageDialog(ventana,"El puerto "+PUERTO+" ya está en uso.","ERROR",JOptionPane.ERROR_MESSAGE);
			System.out.println("Error: El puerto está en uso.");
		}
		else{
			CommPort commPort = portIdentifier.open(this.getClass().getName(),2000);

			if ( commPort instanceof SerialPort ){
				serialPort = (SerialPort) commPort;
				serialPort.setSerialPortParams(57600,SerialPort.DATABITS_8,SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);

				in = serialPort.getInputStream();				
			}else{
				System.out.println("Error: Only serial ports are handled by this example.");
			}
		}
	}
	
	
	public int leerPeso() throws IOException{
		@SuppressWarnings("unused")
		int rPeso = 0;
		int temp = 0;
		int lista[] = new int[10];
		int rep[] = {0,0,0,0,0,0,0,0,0,0};
		
		for(int i = 0; i < 10; i++){
			lista[i] = in.read();
			      
			System.out.println(lista[i]);
			for(int j = 0; j < lista.length; j++){
				if(lista[i] == lista[j] ){
					rep[j]++;
				}
			}
		}
		
		for(int i = 0; i < 10; i++){
			if(rep[i] > temp){
				temp = rep[i];
				rPeso = lista[i];
			}
		}
		System.out.println("Aukeratua: "+lista[4]);
		return lista[4];
	}
	
	
	public void close(){
		try {
			in.close();
		} catch (IOException e) {
		}
		serialPort.close();
	}
}
