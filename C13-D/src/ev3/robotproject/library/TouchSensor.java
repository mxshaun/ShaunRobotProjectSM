package ev3.robotproject.library;

import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.robotics.SampleProvider;


public final class TouchSensor {
	
	final static EV3TouchSensor DRUK_SENSOR = new EV3TouchSensor(SensorPort.S1);
    final static SampleProvider DRUK_SENSOR_SP = DRUK_SENSOR.getTouchMode();

    /**
     * Creates TouchSensor object.
     * @param port SensorPort of Touch Sensor device.
     */
    public TouchSensor(Port port){
    	EV3TouchSensor sensor = new EV3TouchSensor(port);
    	SampleProvider sp = sensor.getTouchMode();
    }

    /**
     * Check state of Touch Sensor.
     * @return True if touched.
     * Let op: deze method return altijd true, als hij eenmaal een keer ingedrukt is.
     * Voor een nieuwe drukmeting moet de sensor eerst geclosed worden, voor de methode nogmaals
     * te gebruiken!
     */
    public static boolean isTouched()
    {
       float[] sample = new float[DRUK_SENSOR_SP.sampleSize()];

       DRUK_SENSOR_SP.fetchSample(sample, 0);

       if (sample[0] == 0)
           return false;
       else
           return true;
    }

    /**
     * Release internal EV3TouchSensor.
     */
    public static void close()
    {
        DRUK_SENSOR.close();
    }
	
}


