package nl.hva.miw.robot.cohort13;

import lejos.hardware.motor.*;
import lejos.hardware.port.*;

import java.io.IOException;

import ev3.robotproject.library.Lcd;
import ev3.robotproject.library.Logging;
import ev3.robotproject.library.Motor;
import ev3.robotproject.library.RedSensor;
import ev3.robotproject.library.Wielaandrijving;
import main.RobotLauncher1;

public class Piloot implements Runnable{
	private final String NAAM = "Bestuurder";
	
	public Piloot() {
	}
	
	/**
	 * Deze methode zorgt voor het rijden met een maximale snelheid proportioneel aan de gewenste hoek van draaiïng.
	 * 
	 * @param hoek, hoek van draaiïng in graden.
	 * @return
	 */
	
	public void rijOverLijn(int hoek) {
		/**
		 * @ To-do: bepaal de relatieve max lineaire snelheid, aan de hand van de hoek die gemaakt dient te worden.
		 */
		Wielaandrijving.setSnelheid(Wielaandrijving.getMaxLineaireSnelheid(), hoek);
	}


	/**
	 * zorgt ervoor dat er een richting op gereden wordt zolang deze thread actief is.
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(RobotLauncher1.getStartOpdracht()) {
			rijOverLijn(RobotLauncher1.getRijRichting());
			System.out.println("rijrichting opgehaald");
			Lcd.clear();
		}
	}
	
	
	

}
