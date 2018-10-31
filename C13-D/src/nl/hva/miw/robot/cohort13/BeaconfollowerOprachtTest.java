package nl.hva.miw.robot.cohort13;

import ev3.robotproject.library.GrijpMotor;
import ev3.robotproject.library.InfraroodSensor;
import ev3.robotproject.library.Logging;
import ev3.robotproject.library.Motor;
import ev3.robotproject.library.TouchSensor;
import ev3.robotproject.library.Wielaandrijving;
import lejos.hardware.Sound;
import lejos.utility.Delay;

public class BeaconfollowerOprachtTest {
	
	
	

	// De robot gaat zoeken naar de beaconsensor.
	// begint met draaien en zoeken naar de beacon

	public BeaconfollowerOprachtTest() {
		// TODO Auto-generated constructor stub
		
		
	}

	public void rijNaarBeacon()  throws Exception{
		try {
		Logging.setup(BeaconfollowerOprachtTest.class.getPackage(), false);

		int direction = (int) InfraroodSensor.getDirection();
		int distance = (int) InfraroodSensor.getDistance();

		
		if (direction == 0) {
			do {
				Logging.log("in de eerste if direction while loop: direction=" + direction);
				Logging.log("moet er uit knallen als de direction 1 of 0 is");
				Wielaandrijving.draaiOmAs(8, false);
				Delay.msDelay(500);
				direction = (int) InfraroodSensor.getDirection();
				
			} while (direction > 2 || direction < -2 || direction == 0);
		} else {

			while ((direction > 1 || direction < -1)) {
				Logging.log("in de eerste else direction while loop: direction=" + direction);
				Logging.log("moet er uit knallen als de direction 1 of 0 is");

				Wielaandrijving.draaiOmAs(5, false);
				Delay.msDelay(1000);

				direction = (int) InfraroodSensor.getDirection();
			}

		}
		Logging.log("uit de eerste while loop want direction: " + direction);

		// Gaat rijden naar de beacon totdat de distance kleiner is dan 10
		
		distance = (int) InfraroodSensor.getDistance();

		while (distance > 10) {
			Logging.log("in de tweede while loop: distance=" + distance);
			Logging.log("moet er uit als distance kleiner is dan 10");
			
			Wielaandrijving.setSnelheid(80, 0);

			//Wielaandrijving.rijAfstand((Wielaandrijving.getMaxLineaireSnelheid() / 4), 150, true);
			//Delay.msDelay(1000);
			distance = (int) InfraroodSensor.getDistance();
		}
		Logging.log("uit de tweede while loop: want distance=" + distance);

		// gaat weer de hoek bepalen en nu nauwkeuriger.
		direction = (int) InfraroodSensor.getDirection();
		Logging.log("uit de tweede direction while loop");
		Logging.log("de direction is nu: " + direction);

		while (direction > 0 || direction < 0) {
			Logging.log("in de derde while loop: direction=" + direction);
			Logging.log("moet er uit als direction 0 is");

			if (direction > 0) {
				Wielaandrijving.draaiOmAs(2, true);
			} else if (direction < 0) {
				Wielaandrijving.draaiOmAs(-2, true);
			}
			direction = (int) InfraroodSensor.getDirection();
		}

		Logging.log("uit de derde while loop want direction: " + direction);

		// gaat nu weer naar de beacon rijden totdat de afstand 0 is.
		while (!TouchSensor.isTouched()) {
			Logging.log("In de vierde while loop");
			Logging.log("moet er uit als touchsensor is touched");
			//Wielaandrijving.rijAfstand((Wielaandrijving.getMaxLineaireSnelheid() / 4), 200, true);
			Wielaandrijving.setSnelheid(100, 0);
			
			//distance = (int) InfraroodSensor.getDistance();
		}
		Wielaandrijving.stop();
		
		} catch(Exception e) {
			System.out.println("error bij rijnaarbeacon");
		}
		
	}
	
	
	public void grijpBeacon() {
		GrijpMotor.grijpen();
		Sound.beepSequenceUp();
	}
	
	public void rijdMetBeacon() {
		//Wielaandrijving.rijAfstand(Wielaandrijving.getMaxLineaireSnelheid()/5 , 200, false);
		
		Wielaandrijving.rijAfstand(100, 200, false);
		//Wielaandrijving.setSnelheid(50, 100);
		Delay.msDelay(2000);
		Wielaandrijving.stop();
		Wielaandrijving.setSnelheid(100, 200);
		Delay.msDelay(2000);
		Wielaandrijving.stop();
	}
	
	public void laatBeaconLos() {
		GrijpMotor.losLaten();
		Delay.msDelay(2000);
	}
	
	public void rijdWeg() {
		Motor.rechtAchteruit(100);
		Delay.msDelay(1000);
		Motor.rem();
	}
	
	

}
