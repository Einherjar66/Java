package unterricht;

public class Multithreading01 extends Thread {

	public static void main(String[] args) 
	{
		Multithreading01 berechnePrimzahlen = new Multithreading01();
		Mt02 druckePrimzahlen = new Mt02();

		Mt03 machIrgendwas = new Mt03();
		Thread t3 = new Thread(machIrgendwas);
		
		berechnePrimzahlen.start();
		druckePrimzahlen.start();
		t3.start();
		
		
		System.out.println("Hauptprogramm = Mainthread läuft weiter");
	}
	
	/**
	 * Überschreibt geerbte run-Methode aus der Klasse Thread
	 */
	@Override
	public void run() {
		System.out.println("Berechne Primzahlen"); 
	}

}

class Mt03 implements Runnable {

	@Override
	public void run() {
		System.out.println("Mt03-Thread");
	}
	
}

class Mt02 extends Thread {
	
	/**
	 * Überschreibt geerbte run-Methode aus der Klasse Thread
	 */
	@Override
	public void run() {
		System.out.println("Mt02-Thread");
	}
}
