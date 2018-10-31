package main;

import ev3.robotproject.library.Lcd;
import lejos.hardware.Button;
import lejos.utility.Delay;
import nl.hva.miw.robot.cohort13.LijnvolgerOpdracht1;
import nl.hva.miw.robot.cohort13.BeaconFollowerOpdracht2;
import nl.hva.miw.robot.cohort13.BeaconfollowerOprachtTest;
import nl.hva.miw.robot.cohort13.PathFinderOpdracht3;

public class RobotOpdrachtenMain {

	public static void main(String[] args) throws Exception {
		
		while (Button.ESCAPE.isUp()) {
			Lcd.clear();
			Lcd.print(2, "Links: opdr 1");
			Lcd.print(3, "Midden: opdr 2");
			Lcd.print(4, "Rechts: opdr 3");
			Button.waitForAnyPress();
			int buttonId = Button.getButtons();
			
			if (buttonId == 16) {
				LijnvolgerOpdracht1 opdracht1 = new LijnvolgerOpdracht1();
				opdracht1.lijnVolgerOpdracht();
			} else if (buttonId == 2) {
				BeaconfollowerOprachtTest opdracht2 = new BeaconfollowerOprachtTest();
				
//				opdracht2.XXXXX();
				Lcd.clear();
				Lcd.print(2, "Opdracht 2");
				opdracht2.rijNaarBeacon();
				opdracht2.grijpBeacon();
				opdracht2.rijdMetBeacon();
				
				opdracht2.laatBeaconLos();
				opdracht2.rijdWeg();
				
				
			} else if (buttonId == 8) {
//				PathFinderOpdracht3 opdracht3 = new PathFinderOpdracht3();
//				opdracht3.XXXX();
				Lcd.clear();
				Lcd.print(2, "Opdracht 3");
			} else {
				Lcd.clear();
				Lcd.print(2, "Wrong button");
				Lcd.print(3, "Press again");
				Delay.msDelay(1000);
			}
			
		}


	}

}
