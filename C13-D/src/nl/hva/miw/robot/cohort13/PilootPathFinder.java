package nl.hva.miw.robot.cohort13;

import ev3.robotproject.library.Wielaandrijving;

public class PilootPathFinder extends Piloot {
	private PathFinderOpdracht3 opdracht3;
	private double lineaireSnelheid = 200;

	public PilootPathFinder(PathFinderOpdracht3 pathFinderOpdracht3) {
		super();
		this.opdracht3 = pathFinderOpdracht3;

	}

	// public void rijVooruit(int afwijking) {
	//
	// Wielaandrijving.vooruit();
	// }

	// public void draai() {
	// Wielaandrijving.draaiOmAs(90, true);
	// }

	public void run() {
		int draaiTeller = 0;

		while (opdracht3.getStart())
			if (opdracht3.isKruising()) {
				Wielaandrijving.setSnelheid((lineaireSnelheid / 2), 0);
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Wielaandrijving.draaiOmAs(88, true);
				try {
					Thread.sleep(1000);
					;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				if (opdracht3.getAfstandObstakel() > 20) {
					draaiTeller = 0;
					Wielaandrijving.setSnelheid(lineaireSnelheid, 0); //

				} else {
					Wielaandrijving.stop();
					try {
						Thread.sleep(1000);
						;
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (draaiTeller != 1) {
						Wielaandrijving.draaiOmAs(88, true);
						draaiTeller++;
						try {
							Thread.sleep(1000);
							;
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {
						Wielaandrijving.draaiOmAs(88, true);
						try {
							Thread.sleep(1000);
							;
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						Wielaandrijving.draaiOmAs(88, true);
						try {
							Thread.sleep(1000);
							;
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
	}

}
