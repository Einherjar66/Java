package aufgaben;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

/**
 * Klasse für das Programmfenster
 *
 * Zeigt die Position des Mauszeigers neben dem Mauszeiger an
 * 
 * @author André
 */
public class KoordinatenPanel extends JPanel
	implements MouseMotionListener, MouseListener
{
	/* ----------------------------------------
	 * Instanzeigenschaften
	 * ----------------------------------------*/
	private int breite  = 0; // Breite des Anzeigebereichs
	private int hoehe   = 0; // Höhe des Anzeigebereichs
	
	private int x = 0; // horizontale Position der Maus
	private int y = 0; // vertikale Position der Maus
	
	private int offsetX = 10; // horizontaler Offset der Textposition zur Mauszeigerposition
	private int offsetY = 0;  // vertikaler Offset der Textposition zur Mauszeigerposition
	
	private boolean anzeigen = false; // true, wenn der Mauszeiger innerhalb des 
	                                  // Fensters ist, sonst false
	
	private FontMetrics fm = null;
	
	/* ----------------------------------------
	 * Konstruktor
	 * ----------------------------------------*/
	/**
	 * Initialisiert das Objekt
	 */
	public KoordinatenPanel() {
		addMouseMotionListener(this);
		addMouseListener(this);
	}
	
	/* ----------------------------------------
	 * Überschriebene Methoden aus den 
	 * Interfaces / Superklassen
	 * ----------------------------------------*/
	/**
	 * Zeichnet die Arbeitsfläche neu
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		fm = g.getFontMetrics();
		
		breite  = this.getWidth();
		hoehe   = this.getHeight();
		
		// Anzeige der Mauskioordinaten, wenn die Maus
		// innerhalb des Anzeigefensters ist
		if(anzeigen) {
			g.drawString(x + ", " + y, x + offsetX, y + offsetY);
		}
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	/**
	 * Beim Bewegen der Maus:
	 * Ermittlung der Mauskoordinaten sowie der Anzeigeposition
	 * der Mauskoordinaten relativ zum Mauszeiger
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		x = e.getX();
		y = e.getY();
		
		int textbreite = fm.stringWidth(x + ", " + y);
		int texthoehe = fm.getHeight();
		
		offsetX = x > breite - textbreite - 13 ? -5 - textbreite : 10;
		offsetY = y < texthoehe ? 30 : 0;
		
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	/**
	 * Beim Betreten des Anzeigefensters:
	 * Beginn der Anzeige der Mauskoordinaten
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		anzeigen = true;
		repaint();
	}

	/**
	 * Beim Verlassen des Anzeigefensters:
	 * Ende der Anzeige der Mauskoordinaten
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		anzeigen = false;
		repaint();
	}

}
