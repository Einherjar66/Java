package aufgaben;

import javax.swing.JFrame;

/**
 * Haupt-Programmklasse mit der main-Methode
 * 
 * @author André
 */
public class Koordinaten extends JFrame {

	/* ----------------------------------------
	 * Konstruktor
	 * ----------------------------------------*/
	public Koordinaten(String titel) {
		super(titel);
		setBounds(200,200,400,300);              // Fensterposition und -größe festlegen
		setDefaultCloseOperation(EXIT_ON_CLOSE); // Aktion beim Schließen des Fensters 
		                                         // festlegen (Programm wird beendet)
		add(new KoordinatenPanel());			 // Panel zum Fenster hinzufügen
		setVisible(true);                        // Fenster sichtbar schalten
	}

	public static void main(String[] args) {
		new Koordinaten("Koordinaten");

	}

}
