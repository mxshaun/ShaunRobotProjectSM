package ev3.robotproject.library;

import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.utility.Delay;

public final class GrijpMotor {

	final static EV3MediumRegulatedMotor MOTOR_GRIJP = new EV3MediumRegulatedMotor(MotorPort.C);

	
	//default constructor. doet niks.
	private GrijpMotor() {	
	}

	public static void grijpen() {
		
		MOTOR_GRIJP.setSpeed(500);
		//MOTOR_GRIJP.setSpeed((MOTOR_GRIJP.getMaxSpeed() / 3));
		MOTOR_GRIJP.backward();
		Delay.msDelay(1900);
		MOTOR_GRIJP.stop();

	}

	public static void losLaten() {
		//MOTOR_GRIJP.setSpeed((MOTOR_GRIJP.getMaxSpeed()));
		MOTOR_GRIJP.setSpeed(500);
		MOTOR_GRIJP.forward();
		Delay.msDelay(1900);
		MOTOR_GRIJP.stop();
	}

	public static void stop() {
		MOTOR_GRIJP.stop();
	}

	public static void sluit() {
		MOTOR_GRIJP.close();
	}

}
