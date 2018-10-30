package nl.hva.miw.robot.cohort13;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.port.*;
import lejos.robotics.Color;
import lejos.utility.Delay;
import lejos.utility.Stopwatch;
import ev3.robotproject.library.*;
import ev3.robotproject.library.Motor;

public class LineFollower {
	
//	static TouchSensor touch = new TouchSensor(SensorPort.S1);
//	 RedSensor color = new RedSensor(SensorPort.S3);	 
	
	public static void main(String[] args) throws Exception {
		Logging.setup(LineFollower.class.getPackage(), false);
		Logging.log("Start");
		float colorValueWhite;
		float colorValueBlack;
		float colorBorder;
		int tellerAantalKeerBlauweLijn = 0;
		
		System.out.println("Line Follower\n");
		
		//STARTEN VAN HET CALIBRATIE PROCES.
		
		//Biebs (fancy geluidkjes, etc)
		Button.LEDPattern(4); // flash green led and
		Sound.beepSequenceUp(); // make sound when ready.
		
		startCalibratie();
		colorValueWhite = calibreerWit();
		colorValueBlack = calibreerZwart();
		colorBorder = bepaalColorBorder(colorValueWhite, colorValueBlack);
		
		Button.LEDPattern(4); // flash green led and
		Sound.beepSequenceUp(); // make sound when ready.

		System.out.println("Press any key to start the race");
		Button.waitForAnyPress();
		
		//Starten blauwe lijn scanner
		ScanBlueLine blauweLijn = new ScanBlueLine();
		blauweLijn.start();
		
		//Starten met rijden
		Logging.log("begint met rijden");
		Motor.rechtVooruit(180);
		Delay.msDelay(250);
//		Logging.log("Teller bedraagt direct na de start: %d", tellerAantalKeerBlauweLijn);
		
		//Starten controle-loop
		Logging.log("begin loop");
//		Stopwatch sw = new Stopwatch();
		while (Button.ESCAPE.isUp() && tellerAantalKeerBlauweLijn<2) {
//			Logging.log("Teller bedraagt direct voor 'followLine()': %d", tellerAantalKeerBlauweLijn);
			followLine(colorValueWhite, colorValueBlack);
//			if(blauweLijn.findBlueLine()) {
//				tellerAantalKeerBlauweLijn++;
//			}
//			if (tellerAantalKeerBlauweLijn ==1) {
//				sw.reset();
//			}
//			if (tellerAantalKeerBlauweLijn == 2) {
//				int rondetijd = sw.elapsed();
//				Logging.log("Teller staat op 2 met %d milliseconden", rondetijd);
//			}
		}

		// stop motors with brakes on.
		Motor.rem();

		// free up resources.
		Motor.sluit();
//		touch.close();
		RedSensor.close();
		RgbSensor.close();
		Sound.beepSequence(); // we are done.
	}
	
	public static void followLine(float colorValueWhite, float colorValueBlack) {
		//Scanner in Red-mode
		RedSensor.setRedMode();
		float colorValue = RedSensor.getRed();
		
		//Bepalen "vaste" waarden en variabelen
		final float CORRECTION_COLOR_MARGE = 0.08f;
		float min = colorValueBlack + CORRECTION_COLOR_MARGE;
		float max = colorValueWhite - CORRECTION_COLOR_MARGE*((min+CORRECTION_COLOR_MARGE)/min);
		int maxSpeed = 720;
		double speedCorrection = 0.7;
		
		
		
		//Bepalen vermogen links
//		int vermogenLinks = (int)(colorValue - min) * 100;
		int vermogenLinks =(int)((max-colorValue) / (max-min) * maxSpeed * speedCorrection);
		
		//Bepalen vermogen rechts
//		int vermogenRechts = (int)(max - colorValue) * 100;
		int vermogenRechts = (int)((colorValue-min) / (max-min) * maxSpeed * speedCorrection);
		
		//Aansturen motoren
		if (colorValue<min) {
			if (vermogenRechts > -75) {
				Motor.draaiOmAs(vermogenLinks, (vermogenRechts * 1));
			} else {
				Motor.draaiOmAs(vermogenLinks, (vermogenRechts * 2));
			}
		} else if (colorValue>max) {
			if (vermogenLinks > -75) {
				Motor.draaiOmAs((vermogenLinks * 1), vermogenRechts);
			} else {
				Motor.draaiOmAs((vermogenLinks * 2), vermogenRechts);
			}
		} else {
			Motor.bochtVooruit(vermogenLinks, vermogenRechts);
		}
		Logging.log("vermogenLinks: %d / Speed: %.0f en vermogenRechts: %d / Speed: %.0f obv colorValue: %f", vermogenLinks, Motor.getSpeedLinks(), vermogenRechts, Motor.getSpeedRechts(), colorValue);
	}
	
	public static void startCalibratie() {
		System.out.println("Press any key to start the calibration");
		Button.waitForAnyPress();
		Logging.log("Button is pressed");
		RedSensor.setFloodLight(true);
	}
	
	
	public static float calibreerWit() {
		System.out.println("Scan het witte vlak,\n\npress button");
		Button.waitForAnyPress();
		float colorValueWhite = RedSensor.getRed();
		Logging.log("colorValueWhite: %f", colorValueWhite);
		return colorValueWhite;
	}
	
	public static float calibreerZwart() {
		System.out.println("Scan de zwarte lijn,\n\nzwart vlak, press button");
		Button.waitForAnyPress();
		float colorValueBlack = RedSensor.getRed();
		Logging.log("colorValueBlack: %f", colorValueBlack);
		return colorValueBlack;
	}
	
	public static float bepaalColorBorder(float colorValueWhite, float colorValueBlack) {
		float colorBorder = (colorValueBlack + colorValueWhite) / 2;
		Logging.log("colorborder: %f", colorBorder);
		return colorBorder;
	}
	

	
	

}
