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
		float colorValue;

		System.out.println("Line Follower\n");

		color.setRedMode();
		color.setFloodLight(Color.RED);
		color.setFloodLight(true);

		Button.LEDPattern(4); // flash green led and
		Sound.beepSequenceUp(); // make sound when ready.

		System.out.println("Press any key to start");

		Button.waitForAnyPress();
		Logging.log("Button is pressed");
		motorA.setPower(40);
		motorB.setPower(40);

		// drive waiting for touch sensor or escape key to stop driving.

		while (!touch.isTouched() && Button.ESCAPE.isUp()) {
			
			colorValue = color.getRed();
			Logging.log("gemeten colorvalue: %f",  colorValue);
			LCD.clear(7);

			if (colorValue > .35) {
				if (colorValue > .70) {
					motorA.setPower(90);
					motorB.setPower(10);
				} else if (colorValue > .65) {
					motorA.setPower(90);
					motorB.setPower(0);
				} else if (colorValue > .60) {
					motorA.setPower(80);
					motorB.setPower(10);
				} else if (colorValue > .55) {
					motorA.setPower(70);
					motorB.setPower(20);
				} else if (colorValue > .50) {
					motorA.setPower(60);
					motorB.setPower(20);
				} else if (colorValue > .45) {
					motorA.setPower(50);
					motorB.setPower(20);
				} else if (colorValue > .40) {
					motorA.setPower(40);
					motorB.setPower(20);
				}
				motorA.setPower(40);
				motorB.setPower(20);
			} else {
				if (colorValue < .05) {
					motorA.setPower(0);
					motorB.setPower(90);
				} else if (colorValue < .10) {
					motorA.setPower(0);
					motorB.setPower(90);
				} else if (colorValue < .15) {
					motorA.setPower(20);
					motorB.setPower(80);
				} else if (colorValue < .20) {
					motorA.setPower(20);
					motorB.setPower(70);
				} else if (colorValue < .25) {
					motorA.setPower(20);
					motorB.setPower(60);
				} else if (colorValue < .30) {
					motorA.setPower(20);
					motorB.setPower(50);
				}
				Logging.log("Moterkrcht A: %d Motorkracht B: %d", motorA.getPower(), motorB.getPower());
				motorA.setPower(20);
				motorB.setPower(40);
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
