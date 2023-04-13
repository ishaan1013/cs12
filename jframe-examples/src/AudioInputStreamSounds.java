// The "AudioInputStreamSounds class
// This is a more updated version of JFrames using sounds.  It is able to play a larger .wav files, but it cannot
// play .au or .mp3 files.


import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;


import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class AudioInputStreamSounds implements KeyListener
{
	JFrame frame; // needs to be static since I need to access this in the constructor for the goodbye clip
	JPanel myPanel;
	Clip hello, goodbye, background, beep;
	JLabel label;
	
	public AudioInputStreamSounds ()
	{
		
		frame = new JFrame ("Playingh music and sounds");
		myPanel = new JPanel ();	
		myPanel.setPreferredSize(new Dimension(500, 100));
		 
		myPanel.setLayout (new BoxLayout (myPanel, BoxLayout.PAGE_AXIS));
		myPanel.setBackground (Color.white);
		myPanel.setBorder (BorderFactory.createEmptyBorder (10,10,10,10));

		

		label = new JLabel ("Press 'H' - Horn or 'B' - Background Sound");	
		myPanel.add (label);
	
		
		
		myPanel.setFocusable (true); // Need this to set the focus to the panel in order to add the keyListener
		myPanel.addKeyListener (this);
		
		
		try {
		
			AudioInputStream sound = AudioSystem.getAudioInputStream(new File ("hello.wav"));
			hello = AudioSystem.getClip();
			hello.open(sound);
			sound = AudioSystem.getAudioInputStream(new File ("goodbye.wav"));
			goodbye = AudioSystem.getClip();
			goodbye.open(sound);
			sound = AudioSystem.getAudioInputStream(new File ("beep.wav"));
			beep = AudioSystem.getClip();
			beep.open(sound);
			sound = AudioSystem.getAudioInputStream(new File ("backgroundmusic.wav"));
			background = AudioSystem.getClip();
			background.open(sound);			
		} 
		catch (Exception e) {
		}

		

		
		
		// To set "goodbye" to play when window closes
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
		    public void windowClosing (java.awt.event.WindowEvent windowEvent) {
		    	goodbye.start();
				try
				{
					Thread.sleep (1200);
				}
				catch (InterruptedException e)
				{
				}
		    	System.exit(0);
		    }   
		});		
        
        // Play "hello" when program starts
		hello.start();
		frame.add(myPanel);
		frame.pack();
		frame.setVisible(true);



	} 
	
	public void keyPressed (KeyEvent kp) {
		char key = Character.toUpperCase (kp.getKeyChar ());
		if (key == 'H') {
			beep.setFramePosition (0); //<-- play sound file again from beginning
			beep.start ();
		}
		else if (key == 'B') {
			//background.start (); -- don't need this if looping
			background.setFramePosition (0); //<-- play sound file again from beginning
			background.loop(Clip.LOOP_CONTINUOUSLY);
		}
		else {
			label.setText ("Press 'H' - Horn or 'B' - Background Sound");
			background.stop();
			
		}	
	}
	
	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}
	
	
	
	public static void sound(String fileName) throws Exception {
		
	}
		
	public static void main (String [] args){
		new AudioInputStreamSounds ();
			
		
	}


}