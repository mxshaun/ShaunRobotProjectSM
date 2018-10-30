package nl.hva.miw.robot.cohort13;

import ev3.robotproject.library.Wielaandrijving;

public class PilootPathFinder extends Piloot {
	private PathFinderOpdracht3 opdracht3;
	
	public PilootPathFinder(PathFinderOpdracht3 pathFinderOpdracht3) {
		this.opdracht3 = pathFinderOpdracht3;
	}


	public void rijVooruit(int afwijking) {
		
		Wielaandrijving.vooruit();
	}
	
	public void draai() {
		Wielaandrijving.draaiOmAs(90, true);
	}

	public void run() {
		while(opdracht3.getStart())
			if(opdracht3.getAfstandObstakel() < 30) {
				Wielaandrijving.stop();
				try {
					Thread.sleep(1000);;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Wielaandrijving.draaiOmAs(90, true);
				try {
					Thread.sleep(2000);;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				Wielaandrijving.setSnelheid(Wielaandrijving.getMaxLineaireSnelheid(), 0);
			}
	}


	@Override
	public void rij(int afwijking) {
		// TODO Auto-generated method stub
		
	}
}
