import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.*;

import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;

@SuppressWarnings("serial") //funky warning, just suppress it. It's not gonna do anything.
public class Background extends JPanel implements Runnable, KeyListener, MouseListener{
	
	//self explanatory variables
	int FPS = 60;
	Thread thread;
	int screenWidth = 600;
	int screenHeight = 600;
	BufferedImage bg;
	int speed = 1;
	int x = 0;  //reference point
	int width;	//background width
	boolean autoMove = true;
	
	public Background() {
		//sets up JPanel
		setPreferredSize(new Dimension(screenWidth, screenHeight));
		setVisible(true);
		
		//starting the thread
		thread = new Thread(this);
		thread.start();
	}
	
	@Override
	public void run() {
		initialize();
		while(true) {
			//main game loop
			update();
			this.repaint();
			try {
				Thread.sleep(1000/FPS);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void initialize() {
		//setups before the game starts running
		try {
			bg = ImageIO.read(new File("background.png"));
			width = bg.getWidth();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void update() {
		//update stuff
		x -= speed;
		if(x < -width)
			x = 0;
		else if(x > width)
			x = 0;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//draw stuff
		g.drawImage(bg, x, 0, null);
		g.drawImage(bg, x + width, 0, null);
		g.drawImage(bg, x - width, 0, null);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int keyCode = e.getKeyCode();
		if(keyCode == KeyEvent.VK_S) {
			if(autoMove)
				autoMove = false;
			else
				autoMove = true;
		}else if(keyCode == KeyEvent.VK_A) {
			speed = 5;
		}else if(keyCode == KeyEvent.VK_D) {
			speed = -5;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(autoMove) 
			speed = 1;
		else 
			speed = 0;
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
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		
		//The following lines create your window
		
		//makes a brand new JFrame
		JFrame frame = new JFrame ("Example");
		//makes a new copy of your "game" that is also a JPanel
		Background myPanel = new Background ();
		//so your JPanel to the frame so you can actually see it
		frame.add(myPanel);
		//so you can actually get keyboard input
		frame.addKeyListener(myPanel);
		//so you can actually get mouse input
		frame.addMouseListener(myPanel);
		//self explanatory. You want to see your frame
		frame.setVisible(true);
		//some weird method that you must run
		frame.pack();
		//place your frame in the middle of the screen
		frame.setLocationRelativeTo(null);
		//without this, your thread will keep running even when you windows is closed!
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//self explanatory. You don't want to resize your window because
		//it might mess up your graphics and collisions
		frame.setResizable(false);
	}
}
