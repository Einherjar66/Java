package aufgaben;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Klasse für das Programmfenster
 *
 * Zeigt 5 Quadrate mit zufälliger Position. Klick auf ein
 * Quadrat entfernt das angeklickte Quadrat aus der Ansicht
 * 
 * @author André
 */
public class QuadratePanel extends JPanel
	implements MouseListener, Runnable
{
	/* ----------------------------------------
	 * Klassenkonstanten
	 * ----------------------------------------*/
	public static final int ANZAHL      = 5;     // Anzahl der Quadrate
	public static final int GROESSE     = 70;    // Seitenlänge der Quadrate
	public static final long WARTEZEIT  = 1000L; // Dauer bis zum Neuzeichnen der Quadrate in ms
	public static final String SIEGTEXT = "Du hast gewonnen!"; 
	
	
	/* ----------------------------------------
	 * Instanzeigenschaften
	 * ----------------------------------------*/
	private int x, y;  // Koordinaten des Mauszeigers
	private Thread t1;
	
	private Quadrat[] quadrate = new Quadrat[ANZAHL]; // zu zeichnende Quadrate
	private Color[] farben     = { Color.RED,         // Farben für die Quadrate
			                       Color.GREEN, 
			                       Color.BLUE, 
			                       Color.CYAN, 
			                       Color.MAGENTA, 
			                       Color.YELLOW, 
			                       Color.ORANGE, 
			                       Color.PINK };
	
	/* ----------------------------------------
	 * Konstruktor
	 * ----------------------------------------*/
	/**
	 * Initialisiert die Klasse und registriert sie für
	 * die Überwachung von Mausereignissen
	 */
	public QuadratePanel() {
		addMouseListener(this);
		t1 = new Thread(this);
	}
	
	/* ----------------------------------------
	 * Sonstige Methoden
	 * ----------------------------------------*/
	/**
	 * Erzeugt die (als Klassenkonstante) angegebene
	 * Anzahl an Quadraten
	 */
	private void buildQuadrate() {
		for(int i = 0; i < quadrate.length; i++) {
			quadrate[i] = new Quadrat(GROESSE, 
					                  this.getWidth(), 
					                  this.getHeight(), 
					                  farben[i % farben.length]); // passende Farbe aus dem 
		                                                          // Farben-Array zuordnen
			
		}
	}
	
	/**
	 * Entfernt das angegebene Quadrat aus dem Quadrate-Array
	 *  
	 * @param quadrat  zu entfernendes Quadrat
	 */
	private void entferneQuadrat(Quadrat quadrat) {
		Quadrat[] temp = new Quadrat[quadrate.length - 1];
		int neu = 0;
		for(int alt = 0; alt < quadrate.length; alt++) {
			if(quadrate[alt] != quadrat) {
				temp[neu] = quadrate[alt];
				neu++;
			}
		}
		quadrate = temp;
	}
	
	/* ----------------------------------------
	 * Überschriebene Methoden aus den 
	 * Interfaces / Superklassen
	 * ----------------------------------------*/
	@Override
	public void run() {
		do {
			buildQuadrate();
			repaint();
			try {
				Thread.sleep(WARTEZEIT);
			} catch (InterruptedException e) { 
				break;
			}
		} while(true);
		
	}

	/**
	 * Zeichnet die Arbeitsfläche neu
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// Thread starten, wenn nicht bereits geschehen
		if(!t1.isAlive()) {
			t1.start();
		}
		
		// Prüfen, ob noch Quadrate übrig sind
		if(quadrate.length > 0) {
			// noch vorhandene Quadrate zeichnen
			for(Quadrat quadrat: quadrate) {
				if(quadrat != null) {
					g.setColor(quadrat.getFarbe());
					g.fillRect(quadrat.getX(), quadrat.getY(), GROESSE, GROESSE);
				}
			}
		}
		else {
			// Erfolgsmeldung ausgeben, wenn alle Quadrate weggeklickt wurden
			Font schrift = new Font("Arial", Font.BOLD, 28);
			g.setFont(schrift);
			
			FontMetrics fm = g.getFontMetrics();
			int textbreite = fm.stringWidth(SIEGTEXT);
			int texthoehe  = fm.getHeight();
			g.drawString(SIEGTEXT, getWidth()/2 - textbreite/2, getHeight()/2 + texthoehe/2);
			
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	/**
	 * Beim Drücken der Maustaste:
	 * prüft, ob ein Quadrat angeklickt wurde und entfernt
	 * dies aus der Liste der Quadrate. Es wird nur das oberste
	 * angeklickte Quadrat entfernt.
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		x = e.getX();
		y = e.getY();
		for(int i = quadrate.length - 1; i >= 0; i--) {
			if(   x >= quadrate[i].getX() 
			   && x <  quadrate[i].getX() + GROESSE
		       && y >= quadrate[i].getY() 
		       && y <  quadrate[i].getY() + GROESSE) {
				entferneQuadrat(quadrate[i]);
				break;
			}
		}
	}

	/**
	 * Beim Loslassen der Maustaste:
	 * Neuzeichnen der Arbeitsfläche
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

}

/**
 * Repräsentiert ein Quadrat mit den Koordinaten der linken
 * oberen Ecke sowie der Farbe des Quadrats
 * 
 * @author André
 */
class Quadrat {
	/* ----------------------------------------
	 * Instanzeigenschaften
	 * ----------------------------------------*/
	private int x;
	private int y;
	private Color farbe; 
	
	/* ----------------------------------------
	 * Konstruktor
	 * ----------------------------------------*/
	/**
	 * Initialisiert das Quadrat mit einer zufälligen Anzeigeposition,
	 * so dass das Quadrat vollständig innerhalb des Fensters liegt
	 * @param groesse     Seitenlänge des Quadrats
	 * @param panelWidth  Breite der Anzeigefläche
	 * @param panelHeight Höhe der Anzeigefläche
	 * @param farbe       Farbe des Quadrats
	 */
	public Quadrat(int groesse, int panelWidth, int panelHeight, Color farbe) {
		
		// Zufällige Position für das Quadrat festlegen
		x = (int) (Math.random() * (panelWidth - groesse));
		y = (int) (Math.random() * (panelHeight - groesse));
		
		// Farbe festlegen
		this.farbe = farbe;
	}
	
	/* ----------------------------------------
	 * Getter
	 * ----------------------------------------*/
	/**
	 * Liefert die horizontale Position der oberen
	 * linken Ecke des Quadrats
	 * @return
	 */
	public int getX() {
		return x;
	}
	/**
	 * Liefert die vertikale Position der oberen
	 * linken Ecke des Quadrats
	 * @return
	 */
	public int getY() {
		return y;
	}
	/**
	 * Liefert die Farbe des Quadrats
	 * @return
	 */
	public Color getFarbe() {
		return farbe;
	}
}

