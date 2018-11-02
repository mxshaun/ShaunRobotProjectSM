package nl.hva.miw.robot.cohort13;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.utility.Delay;
import ev3.robotproject.library.Lcd;
import ev3.robotproject.library.Motor;
import ev3.robotproject.library.RedSensor;

public class LineFollower2 implements Runnable {	 
	
	private LijnvolgerOpdracht1 opdracht1;
	private boolean start;
	private boolean afmeldenThread;
	
	public LineFollower2(LijnvolgerOpdracht1 opdracht1) {
		super();
		this.opdracht1 = opdracht1;
	}

	@Override
	public void run() {
		
		//Calibratie variabelen
		float colorValueWhite;
		float colorValueBlack;
		float colorBorder;
		
		//Calibratie-proces
		startCalibratie();
		colorValueWhite = calibreerWit();
		colorValueBlack = calibreerZwart();
		colorBorder = bepaalColorBorder(colorValueWhite, colorValueBlack);
		Sound.beepSequenceUp(); // make sound when ready.
		
		//Klaar zetten van de robot voor het rijden
		Lcd.clear();
        Lcd.print(2, "Press any key");
        Lcd.print(3, "to start the round");
		Button.waitForAnyPress();
		
		//Starten met rijden
		start = true; //de boolean voor het starten van de 2e thread wordt op true gezet.
		Motor.rechtVooruit(180);
		Delay.msDelay(250);
		
		//Starten controle-loop en rijden zolang rijopdracht op true staat.
		while (opdracht1.getStartOpdracht()) {
			followLine(colorValueWhite, colorValueBlack);
		}
		
		//Stoppen en sluiten van de motor
		Motor.rem();
				
		//Afmelden van de Thread
		afmeldenThread();
	}
	
	//De methode waarmee de LineFollower de motoren aanstuurt.
	public void followLine(float colorValueWhite, float colorValueBlack) {
		//Scanner in Red-mode scannen
		float colorValue = RedSensor.getRed();
		
		//Bepalen "vaste" waarden en variabelen
		final float CORRECTION_COLOR_MARGE = 0.05f;
		final float CORRECTION_POWER_MARGE = 4.5f;
		float min = colorValueBlack + CORRECTION_COLOR_MARGE;
		float max = colorValueWhite - CORRECTION_COLOR_MARGE*((min+CORRECTION_COLOR_MARGE)/min);
		final int MAX_SPEED = 720;
		final double SPEED_CORRECTION = 1.0;
		
		
		//Bepalen vermogen links
		int vermogenLinks =(int)((max-colorValue) * MAX_SPEED * SPEED_CORRECTION);	
		
		//Bepalen vermogen rechts
		int vermogenRechts = (int)((colorValue-min) * MAX_SPEED * SPEED_CORRECTION);
		
		//Aansturen motoren
		if (colorValue<min) {
			Motor.draaiOmAs(vermogenLinks, (int)(vermogenRechts * CORRECTION_POWER_MARGE));
		} else if (colorValue>max) {
			Motor.draaiOmAs(vermogenLinks, vermogenRechts);
		} else {
			Motor.bochtVooruit(vermogenLinks, vermogenRechts);
		}
	}
	
	//Starten van de calibratie
	public void startCalibratie() {
		Lcd.clear();
        Lcd.print(2, "Press any key");
        Lcd.print(3, "to start the");
        Lcd.print(4, "calibration");
		Button.waitForAnyPress();
		RedSensor.setRedMode();
		RedSensor.setFloodLight(true);
	}

	//scannen van de wit-waarde op dat moment (direct voor rijden van de ronde).
	public float calibreerWit() {
		Lcd.clear();
        Lcd.print(2, "Scan WHITE &");
        Lcd.print(3, "press any key");
		Button.waitForAnyPress();
		float colorValueWhite = RedSensor.getRed();
		return colorValueWhite;
	}
	
	//scannen van de zwart-waarde op dat moment (direct voor rijden van de ronde).
	public float calibreerZwart() {
		Lcd.clear();
        Lcd.print(2, "Scan BLACK &");
        Lcd.print(3, "press any key");
        Button.waitForAnyPress();
		float colorValueBlack = RedSensor.getRed();
		return colorValueBlack;
	}
	
	//bepalen van de grenswaarde (=gemiddelde) van de zwart en wit.
	public float bepaalColorBorder(float colorValueWhite, float colorValueBlack) {
		float colorBorder = (colorValueBlack + colorValueWhite) / 2;
		return colorBorder;
	}
	
	//De getter voor de starten van de start/finish lijn tread.
	public boolean getStart() {
		return this.start;
	}
	
	//De getter voor het afmelden van de Thread
	public boolean afmeldenThread() {
		afmeldenThread = true;
		return afmeldenThread;
	}


}
