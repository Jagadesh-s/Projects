package SG1;



import javax.swing.*;

public class SnakeGame1 extends JFrame{

	SnakeGame1() 
	{
		super("SNAKE GAME");
		
		
		add(new board());
	
		pack();     //Used for frame refresh
		
	setLocationRelativeTo(null);
	setResizable(false);
		
	}

	public static void main(String[] args) {
			
	new SnakeGame1() .setVisible(true);;
	}

}
