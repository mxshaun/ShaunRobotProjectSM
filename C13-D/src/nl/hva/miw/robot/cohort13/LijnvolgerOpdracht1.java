package nl.hva.miw.robot.cohort13;

import ev3.robotproject.library.Logging;
import ev3.robotproject.library.Wielaandrijving;
import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;


public class LijnvolgerOpdracht1 {
	private Piloot piloot = new Piloot(this);
	private LijnVolger lijnScanner = new LijnVolger(this);
	private volatile int rijRichting;
	private boolean startOpdracht;

	public LijnvolgerOpdracht1() {
	}
	
	public void lijnVolgerOpdracht() {
		Button.LEDPattern(4); // flash green led and
		Sound.beepSequenceUp(); // make sound when ready.

		System.out.println("Press any key to start the Test");
		

		Button.waitForAnyPress();
		Logging.log("Button is pressed");
		
		lijnScanner.calibreerWit();
		lijnScanner.calibreerZwart();
		
		Thread t1 = new Thread(piloot);
		Thread t2 = new Thread(lijnScanner);
		startOpdracht = true;
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		t1.start();
		t2.start();
	}

	public void setRijRichting(int richting) {
		this.rijRichting = richting;	
	}

	public int getRijRichting() {
		// TODO Auto-generated method stub
		return rijRichting;
	}
	
	public synchronized void setStartOpdracht(boolean start) {
		this.startOpdracht = start;
	}
	
	public synchronized boolean getStartOpdracht() {
		return startOpdracht;
	}

}
