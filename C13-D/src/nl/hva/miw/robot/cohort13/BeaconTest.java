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
		int distance = (int) BeaconSensor.getDistance();
		
		
		while (!TouchSensor.isTouched() && Button.ESCAPE.isUp() && (distance > 5)) {

//			float dir = BeaconSensor.getDirection();
//			float dis = BeaconSensor.getDistance();
//		
//			int direction  = (int) dir;
//			int distance = (int) dis;
			
			int direction = (int) BeaconSensor.getDirection();
			distance = (int) BeaconSensor.getDistance();

			Lcd.clear(1);
			Lcd.print(2, "BeaconTest actief");
			Lcd.clear(3);
			Lcd.clear(4);
			Lcd.print(5, "Versie 1.0.5");
			Lcd.print(6, "Dir: %7d", direction);
			Lcd.print(7, "Dis: %7d", distance);

			// aansturen motoren op basis van direction
			if (direction > 1) {
				Motor.bochtVooruit(400, 200);
			} else if (direction < 1) {
				Motor.bochtVooruit(200, 400);
			} else {
				Motor.rechtVooruit(300);
			}
		}
		
		
		Motor.rem();
		
		Lcd.clear();
		Lcd.print(3, "moet nu gaan grijpen");
		// Beacon zou nu voor de robot moeten staan. De grijper gaat nu het object pakken. 
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
		Lcd.clear(3);
		Lcd.print(3, "recht achteruit");
		Motor.rechtAchteruit(400);
		Delay.msDelay(2000);
		Lcd.clear(3);
		Lcd.print(3, "klaar");
		
		//
		Motor.sluit();
		GrijpMotor.sluit();
		BeaconSensor.sluit();
		

	}
}