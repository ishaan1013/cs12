package assignment4;

import java.awt.*;
import java.util.*;
import java.io.*;
import javax.swing.*;

public class Driver extends JPanel {

  MenuState state = MenuState.ALBUMS;

  public Driver() {
    setPreferredSize(new Dimension(900, 600));
    setBackground(new Color(25, 25, 25));
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    if (state == MenuState.ALBUMS) {
      g.setColor(new Color(50, 50, 50));
      g.fillRect(0, 0, getWidth(), 100);
//      g.drawString("Albums", 50, 50);
    } else if (state == MenuState.CARDS) {
      g.setColor(Color.WHITE);
      g.drawString("Cards", 50, 50);
    }
  }

  public static void main(String[] args) throws IOException {

    JFrame frame = new JFrame("Pokemon Cards Collection");
    Driver panel = new Driver();
    frame.add(panel);

    JButton jb1 = new JButton("Button 1");
    JButton jb2 = new JButton("Button 2");
    JButton jb3 = new JButton("Button 3");

    // Define the panel to hold the buttons
    panel.setLayout(new FlowLayout());
    panel.add(jb1);
    panel.add(jb2);
    panel.add(jb3);

    // Set the window to be visible as the default to be false
    frame.pack();
    frame.setVisible(true);

  }

  public static void add(String file) throws IOException {
    BufferedReader inFile = new BufferedReader(new FileReader("input.txt"));

    String line;
    while ((line = inFile.readLine()) != null) {
      System.out.println(line);
    }
  }
}
