package nl.hva.miw.robot.cohort13;

import lejos.hardware.motor.*;
import lejos.hardware.port.*;

import java.io.IOException;

import ev3.robotproject.library.Lcd;
import ev3.robotproject.library.Logging;
import ev3.robotproject.library.Motor;
import ev3.robotproject.library.RedSensor;
import ev3.robotproject.library.Wielaandrijving;

public abstract class Piloot implements Runnable {
	private final String NAAM;

	public Piloot() {
		this.NAAM = "Default";
	}

	public abstract void rij(int afwijking);


	@Override
	public void run() {
	}

}
