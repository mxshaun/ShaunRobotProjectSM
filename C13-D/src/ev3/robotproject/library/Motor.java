package ev3.robotproject.library;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;

public final class Motor {


	// declareren van het rechter wiel motor (MOTOR_A) en het linker wiel motor
	// (MOTOR_B):

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

	/**
	 * Deze methode laat de robot om zijn as draaien, middels de wielen. Let op: Een
	 * van de parameters dient een negatieve waarde te zijn!
	 * 
	 * @param vermogenLinks, draai deze motor met dit vermogen forward of backward.
	 * @param vermogenRechts, draai de motor met dit vermogen forward of backward.
	 */
	public static void draaiOmAs(int vermogenLinks, int vermogenRechts) {
		// test voor negatieve waarde op de linkermotor parameter
		if (vermogenLinks < 0) {
			MOTOR_LINKER.setSpeed((vermogenLinks * (-1)));
			MOTOR_RECHTER.setSpeed(vermogenRechts);
			MOTOR_LINKER.backward();
			MOTOR_RECHTER.forward();
		} else {
			MOTOR_LINKER.setSpeed(vermogenLinks);
			MOTOR_RECHTER.setSpeed((vermogenRechts * (-1)));
			MOTOR_LINKER.forward();
			MOTOR_RECHTER.backward();
		}
	}

	/**
	 * Deze methode remt de motoren, en wacht totdat de motoren stilstaan.
	 */
	public static void rem() {
		MOTOR_LINKER.stop();
		MOTOR_RECHTER.stop();
	}

	/**
	 * Deze methode laat de motoren uitrollen, maar wacht niet tot de motorel
	 * stilstaan.
	 */
	public static void uitRollen() {
		MOTOR_LINKER.flt(true);
		MOTOR_RECHTER.flt(true);
	}

	/**
	 * Sluit alle motoren en geeft de functies vrij.
	 */
	public static void sluit() {
		MOTOR_LINKER.close();
		MOTOR_RECHTER.close();
	}

	/**
	 * het opvragen van de maximale snelheid
	 * 
	 * @return de maximale draaisnelheid in graden per seconden, in het datatype
	 *         'float'
	 */
	public static float getMaxSpeed() {
		return MOTOR_LINKER.getMaxSpeed();
	}

	public static float getSpeedLinks() {
		return MOTOR_LINKER.getSpeed();
	}

	public static float getSpeedRechts() {
		return MOTOR_RECHTER.getSpeed();
	}

}
