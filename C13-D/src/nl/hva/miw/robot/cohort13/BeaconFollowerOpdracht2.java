package nl.hva.miw.robot.cohort13;

import ev3.robotproject.library.Logging;
import ev3.robotproject.library.Wielaandrijving;
import lejos.hardware.Button;
import lejos.hardware.Sound;

public class BeaconFollowerOpdracht2 {
	private PilootBeaconGrijper piloot = new PilootBeaconGrijper(this);
	private BeaconTest BeaconScanner = new BeaconTest(this);
	private int direction;
	private int distance;
	private boolean startOpdracht = true;

	public BeaconFollowerOpdracht2() {
	}

	public void BeaconFollowerOpdracht22() {
		Button.LEDPattern(4); // flash green led and
		Sound.beepSequenceUp(); // make sound when ready.

		System.out.println("Press any key to start the Test");

		Button.waitForAnyPress();

		Thread t1 = new Thread(piloot);
		Thread t2 = new Thread(BeaconScanner);
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		t1.start();
		t2.start();

	}

	public synchronized boolean getStartOpdracht() {
		return startOpdracht;
	}
	
	public synchronized void setStartOpdracht(boolean start) {
		this.startOpdracht = start;
	}
	
	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}


}
