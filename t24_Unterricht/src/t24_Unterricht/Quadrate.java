package aufgaben;

import javax.swing.JFrame;

/**
 * Haupt-Programmklasse mit der main-Methode
 * 
 * @author André
 */
public class Quadrate extends JFrame 
{
	/* ----------------------------------------
	 * Konstruktor
	 * ----------------------------------------*/
	public Quadrate(String titel) {
		super(titel);
		setBounds(200,200,600,500);              // Fensterposition und -größe festlegen
		setDefaultCloseOperation(EXIT_ON_CLOSE); // Aktion beim Schließen des Fensters 
		                                         // festlegen (Programm wird beendet)
		add(new QuadratePanel());			     // Panel zum Fenster hinzufügen
		setVisible(true);                        // Fenster sichtbar schalten
	}
	
	public static void main(String[] args) {
		new Quadrate("Quadrate");
	}

}
