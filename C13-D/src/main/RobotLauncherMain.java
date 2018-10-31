package main;

import ev3.robotproject.library.Wielaandrijving;
import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import nl.hva.miw.robot.cohort13.BeaconFollowerOpdracht2;
import nl.hva.miw.robot.cohort13.BeaconfollowerOprachtTest;
import nl.hva.miw.robot.cohort13.LijnVolger;
import nl.hva.miw.robot.cohort13.LijnvolgerOpdracht1;
import nl.hva.miw.robot.cohort13.PathFinderOpdracht3;

public class RobotLauncherMain {
	private static boolean start;
	private static LijnvolgerOpdracht1 opdracht1;
	private static BeaconFollowerOpdracht2 opdracht2;
	private static PathFinderOpdracht3 opdracht3;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		opdracht2 = new BeaconFollowerOpdracht2();
		//opdracht2.BeaconFollowerOpdracht22();

		//opdracht1 = new LijnvolgerOpdracht1();
		//opdracht1.lijnVolgerOpdracht();
		
		opdracht3 = new PathFinderOpdracht3();
		opdracht3.startOpdracht3();


		BeaconfollowerOprachtTest test = new BeaconfollowerOprachtTest();
		
		test.rijNaarBeacon();
		
		test.grijpBeacon();
		test.rijdMetBeacon();
		test.laatBeaconLos();
		
		

	}

}
