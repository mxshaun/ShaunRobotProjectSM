package nl.hva.miw.robot.cohort13;

import lejos.hardware.motor.*;
import lejos.hardware.port.*;

import java.io.IOException;

import ev3.robotproject.library.Logging;
import ev3.robotproject.library.Motor;
import ev3.robotproject.library.RedSensor;
import main.RobotLauncher1;

public class Bestuurder extends Thread {
	private Thread b;
	private String threadName = "Bestuurder";

	public Bestuurder() {
		System.out.printf("%s: Wiel besturing gereed\n", threadName);
	}

	public void run() {
			while (1 == 1) {
				Motor.rechtVooruit((int) Motor.getMaxSpeed());
				Logging.log("begonnen met rijden");
				//if ((int) RedSensor.getRed() == (int) lijnScanner.getColorValueBlack()) {
				//	Motor.uitRollen();
				//}
			}		
	}

	public void start() {
		System.out.println("Starting " + threadName);
		b = new Thread(this, threadName);
		b.start();
		Logging.log("piloot gaat runnen");
	}

}
