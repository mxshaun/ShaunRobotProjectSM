package nl.hva.miw.robot.cohort13;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.*;
import lejos.hardware.port.*;
import lejos.robotics.Color;
import ev3.robotproject.library.ColorSensor;
import ev3.robotproject.library.TouchSensor;

public class LineFollower 
{ 
    static UnregulatedMotor motorA = new UnregulatedMotor(MotorPort.A);
    static UnregulatedMotor motorB = new UnregulatedMotor(MotorPort.B);
    static TouchSensor        touch = new TouchSensor(SensorPort.S1);
    static ColorSensor        color = new ColorSensor(SensorPort.S3);
    
    public static void main(String[] args)
    {
        float    colorValue;
        
        System.out.println("Line Follower\n");
        
        color.setRedMode();
        color.setFloodLight(Color.RED);
        color.setFloodLight(true);

        Button.LEDPattern(4);    // flash green led and 
        Sound.beepSequenceUp();  // make sound when ready.

        System.out.println("Press any key to start");
        
        Button.waitForAnyPress();
        
        motorA.setPower(40);
        motorB.setPower(40);
       
        // drive waiting for touch sensor or escape key to stop driving.

        while (!touch.isTouched() && Button.ESCAPE.isUp()) 
        {
            colorValue = color.getRed();
            
            LCD.clear(7);
            

            if (colorValue > .100)
            {
                motorA.setPower(40);
                motorB.setPower(20);
            }
            else
            {
                motorA.setPower(20);    
                motorB.setPower(40);
            }
        }
       
        // stop motors with brakes on.
        motorA.stop();
        motorB.stop();

        // free up resources.
        motorA.close();
        motorB.close();
        touch.close();
        color.close();
       
        Sound.beepSequence(); // we are done.
    }
}
   