package nl.hva.miw.robot.cohort13;

import ev3.robotproject.library.Logging;
import lejos.hardware.Button;
import lejos.hardware.Sound;

public class PathFinderOpdracht3 {
	private ObstakelDetector sensor = new ObstakelDetector(this);
	private PilootPathFinder piloot = new PilootPathFinder(this);
	private volatile int afstandObstakel;
	private boolean eindeBereikt;
	private boolean start;
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public void startOpdracht3() {
		Button.LEDPattern(4); // flash green led and
		Sound.beepSequenceUp(); // make sound when ready.

		System.out.println("Press any key to start the Test");
		

		Button.waitForAnyPress();
		
		start = true; // start de opdracht
		
		Thread t1 = new Thread(piloot);
		Thread t2 = new Thread(sensor);
		
		t1.start();
		t2.start();
	}
	
	public void setAfstandObstakel(int afstand) {
		this.afstandObstakel = afstand;
	}
	
	public int getAfstandObstakel() {
		return this.afstandObstakel;
	}
	
	public void setEindeBereikt(boolean einde) {
		this.eindeBereikt = einde;
	}
	
	public boolean getEindeBereikt() {
		return this.eindeBereikt;
	}
	
	public void setStart(boolean start) {
		this.start = start;
	}
	
	public boolean getStart() {
		return this.start;
	}

}
