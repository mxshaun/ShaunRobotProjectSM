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
		float minimaalVermogen = -80;
		float maximaalVermogen = 720;

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
		float maxSpeedMotor = (maximaalVermogen * ((float) 0.75));
		Motor.rechtVooruit((int) maxSpeedMotor);
		Logging.log("maximale snelheid is %f", maxSpeedMotor);

		while (!TouchSensor.isTouched() && Button.ESCAPE.isUp()) {
			colorValue = RedSensor.getRed();
			Lcd.clear(7);
			Lcd.print(7, "value=%.3f", colorValue);
			Logging.log("ColorValue: %f", colorValue);
			float snelheidWielB = ((colorValue / colorValueWhite) * ((float)0.8 * maxSpeedMotor)) 
					- ((float) 0.2 * maxSpeedMotor);
			float snelheidWielA = (((1 - colorValue) / colorValueBlack) * ((float)0.8 * maxSpeedMotor)) 
					- ((float) 0.2 * maxSpeedMotor);
			//float snelheidWielA = maximaalVermogen - ((-(maximaalVermogen - minimaalVermogen) / (colorValueWhite - colorValueBlack))
			//					* ((colorValueWhite - colorValueBlack) + (colorValueBlack - colorValue)) 
			//					+ (maximaalVermogen - minimaalVermogen));
			//float snelheidWielB = minimaalVermogen - ((maximaalVermogen - minimaalVermogen) / (colorValueWhite - colorValueBlack))
			//					* ((colorValueWhite - colorValueBlack) + (colorValueBlack - colorValue)) 
			//					- (maximaalVermogen - minimaalVermogen);
			Logging.log("wiel a snelheid: %f en wiel b snelheid: %f", snelheidWielA, snelheidWielB);
			if (snelheidWielA < 0 || snelheidWielB < 0) {
				Logging.log("draait om as 'als het ware'");
				if(snelheidWielA < 0) {				
					Motor.draaiOmAs(((int) (snelheidWielA * (float) 1.3)), (int) snelheidWielB);
					Logging.log("snelheid ach teruit = %f", snelheidWielA);
				} else {
					Motor.draaiOmAs((int) snelheidWielA, ((int) (snelheidWielB * (float) 1.3)));
					Logging.log("snelheid achteruit = %f", snelheidWielB);
				}
				
				
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