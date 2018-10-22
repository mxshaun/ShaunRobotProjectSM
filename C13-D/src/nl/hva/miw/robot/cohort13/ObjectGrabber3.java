package nl.hva.miw.robot.cohort13;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.utility.Delay;
import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.*;
import lejos.hardware.port.*;
import lejos.robotics.Color;
import ev3.robotproject.library.*;
import ev3.robotproject.library.Motor;

public class ObjectGrabber3 {

	public static void main(String[] args) {
		float range = 100;
		InfraroodSensor     uss = new InfraroodSensor(SensorPort.S4);
		System.out.println("ObjectGrabber\n");

		Button.LEDPattern(4); // flash green led and
		Sound.beepSequenceUp(); // make sound when ready.

		System.out.println("Press any key to start");
		Button.waitForAnyPress();
		
		// infraroofSensor aanzetten
		uss.enable();
		range = uss.getRange();
		
		
		// robot gaat rijden
		Motor.rechtVooruit((int)Motor.getMaxSpeed()/4);
			
		
		
		while(range > 50) {
			
			
			Lcd.clear(7);
	        Lcd.print(7,  "range=%f", range);
	        Delay.msDelay(500);
	        
	        range = uss.getRange();
		}
		
		
		Motor.rem();
		GrijpMotor.grijpen();
		Delay.msDelay(500);
		GrijpMotor.stop();
		
		GrijpMotor.losLaten();
		Delay.msDelay(1000);
		GrijpMotor.stop();
		
		
		// Free up resources
		
		Motor.sluit();
		GrijpMotor.sluit();
		uss.close();
		
		
		Sound.beepSequence(); // we are done.

		
		
		
		

	}

}
