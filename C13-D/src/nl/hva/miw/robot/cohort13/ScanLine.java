package nl.hva.miw.robot.cohort13;


import ev3.robotproject.library.ColorIdSensor;
import ev3.robotproject.library.Logging;
import ev3.robotproject.library.RgbSensor;
import lejos.hardware.Button;
import lejos.hardware.port.SensorPort;
import lejos.robotics.Color;
import lejos.utility.Delay;

public class ScanLine extends Thread {
	int colorInt;
//	int teller = 0;
	
	public void run() {
		ColorIdSensor.setColorIdMode();
				
        while (Button.ESCAPE.isUp()) {
        	boolean foundLine = findLine();
//        	Logging.log("Teller: %d", teller);
        	
			if (foundLine) {
				Logging.log("\n\nLIJN GEVONDEN\n\n");
				Delay.msDelay(500);
//				teller++;
			}
		}
	}
	
	public synchronized boolean findLine() {
		colorInt = ColorIdSensor.getColor();
		String colorName = ColorIdSensor.colorName(colorInt);
		try {
			Logging.log("Kleurnaam: %s", colorName);
		} catch (Exception e) {
			System.out.println("Loggen in finLine() lukt niet");
		}
		return (colorName == "Red" || colorName == "Orange");
	}
}