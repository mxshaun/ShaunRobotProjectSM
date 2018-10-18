package ev3.robotproject.library;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;

public class Motor {
	
	// declareren en initialiseren van het rechter wiel motor (MOTOR_A) en het linker wiel motor (MOTOR_B):
	final static EV3LargeRegulatedMotor MOTOR_RECHTERWIEL = new EV3LargeRegulatedMotor(MotorPort.A);
	final static EV3LargeRegulatedMotor MOTOR_LINKERWIEL = new EV3LargeRegulatedMotor(MotorPort.B);
	
	
}


