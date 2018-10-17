package nl.hva.miw.robot.cohort13;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.*;
import lejos.hardware.port.*;
import lejos.robotics.Color;
import ev3.robotproject.library.*;
import java.io.IOException;

public class LineFollower {
	static UnregulatedMotor motorA = new UnregulatedMotor(MotorPort.A);
	static UnregulatedMotor motorB = new UnregulatedMotor(MotorPort.B);
	static TouchSensor touch = new TouchSensor(SensorPort.S1);
	static ColorSensor color = new ColorSensor(SensorPort.S3);

	public static void main(String[] args) throws Exception {
		Logging.setup(LineFollower.class.getPackage(), false);
		Logging.log("Start");
		float colorValueWhite;
		float colorValueBlack;
		float colorValue;
		float colorBorder;

		System.out.println("Line Follower\n");

		color.setRedMode();
		color.setFloodLight(Color.RED);
		color.setFloodLight(true);
		

		Button.LEDPattern(4); // flash green led and
		Sound.beepSequenceUp(); // make sound when ready.

		System.out.println("Press any key to start the calibration");

		Button.waitForAnyPress();
		Logging.log("Button is pressed");
		
		System.out.println("Scan wit vlak, press button");
		Button.waitForAnyPress();
		colorValueWhite = color.getRed();
		Logging.log("colorValueWhite: %f", colorValueWhite);
		
		System.out.println("Scan zwart vlak, press button");
		Button.waitForAnyPress();
		colorValueBlack = color.getRed();
		Logging.log("colorValueBlack: %f", colorValueBlack);

		
		colorBorder = (colorValueBlack + colorValueWhite) / 2;
		Logging.log("colorborder: %f", colorBorder);
		Button.LEDPattern(4); // flash green led and
		Sound.beepSequenceUp(); // make sound when ready.

		System.out.println("Press any key to start the race");
		Button.waitForAnyPress();
		motorA.setPower(25);
		motorB.setPower(25);

		// drive waiting for touch sensor or escape key to stop driving.

		while (!touch.isTouched() && Button.ESCAPE.isUp()) {
			
			colorValue = color.getRed();
			Logging.log("gemeten colorvalue: %f",  colorValue);
			LCD.clear(7);

			if (colorValue > colorBorder) {
				if (colorValue/colorBorder > 1.7) {
					motorA.setPower(90);
					motorB.setPower(0);
				} else if (colorValue/colorBorder > 1.6) {
					motorA.setPower(90);
					motorB.setPower(0);
				} else if (colorValue/colorBorder > 1.5) {
					motorA.setPower(80);
					motorB.setPower(10);
				} else if (colorValue/colorBorder > 1.4) {
					motorA.setPower(70);
					motorB.setPower(10);
				} else if (colorValue/colorBorder > 1.3) {
					motorA.setPower(60);
					motorB.setPower(10);
				} else if (colorValue/colorBorder > 1.2) {
					motorA.setPower(50);
					motorB.setPower(10);
				} else if (colorValue/colorBorder > 1.1) {
					motorA.setPower(40);
					motorB.setPower(10);
				}
				motorA.setPower(20);
				motorB.setPower(10);
			} else {
				if (colorValue/colorBorder < 0.40) {
					motorA.setPower(0);
					motorB.setPower(90);
				} else if (colorValue/colorBorder < 0.50) {
					motorA.setPower(0);
					motorB.setPower(80);
				} else if (colorValue/colorBorder < 0.60) {
					motorA.setPower(10);
					motorB.setPower(70);
				} else if (colorValue/colorBorder < 0.70) {
					motorA.setPower(10);
					motorB.setPower(60);
				} else if (colorValue/colorBorder < 0.80) {
					motorA.setPower(10);
					motorB.setPower(50);
				} else if (colorValue/colorBorder < 0.90) {
					motorA.setPower(10);
					motorB.setPower(40);
				}
				Logging.log("Moterkrcht A: %d Motorkracht B: %d", motorA.getPower(), motorB.getPower());
				motorA.setPower(10);
				motorB.setPower(20);
			}
		}

		// stop motors with brakes on.
		motorA.stop();
		motorB.stop();

		// free up resources.
		motorA.close();
		motorB.close();
		touch.close();
		color.close();

		Sound.beepSequence(); // we are done.
	}
}
