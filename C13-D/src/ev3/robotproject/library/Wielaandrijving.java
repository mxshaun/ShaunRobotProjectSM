
package ev3.robotproject.library;


import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;

public final class Wielaandrijving {

	/**
	 * @ To-Do, Het Chassis moet nog exacter uitgemeten, in de zin van meten en
	 *   testen. Dus Wheel1 en 2, dienen nog exactere waardes te krijgen - ter
	 *   vervanging van '43.2' en '64.8'.
	 */

	private static Wheel wheel1 = WheeledChassis.modelWheel(Motor.MOTOR_LINKER, 43.2).offset(-64.8);
	private static Wheel wheel2 = WheeledChassis.modelWheel(Motor.MOTOR_RECHTER, 43.2).offset(64.8);
	private static Chassis chassis = new WheeledChassis(new Wheel[] { wheel1, wheel2 },WheeledChassis.TYPE_DIFFERENTIAL);
	private static MovePilot piloot = new MovePilot(chassis);

	private Wielaandrijving() {
	}

	/**
	 * @return double van de maximale draaisnelheid om de as in graden per seconde
	 */
	public static double getMaxDraaisnelheid() {
		return chassis.getMaxAngularSpeed();
	}

	/**
	 * @return double van de maximale snelheid in eenheden afstand per seconde
	 */
	public static double getMaxLineaireSnelheid() {
		return chassis.getMaxLinearSpeed();
	}

	/**
	 * Geeft vals bij stilstand en vice versa.
	 * 
	 * @return boolean
	 */
	public static boolean beweegt() {
		return chassis.isMoving();
	}

	/**
	 * Snelheid van bewegen in eenheden afstand per seconde.
	 * 
	 * Let op! Bij een maximale lineaire snelheid kan de robot niet een kant op
	 * draaien, omdat het draaien een hogere snelheid van het ene wiel te
	 * overstaande van het andere wiel vereist.
	 * 
	 * @param lineaireSnelheid, de eenheden afstand per seconde, die de robot
	 *        rechtvooruit aflegt.
	 * @param draaiSnelheid, de snelheid in graden per seconde die de robot draait.
	 */
	public static void setSnelheid(double lineaireSnelheid, double draaiSnelheid) {
		chassis.setVelocity(lineaireSnelheid, draaiSnelheid);
	}

	/**
	 * @param graden, draait x 'graden' om de as.
	 * @param wacht, wacht tot de draai voltooid is bij 'true'
	 */
	public static void draaiOmAs(double graden, boolean wacht) {
		piloot.rotate(graden, wacht);
	}

	/**
	 * Rij met maximale snelheid vooruit.
	 * 
	 * Let op! Deze methode zorgt er eerst voor dat de robot stil staat! Dus als de
	 * robot draaiende om de as is, en de methode zorgt niet voor het wachten tot
	 * het afmaken van de draai, dan wordt de draai onderbroken!
	 * 
	 */
	public static void vooruit(double snelheid) {
		piloot.stop();
		piloot.setLinearSpeed(snelheid);
		piloot.forward();
	}

	public static void rijAfstand(double snelheid, double afstand, boolean wacht) {
		piloot.stop();
		piloot.setLinearSpeed(snelheid);
		piloot.travel(afstand, wacht);
	}

	/**
	 * @return boolean, true als de robot in beweging was en vice versa.
	 */
	public static boolean stop() {
		if (beweegt()) {
			piloot.stop();
			return true;
		} else {
			piloot.stop();
			return false;
		}

	}

}
