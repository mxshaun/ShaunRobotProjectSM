package main;

import ev3.robotproject.library.Logging;
import nl.hva.miw.robot.cohort13.Bestuurder;
import nl.hva.miw.robot.cohort13.LijnScanner;


public class RobotLauncher1 {
	static Bestuurder piloot = new Bestuurder();
	static LijnScanner lijnScanner = new LijnScanner();

	public static void main(String[] args) throws Exception{
		Logging.setup(RobotLauncher1.class.getPackage(), false);
        Logging.log("Starting test");
        lijnScanner.start();
        piloot.start();
	}

}
