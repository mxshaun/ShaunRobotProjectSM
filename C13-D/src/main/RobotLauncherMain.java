package main;

import ev3.robotproject.library.Wielaandrijving;
import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import nl.hva.miw.robot.cohort13.BeaconFollowerOpdracht2;
import nl.hva.miw.robot.cohort13.BeaconfollowerOprachtTest;
import nl.hva.miw.robot.cohort13.LijnVolger;
import nl.hva.miw.robot.cohort13.LijnvolgerOpdracht1;

public class RobotLauncherMain {
	private static boolean start;
	private static LijnvolgerOpdracht1 opdracht1;
	private static BeaconFollowerOpdracht2 opdracht2;
	

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BeaconfollowerOprachtTest test = new BeaconfollowerOprachtTest();
		
		test.run();
	}

}
