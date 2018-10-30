package nl.hva.miw.robot.cohort13;

import ev3.robotproject.library.Lcd;
import ev3.robotproject.library.Wielaandrijving;

public class PilootLineFollower extends Piloot{
	private final String NAAM = "Bestuurder";
	private LijnvolgerOpdracht1 opdracht1;

	public PilootLineFollower(LijnvolgerOpdracht1 lijnvolgerOpdracht1) {
		super();
		this.opdracht1 = lijnvolgerOpdracht1;
	}

	/**
	 * Deze methode zorgt voor het rijden met een maximale snelheid proportioneel
	 * aan de gewenste hoek van draaiïng.
	 * 
	 * @param hoek,
	 *            hoek van draaiïng in graden.
	 * @return
	 */


	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (opdracht1.getStartOpdracht()) {
			rij(opdracht1.getRijRichting());
			System.out.println("rijrichting opgehaald");
			Lcd.clear();
		}
	}

	@Override
	public void rij(int afwijking) {
		/**
		 * @ To-do: bepaal de relatieve max lineaire snelheid, aan de hand van de hoek
		 *   die gemaakt dient te worden.
		 */
		double maxSnelheid = Wielaandrijving.getMaxLineaireSnelheid();
		double snelheid = (afwijking / 360);
		if (snelheid > 1) {
			snelheid = snelheid - 1;
		}
		snelheid = snelheid * (maxSnelheid * 0.5);
		double draaisnelheid = (afwijking - 360);
			Wielaandrijving.setSnelheid(snelheid, draaisnelheid);

	}



}
