package ev3.robotproject.library;

import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.Color;

public final class RedSensor {
	final static EV3ColorSensor RED_SENSOR = new EV3ColorSensor(SensorPort.S3);
	final static String KLEUR = "Red"; // 'Red', omdat we een roodkleurensensor gebruiken
	final static float[] SAMPLE = new float[RED_SENSOR.sampleSize()];

    /**
   	* Creates ColorSensor object. This is a wrapper class for EV3ColorSensor.
  	* @param port SensorPort of EV3ColorSensor device.
  	*/
	private RedSensor(Port port){
		EV3ColorSensor sensor = new EV3ColorSensor(port);
		sensor.setCurrentMode("Red");
		setFloodLight(false);
	}
	
	/**
	 * Set color sensor to RED light level mode.
	 */
	public static void setRedMode(){
		RED_SENSOR.setCurrentMode("Red");
	}

	/**
	 * Return Red light level. Use with Red mode. Sensor led should be red.
	 * @return Light level as range 0 to 1.
	 */
	public static float getRed(){
		RED_SENSOR.setCurrentMode(KLEUR);
		setFloodLight(true);
		setFloodLight(Color.RED); // de meegegeven parameter is een int uit de Color klasse
		RED_SENSOR.fetchSample(SAMPLE, 0);
		return SAMPLE[0];
	}
	
	/**
	 * Release resources.
	 */
	public static void close(){
		RED_SENSOR.close();
	}

	/**
	 * Return current status of floodlight led.
	 * @return True if on, false if off.
	 */
	public static boolean isFloodLightOn(){
		return RED_SENSOR.isFloodlightOn();
	}

	/**
	 * Set floodlight led on/off with default color.
	 * 
	 * @param on True to turn floodlight on, false to turn off.
	 */
	public static void setFloodLight(boolean on){
		RED_SENSOR.setFloodlight(on);
	}
	
	/**
	 * Set floodlight default led color.
	 * @param color Color id value from Color object.
	 */
	public static void setFloodLight(int color){
		RED_SENSOR.setFloodlight(color);
	}
	
}