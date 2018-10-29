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
	Piloot piloot = new Piloot(this);
	LijnVolger lijnScanner = new LijnVolger(this);
	int rijRichting;
	boolean startOpdracht;

	public LijnvolgerOpdracht1(boolean startOpdracht) {
		this.startOpdracht = startOpdracht;
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
		//Logging.log("Thread 1 gemaakt");
		Thread t2 = new Thread(lijnScanner);
		//Logging.log("Thread 2 gemaakt");
		startOpdracht = true;

		t1.start();
		//Logging.log("Thread 1 gestart");		
		t2.start();
		//Logging.log("Thread 2 gestart");
	}

	public synchronized void setRijRichting(int richting) {
		rijRichting = richting;	
	}

	public synchronized int getRijRichting() {
		// TODO Auto-generated method stub
		return rijRichting;
	}
	
	public synchronized void setStartOpdracht(boolean start) {
		startOpdracht = start;
	}
	
	public synchronized boolean getStartOpdracht() {
		return startOpdracht;
	}

}
