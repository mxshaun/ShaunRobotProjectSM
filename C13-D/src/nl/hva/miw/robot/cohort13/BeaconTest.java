package nl.hva.miw.robot.cohort13;

import java.io.IOException;

import ev3.robotproject.library.BeaconSensor;
import ev3.robotproject.library.GrijpMotor;
import ev3.robotproject.library.Lcd;
import ev3.robotproject.library.Logging;
import ev3.robotproject.library.Motor;
import ev3.robotproject.library.TouchSensor;
import ev3.robotproject.library.Wielaandrijving;
import lejos.hardware.Button;
import lejos.utility.Delay;

public class BeaconTest {

	public static void main(String[] args) throws IOException {
		Logging.setup(BeaconTest.class.getPackage(), false);
		Logging.log("Start");

		

		final int GRADEN_DIRECTION_FACTOR = 180 / 50;

		// Begin van de code. Robot vraagt om te beginnen en wacht op een knop
		// indrukken.
		System.out.println("start om te beginnen");
		Button.waitForAnyPress();
		Logging.log("Button is pressed");

		// De robot gaat zoeken naar de beaconsensor.
		// begint met draaien en zoeken naar de beacon
		int direction = (int) BeaconSensor.getDirection();
		Logging.log("Direction = " + direction);

		if (direction == 0) {
			do {
				Logging.log("in de eerste if direction while loop: direction=" + direction);
				Logging.log("moet er uit knallen als de direction 1 of 0 is");
				Wielaandrijving.draaiOmAs(5, false);
				direction = (int) BeaconSensor.getDirection();
			} while (direction > 2 || direction < -2 || direction == 0);
		} else {

			while ((direction > 1 || direction < -1)) {
				Logging.log("in de eerste else direction while loop: direction=" + direction);
				Logging.log("moet er uit knallen als de direction 1 of 0 is");

				Wielaandrijving.draaiOmAs(5, false);
				direction = (int) BeaconSensor.getDirection();
			}

		}
		Logging.log("uit de eerste while loop want direction: " + direction);

		// Gaat rijden naar de beacon totdat de distance kleiner is dan 15

		int distance = (int) BeaconSensor.getDistance();

		while (distance > 10) {
			Logging.log("in de tweede while loop: distance=" + distance);
			Logging.log("moet er uit als distance kleiner is dan 15");

			Wielaandrijving.rijAfstand((Wielaandrijving.getMaxLineaireSnelheid() / 4), 200, false);
			distance = (int) BeaconSensor.getDistance();
		}
		Logging.log("uit de tweede while loop: want distance=" + distance);

		// gaat weer de hoek bepalen en nu nauwkeuriger.
		direction = (int) BeaconSensor.getDirection();
		Logging.log("uit de tweede direction while loop");
		Logging.log("de direction is nu: " + direction);

		while (direction > 0 || direction < 0) {
			Logging.log("in de derde while loop: direction=" + direction);
			Logging.log("moet er uit als direction 1 of 0 is");

			if (direction > 0) {
				Wielaandrijving.draaiOmAs(1, false);
			} else if (direction < 0) {
				Wielaandrijving.draaiOmAs(-1, false);
			}
			direction = (int) BeaconSensor.getDirection();
		}

		Logging.log("uit de derde while loop want direction: " + direction);

		// gaat nu weer naar de beacon rijden totdat de afstand 0 is.
		while (distance > 0 && !TouchSensor.isTouched()) {
			Logging.log("in de vierde while loop: distance=" + distance);
			Logging.log("moet er uit als distance kleiner is dan 3");
			Wielaandrijving.rijAfstand((Wielaandrijving.getMaxLineaireSnelheid() / 4), 10, false);
			distance = (int) BeaconSensor.getDistance();
		}

		Logging.log("uit de vierde while loop want distance= " + distance);
		Logging.log("of de touchsensor is touched");

		/*
		 * //zoekBeacon(); Logging.log("Heeft beacon gevonden?");
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * // Zet de distance om in de while loop te kunnen gaan. int distance = (int)
		 * BeaconSensor.getDistance(); Logging.log("Distance = " + distance);
		 * 
		 * // Loop die scant waar de beacon is en er naartoe rijd.
		 * Logging.log("Gaan de while loop in: ");
		 * 
		 * 
		 * 
		 * // Andere mogelijkheid is om de touchsensor te gebruiken om uit de loop te
		 * breken en het object te pakken! zeker als de uitslagen van de nabijheid
		 * afstand zo slecht blijven gaan.
		 * 
		 * 
		 * while (!TouchSensor.isTouched() && Button.ESCAPE.isUp() && (nabijMeting !=
		 * 30)) {
		 * 
		 * Logging.log("We zijn in de while loop"); direction = (int)
		 * BeaconSensor.getDirection(); Logging.log("Nu is de direction: " + direction);
		 * 
		 * distance = (int) BeaconSensor.getDistance();
		 * Logging.log("Nu is de distance: " + distance);
		 * 
		 * 
		 * 
		 * if (distance <= 1) { nabijMeting++; }
		 * 
		 * Logging.log("Nabijmeting = " + nabijMeting);
		 * 
		 * 
		 * Lcd.clear(1); Lcd.print(2, "BeaconTest actief"); Lcd.clear(3); Lcd.clear(4);
		 * Lcd.print(5, "Versie 1.0.5"); Lcd.print(6, "Dir: %7d", direction);
		 * Lcd.print(7, "Dis: %7d", distance);
		 * 
		 * // aansturen motoren op basis van direction if (direction > 1) {
		 * Motor.bochtVooruit(200, 150); } else if (direction < 1) {
		 * Motor.bochtVooruit(150, 200); } else { Motor.rechtVooruit(100); } }
		 * 
		 * 
		 * Logging.log("We zijn uit de while loop");
		 * Logging.log("Want de nabijMeting is 10?"); Logging.log("De nabijMeting is: "
		 * + nabijMeting);
		 * 
		 * Motor.rem();
		 * 
		 * Lcd.clear(); Lcd.print(3, "moet nu gaan grijpen"); // Beacon zou nu voor de
		 * robot moeten staan. De grijper gaat nu het object // pakken.
		 * GrijpMotor.grijpen();
		 * 
		 * Lcd.clear(3); Lcd.print(3, "bocht achteruit"); // Motor maakt bochtje
		 * achteruit Motor.bochtAchteruit(350, 250); Delay.msDelay(5000); Motor.rem();
		 * 
		 * // Motor maakt bocht vooruit Lcd.clear(3); Lcd.print(3, "bocht voorruit");
		 * Motor.bochtVooruit(400, 200); Delay.msDelay(5000); Motor.rem();
		 * 
		 * // Laat beacon los Lcd.clear(3); Lcd.print(3, "Object loslaten");
		 * GrijpMotor.losLaten();
		 * 
		 * // Motor stukje achteruit Lcd.clear(3); Lcd.print(3, "recht achteruit");
		 * Motor.rechtAchteruit(400); Delay.msDelay(2000); Lcd.clear(3); Lcd.print(3,
		 * "klaar");
		 * 
		 * // Motor.sluit(); GrijpMotor.sluit(); BeaconSensor.sluit();
		 */

	}

	private static void zoekBeacon() {
		int direction = (int) BeaconSensor.getDirection();
		while (direction > 10 || direction < -10) {
			Motor.draaiOmAs(-100, 100);
			direction = (int) BeaconSensor.getDirection();
		}
		Motor.rem();
	}
}