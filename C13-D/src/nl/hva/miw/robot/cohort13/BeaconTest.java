package nl.hva.miw.robot.cohort13;

import java.io.IOException;

import ev3.robotproject.library.BeaconSensor;
import ev3.robotproject.library.GrijpMotor;
import ev3.robotproject.library.Lcd;
import ev3.robotproject.library.Logging;
import ev3.robotproject.library.Motor;
import ev3.robotproject.library.TouchSensor;
import ev3.robotproject.library.Wielaandrijving;
import lejos.hardware.Button;
import lejos.utility.Delay;

public class BeaconTest implements Runnable {
	private final String NAAM = "BeaconScanner";
	private BeaconFollowerOpdracht2 opdracht2;

	public BeaconTest(BeaconFollowerOpdracht2 beaconFollowerOpdracht2) {
		this.opdracht2 = beaconFollowerOpdracht2;
	}

	public  int getDirection() {
		int direction = (int) BeaconSensor.getDirection();
		return direction;
	}
	
	public int getDistance() {
		int distance = (int) BeaconSensor.getDistance();
		return distance;
	}
	
	


	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		while(opdracht2.getStartOpdracht()) {
			
			opdracht2.setDirection(getDirection());
			opdracht2.setDistance(getDistance());
			//System.out.println("Direction bepaald: " + getDirection());
			//System.out.println("Distance bepaald: " + getDistance());
			
			
		}
		
	}

}
