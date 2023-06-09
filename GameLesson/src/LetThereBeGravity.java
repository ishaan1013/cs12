import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class LetThereBeGravity extends JPanel implements Runnable, KeyListener {
	
	Thread thread;
	int FPS = 60;
	int screenWidth = 600;
	int screenHeight = 600;
	
	Rectangle rect = new Rectangle(0, 0, 30, 30);
	boolean jump, left, right;
	double speed = 3.5;			//double variables for better accuracy/simulation of gravity
	double jumpSpeed = 15;		//using int for these type of variables is a bad idea
	double xVel = 0;
	double yVel = 0;
	double gravity = 0.8;
	boolean airborne = true;
	
	
	public LetThereBeGravity() {
		setPreferredSize(new Dimension(screenWidth, screenHeight));
		setVisible(true);
		
		jump = false;
		left = false;
		right = false;
		
		thread = new Thread(this);
		thread.start();
	}
	
	@Override
	public void run() {
		while(true) {
			
			move();
			keepInBound();
			
			this.repaint();
			
			try {
				Thread.sleep(1000/FPS);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.GREEN);
		g2.fillRect(0, 0, screenWidth, screenHeight);
		g2.setColor(Color.RED);
		g2.fill(rect);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_A) {
			left = true;
			right = false;
		}else if(key == KeyEvent.VK_D) {
			right = true;
			left = false;
		}else if(key == KeyEvent.VK_W) {
			jump = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_A) {
			left = false;
		}else if(key == KeyEvent.VK_D) {
			right = false;
		}else if(key == KeyEvent.VK_W) {
			jump = false;
		}
	}
	
	void move() {
		if(left)
			xVel = -speed;
		else if(right)
			xVel = speed;
		else
			xVel = 0;
		
		if(airborne) {
			yVel -= gravity;
		}else {
			if(jump) {
				airborne = true;
				yVel = jumpSpeed;
			}
		}
		
		rect.x += xVel;
		rect.y -= yVel;
	}
	
	void keepInBound() {
		if(rect.x < 0) {
			rect.x = 0;
		}
		else if(rect.x > screenWidth - rect.width) {
			rect.x = screenWidth - rect.width;
		}
		
		if(rect.y < 0) {
			rect.y = 0;
			yVel = 0;
		}else if(rect.y > screenHeight - rect.height) {
			rect.y = screenHeight - rect.height;
			airborne = false;
			yVel = 0;
		}
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame ("Example");
		LetThereBeGravity myPanel = new LetThereBeGravity ();
		frame.add(myPanel);
		frame.addKeyListener(myPanel);
		frame.setVisible(true);
		frame.pack();
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
