package ev3.robotproject.library;

import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;

public final class GrijpMotor {
	
	
	final static EV3MediumRegulatedMotor MOTOR_GRIJP = new EV3MediumRegulatedMotor(MotorPort.C);
	
	
	//default constructor. doet niks.
	private GrijpMotor() {	
	}
		
	 
	public static void grijpen() {
		
		MOTOR_GRIJP.setSpeed((MOTOR_GRIJP.getMaxSpeed()/2));
		MOTOR_GRIJP.forward();
		
	}
	
	public static void losLaten() {
		MOTOR_GRIJP.setSpeed((MOTOR_GRIJP.getMaxSpeed()/2));
		MOTOR_GRIJP.backward();
	}
	
	
	public static void stop() {
		MOTOR_GRIJP.stop();
	}
	
	
	public static void sluit() {
		MOTOR_GRIJP.close();
	}

}
