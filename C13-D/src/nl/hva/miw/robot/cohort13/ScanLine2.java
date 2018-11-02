package nl.hva.miw.robot.cohort13;


import ev3.robotproject.library.ColorIdSensor;
import ev3.robotproject.library.Lcd;
import lejos.hardware.Button;
import lejos.utility.Delay;
import lejos.utility.Stopwatch;

public class ScanLine2 implements Runnable {
	int colorInt;
	int teller = 0;
	int rondetijd = 0;
	boolean foundLine;
	boolean swAan = false;
	boolean swTijdAanwezig = false;
	boolean afmeldenThread;
	private LijnvolgerOpdracht1 opdracht1;
	
	public ScanLine2(LijnvolgerOpdracht1 opdracht1) {
		super();
		this.opdracht1 = opdracht1;
	}

	public void run() {
		ColorIdSensor.setColorIdMode();
		Stopwatch sw = new Stopwatch();
		
		try {
			while (opdracht1.getStartOpdracht()) {
	        	foundLine = findLine();
	        	if (foundLine) {
	        		teller++;
	        		if (teller == 1 && !swAan) { //starten van de stopwatch
	    				sw.reset();
	    				swAan = true;
	    			}
					Delay.msDelay(1000);
				}
	        	if (teller == 2 && swAan) { //stoppen van de stopwatch
					rondetijd = sw.elapsed();
					swAan = false;
					swTijdAanwezig = true;
					opdracht1.setStartOpdracht(false);
				}
			}
		} catch (Exception e) {
			Lcd.clear();
			Lcd.print(2, "Foutje bedankt!");
			Lcd.print(3, "ScanLine2");
		}
				
		//Tonen van de ronde tijd op het scherm
		if (swTijdAanwezig) {
			Lcd.clear();
			Lcd.print(2, "Rondetijd: %.3f seconden\n", (float)(rondetijd/1000.0));
			Button.waitForAnyPress();
        }
		
		afmeldenThread();
	}
	
	/*
	 * Getters voor "gevonden lijn" en "teller".
	 */
	
	public boolean getFoundLine() {
		return foundLine;
	}
	
	public int getTeller() {
		return teller;
	}

	//De finLine() methode voor het vinden van de rode/oranje lijn.
	public synchronized boolean findLine() {
		colorInt = ColorIdSensor.getColor();
		String colorName = ColorIdSensor.colorName(colorInt);
		try {
			//leeg
		} catch (Exception e) {
			Lcd.clear();
			Lcd.print(2, "Loggen in findLine() lukt niet.");
		}
		return (colorName == "Red" || colorName == "Orange");
	}
	
	//De getter voor het afmelden van de Thread
		public boolean afmeldenThread() {
			afmeldenThread = true;
			return afmeldenThread;
		}
}