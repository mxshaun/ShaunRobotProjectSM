package nl.hva.miw.robot.cohort13;


import ev3.robotproject.library.Logging;
import ev3.robotproject.library.RgbSensor;
import lejos.hardware.Button;
import lejos.hardware.port.SensorPort;
import lejos.robotics.Color;
import lejos.utility.Delay;

public class ScanBlueLine extends Thread {
	
	int teller = 0;
	
	public void run() {
				
        while (Button.ESCAPE.isUp()) {
			if (findBlueLine()) {
				Logging.log("\n\nBLAUWE LIJN\n\n");
				Delay.msDelay(500);
				teller++;
			}
		}
		// free up resources.
		RgbSensor.close();
	}
	
	public boolean findBlueLine() {
		int rgbRed = RgbSensor.getColor().getRed();
		int rgbGreen = RgbSensor.getColor().getGreen();
		int rgbBlue = RgbSensor.getColor().getBlue();
		return (rgbRed >= 9 && rgbRed <= 11 && rgbGreen >= 16 && rgbGreen <= 18 && rgbBlue >= 23 && rgbBlue <= 25);
	}
	
	

}