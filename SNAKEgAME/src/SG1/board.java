package SG1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
public class board extends JPanel implements ActionListener{
	
private Image head;
private Image dot;
private Image apple;
private int apple_x ;
private int apple_y;

private int dots ;
private final int RANDOMPOSITION = 29;

private final int all_dots = 900;
private final int dot_size = 10;

private final int x[] = new int [all_dots]; 
private final int y[] = new int [all_dots]; 

private boolean leftDirection = false ;
private boolean rightDirection = true;
private boolean upDirection = false ;
private boolean downDirection = false;

private boolean inGame = true;

private Timer timer ;

	board(){
		addKeyListener(new TAdapter());
		setBackground(Color.BLACK);
		
		setPreferredSize(new Dimension (300,300));
		setFocusable(true);
		
		loadImages();
		initGame();
	}
	
	
	public void loadImages() 
	{
	ImageIcon i1 = new ImageIcon(this.getClass().getResource("/apple.png"));
	apple = i1.getImage();
	
	ImageIcon i2 = new ImageIcon(this.getClass().getResource("/dot.png"));
	dot = i2.getImage();
	
	ImageIcon i3 = new ImageIcon(this.getClass().getResource("/head.png"));
	head = i3.getImage(); 	
	}
	
	
	public void initGame() 
	{
	
		dots = 3;
		for(int i = 0; i< dots ;i++) {
			y[i] = 50;
			x[i] = 50 - (i*dot_size);
		}
		locateApple();
		 timer = new Timer (180,this);    //how speed the snake has to move between one dot to another
		timer.start();
	}
	
	
	public void locateApple() 
	{
	int r = (int)(Math.random()*RANDOMPOSITION);	
	apple_x= r * dot_size;
	
	r = (int)(Math.random()*RANDOMPOSITION);	
	apple_y = r * dot_size;
		
	}
	
	
	public void paintComponent(Graphics g) {
	super .paintComponent(g);	
	draw (g);

	
	}
	
	public void draw (Graphics g) 
		{
		if(inGame) {
		g.drawImage(apple,  apple_x,  apple_y,  this);
		for(int i = 0 ; i < dots ; i++) 
		{
			if(i == 0 )
			{
				g.drawImage(head , x[i], y[i], this);
			}
			else {
				
				g.drawImage(dot, x[i], y[i], this);
				}
		}
	Toolkit.getDefaultToolkit().sync();
		}
		else {
			gameOver(g);
		}
	
}
	
	public void gameOver (Graphics g) 
	{
	String message = "Game Over!";	
	Font font = new Font ("SAN SERIF",Font.BOLD,40);
	FontMetrics metrices = getFontMetrics(font);
	
	g.setColor(Color.RED);
	g.setFont(font);
	g.drawString(message, ((300-metrices.stringWidth(message))/2), 300/2);
	}



public void move() 
{
	for(int i = dots ; i > 0 ; i--) 
	{
	x[i]= x[i-1];
	y[i]= y[i-1];
	}
	
	if(leftDirection ) 
	{
	x[0] = x[0] - dot_size;	
	}
	if(rightDirection ) 
	{
	x[0] = x[0] + dot_size;	
	}
	if(upDirection ) 
	{
	y[0] = y[0] - dot_size;	
	}
	if(downDirection ) 
	{
	y[0] = y[0] + dot_size;	
	}
	
	
}
public void nextApple() {
	if(  (x[0] == apple_x) && (y[0] ==apple_y)   ) 
	{
		dots++;
		locateApple();
	}
}
public void checkCollision() 
{
for(int i = dots ; i > 0 ; i--) 
{
	if((i>4) && (x[0] == x[i]) && (y[0] == y[i] )) 
	{
	inGame = false;	
	}
	
}
	if(y[0] >= 290) 
	{
	inGame = false;	
	}
	if(x[0] >= 290) {
		inGame = false;
	}
	if(x[0] <= 0) {
		inGame = false;
	}
	if(y[0] <= 0) {
		inGame = false;
	}
	
	if(!inGame) {
		timer.stop();
	}
	
}

public void actionPerformed(ActionEvent e) 					
{
	if(inGame) 
	{
	nextApple();
	checkCollision();
	move();
	}
	repaint();
}




public class TAdapter extends KeyAdapter   //for controls
			{
				
	public void keyPressed (KeyEvent e) 
	{
		int key = e.getKeyCode();
		
		if (   key == KeyEvent.VK_LEFT && (!rightDirection)  ) {
			leftDirection = true;
			upDirection = false;
			downDirection = false;
		}
		if (   key == KeyEvent.VK_RIGHT && (!leftDirection)  ) {
			rightDirection = true;
			upDirection = false;
			downDirection = false;
		}
		if (   key == KeyEvent.VK_UP && (!downDirection)  ) {
			upDirection = true;
			rightDirection = false;
			leftDirection = false;
		}
		if (   key == KeyEvent.VK_DOWN && (!upDirection)  ) {
			downDirection = true;
			rightDirection = false;
			leftDirection = false;
		}
	}
				
				
	
			}
			
			
			
			
			
						
			
}
