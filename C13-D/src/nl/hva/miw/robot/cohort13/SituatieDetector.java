package nl.hva.miw.robot.cohort13;

import ev3.robotproject.library.ColorIdSensor;

public class SituatieDetector implements Runnable{
	private PathFinderOpdracht3 opdracht3;
	private int colorInt;
	private String colorName;
	
	public SituatieDetector(PathFinderOpdracht3 opdracht3) {
		this.opdracht3 = opdracht3;
	}
	
	public boolean checkKruising() {
		try{
			colorInt = ColorIdSensor.getColor();
			colorName = ColorIdSensor.colorName(colorInt);
		} catch (Exception e) {
				
		}
		return (colorName == "Green");
	}
	
	public boolean checkStart() {
		try {
		colorInt = ColorIdSensor.getColor();
		colorName = ColorIdSensor.colorName(colorInt);
		} catch (Exception e) {
			
		}
		return (colorName == "White");
	}
	
	public boolean checkFinish() {
		try {
		
		} catch (Exception e) {
			
		}
		if(colorName == "Black") {
			opdracht3.setStart(false);
			
		}
		return (colorName == "Black");
		
	}
	
	@Override
	public void run() {
		ColorIdSensor.setColorIdMode();
		while(opdracht3.getStart()) {
			colorInt = ColorIdSensor.getColor();
			colorName = ColorIdSensor.colorName(colorInt);
			if((!opdracht3.isStartDoolhof()) && (colorName == "White")) {
				opdracht3.setStartDoolhof(true);
			}
			
			if(!opdracht3.isFinishDoolhof() && (colorName == "Black")) {
				opdracht3.setFinishDoolhof(true);
				opdracht3.setStart(false);
			}
			
			if(!(colorName == "Blue")){
				opdracht3.setKruising(false);
			}
			
			if(colorName == "Blue") {
			opdracht3.setKruising(true);
			}
		}
	}

}
