package christian_david;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.*;



public class funfQuadrateRandom extends JFrame 
	
{
	private int breite=600;
	private int hoehe=600;
		
	public funfQuadrateRandom(String titel) {
		super(titel);
		setBounds(0, 0, breite, h�he);
		add(new Panel02());
		setVisible(true);
	}
	
	

	public static void main(String[] args) {
		new funfQuadrateRandom("funfQuadrateRandom Fenster");
	}
}




class Panel02
extends JPanel implements MouseListener, MouseMotionListener
{

	public static final int GROESSE = 100;
	
	private int breite;
	private int h�he;
	public Panel02() {
		
		 this.addMouseListener(this);  
		 this.addMouseMotionListener(this);   

		
	}
	
	 public void mouseClicked(MouseEvent e) {                     // Algo pasa cuando haces click ( presionar y soltar  ) 
		 // Pr�fen, ob innerhalb eines Quadrates geklickt wurde
		 int x = e.getX();
		 int y = e.getY();
		 
		 for(int i = quadrate.length - 1; i >= 0; i--) {
			 if( x >= quadrate[i][0]
			  && x <  quadrate[i][0] + GROESSE
			  && y >= quadrate[i][1]
			  && y <  quadrate[i][1] + GROESSE
					 ) {
				 quadrate[i][0] = -GROESSE;
				 quadrate[i][1] = -GROESSE;
				 break;
			 }
		 }
		 repaint();

      }
	 
	private int[][] quadrate = null; // Instanzvariable
	 
	 
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Color c = new Color(100, 23, 42); // Angabe �ber RGB-Werte (0 bis 255)
		Color c2 = new Color(0, 0, 0); // Angabe �ber RGB-Werte (0 bis 255)
		
		g.setColor(c);
		
		if(quadrate == null) {
			quadrate = new int[5][2];
			for(int i = 0; i < 5; i++) {
				quadrate[i][0] = (int) (Math.random() * (getWidth() - GROESSE)); // Math.random() erzeugt Zahl >= 0.0 und < 1.0
				                                                            // Math.random()*560 ... >= 0.0 und < 560.0
				quadrate[i][1] = (int) (Math.random() * (getHeight() - GROESSE));
			}
		}
		
		for(int[] quadrat: quadrate) {
			g.fillRect(quadrat[0], quadrat[1], GROESSE, GROESSE);
		}
			    
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
			
		
	
}
