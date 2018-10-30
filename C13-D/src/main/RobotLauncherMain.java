package main;

import ev3.robotproject.library.Wielaandrijving;
import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import nl.hva.miw.robot.cohort13.LijnVolger;
import nl.hva.miw.robot.cohort13.LijnvolgerOpdracht1;

public class RobotLauncherMain {
	private static boolean start;
	private static LijnvolgerOpdracht1 opdracht1;
	// private 

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		opdracht1 = new LijnvolgerOpdracht1();
		opdracht1.lijnVolgerOpdracht();
	}

}
