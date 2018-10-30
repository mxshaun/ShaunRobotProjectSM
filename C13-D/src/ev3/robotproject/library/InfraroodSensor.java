package ev3.robotproject.library;

import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.robotics.SampleProvider;

public class InfraroodSensor {

	final static EV3IRSensor ir = new EV3IRSensor(SensorPort.S4);
	static SampleProvider seek = ir.getSeekMode(); 
	static SampleProvider scan = ir.getDistanceMode();

	public InfraroodSensor(Port port) {
		// dit is een utility class en gebruikt dus geen constructor.
	}

	public static float getDirection() {
		// methode die de richting opvraagt van de beacon
		float[] sample = new float[seek.sampleSize()];

		seek.fetchSample(sample, 0);
		return sample[0];
	}

	public static float getDistance() {
		// methode die de afstand opvraagt van de beacon
		float[] sample = new float[seek.sampleSize()];

		seek.fetchSample(sample, 0);
		return sample[1];
	}

	public static void sluit() {
		// sluiten van de BeaconSensor (free up resources).
		ir.close();
	}
	
	

	public static void zoekBeacon() {
		// Methode die de robot laat draaien totdat de beacon voor hem ligt.
		float direction = 100;
		
		
		while (direction > 10 || direction < -10) {
			
			direction = getDirection();
			float distance = getDistance();
			
			Lcd.clear(1);
			Lcd.print(2, "BeaconZoeker actief");
			Lcd.clear(3);
			Lcd.clear(4);
			Lcd.print(5, "Versie 1.0.5");
			Lcd.print(6, "Dir: %f", direction);
			Lcd.print(7, "Dis: %f", distance);
			Motor.draaiOmAs(100, -100);
		} 

		Motor.rem();
		Lcd.clear();
	}
	
	public static float getAfstand() {
		// methode die de afstand opvraagt van de beacon
		float[] sample = new float[scan.sampleSize()];

		scan.fetchSample(sample, 0);
		return sample[0];
	}

}
