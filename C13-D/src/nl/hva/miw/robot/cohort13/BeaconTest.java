package nl.hva.miw.robot.cohort13;

import java.io.IOException;

import ev3.robotproject.library.BeaconSensor;
import ev3.robotproject.library.GrijpMotor;
import ev3.robotproject.library.Lcd;
import ev3.robotproject.library.Logging;
import ev3.robotproject.library.Motor;
import ev3.robotproject.library.TouchSensor;
import lejos.hardware.Button;
import lejos.utility.Delay;

public class BeaconTest {

	public static void main(String[] args) throws IOException {
		Logging.setup(BeaconTest.class.getPackage(), false);
		Logging.log("Start");

		int nabijMeting = 0;
		Logging.log("nabijmeting is op 0 gezet: " + nabijMeting);
		// Begin van de code. Robot vraagt om te beginnen en wacht op een knop
		// indrukken.
		System.out.println("start om te beginnen");
		Button.waitForAnyPress();
		Logging.log("Button is pressed");

		// De robot gaat zoeken naar de beaconsensor.
		int direction = (int) BeaconSensor.getDirection();
		Logging.log("Direction = " + direction);
		
		zoekBeacon();
		Logging.log("Heeft beacon gevonden?");


		// Zet de distance om in de while loop te kunnen gaan.
		int distance = (int) BeaconSensor.getDistance();
		Logging.log("Distance = " + distance);

		// Loop die scant waar de beacon is en er naartoe rijd.
		Logging.log("Gaan de while loop in: ");

		
		while (!TouchSensor.isTouched() && Button.ESCAPE.isUp() && (nabijMeting != 4)) {
			
			Logging.log("We zijn in de while loop");
			direction = (int) BeaconSensor.getDirection();
			Logging.log("Nu is de direction: " + direction);

			distance = (int) BeaconSensor.getDistance();
			Logging.log("Nu is de distance: " + distance);

			
			
			if (distance < 1) {
				nabijMeting++;
			}
			
			Logging.log("Nabijmeting = " + nabijMeting);

			
			Lcd.clear(1);
			Lcd.print(2, "BeaconTest actief");
			Lcd.clear(3);
			Lcd.clear(4);
			Lcd.print(5, "Versie 1.0.5");
			Lcd.print(6, "Dir: %7d", direction);
			Lcd.print(7, "Dis: %7d", distance);

			// aansturen motoren op basis van direction
			if (direction > 1) {
				Motor.bochtVooruit(250, 150);
			} else if (direction < 1) {
				Motor.bochtVooruit(150, 250);
			} else {
				Motor.rechtVooruit(100);
			}
		}
		
		
		Logging.log("We zijn uit de while loop");
		Logging.log("Want de nabijMeting is 4?");
		Logging.log("De nabijMeting is: " + nabijMeting);

		Motor.rem();

		Lcd.clear();
		Lcd.print(3, "moet nu gaan grijpen");
		// Beacon zou nu voor de robot moeten staan. De grijper gaat nu het object
		// pakken.
		GrijpMotor.grijpen();

		Lcd.clear(3);
		Lcd.print(3, "bocht achteruit");
		// Motor maakt bochtje achteruit
		Motor.bochtAchteruit(-300, 300);
		Delay.msDelay(5000);
		Motor.rem();

		// Motor maakt bocht vooruit
		Lcd.clear(3);
		Lcd.print(3, "bocht voorruit");
		Motor.bochtVooruit(400, 200);
		Delay.msDelay(5000);
		Motor.rem();

		// Laat beacon los
		Lcd.clear(3);
		Lcd.print(3, "Object loslaten");
		GrijpMotor.losLaten();

		// Motor stukje achteruit
		/*Lcd.clear(3);
		Lcd.print(3, "recht achteruit");
		Motor.rechtAchteruit(400);
		Delay.msDelay(2000);
		Lcd.clear(3);
		Lcd.print(3, "klaar");*/

		//
		Motor.sluit();
		GrijpMotor.sluit();
		BeaconSensor.sluit();

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