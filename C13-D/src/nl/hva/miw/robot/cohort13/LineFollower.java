package nl.hva.miw.robot.cohort13;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.*;
import lejos.hardware.port.*;
import lejos.robotics.Color;
import ev3.robotproject.library.*;
import ev3.robotproject.library.Motor;

import java.io.IOException;

public class LineFollower {

	public static void main(String[] args) throws Exception {
		Logging.setup(LineFollower.class.getPackage(), false);
		Logging.log("Start");
		float colorValueWhite;
		float colorValueBlack;
		float colorValue;
		float colorBorder;

		System.out.println("Line Follower\n");

		Button.LEDPattern(4); // flash green led and
		Sound.beepSequenceUp(); // make sound when ready.

		System.out.println("Press any key to start the calibration");

		Button.waitForAnyPress();
		Logging.log("Button is pressed");

		System.out.println("Scan wit vlak, press button");
		Button.waitForAnyPress();

		colorValueWhite = RedSensor.getRed();

		Logging.log("colorValueWhite: %f", colorValueWhite);

		System.out.println("Scan zwart vlak, press button");
		Button.waitForAnyPress();

		colorValueBlack = RedSensor.getRed();

		Logging.log("colorValueBlack: %f", colorValueBlack);

		colorBorder = (colorValueBlack + colorValueWhite) / 2;
		Logging.log("colorborder: %f", colorBorder);
		Button.LEDPattern(4); // flash green led and
		Sound.beepSequenceUp(); // make sound when ready.

		System.out.println("Press any key to start the race");
		Button.waitForAnyPress();
		float maxSpeedMotor = (Motor.getMaxSpeed() * (float) 0.75);
		Motor.rechtVooruit((int) maxSpeedMotor);
		Logging.log("maximale snelheid is %f", maxSpeedMotor);

		while (!TouchSensor.isTouched() && Button.ESCAPE.isUp()) {
			colorValue = RedSensor.getRed();
			Lcd.clear(7);
			Lcd.print(7, "value=%.3f", colorValue);
			Logging.log("ColorValue: %f", colorValue);
			float snelheidWielB = ((colorValue / colorValueWhite) * ((float)0.9 * maxSpeedMotor)) - ((float) 0.1 * maxSpeedMotor);
			float snelheidWielA = ((float) 0.4 * maxSpeedMotor) - snelheidWielB;
			Logging.log("wiel a snelheid: %f en wiel b snelheid: %f", snelheidWielA, snelheidWielB);
			if (snelheidWielA < 0 || snelheidWielB < 0) {
				Motor.draaiOmAs((int) snelheidWielA, (int) snelheidWielB);
				Logging.log("draait om as 'als het ware'");
			} else {
				Motor.bochtVooruit((int) snelheidWielA, (int) snelheidWielB);
				Logging.log("bocht vooruit 'als het ware'");
			}
			
		}

		// stop motors with brakes on.
		Motor.uitRollen();

		// free up resources.
		Motor.sluit();
		TouchSensor.close();
		RedSensor.close();

		Sound.beepSequence(); // we are done.
	}
}