package main;

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
import nl.hva.miw.robot.cohort13.Piloot;
import nl.hva.miw.robot.cohort13.LijnVolger;


public class RobotLauncher1 {
	static Piloot piloot = new Piloot();
	static LijnVolger lijnScanner = new LijnVolger();
	static int rijRichting;
	static boolean start;

	public static void main(String[] args) throws Exception{
		Logging.setup(RobotLauncher1.class.getPackage(), false);
		Logging.log("Starting test");
		
		//lijnVolgerOpdracht();

	}
	
	public static void lijnVolgerOpdracht() {
		start = true;
		lijnScanner.setVoerTaakUit(start);
		piloot.setVoerTaakUit(start);
		
		Button.LEDPattern(4); // flash green led and
		Sound.beepSequenceUp(); // make sound when ready.

		System.out.println("Press any key to start the Test");

		Button.waitForAnyPress();
		Logging.log("Button is pressed");
		
		Thread t1 = new Thread((Runnable) piloot);
		Logging.log("Thread 1 gemaakt");
		Thread t2 = new Thread((Runnable) lijnScanner);
		Logging.log("Thread 2 gemaakt");
		try {
			t1.join();
			Logging.log("Thread 1 gejoint");
			t2.join();
			Logging.log("Thread 2 gejoint");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		t1.start();
		Logging.log("Thread 1 gestart");
		t2.start();
		Logging.log("Thread 2 gestart");
	}

	public synchronized static void setRijRichting(int richting) {
		rijRichting = richting;	
	}

	public synchronized static int getRijRichting() {
		// TODO Auto-generated method stub
		return rijRichting;
	}

}
