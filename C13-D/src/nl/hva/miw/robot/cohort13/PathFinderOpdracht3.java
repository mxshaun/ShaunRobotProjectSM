package nl.hva.miw.robot.cohort13;

import ev3.robotproject.library.Lcd;
import ev3.robotproject.library.Wielaandrijving;
import lejos.hardware.Button;
import lejos.hardware.Sound;

public class PathFinderOpdracht3 {
	private ObstakelDetector sensor = new ObstakelDetector(this);
	private PilootPathFinder piloot = new PilootPathFinder(this);
	private SituatieDetector situatie = new SituatieDetector(this);
	
	private int afstandObstakel;
	private boolean start;
	private boolean kruising;
	private boolean startDoolhof;
	private boolean finishDoolhof;
	
	public void startOpdracht3() {
		Button.LEDPattern(4); // flash green led and
		Sound.beepSequenceUp(); // make sound when ready.
		Lcd.clear();
		Lcd.print(1, "Press any key to start the Test");

		Button.waitForAnyPress();

		start = true; // start de opdracht

		Thread t1 = new Thread(sensor);
		Thread t2 = new Thread(piloot);
		Thread t3 = new Thread(situatie);

		t1.start();
		t3.start();

		Wielaandrijving.setSnelheid(125, 0);
		while (!startDoolhof) {

		}
		Wielaandrijving.stop();
		t2.start();
		while (start) {
		}
		try {
			t1.join();
			t2.join();
			t3.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public synchronized boolean isKruising() {
		return kruising;
	}

	public synchronized void setKruising(boolean kruising) {
		this.kruising = kruising;
	}

	public boolean isStartDoolhof() {
		return startDoolhof;
	}

	public void setStartDoolhof(boolean startDoolhof) {
		this.startDoolhof = startDoolhof;
	}

	public boolean isFinishDoolhof() {
		return finishDoolhof;
	}

	public void setFinishDoolhof(boolean finishDoolhof) {
		this.finishDoolhof = finishDoolhof;
	}

	public void setAfstandObstakel(int afstand) {
		this.afstandObstakel = afstand;
	}

	public int getAfstandObstakel() {
		return this.afstandObstakel;
	}

	public void setStart(boolean start) {
		this.start = start;
	}

	public boolean getStart() {
		return this.start;
	}

}
