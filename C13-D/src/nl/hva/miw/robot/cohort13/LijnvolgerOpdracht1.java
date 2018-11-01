package nl.hva.miw.robot.cohort13;


public class LijnvolgerOpdracht1 {
	//Dit is de bestuurder die de zwarte lijn volgt.
	private LineFollower2 piloot = new LineFollower2(this); 
	//Dit is het meten van de start/finish lijn en ronde tijd.
	private ScanLine2 lijnScanner = new ScanLine2(this); 
	/*
	 * Dit is de boolean waarmee het wel (true) of niet (fasle) uitvoeren 
	 * van de opdracht wordt geregeld.
	 */
	private boolean startOpdracht; 
	
	//Constructor voor de LijnVolger opdracht
	public LijnvolgerOpdracht1() {		
	}
	
	//De methode voor het starten van de opdracht.
	public void lijnVolgerOpdracht() {

		
		Thread t1 = new Thread(piloot);
		Thread t2 = new Thread(lijnScanner);
		
		startOpdracht = true; //opdracht boolean op true zetten
		
		t1.start();
		//wachten met starten van de Lijn Scanner thread
		while(!piloot.getStart()) {
		}
		t2.start();
		while(!piloot.afmeldenThread() && !lijnScanner.afmeldenThread()) {
			/*
			 * Wachten totdat de threads afgemeld/gejoint kunnen worden.
			 * De piloot-thread stopt zijn while loop nav een seintje
			 * uit de lijnScanner-thread. Omdat de lijnScanner-thread 
			 * ook eerst de stopwatch tijd toont, is de piloot-thread
			 * altijd eerder klaar.
			 */
		}
		
		//Afsluiten van piloot-thread
		try {
			t1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		while (!lijnScanner.afmeldenThread()) {
			//Wachten totdat tijd is getoond en afmelden thread op true is gezet.
		}
		
		try {
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	/*
	 * De getter en setter voor de Boolean voor het uitvoeren
	 * van de opdracht.
	 */
	public synchronized void setStartOpdracht(boolean start) {
		this.startOpdracht = start;
	}
	
	public synchronized boolean getStartOpdracht() {
		return startOpdracht;
	}
}
