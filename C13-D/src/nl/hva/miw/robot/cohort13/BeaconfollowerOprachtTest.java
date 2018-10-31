package nl.hva.miw.robot.cohort13;

import ev3.robotproject.library.InfraroodSensor;
import ev3.robotproject.library.Logging;
import ev3.robotproject.library.TouchSensor;
import ev3.robotproject.library.Wielaandrijving;

public class BeaconfollowerOprachtTest {
	
	
	

	// De robot gaat zoeken naar de beaconsensor.
	// begint met draaien en zoeken naar de beacon

	public BeaconfollowerOprachtTest() {
		// TODO Auto-generated constructor stub
		
		
	}

	public void run()  throws Exception{

		Logging.setup(BeaconfollowerOprachtTest.class.getPackage(), false);

		int direction = (int) InfraroodSensor.getDirection();

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
				Wielaandrijving.draaiOmAs(2, false);
			} else if (direction < 0) {
				Wielaandrijving.draaiOmAs(-2, false);
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
		}

	}

}
