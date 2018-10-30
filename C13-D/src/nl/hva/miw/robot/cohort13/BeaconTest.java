package nl.hva.miw.robot.cohort13;

import java.io.IOException;

import ev3.robotproject.library.InfraroodSensor;
import ev3.robotproject.library.Motor;


public class BeaconTest implements Runnable {
	private final String NAAM = "BeaconScanner";
	private BeaconFollowerOpdracht2 opdracht2;

	public BeaconTest(BeaconFollowerOpdracht2 beaconFollowerOpdracht2) {
		this.opdracht2 = beaconFollowerOpdracht2;
	}

		// De robot gaat zoeken naar de beaconsensor.
		// begint met draaien en zoeken naar de beacon
		
/*	int direction = (int) InfraroodSensor.getDirection();
		Logging.log("Direction = " + direction);

		if (direction == 0) {
			do {
				Logging.log("in de eerste if direction while loop: direction=" + direction);
				Logging.log("moet er uit knallen als de direction 1 of 0 is");
				Wielaandrijving.draaiOmAs(5, false);
				direction = (int) InfraroodSensor.getDirection();
			} while (direction > 2 || direction < -2 || direction == 0);
		} else {

			while ((direction > 1 || direction < -1)) {
				Logging.log("in de eerste else direction while loop: direction=" + direction);
				Logging.log("moet er uit knallen als de direction 1 of 0 is");

				Wielaandrijving.draaiOmAs(5, false);
				direction = (int) InfraroodSensor.getDirection();
			}

		}
		Logging.log("uit de eerste while loop want direction: " + direction);

		// Gaat rijden naar de beacon totdat de distance kleiner is dan 15

		int distance = (int) InfraroodSensor.getDistance();

		while (distance > 10) {
			Logging.log("in de tweede while loop: distance=" + distance);
			Logging.log("moet er uit als distance kleiner is dan 15");

			Wielaandrijving.rijAfstand((Wielaandrijving.getMaxLineaireSnelheid() / 4), 200, false);
			distance = (int) InfraroodSensor.getDistance();
		}
		Logging.log("uit de tweede while loop: want distance=" + distance);

		// gaat weer de hoek bepalen en nu nauwkeuriger.
		direction = (int) InfraroodSensor.getDirection();
		Logging.log("uit de tweede direction while loop");
		Logging.log("de direction is nu: " + direction);

		while (direction > 0 || direction < 0) {
			Logging.log("in de derde while loop: direction=" + direction);
			Logging.log("moet er uit als direction 1 of 0 is");

			if (direction > 0) {
				Wielaandrijving.draaiOmAs(1, false);
			} else if (direction < 0) {
				Wielaandrijving.draaiOmAs(-1, false);
			}
			direction = (int) InfraroodSensor.getDirection();
		}

		Logging.log("uit de derde while loop want direction: " + direction);

		// gaat nu weer naar de beacon rijden totdat de afstand 0 is.
		while (distance > 0 && !TouchSensor.isTouched()) {
			Logging.log("in de vierde while loop: distance=" + distance);
			Logging.log("moet er uit als distance kleiner is dan 3");
			Wielaandrijving.rijAfstand((Wielaandrijving.getMaxLineaireSnelheid() / 4), 10, false);
			distance = (int) InfraroodSensor.getDistance();
*/
			
	public  int getDirection() {
		int direction = (int) InfraroodSensor.getDirection();
		return direction;
	}
	
	public int getDistance() {
		int distance = (int) InfraroodSensor.getDistance();
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


/*	private static void zoekBeacon() {
		int direction = (int) InfraroodSensor.getDirection();
		while (direction > 10 || direction < -10) {
			Motor.draaiOmAs(-100, 100);
			direction = (int) InfraroodSensor.getDirection();
		}
		Motor.rem();
	}*/
}


