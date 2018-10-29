package ev3.robotproject.library;

import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.Color;

public final class RgbSensor {
	final static EV3ColorSensor RGB_SENSOR = new EV3ColorSensor(SensorPort.S2);
	final static String KLEUR = "RGB"; // 'RGB', omdat we een RGB modus gebruiken
	final static float[] SAMPLE = new float[RGB_SENSOR.sampleSize()];

    /**
   	* Creates ColorSensor object. This is a wrapper class for EV3ColorSensor.
  	* @param port SensorPort of EV3ColorSensor device.
  	*/
	private RgbSensor(Port port){
		EV3ColorSensor sensor = new EV3ColorSensor(port);
		sensor.setCurrentMode("RGB");
		setFloodLight(false);
	}
	
	/**
	 * Set color sensor to RGB light level mode.
	 */
	public static void setRgbMode(){
		RGB_SENSOR.setCurrentMode("RGB");
	}

	/**
	 * Returns Color object with current detected color. Use with
	 * RGB mode and white light on target. Note that these values are
	 * the relative intensity of the reflected light of the primary colors.
	 * This is not the actual RGB value that would reproduce the color of
	 * the target surface.
	 * @return Color object with RGB intensity values of detected color.
	 */
	public static Color getColor()
	{
		RGB_SENSOR.fetchSample(SAMPLE, 0);
		
		return new Color((int)(SAMPLE[0] * 255), (int)(SAMPLE[1] * 255), (int)(SAMPLE[2] * 255));
	}

	
	/**
	 * Release resources.
	 */
	public static void close(){
		RGB_SENSOR.close();
	}

	/**
	 * Return current status of floodlight led.
	 * @return True if on, false if off.
	 */
	public static boolean isFloodLightOn(){
		return RGB_SENSOR.isFloodlightOn();
	}

	/**
	 * Set floodlight led on/off with default color.
	 * 
	 * @param on True to turn floodlight on, false to turn off.
	 */
	public static void setFloodLight(boolean on){
		RGB_SENSOR.setFloodlight(on);
	}
	
	/**
	 * Set floodlight default led color.
	 * @param color Color id value from Color object.
	 */
	public static void setFloodLight(int color){
		RGB_SENSOR.setFloodlight(color);
	}
	
}