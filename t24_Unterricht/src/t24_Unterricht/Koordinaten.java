package aufgaben;

import javax.swing.JFrame;

/**
 * Haupt-Programmklasse mit der main-Methode
 * 
 * @author Andr�
 */
public class Koordinaten extends JFrame {

	/* ----------------------------------------
	 * Konstruktor
	 * ----------------------------------------*/
	public Koordinaten(String titel) {
		super(titel);
		setBounds(200,200,400,300);              // Fensterposition und -gr��e festlegen
		setDefaultCloseOperation(EXIT_ON_CLOSE); // Aktion beim Schlie�en des Fensters 
		                                         // festlegen (Programm wird beendet)
		add(new KoordinatenPanel());			 // Panel zum Fenster hinzuf�gen
		setVisible(true);                        // Fenster sichtbar schalten
	}

	public static void main(String[] args) {
		new Koordinaten("Koordinaten");

	}

}
