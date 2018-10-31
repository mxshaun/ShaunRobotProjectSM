package nl.hva.miw.robot.cohort13;

import ev3.robotproject.library.GrijpMotor;
import ev3.robotproject.library.Lcd;
import ev3.robotproject.library.Logging;
import ev3.robotproject.library.TouchSensor;
import ev3.robotproject.library.Wielaandrijving;

public class PilootBeaconGrijper extends Piloot {
	private final String NAAM = "Bestuurder";
	private BeaconFollowerOpdracht2 opdracht2;
	final double GRADEN_DIRECTION = 180 / 50;

	public PilootBeaconGrijper(BeaconFollowerOpdracht2 beaconFollowerOpdracht2) {
		super();
		this.opdracht2 = beaconFollowerOpdracht2;
	}

	public void rijd(int direction, int distance) {
		
		zoekBeacon(direction, distance);
		
		

		while (distance > 2 && !TouchSensor.isTouched()) {

			double snelheid = Wielaandrijving.getMaxLineaireSnelheid() / 2;
			double draaisnelheid = Wielaandrijving.getMaxDraaisnelheid() /4;

			while (direction == 0 && distance < 100) {
				Wielaandrijving.vooruit(snelheid);
			} 
			while (direction != 0 && distance < 100) {
				Wielaandrijving.setSnelheid(snelheid, (draaisnelheid * direction * GRADEN_DIRECTION));
			} 
			
			while (direction == 0 && distance == 100)
				
				draaifactor += 0.01;
				Wielaandrijving.setSnelheid((snelheid * draaifactor), draaisnelheid);
				}
			}

	public void zoekBeacon(int direction, int distance) {
		
		while (direction == 0) {
		Wielaandrijving.draaiOmAs(90, true);
		}
		
		
		
	}
		//opdracht2.setStartOpdracht(false);

		

	/*	while ((direction > 1 || direction < -1) && !TouchSensor.isTouched()) {
			// aantal graden dat de brobot moet draaien is gelijk aan de distance x 3.6
			// want: 180 / 50 = 3.6

			Wielaandrijving.draaiOmAs(direction * GRADEN_DIRECTION, false);
		}

		while (distance > 1 && !TouchSensor.isTouched()) {
			double snelheid = Wielaandrijving.getMaxLineaireSnelheid() / 4;

			Wielaandrijving.vooruit(snelheid);
		}

		opdracht2.setStartOpdracht(false);

	}*/

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (opdracht2.getStartOpdracht()) {
			while((opdracht2.getDirection() == 0) && (opdracht2.getDistance() == 100)) {
				Wielaandrijving.setSnelheid((Wielaandrijving.getMaxLineaireSnelheid() / 2), 45);
			
			}
			
			if((opdracht2.getDirection() == 0) && (opdracht2.getDistance() <= 2)) {
				Wielaandrijving.stop();
				//GrijpMotor.grijpen();
				//opdracht2.setStartOpdracht(false);
			}
			
			double draaiSnelheid = (opdracht2.getDirection() / 25) * 90;
			
			Wielaandrijving.setSnelheid((Wielaandrijving.getMaxLineaireSnelheid() / 2), draaiSnelheid);

			// System.out.println("Hoek en afstand bepaald.");
		}

	}


}
