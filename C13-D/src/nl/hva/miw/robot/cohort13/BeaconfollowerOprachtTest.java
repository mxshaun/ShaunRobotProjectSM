package nl.hva.miw.robot.cohort13;

import ev3.robotproject.library.GrijpMotor;
import ev3.robotproject.library.InfraroodSensor;
import ev3.robotproject.library.Lcd;
import ev3.robotproject.library.Logging;
import ev3.robotproject.library.Motor;
import ev3.robotproject.library.TouchSensor;
import ev3.robotproject.library.Wielaandrijving;
import lejos.hardware.Sound;
import lejos.utility.Delay;

/**
 * Dit is de beaconfollower opdracht. Hier staan alle methodes in om het beacon Object te kunnen grijpen. 
 *
 */
public class BeaconfollowerOprachtTest {

	// De robot gaat zoeken naar de beaconsensor.
	// begint met draaien en zoeken naar de beacon

	public BeaconfollowerOprachtTest() {
		// TODO Auto-generated constructor stub

	}

	public void rijNaarBeacon() throws Exception {
		try {
			Logging.setup(BeaconfollowerOprachtTest.class.getPackage(), false);

			int direction = (int) InfraroodSensor.getDirection();
			int distance = (int) InfraroodSensor.getDistance();
			Lcd.clear();

			// Gaat zoeken naar de beacon. Draait totdat de beacon is gevonden.
			zoekBeacon(direction);
			Logging.log("Beacon is gevonden, direction: " + direction);
			Lcd.clear();

			// De beacon is gevonden, maar hij gaat nu nauwkeurig naar de beacon draaien.
			zoekNauwkeurigBeacon(direction);
			Logging.log("Nauwkeurig gedraait naar de beacon, direction: " + direction);
			Lcd.clear();

			// Gaat rijden naar de beacon totdat de distance kleiner is dan 10
			distance = (int) InfraroodSensor.getDistance();
			rijdStukNaarBeacon(distance);
			Logging.log("Stuk naar beacon gereden totdat de distance = " + distance);
			Lcd.clear();

			// gaat weer de hoek bepalen en nu nauwkeuriger.
			direction = (int) InfraroodSensor.getDirection();
			Logging.log("de direction is nu: " + direction);
			
			draaiNaarBeacon(direction);
			Logging.log("draaiNaarBeacon uitgevoerd, direction: " + direction);
			Lcd.clear();

			// gaat nu weer naar de beacon rijden totdat de touchsensor het object heeft aangeraakt.
			rijdLaatsteStukBeacon();
			Lcd.clear();
			Wielaandrijving.stop();

		} catch (Exception e) {
			System.out.println("error bij rijnaarbeacon");
		}

	}

	private void rijdLaatsteStukBeacon() {
		int direction;
		int distance;
		while (!TouchSensor.isTouched()) {
			Logging.log("In de vierde while loop");
			Logging.log("moet er uit als touchsensor is touched");

			Wielaandrijving.setSnelheid(150, 0);

			direction = (int) InfraroodSensor.getDirection();
			distance = (int) InfraroodSensor.getDistance();
			Lcd.print(2, "direction: " + direction);
			Lcd.print(3, "distance: " + distance);
			Lcd.print(4, "in de vierde while loop");
		}
	}

	private void draaiNaarBeacon(int direction) {
		//int distance;
		while ((direction > 1) || (direction < -1)) {
			Logging.log("in de derde while loop: direction=" + direction);
			Logging.log("moet er uit als direction 0 is");

			Motor.draaiOmAs(20, -20);

			direction = (int) InfraroodSensor.getDirection();
			//distance = (int) InfraroodSensor.getDistance();
			Lcd.print(2, "direction: " + direction);
			//Lcd.print(3, "distance: " + distance);
			Lcd.print(4, "in de derde while loop. (direction opniew)");
		}
	}

	private void rijdStukNaarBeacon(int distance) {
		// int direction;
		while ((distance > 10)) {
			Logging.log("in de tweede while loop: distance=" + distance);
			Logging.log("moet er uit als distance kleiner is dan 10");

			Wielaandrijving.setSnelheid(70, 0);

			// direction = (int) InfraroodSensor.getDirection();
			distance = (int) InfraroodSensor.getDistance();
			// Lcd.print(2, "direction: " + direction);
			Lcd.print(3, "distance: " + distance);
			Lcd.print(4, "rijd stukje naar Beacon");
		}
	}

	private void zoekNauwkeurigBeacon(int direction) {
		int distance;
		while (direction > 1 || direction < -1) {

			Motor.draaiOmAs(20, -20);

			direction = (int) InfraroodSensor.getDirection();
			distance = (int) InfraroodSensor.getDistance();
			Lcd.print(2, "direction: " + direction);
			Lcd.print(3, "distance: " + distance);
			Lcd.print(4, "De speciale loop");
		}
	}

	private void zoekBeacon(int direction) {
		int distance;
		if (direction == 0) {
			do {
				Logging.log("in de eerste if direction while loop: direction=" + direction);
				Logging.log("moet er uit knallen als de direction 1 of 0 is");
				Motor.draaiOmAs(40, -40);
				// Wielaandrijving.draaiOmAs(8, false);
				// Delay.msDelay(500);
				direction = (int) InfraroodSensor.getDirection();
				distance = (int) InfraroodSensor.getDistance();
				Lcd.print(2, "direction: " + direction);
				Lcd.print(3, "distance: " + distance);
				Lcd.print(4, "in de eerste if while loop");
			} while ((direction > 10) || (direction < -10) || (direction == 0));

		} else {

			while ((direction > 1 || direction < -1)) {
				Logging.log("in de eerste else direction while loop: direction=" + direction);
				Logging.log("moet er uit knallen als de direction 1 of 0 is");

				Motor.draaiOmAs(20, 0);
				Wielaandrijving.draaiOmAs(5, false);
				Delay.msDelay(1000);

				direction = (int) InfraroodSensor.getDirection();
				distance = (int) InfraroodSensor.getDistance();
				Lcd.print(2, "direction: " + direction);
				Lcd.print(3, "distance: " + distance);
				Lcd.print(4, "in de eerste else while loop");
			}
		}
	}

	public void grijpBeacon() {
		Lcd.print(3, "GRIJPT BEACON");
		GrijpMotor.grijpen();
		Sound.beepSequenceUp();
		Lcd.clear();
	}

	public void rijdMetBeacon() {
		// Wielaandrijving.rijAfstand(Wielaandrijving.getMaxLineaireSnelheid()/5 , 200,
		// false);
		Lcd.print(3, "RIJDMETBEACON");

		Wielaandrijving.rijAfstand(100, 200, false);
		// Wielaandrijving.setSnelheid(50, 100);
		Delay.msDelay(2000);
		Wielaandrijving.stop();
		Wielaandrijving.setSnelheid(100, 200);
		Delay.msDelay(2000);
		Wielaandrijving.stop();
		Lcd.clear();

	}

	public void laatBeaconLos() {
		Lcd.print(3, "LOSLATEN BEACON");

		GrijpMotor.losLaten();
		Delay.msDelay(2000);
		Lcd.clear();
	}

	public void rijdWeg() {

		Lcd.print(3, "RIJD WEG");

		Motor.rechtAchteruit(800);
		Delay.msDelay(3000);
		Motor.rem();
		Lcd.clear();
	}

}
