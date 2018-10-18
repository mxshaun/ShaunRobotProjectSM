package nl.hva.miw.robot.cohort13;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.*;
import lejos.hardware.port.*;
import lejos.robotics.Color;
import ev3.robotproject.library.*;
import java.io.IOException;

public class LineFollowerVersie2 {
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
		
		//Scanner instellingen
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
            } else if (colorValue < ((colorBorder + colorValueBlack)) / 2) { //Rechtsom snel draaien
            	motorAA.setSpeed(maxSpeedMotorA * (float)0.15);
            	motorBB.setSpeed(maxSpeedMotorB * (float)0.3);
            	motorAA.backward();
            	motorBB.forward();
            } else { //Rechtsom draaien
            	motorAA.setSpeed(maxSpeedMotorA * (colorValue-colorValueBlack));
            	motorBB.setSpeed(maxSpeedMotorB * (colorValueWhite-colorValue));
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
