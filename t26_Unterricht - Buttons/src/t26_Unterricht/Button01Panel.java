package t26_Unterricht;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Button01Panel extends JPanel 
	implements ActionListener
{
	private JButton b1 = new JButton("Rot");
	private JButton b2 = new JButton("Blau");
	private JButton b3 = new JButton("Gelb");
	
	private JTextField tb1 = new JTextField ("Test ausgabe");
	
	private JLabel label01 = new JLabel("Eingabe: ");
	
	public Button01Panel() 
	{
		setLayout(null); // Löscht das automatisches Layout
	
		// Buttons
		b1.setBounds(40, 10, 100, 30); // SetBounds(y,x,b,h)
		b2.setBounds(40, 45, 100, 30);
		b3.setBounds(40, 80, 100, 30);
		
		// Textfelder
		tb1.setBounds(240, 10, 200, 30);
		label01.setBounds(180, 10, 60, 30);
		
		add(b1);
		add(b2);
		add(b3);
		
		add(tb1);
		
		add(label01);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
	}

	@Override
	protected void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == b1) 
		{
			setBackground(Color.RED);
			tb1.setText("Farbe ist Rot");
		}
		
		if (e.getSource() == b2) 
		{
			setBackground(Color.BLUE);
			tb1.setText("Farbe ist Blue");
		}
		
		if(e.getSource() == b3)
		{
			setBackground(Color.YELLOW);
			tb1.setText("Farbe ist Gelb");
		}
		
	}
	
	
}
