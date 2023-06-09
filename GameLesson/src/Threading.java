import java.awt.event.*;
import java.awt.*;

import javax.swing.*;

@SuppressWarnings("serial") //funky warning, just suppress it. It's not gonna do anything.
public class Threading extends JPanel implements Runnable, KeyListener, MouseListener{
	
	//self explanatory variables
	int FPS = 60;
	Thread thread;
	int screenWidth = 600;
	int screenHeight = 600;
	
	long startTime, timeElapsed;
	int frameCount = 0;
	
	public Threading() {
		//sets up JPanel
		setPreferredSize(new Dimension(screenWidth, screenHeight));
		setVisible(true);
		
		//starting the thread
		thread = new Thread(this);
		thread.start();
	}
	
	@Override
	public void run() {
		System.out.println("Thread: Starting thread");
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
		System.out.println("Thread: Initializing game");
		startTime = System.currentTimeMillis();
		timeElapsed = 0;
		FPS = 60;
		for(int i = 0; i < 100000; i++) {
			// this is just to delay time
			String s = "set up stuff blah blah blah";
			s.toUpperCase();
		}
		System.out.println("Thread: Done initializing game");
	}
	
	public void update() {
		//update stuff
		timeElapsed = System.currentTimeMillis() - startTime;
		frameCount++;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, screenWidth, screenHeight);
		
		//draw stuff
		
		g.setColor(Color.BLACK);
		g.drawString("" + timeElapsed + " ms since start of program", 200, 300);
		g.drawString("" + timeElapsed/1000 + " seconds since the start of program", 200, 350);
		g.drawString(frameCount + " frames ran since the start of program", 200, 400);
		g.drawString("FPS: " + (double)frameCount / ((double)timeElapsed / 1000), 200, 450);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
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
		
		//The following lines creates your window
		System.out.println("  Main: Booting game");
		//makes a brand new JFrame
		JFrame frame = new JFrame ("Example");
		//makes a new copy of your "game" that is also a JPanel
		Threading myPanel = new Threading ();
		//so your JPanel to the frame so you can actually see it
		System.out.println("  Main: Initializing Jframe 1/3");
		frame.add(myPanel);
		//so you can actually get keyboard input
		frame.addKeyListener(myPanel);
		//so you can actually get mouse input
		frame.addMouseListener(myPanel);
		//self explanatory. You want to see your frame
		System.out.println("  Main: Initializing Jframe 2/3");
		frame.setVisible(true);
		//some weird method that you must run
		frame.pack();
		//place your frame in the middle of the screen
		frame.setLocationRelativeTo(null);
		System.out.println("  Main: Initializing Jframe 3/3");
		//without this, your thread will keep running even when you windows is closed!
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//self explanatory. You don't want to resize your window because
		//it might mess up your graphics and collisions
		frame.setResizable(false);
		System.out.println("  Main: Done initializing Jframe");
		
	}
}
