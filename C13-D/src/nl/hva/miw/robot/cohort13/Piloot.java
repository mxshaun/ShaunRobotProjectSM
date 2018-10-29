package nl.hva.miw.robot.cohort13;

import lejos.hardware.motor.*;
import lejos.hardware.port.*;

import java.io.IOException;

import ev3.robotproject.library.Lcd;
import ev3.robotproject.library.Logging;
import ev3.robotproject.library.Motor;
import ev3.robotproject.library.RedSensor;
import ev3.robotproject.library.Wielaandrijving;

public class Piloot implements Runnable {
	private final String NAAM = "Bestuurder";
	private LijnvolgerOpdracht1 opdracht1;

	public Piloot(LijnvolgerOpdracht1 lijnvolgerOpdracht1) {
		this.opdracht1 = lijnvolgerOpdracht1;
	}

	/**
	 * Deze methode zorgt voor het rijden met een maximale snelheid proportioneel
	 * aan de gewenste hoek van draaiïng.
	 * 
	 * @param hoek,
	 *            hoek van draaiïng in graden.
	 * @return
	 */

	public void rijOverLijn(int afwijking) {
		/**
		 * @ To-do: bepaal de relatieve max lineaire snelheid, aan de hand van de hoek
		 *   die gemaakt dient te worden.
		 */
		double maxSnelheid = Wielaandrijving.getMaxLineaireSnelheid();
		double snelheid = (afwijking / 90);
		if (snelheid > 1) {
			snelheid = snelheid - 1;
		}
		snelheid = snelheid * maxSnelheid;
		double draaisnelheid = (afwijking/2) - 45;
			Wielaandrijving.setSnelheid(snelheid, draaisnelheid);

	}

	/**
	 * zorgt ervoor dat er een richting op gereden wordt zolang deze thread actief
	 * is.
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (opdracht1.getStartOpdracht()) {
			rijOverLijn(opdracht1.getRijRichting());
			System.out.println("rijrichting opgehaald");
			Lcd.clear();
		}
	}

}
