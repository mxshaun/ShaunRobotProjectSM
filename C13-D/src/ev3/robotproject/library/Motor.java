package ev3.robotproject.library;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;

public final class Motor {
	
	// declareren van het rechter wiel motor (MOTOR_A) en het linker wiel motor (MOTOR_B):
	final static EV3LargeRegulatedMotor MOTOR_LINKER = new EV3LargeRegulatedMotor(MotorPort.A);
	final static EV3LargeRegulatedMotor MOTOR_RECHTER = new EV3LargeRegulatedMotor(MotorPort.B);
	
	private Motor() {
	}
	
	// input is in graden per seconde
	public static void rechtVooruit(int vermogen) {
		MOTOR_LINKER.setSpeed(vermogen);
		MOTOR_RECHTER.setSpeed(vermogen);
		MOTOR_LINKER.forward();
		MOTOR_RECHTER.forward();
		
	}
	
	// input is in graden per seconde
	public static void bochtVooruit(int vermogenLinks, int vermogenRechts) {
		MOTOR_LINKER.setSpeed(vermogenLinks);
		MOTOR_RECHTER.setSpeed(vermogenRechts);
		MOTOR_LINKER.forward();
		MOTOR_RECHTER.forward();
	}
	
	// input is in graden per seconde
	public static void rechtAchteruit(int vermogen) {
		MOTOR_LINKER.setSpeed(vermogen);
		MOTOR_RECHTER.setSpeed(vermogen);
		MOTOR_LINKER.backward();
		MOTOR_RECHTER.backward();
	}
	
	// input is in graden per seconde
	public static void bochtAchteruit(int vermogenLinks, int vermogenRechts) {
		MOTOR_LINKER.setSpeed(vermogenLinks);
		MOTOR_RECHTER.setSpeed(vermogenRechts);
		MOTOR_LINKER.backward();
		MOTOR_RECHTER.backward();
	}
	
	public static void sluit() {
		MOTOR_LINKER.close();
		MOTOR_RECHTER.close();
	}
}


