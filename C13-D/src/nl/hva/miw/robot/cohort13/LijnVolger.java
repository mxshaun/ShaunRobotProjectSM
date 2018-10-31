package nl.hva.miw.robot.cohort13;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.*;
import lejos.hardware.port.*;
import lejos.robotics.Color;
import ev3.robotproject.library.*;
import java.io.IOException;

public class LijnVolger implements Runnable{
	private final String NAAM = "Lijnvolger";
	private float colorValue;
	private float colorValueBlack;
	private float colorValueWhite;
	private float colorBorder;
	private LijnvolgerOpdracht1 opdracht1;
	
	//private float afwijking; // dit getal reprenteerd de hoek of draai naar/ of van de colorborder,
							 // waarmee het stuursysteem aangestuurd kan worden
	
	public LijnVolger(LijnvolgerOpdracht1 lijnvolgerOpdracht1) {
		this.opdracht1 = lijnvolgerOpdracht1;
	}
	
	public void calibreerWit() {
		System.out.println("Scan wit vlak, press button");
		Button.waitForAnyPress();

		colorValueWhite = RedSensor.getRed();
	}
	
	public void calibreerZwart() {
		System.out.println("Scan zwart vlak, press button");
		Button.waitForAnyPress();

		colorValueBlack = RedSensor.getRed();
	}
	
	
	/**
	 * afwijking bepaalt of er of uiterst links, of uiterst rechts van het midden gemeten wordt. 	
	 * @return, hierop geeft hij de een hoek van 0, in (int), op uiterst links en 180 op uiterst rechts.
	 * 			
	 */
	public int bepaalAfwijking() {
		int afwijking;
		colorBorder = (colorValueWhite - colorValueBlack) / 2;
		colorValue = RedSensor.getRed();
		
		afwijking = (int) (colorValue / ((colorValueWhite - colorValueBlack) / 720));
		
		// hiering moet de hoek of draai�ng van de afwijking van de colorborder bepaalt worden.
		return afwijking;
	}

	/**
	 * moet blijvend lijnwaardes uitgeven zolangs als hij runt, zolang deze thread actief is.
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub

		while(opdracht1.getStartOpdracht()) {

//			opdracht1.setRijRichting(bepaalAfwijking());
			System.out.println("rijrichting bepaalt");
			System.out.println(bepaalAfwijking());
			Lcd.clear();
		}
	}
	
	
}
