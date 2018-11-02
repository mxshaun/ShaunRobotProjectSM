package nl.hva.miw.robot.cohort13;

import ev3.robotproject.library.Wielaandrijving;

public class PilootPathFinder extends Piloot {
	private PathFinderOpdracht3 opdracht3;
	private double lineaireSnelheid = 200;
	private int draaiTeller;
	private final int DRAAI_TELLER_RESET = 0;
	private final int DRAAI_HOEK = 88;
	private final int MUUR_GRENS = 30;
	private final int RIJ_NAAR_MIDDEN_KRUISING = 330;
	private final int RIJSNELHEID_OP_KRUISING = 225;

	public PilootPathFinder(PathFinderOpdracht3 pathFinderOpdracht3) {
		super();
		this.opdracht3 = pathFinderOpdracht3;

	}

	public void run() {

		while (opdracht3.getStart()) {

			kruisingHandeling(opdracht3.isKruising());
			rechtdoor(opdracht3.getAfstandObstakel());
			vindWeg(opdracht3.getAfstandObstakel());
			Wielaandrijving.stop();
			
		}
		Wielaandrijving.stop();
	}


	public int getDraaiTeller() {
		return draaiTeller;
	}
	
	public void setDraaiTeller(int draaiTeller) {
		this.draaiTeller = draaiTeller;
	}

	public void setDraaiTellerPlus1() {
		this.draaiTeller++;
	}

	public void rechtdoor(int afstandObstakel) {
		if (afstandObstakel > MUUR_GRENS) {
			setDraaiTeller(DRAAI_TELLER_RESET);
			Wielaandrijving.setSnelheid(lineaireSnelheid, 0);

		}
	}
	
	public void vindWeg(int afstandObstakel) {
		if (afstandObstakel < MUUR_GRENS) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (getDraaiTeller() != 1) {
				Wielaandrijving.draaiOmAs(DRAAI_HOEK, true);
				setDraaiTellerPlus1();
				try {
					Thread.sleep(1000);
					;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				Wielaandrijving.draaiOmAs(DRAAI_HOEK, true);
				setDraaiTellerPlus1();
				try {
					Thread.sleep(1000);
					;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Wielaandrijving.draaiOmAs(DRAAI_HOEK, true);
				setDraaiTellerPlus1();
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

	/**
	 * 
	 */
	public void kruisingHandeling(boolean isKruising) {
		Wielaandrijving.rijAfstand(RIJSNELHEID_OP_KRUISING, RIJ_NAAR_MIDDEN_KRUISING, true);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Wielaandrijving.draaiOmAs(DRAAI_HOEK, true);
		try {
			Thread.sleep(1000);
			;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
