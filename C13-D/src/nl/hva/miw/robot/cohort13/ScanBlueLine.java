package nl.hva.miw.robot.cohort13;


import ev3.robotproject.library.Logging;
import ev3.robotproject.library.RgbSensor;
import lejos.hardware.Button;
import lejos.hardware.port.SensorPort;
import lejos.robotics.Color;
import lejos.utility.Delay;

public class ScanBlueLine extends Thread {
	Color RGB;
	int teller = 0;
	
	public void run() {
		
		RgbSensor.setRgbMode();
				
        while (Button.ESCAPE.isUp()) {
        	boolean found = findBlueLine();
        	
        	Logging.log("%s", found);
        	
			if (found) {
				Logging.log("\n\nBLAUWE LIJN\n\n");
				Delay.msDelay(500);
				teller++;
			}
		}
		// free up resources.
//		RgbSensor.close();
	}
	
	public boolean findBlueLine() {
	
//		Logging.log("RGB sampleSize (voor aanroep): %d", RgbSensor.getSampleSize());
		RGB = RgbSensor.getColor();
//		Logging.log("RGB sampleSize (na aanroep): %d", RgbSensor.getSampleSize());
		
		int rgbRed = RGB.getRed();
		int rgbGreen = RGB.getGreen();
		int rgbBlue = RGB.getBlue();
		Logging.log("Rood: %d - Groen: %d - Blauw: %d", rgbRed, rgbGreen, rgbBlue);
//		return (rgbRed >= 9 && rgbRed <= 15 && rgbGreen >= 12 && rgbGreen <= 20 && rgbBlue >= 20 && rgbBlue <= 28);
		return (rgbRed >= 55 && rgbRed <= 65 && rgbGreen >= 8 && rgbGreen <= 13 && rgbBlue >= 8 && rgbBlue <= 13);
	}
	
	

}