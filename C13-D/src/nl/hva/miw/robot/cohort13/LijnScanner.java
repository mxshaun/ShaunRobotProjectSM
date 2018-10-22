package nl.hva.miw.robot.cohort13;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.*;
import lejos.hardware.port.*;
import lejos.robotics.Color;
import ev3.robotproject.library.*;
import java.io.IOException;

public class LijnScanner extends Thread {
	private Thread t;
	private String threadName = "LineScanner";
	private float colorValueBlack;

	public LijnScanner() {
		System.out.printf("%s: de lijnscanner is gereed\n", threadName);
	}

	public void run() {
		//try {
			// calibreren colorvalue black
			//System.out.println("Scan zwart vlak, press button");
			//Button.waitForAnyPress();
			Logging.log("%f", colorValueBlack);

			while (1 == 1) {
				System.out.println(RedSensor.getRed());
				Logging.log("Sensor blijft lopen");
			}
		//} catch (InterruptedException e) {
		//	System.out.println("moeimakerij");
		//}
	}

	public void start() {
		System.out.println("Starting " + threadName);
		t = new Thread(this, threadName);
		t.start();
		Logging.log("Lijn scanner gaat runnen");
	}

	public float getColorValueBlack() {
		return this.colorValueBlack;
	}
}
