package nl.hva.miw.robot.cohort13;

import ev3.robotproject.library.BeaconSensor;
import ev3.robotproject.library.GrijpMotor;
import ev3.robotproject.library.Lcd;
import ev3.robotproject.library.Motor;
import ev3.robotproject.library.TouchSensor;
import lejos.hardware.Button;
import lejos.utility.Delay;

public class BeaconTest {

	public static void main(String[] args) {

		// Begin van de code. Robot vraagt om te beginnen en wacht op een knop
		// indrukken.
		System.out.println("start om te beginnen");
		Button.waitForAnyPress();

		// de robot blijft draaien tot de beacon is gevonden (totdat de beacon in zicht
		// is van de infrarood sensor).
		BeaconSensor.zoekBeacon();

		// Loop die scant waar de beacon is en er naartoe rijd.
		while (!TouchSensor.isTouched() && Button.ESCAPE.isUp() && (BeaconSensor.getDistance() > 10)) {

			int direction = (int) BeaconSensor.getDirection();
			int distance = (int) BeaconSensor.getDistance();

			Lcd.print(1, "BeaconTest actief");
			Lcd.clear(2);
			Lcd.clear(3);
			Lcd.clear(4);
			Lcd.print(5, "Versie 1.0.5");
			Lcd.print(6, "Dir: %d", direction);
			Lcd.print(7, "Dis: %d", distance);

			// aansturen motoren op basis van direction
			if (direction > 0) {
				Motor.bochtVooruit(400, 200);
			} else if (direction < 0) {
				Motor.bochtVooruit(200, 400);
			} else {
				if (distance < 10) {
					Motor.rem();
					// we moeten uit de while loop (break statement zorgde laatste keer voor een crash. dus misschien in de while loop voorwaarden verwerken).

				} else {
					Motor.rechtVooruit(100);
				}
			}
		}
		
		// Beacon zou nu voor de robot moeten staan. De grijper gaat nu het object pakken. 
		GrijpMotor.grijpen();
		// Een stuk ermee rijden? en daar weer loslaten.
		
		// Motor maakt bochtje achteruit
		Motor.bochtAchteruit(-100, 100);
		Delay.msDelay(5000);
		Motor.rem();
		
		// Motor maakt bocht vooruit
		Motor.bochtVooruit(400, 200);
		Delay.msDelay(5000);
		Motor.rem();
		
		// Laat beacon los
		GrijpMotor.losLaten();
		
		// Motor stukje achteruit
		Motor.rechtAchteruit(400);
		
		

	}
}