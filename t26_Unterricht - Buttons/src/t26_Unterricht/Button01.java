package t26_Unterricht;

import javax.swing.JFrame;

public class Button01 extends JFrame 
{
	
	public Button01(String titel) 
	{
		super(titel);
		setBounds(550,400,600,400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(new Button01Panel());
		setVisible(true);
			
	}

	public static void main(String[] args) 
	{
		new Button01("BOUTTONS");
	}

}
