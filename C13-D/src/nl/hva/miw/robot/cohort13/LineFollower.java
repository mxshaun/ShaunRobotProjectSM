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
	//static UnregulatedMotor motorA = new UnregulatedMotor(MotorPort.A);
	//static UnregulatedMotor motorB = new UnregulatedMotor(MotorPort.B);
	static TouchSensor touch = new TouchSensor(SensorPort.S1);
	static ColorSensor color = new ColorSensor(SensorPort.S3);
	static EV3LargeRegulatedMotor motorAA = new EV3LargeRegulatedMotor(MotorPort.A);
	static EV3LargeRegulatedMotor motorBB = new EV3LargeRegulatedMotor(MotorPort.B);

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
		float maxSpeedMotorA = motorAA.getMaxSpeed(); 
		float maxSpeedMotorB = motorBB.getMaxSpeed();
		
//		motorAA.setSpeed(maxSpeedMotorA / 2);
//		motorBB.setSpeed(maxSpeedMotorB / 2);
//		motorAA.forward();
//		motorBB.forward();
		//motorB.setPower(25);

		// drive waiting for touch sensor or escape key to stop driving.

//		while (!touch.isTouched() && Button.ESCAPE.isUp()) {
//			
//			colorValue = color.getRed();
//			Logging.log("gemeten colorvalue: %f",  colorValue);
//			LCD.clear(7);
////			float maxSpeedMotorAA = motorAA.getMaxSpeed();
////			float maxSpeedMotorBB = motorBB.getMaxSpeed();
//			
//			//Voordat de loop-comando's gegeven worden, worden de motoren gestopt.
//			motorAA.stop();
//			motorBB.stop();
//
//			if (colorValue > colorBorder) {
//				if (colorValue/colorBorder > 1.5) {
//					motorAA.setSpeed(maxSpeedMotorA * (float)0.5);
//					motorBB.setSpeed(maxSpeedMotorB * (float)0.5);
//					motorAA.forward();
//					motorBB.backward();
////				} else if (colorValue/colorBorder > 1.6) {
////					motorAA.setSpeed(maxSpeedMotorAA * (float)0.8);
////					motorBB.setSpeed(maxSpeedMotorBB * (float)0.2);
////
////					motorAA.forward();
////					motorBB.backward();
////				} else if (colorValue/colorBorder > 1.5) {
////					motorAA.setSpeed(maxSpeedMotorAA * (float)0.4);
////					motorBB.setSpeed(maxSpeedMotorBB * (float)0.1);
////
////					motorAA.forward();
////					motorBB.backward();
////				} else if (colorValue/colorBorder > 1.4) {
////					motorAA.setSpeed(maxSpeedMotorAA * (float)0.4);
////					motorBB.setSpeed(maxSpeedMotorBB * (float)0.0);
////
////					motorAA.forward();
////					motorBB.forward();
////				} else if (colorValue/colorBorder > 1.3) {
////					motorAA.setSpeed(maxSpeedMotorAA * (float)0.3);
////					motorBB.setSpeed(maxSpeedMotorBB * (float)0.1);
////
////					motorAA.forward();
////					motorBB.forward();
//				} else if (colorValue/colorBorder > 1.2) {
//					motorAA.setSpeed(maxSpeedMotorA * (float)0.5);
//					motorBB.setSpeed(maxSpeedMotorB * (float)0.3);
//
//					motorAA.forward();
//					motorBB.forward();
//				} 
//				motorAA.setSpeed(maxSpeedMotorA * (float)0.5);
//				motorBB.setSpeed(maxSpeedMotorB * (float)0.45);
//
//				motorAA.forward();
//				motorBB.forward();
//			} else {
//				if (colorValue/colorBorder < 0.30) {
//					motorAA.setSpeed(maxSpeedMotorA * (float)0.5);
//					motorBB.setSpeed(maxSpeedMotorB * (float)0.5);
//
//					motorAA.backward();
//					motorBB.forward();
////				} else if (colorValue/colorBorder < 0.40) {
////					motorAA.setSpeed(maxSpeedMotorAA * (float)0.2);
////					motorBB.setSpeed(maxSpeedMotorBB * (float)0.8);
////
////					motorAA.backward();
////					motorBB.forward();
////				} else if (colorValue/colorBorder < 0.50) {
////					motorAA.setSpeed(maxSpeedMotorAA * (float)0.1);
////					motorBB.setSpeed(maxSpeedMotorBB * (float)0.4);
////
////					motorAA.backward();
////					motorBB.forward();
////				} else if (colorValue/colorBorder < 0.60) {
////					motorAA.setSpeed(maxSpeedMotorAA * (float)0.0);
////					motorBB.setSpeed(maxSpeedMotorBB * (float)0.4);
////
////					motorAA.forward();
////					motorBB.forward();
////				} else if (colorValue/colorBorder < 0.70) {
////					motorAA.setSpeed(maxSpeedMotorAA * (float)0.1);
////					motorBB.setSpeed(maxSpeedMotorBB * (float)0.3);
////
////					motorAA.forward();
////					motorBB.forward();
//				} else if (colorValue/colorBorder < 0.80) {
//					motorAA.setSpeed(maxSpeedMotorA * (float)0.3);
//					motorBB.setSpeed(maxSpeedMotorB * (float)0.5);
//
//					motorAA.forward();
//					motorBB.forward();
//				}
//				Logging.log("Moterkrcht A: %d Motorkracht B: %d", motorAA.getSpeed(), motorBB.getSpeed());
//				motorAA.setSpeed(maxSpeedMotorA * (float)0.45);
//				motorBB.setSpeed(maxSpeedMotorB * (float)0.5);
//
//				motorAA.forward();
//				motorBB.forward();
//			}
//		}
		
		motorAA.setSpeed(maxSpeedMotorA / 2);
		motorBB.setSpeed(maxSpeedMotorB / 2);
		motorAA.forward();
		motorBB.forward();
		
		while (!touch.isTouched() && Button.ESCAPE.isUp()) {

            colorValue = color.getRed();
            
            Lcd.clear(7);
            Lcd.print(7,  "value=%.3f", colorValue);

            if (colorValue > ((colorBorder + colorValueWhite) / 2)) { //Linksom snel draaien
            	motorAA.setSpeed(maxSpeedMotorA * (float)0.3);
            	motorBB.setSpeed(maxSpeedMotorB * (float)0.15);
            	motorAA.forward();
            	motorBB.backward();
            } else if (colorValue > colorBorder) { //Linksom draaien
            	motorAA.setSpeed(maxSpeedMotorA * (float)0.4);
            	motorBB.setSpeed(maxSpeedMotorB * (float)0.2);
            	motorAA.forward();
            	motorBB.forward();
            } else if (colorValue < ((colorBorder + colorValueBlack)) / 2) { //Rechtsom snel draaien
            	motorAA.setSpeed(maxSpeedMotorA * (float)0.15);
            	motorBB.setSpeed(maxSpeedMotorB * (float)0.3);
            	motorAA.backward();
            	motorBB.forward();
            } else { //Rechtsom draaien
            	motorAA.setSpeed(maxSpeedMotorA * (float)0.2);
            	motorBB.setSpeed(maxSpeedMotorB * (float)0.4);
            	motorAA.forward();
            	motorBB.forward();
            }
        }
		
		

		// stop motors with brakes on.
		motorAA.stop();
		motorBB.stop();

		// free up resources.
		motorAA.close();
		motorBB.close();
		touch.close();
		color.close();

		Sound.beepSequence(); // we are done.
	}
}
