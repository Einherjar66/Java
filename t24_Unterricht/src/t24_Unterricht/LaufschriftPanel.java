package aufgaben;

import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * Klasse für das Programmfenster
 *
 * Zeigt 2 Laufschriften mit unterschiedlichen Geschwindigkeiten,
 * die beide zwischen linkem und rechtem Rand hin- und herlaufen
 * 
 * @author André
 */
public class LaufschriftPanel extends JPanel
	implements Runnable
{
	/* ----------------------------------------
	 * Klassenkonstanten
	 * ----------------------------------------*/
	public static final String TEXT1 = "Ich bin ein lahmer Text";
	public static final String TEXT2 = "Ich bin ein flotter Text";
	
	public static final long SLEEP1 = 8; // Geschwindigkeit für erste Laufschrift 
	                                     // (größere Zahl führt zu langsamerer Laufschrift)
	public static final long SLEEP2 = 5; // Geschwindigkeit für zweite Laufschrift
	
	/* ----------------------------------------
	 * Instanzeigenschaften
	 * ----------------------------------------*/
	
	private Thread schrift1; // Thread für erste Laufschrift
	private Thread schrift2; // Thread für zweite Laufschrift
	
	private int x1 = 1, y1 = 20; // Koordinaten der ersten Laufschrift
	private int x2 = 1, y2 = 42; // Koordinaten der zweiten Laufschrift
	
	private int richtung1 = 1; // Richtung der ersten Laufschrift
	                           // 1: von links nach rechts, -1: von rechts nach links
	private int richtung2 = 1; // Richtung der zweiten Laufschrift
	
	private int breite1 = 0; // Breite der ersten Laufschrift
	private int breite2 = 0; // Breite der zweiten Laufschrift
	
	/* ----------------------------------------
	 * Konstruktor
	 * ----------------------------------------*/
	/**
	 * Initiiert und startet die beiden Threads 
	 */
	public LaufschriftPanel() {
		schrift1 = new Thread(this);
		schrift2 = new Thread(this);
		schrift1.start();
		schrift2.start();
		
	}
	
	/* ----------------------------------------
	 * Überschriebene Methoden aus den 
	 * Interfaces / Superklassen
	 * ----------------------------------------*/
	/**
	 * run-Methode wird beim Start eines Threads automatisch
	 * ausgeführt. 
	 * Ermittelt die neue Position für die jeweilige Laufschrift
	 * und initiiert dann das Neuzeichnen des Programmfensters 
	 */
	@Override
	public void run() {
		if(Thread.currentThread() == schrift1) {
			do {
				// Richtung für erste Laufschrift ermitteln  
				if(x1 <= 0) {
					richtung1 = 1;
				}
				else if(x1 >= getWidth() - breite1) {
					richtung1 = -1;
				}
				// Neue x-Position für erste Laufschrift ermitteln
				x1 += richtung1;
				// Fenster neu zeichnen
				repaint();
				try {
					Thread.sleep(SLEEP1);
				} catch (InterruptedException e) {}
			} while(true);
		}
		else {
			do {
				// Richtung für zweite Laufschrift ermitteln  
				if(x2 <= 0) {
					richtung2 = 1;
				}
				else if(x2 >= getWidth() - breite2) {
					richtung2 = -1;
				}
				// Neue x-Position für zweite Laufschrift ermitteln
				x2 += richtung2;
				// Fenster neu zeichnen
				repaint();
				try {
					Thread.sleep(SLEEP2);
				} catch (InterruptedException e) {}
			} while(true);
		}
	}

	/**
	 * Zeichnet die Arbeitsfläche neu
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		FontMetrics fm = g.getFontMetrics();
		breite1 = fm.stringWidth(TEXT1);
		breite2 = fm.stringWidth(TEXT2);
		
		g.drawString(TEXT1, x1, y1);
		g.drawString(TEXT2, x2, y2);
	}
	
}
