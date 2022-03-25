package daniel;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class CursorPos extends JFrame
{

	public CursorPos(String titel) 
	{
		super(titel);
		
		setBounds(550, 350, 600, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(new MainPanel());
		setVisible(true);
	}
	
	public static void main(String[] args) 
	{
		new CursorPos("MainPanel");
	}
	
}


class MainPanel extends JPanel
implements MouseMotionListener, MouseListener
{

private int XPos;
private int YPos;
private FontMetrics fm;
private String Coordinates = "";

//private int WindowWidth  = getWidth();
//private int WindowHeight = getHeight();

public MainPanel() 
{
	this.addMouseMotionListener(this); 
}

@Override
protected void paintComponent(Graphics g)
{
	super.paintComponent(g);
	fm = g.getFontMetrics();
//	Coordinates = "(" + XPos + ", " + YPos + ")";
//	int StringLength = fm.stringWidth(Coordinates);

	g.drawString(Coordinates, XPos, YPos);

}

@Override
public void mouseDragged(MouseEvent e) 
{
	// TODO Auto-generated method stub

}

@Override
public void mouseMoved(MouseEvent e) 
{
	this.XPos = e.getX();
	this.YPos = e.getY();
	
	Coordinates = "(" + XPos + ", " + YPos + ")";
	int breite = fm.stringWidth(Coordinates);
	int hoehe = fm.getHeight();
	
	if(XPos >= getWidth() - breite - 12) {
		XPos = XPos - breite - 10;
	}
	else {
		XPos = XPos + 10;
	}
	
	if(YPos < hoehe) {
		YPos += hoehe + 13;
	}
		
	repaint();	
}

@Override
public void mouseClicked(MouseEvent e) {
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
	repaint();
}

@Override
public void mouseExited(MouseEvent e) {
	// TODO Auto-generated method stub
	
}
}
