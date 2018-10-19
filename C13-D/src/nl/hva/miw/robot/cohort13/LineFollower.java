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
		float maxSpeedMotor= Motor.getMaxSpeed();

		Motor.rechtVooruit((int)maxSpeedMotor / 2);
		
		while (!TouchSensor.isTouched() && Button.ESCAPE.isUp()) {

            colorValue = RedSensor.getRed();
            
            Lcd.clear(7);
            Lcd.print(7,  "value=%.3f", colorValue);

            if (colorValue > ((colorBorder + colorValueWhite) / 2)) { //Linksom snel draaien
            	Logging.log("linksom snel draaien met color value: %.3f", RedSensor.getRed());
            	Motor.draaiOmAs((-(int)(maxSpeedMotor * (float)0.15)), (int)(maxSpeedMotor * (float)0.3));
            } else if (colorValue > colorBorder) { //Linksom draaien
            	Logging.log("linksom draaien met color value: %.3f", RedSensor.getRed());
            	Motor.bochtVooruit((int)(maxSpeedMotor * (float)0.2), (int)(maxSpeedMotor * (float)0.4));
            } else if (colorValue < ((colorBorder + colorValueBlack)) / 2) { //Rechtsom snel draaien
            	Logging.log("Rechtstom snel draaien met color value: %.3f", RedSensor.getRed());
            	Motor.draaiOmAs((int)(maxSpeedMotor * (float)0.3), (-(int)(maxSpeedMotor * (float)0.15)));
            } else { //Rechtsom draaien
            	Logging.log("Rechtsom draaien met color value: %.3f", RedSensor.getRed());
            	Motor.bochtVooruit((int)(maxSpeedMotor * (float)0.4), (int)(maxSpeedMotor * (float)0.2));
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