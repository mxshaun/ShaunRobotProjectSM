package nl.hva.miw.robot.cohort13;

import ev3.robotproject.library.ColorIdSensor;

public class SituatieDetector implements Runnable{
	private PathFinderOpdracht3 opdracht3;
	private int colorInt;
	private String colorName;
	private final String KLEUR_START = "White";
	private final String KLEUR_KRUISING = "Blue";
	private final String KLEUR_FINISH = "Black";
	
	public SituatieDetector(PathFinderOpdracht3 opdracht3) {
		this.opdracht3 = opdracht3;
	}
		
	@Override
	public void run() {
		ColorIdSensor.setColorIdMode();
		while(opdracht3.getStart()) {
			
			getColor();
			checkStartDoolhof();
			checkFinishDoolhof();
			checkKruising();
		}
	}

	/**
	 * 
	 */
	public void checkKruising() {
		if(!(colorName == KLEUR_KRUISING)){
			opdracht3.setKruising(false);
		} else {
		opdracht3.setKruising(true);
		}
	}

	/**
	 * 
	 */
	public void checkFinishDoolhof() {
		if(!opdracht3.isFinishDoolhof() && (colorName == KLEUR_FINISH)) {
			opdracht3.setFinishDoolhof(true);
			opdracht3.setStart(false);
		}
	}

	/**
	 * 
	 */
	public void checkStartDoolhof() {
		if((!opdracht3.isStartDoolhof()) && (colorName == KLEUR_START)) {
			opdracht3.setStartDoolhof(true);
		}
	}

	/**
	 * 
	 */
	public void getColor() {
		this.colorInt = ColorIdSensor.getColor();
		this.colorName = ColorIdSensor.colorName(colorInt);
	}

	public int getColorInt() {
		return colorInt;
	}

	public void setColorInt(int colorInt) {
		this.colorInt = colorInt;
	}

	public String getColorName() {
		return colorName;
	}

	public void setColorName(String colorName) {
		this.colorName = colorName;
	}

}
