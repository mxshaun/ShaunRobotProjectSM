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
 * Dit is de beaconfollower opdracht. Hier staan alle methodes in om het beacon
 * Object te kunnen grijpen.
 *
 */
public class BeaconfollowerOprachtTest {

	// De robot gaat zoeken naar de beaconsensor.
	// begint met draaien en zoeken naar de beacon

	public BeaconfollowerOprachtTest() {
		// TODO Auto-generated constructor stub

	}

	public void startOpdracht2() throws Exception {

		Logging.setup(BeaconfollowerOprachtTest.class.getPackage(), false);

		int direction = (int) InfraroodSensor.getDirection();
		int distance = (int) InfraroodSensor.getDistance();
		Lcd.clear();

		// Gaat zoeken naar de beacon. Draait totdat de beacon is gevonden.
		zoekBeacon(direction);
		/**Logging.log("Beacon is gevonden, direction: " + direction);**/
		Lcd.clear();

		// De beacon is gevonden, maar hij gaat nu nauwkeurig naar de beacon draaien.
		zoekNauwkeurigBeacon(direction);
		/**Logging.log("Nauwkeurig gedraait naar de beacon, direction: " + direction);**/
		Lcd.clear();

		// Gaat rijden naar de beacon totdat de distance kleiner is dan 10
		distance = (int) InfraroodSensor.getDistance();
		rijdStukNaarBeacon(distance);
		/**Logging.log("Stuk naar beacon gereden totdat de distance = " + distance);**/
		Lcd.clear();

		// gaat weer de hoek bepalen en nu nauwkeuriger.
		direction = (int) InfraroodSensor.getDirection();
		/**Logging.log("de direction is nu: " + direction);**/

		draaiNaarBeacon(direction);
		/**Logging.log("draaiNaarBeacon uitgevoerd, direction: " + direction);**/
		Lcd.clear();

		// gaat nu weer naar de beacon rijden totdat de touchsensor het object heeft
		// aangeraakt.
		rijdLaatsteStukBeacon();
		Lcd.clear();
		Wielaandrijving.stop();

		// Gaat nu gebruik maken van de verschillende grijper en stukken rijden
		// methodes.
		grijpBeacon();
		rijdMetBeacon();
		laatBeaconLos();
		rijdWeg();
	}

	private void zoekBeacon(int direction) {

		if (direction == 0) {
			do {
				//Logging.log("in de eerste if direction while loop: direction=" + direction);
				//Logging.log("moet er uit knallen als de direction 1 of 0 is");

				Motor.draaiOmAs(40, -40);

				direction = (int) InfraroodSensor.getDirection();
				Lcd.print(2, "direction: " + direction);
				Lcd.print(4, "zoekBeacon if");
			} while ((direction > 10) || (direction < -10) || (direction == 0));

		} else {

			while ((direction > 1 || direction < -1)) {
				//Logging.log("in de eerste else direction while loop: direction=" + direction);
				//Logging.log("moet er uit knallen als de direction 1 of 0 is");

				Motor.draaiOmAs(20, 0);
				Wielaandrijving.draaiOmAs(5, false);
				Delay.msDelay(1000);

				direction = (int) InfraroodSensor.getDirection();
				Lcd.print(2, "direction: " + direction);
				Lcd.print(4, "zoekBeacon else");
			}
		}
	}

	private void zoekNauwkeurigBeacon(int direction) {
		while (direction > 1 || direction < -1) {

			Motor.draaiOmAs(20, -20);

			direction = (int) InfraroodSensor.getDirection();
			Lcd.print(2, "direction: " + direction);
			Lcd.print(4, "Nauwkeurig zoeken");
		}
	}

	private void rijdStukNaarBeacon(int distance) {

		while ((distance > 10)) {
			//Logging.log("in de tweede while loop: distance=" + distance);
			//Logging.log("moet er uit als distance kleiner is dan 10");

			Wielaandrijving.setSnelheid(70, 0);

			distance = (int) InfraroodSensor.getDistance();
			Lcd.print(3, "distance: " + distance);
			Lcd.print(4, "Rijd stukje naar Beacon");
		}
	}

	private void draaiNaarBeacon(int direction) {
		while ((direction > 1) || (direction < -1)) {
			//Logging.log("in de derde while loop: direction=" + direction);
			//Logging.log("moet er uit als direction 0 is");

			Motor.draaiOmAs(20, -20);

			direction = (int) InfraroodSensor.getDirection();
			Lcd.print(2, "direction: " + direction);
			Lcd.print(4, "draai naar beacon laatste keer");
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
			Lcd.print(4, "rijd laatste stuk Beacon, bots!");
		}
	}

	public void grijpBeacon() {
		Lcd.print(3, "GRIJPT BEACON");
		GrijpMotor.grijpen();
		Sound.beepSequenceUp();
		Lcd.clear();
	}

	public void rijdMetBeacon() {
		Lcd.print(3, "RIJDMETBEACON");

		Wielaandrijving.rijAfstand(100, 200, false);
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
