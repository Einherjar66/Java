package unterricht;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;


class ThreadPanel extends JPanel
	implements Runnable, MouseListener
{
	
	private Thread t1;
	private Thread t2;
	private int x = 0;
	private boolean isRunning = true;  // true, solange der Thread t1 laufen soll, sonst false
	
	public ThreadPanel() {
		addMouseListener(this);
		t1 = new Thread(this);
		t2 = new Thread(this);
		t1.start();
		t2.start();
	}

	@Override
	public void run() {
		if(Thread.currentThread() == t1) {
			System.out.println("Hier ist Thread t1");
			while(isRunning) {
				x++;
				if(x >= 200) { // Thread beendet sich nach 200 Durchläufen der Schleife selbst
					break;
				}
				repaint();
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					System.out.println("Aufräumarbeiten");
					break;
				}
			}
			System.out.println("t1 beendet");
		}
		else if(Thread.currentThread() == t2) {
			System.out.println("Hier ist Thread t2");
		}
		
		
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawString("Das ist ein Text", x, 20);
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
//		isRunning = false;
		t1.interrupt(); // beendet t1 direkt; Aufräumarbeiten im Catch-Block können dauern!
		try {
			t1.join();  // Wartet auf die Terminierung von t1
		} catch (InterruptedException e1) {
			System.out.println("Warten auf Terminierung von t1 wurde unterbrochen");
		}  
		System.out.println("Weitere Arbeiten");
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
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


public class Thread02 
	extends JFrame 
{
	
	public Thread02(String titel) {
		super(titel);
		setBounds(200, 200, 400, 300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(new ThreadPanel());
		setVisible(true);
	}

	public static void main(String[] args) {
		new Thread02("Multitasking");

	}

}
