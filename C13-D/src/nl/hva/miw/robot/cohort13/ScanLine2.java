package nl.hva.miw.robot.cohort13;


import ev3.robotproject.library.ColorIdSensor;
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
	        		if (teller == 1 && !swAan) {
	    				sw.reset();
	    				swAan = true;
	    			}
					Delay.msDelay(1000);
				}
	        	if (teller == 2 && swAan) {
					rondetijd = sw.elapsed();
					swAan = false;
					swTijdAanwezig = true;
					opdracht1.setStartOpdracht(false);
				}
			}
		} catch (Exception e) {

		}
		
		/*
		 * Tonen van de ronde tijd op het scherm
		 */
		
		if (swTijdAanwezig) {
            for (int i=0;i<8;i++) {
            	System.out.println();
            }
			System.out.printf("Rondetijd: %.3f seconden\n", (float)(rondetijd/1000.0));
			Button.waitForAnyPress();
        }	
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
			System.out.println("Loggen in finLine() lukt niet");
		}
		return (colorName == "Red" || colorName == "Orange");
	}
}