package nl.hva.miw.robot.cohort13;

import ev3.robotproject.library.Lcd;
import ev3.robotproject.library.RgbSensor;
import lejos.hardware.Button;
import lejos.robotics.Color;
import lejos.utility.Delay;

public class Test {

	public static void main(String[] args) {
		
		while (Button.ESCAPE.isUp()) {
		RgbSensor.setRgbMode();
		Color RGB = RgbSensor.getColor();
//		Logging.log("RGB sampleSize (na aanroep): %d", RgbSensor.getSampleSize());
		
		int rgbRed = RGB.getRed();
		int rgbGreen = RGB.getGreen();
		int rgbBlue = RGB.getBlue();
		
		Lcd.clear(6);
        Lcd.print(6, "r=%d g=%d b=%d", rgbRed, rgbGreen, rgbBlue);
        Delay.msDelay(250);

		}
        
	}

}
