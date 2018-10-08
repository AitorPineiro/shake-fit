package clases_personalizadas;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Reproductor {
	String nombreSonido;
	public Reproductor(String nombreSonido) {
		this.nombreSonido = nombreSonido;
		this.ReproducirSonido(nombreSonido);
	}
	public void ReproducirSonido(String nombreSonido){
	       try {
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(nombreSonido).getAbsoluteFile());
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.start();
	       } catch(UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
	         System.out.println("Error al reproducir el sonido.");
	       }
	}

}
