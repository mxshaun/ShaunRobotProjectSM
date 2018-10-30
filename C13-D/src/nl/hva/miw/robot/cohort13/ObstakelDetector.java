package nl.hva.miw.robot.cohort13;

import ev3.robotproject.library.InfraroodSensor;

public class ObstakelDetector implements Runnable{
	private PathFinderOpdracht3 opdracht3;
	
	public ObstakelDetector(PathFinderOpdracht3 pathFinderOpdracht3) {
		this.opdracht3 = pathFinderOpdracht3;
	}
	
	public void run() {
		while(opdracht3.getStart()) {
			opdracht3.setAfstandObstakel((int)InfraroodSensor.getAfstand());
		}
	}

	
}
