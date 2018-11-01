package ev3.robotproject.library;

import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.Color;
import lejos.robotics.ColorDetector;

public final class ColorIdSensor {
	final static EV3ColorSensor COLOR_ID_SENSOR = new EV3ColorSensor(SensorPort.S2);
	final static String KLEUR = "ColorID"; // 'ColorID', omdat we een basis kleur scannen
	
	static float[] sample;
	
	public static int getSampleSize() {
		return COLOR_ID_SENSOR.sampleSize();
	}
	
    /**
   	* Creates ColorSensor object. This is a wrapper class for EV3ColorSensor.
  	* @param port SensorPort of EV3ColorSensor device.
  	*/
	private ColorIdSensor(Port port){
		EV3ColorSensor sensor = new EV3ColorSensor(port);
		sensor.setCurrentMode(KLEUR);
		setFloodLight(false);
	}
	
	/**
	 * Set color sensor to Color ID mode.
	 */
	public static void setColorIdMode(){
		COLOR_ID_SENSOR.setCurrentMode(KLEUR);
		COLOR_ID_SENSOR.setFloodlight(false);
		sample = new float[COLOR_ID_SENSOR.sampleSize()];
	}

	/**
	 * Returns current detected color. Use with Color Id mode.
	 * @return Color id. Color ids are in the Color object.
	 */
	public static int getColor() {
		COLOR_ID_SENSOR.fetchSample(sample, 0);
		return (int) sample[0];
	}
	
	/**
	 * Release resources.
	 */
	public static void close(){
		COLOR_ID_SENSOR.close();
	}

	/**
	 * Set floodlight led on/off with default color.
	 * @param on True to turn floodlight on, false to turn off.
	 */
	public void setFloodLight(boolean on) {
		COLOR_ID_SENSOR.setFloodlight(on);
	}
	
	/**
	 * Set floodlight default led color.
	 * @param color Color id value from Color object.
	 */
	public static void setFloodLight(int color){
		COLOR_ID_SENSOR.setFloodlight(color);
	}
	
	/**
	* Map color integer to name.
	* @param color Color id value.
	* @return String with color name.
	*/
	public static String colorName(int color)
	{
		switch (color)
		{
			case Color.NONE:
				return "None";
				
			case Color.BLACK:
				return "Black";
				
			case Color.BLUE:
				return "Blue";
				
			case Color.BROWN:
				return "Brown";
				
			case Color.CYAN:
				return "Cyan";
				
			case Color.DARK_GRAY:
				return "Dark Gray";
				
			case Color.GRAY:
				return "Gray";
				
			case Color.GREEN:
				return "Green";
				
			case Color.LIGHT_GRAY:
				return "Light Gray";
				
			case Color.MAGENTA:
				return "Magenta";
				
			case Color.ORANGE:
				return "Orange";
				
			case Color.PINK:
				return "Pink";
				
			case Color.RED:
				return "Red";
				
			case Color.WHITE:
				return "White";
				
			case Color.YELLOW:
				return "Yellow";
		}
		
		return "";
	}



	
}