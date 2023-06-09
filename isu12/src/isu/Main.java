package isu;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Main extends JPanel implements Runnable {

  static JFrame frame;
  JPanel menuPanel, gamePanel;

  Font smF, mdF, lgF;

  Screen screen = Screen.MENU;
  int currentMap = 0;

  Color white = new Color(255, 255, 255, 255);
  Color black = new Color(0, 0, 0, 255);

  long startTime, timeElapsed;
  int frameCount = 0;

  Thread thread;

  public Main() {

    setLayout(null);
    setPreferredSize(new Dimension(1100, 600));

    // call all initializing methods
    initFonts();
    initMenu();

    thread = new Thread(this);
    thread.start();
  }

  public void run() {
    System.out.println("Thread: Initializing game");
    startTime = System.currentTimeMillis();
    timeElapsed = 0;
    for (int i = 0; i < 100000; i++) {
      // this is just to delay time
      String s = "set up stuff blah blah blah";
      s.toUpperCase();
    }
    System.out.println("Thread: Done initializing game");

    while (true) {
      update();
      repaint();
      try {
        Thread.sleep(1000 / 60);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  public void update() {
    timeElapsed = System.currentTimeMillis() - startTime;
    frameCount++;
  }

  public void initMenu() {

    menuPanel = new JPanel();
    menuPanel.setBounds(0, 0, 1100, 600);
    menuPanel.setOpaque(false);
    menuPanel.setLayout(null);

    JPanel buttonPanel = new JPanel();
    buttonPanel.setBounds(320, 440, 460, 60);
    buttonPanel.setOpaque(false);
    buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

    JButton playButton = new JButton("Map Select");
    playButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        removeAll();

        screen = Screen.MAPSELECT;
        repaint();
        revalidate();

        try {
          initMapSelect();
        } catch (IOException ex) {
          ex.printStackTrace();
        }

      }
    });

    playButton.setPreferredSize(new Dimension(220, 60));
    playButton.setFont(mdF);
    playButton.setBackground(white);
    playButton.setBorder(BorderFactory.createEmptyBorder());
    buttonPanel.add(playButton);

    buttonPanel.add(Box.createRigidArea(new Dimension(20, 0)));

    JButton infoButton = new JButton("Rules & Info");
    infoButton.setPreferredSize(new Dimension(220, 60));
    infoButton.setFont(mdF);
    infoButton.setBackground(white);
    infoButton.setBorder(BorderFactory.createEmptyBorder());
    buttonPanel.add(infoButton);

    menuPanel.add(buttonPanel);
    add(menuPanel);

//    add(bgLabel);
  }

  public void initMapSelect() throws IOException {

    setBackground(black);

    JPanel optionsPanel = new JPanel();
    optionsPanel.setBounds(25, 100, 1050, 500);
    optionsPanel.setOpaque(false);
    optionsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

    JPanel imageGroup1 = new JPanel();
    imageGroup1.setLayout(new BoxLayout(imageGroup1, BoxLayout.PAGE_AXIS));
    imageGroup1.setOpaque(false);


    BufferedImage pondMapBI = ImageIO.read(new File("assets/screens/pond.png"));
    JLabel pondMapLabel = new JLabel(new ImageIcon(pondMapBI.getScaledInstance(500, 272, Image.SCALE_FAST)));
    pondMapLabel.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        removeAll();

        screen = Screen.GAME;
        repaint();
        revalidate();

        try {
          initGame();
        } catch (IOException ex) {
          ex.printStackTrace();
        }
      }
    });

    imageGroup1.add(pondMapLabel);

    JLabel pondMapTitle = new JLabel("Map 1: Pond");
    pondMapTitle.setFont(lgF);
    pondMapTitle.setForeground(white);

    imageGroup1.add(Box.createRigidArea(new Dimension(0, 10)));
    imageGroup1.add(pondMapTitle);

    JPanel imageGroup2 = new JPanel();
    imageGroup2.setLayout(new BoxLayout(imageGroup2, BoxLayout.Y_AXIS));
    imageGroup2.setOpaque(false);

    BufferedImage beachMapBI = ImageIO.read(new File("assets/screens/beach.png"));
    JLabel beachMapLabel = new JLabel(new ImageIcon(beachMapBI.getScaledInstance(500, 272, Image.SCALE_FAST)));
    beachMapLabel.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        removeAll();

        screen = Screen.GAME;
        repaint();
        revalidate();

        try {
          initGame();
        } catch (IOException ex) {
          ex.printStackTrace();
        }
      }
    });

    imageGroup2.add(beachMapLabel);

    JLabel beachMapTitle = new JLabel("Map 2: Beach");
    beachMapTitle.setFont(lgF);
    beachMapTitle.setForeground(white);

    imageGroup2.add(Box.createRigidArea(new Dimension(0, 10)));

    imageGroup2.add(beachMapTitle);


    optionsPanel.add(imageGroup1);
    optionsPanel.add(Box.createRigidArea(new Dimension(50, 0)));
    optionsPanel.add(imageGroup2);

    add(optionsPanel);

  }

  public void initFonts() {
    try {
      smF = Font.createFont(Font.TRUETYPE_FONT, new File("assets/font/oetztype.ttf")).deriveFont(12f);
      mdF = Font.createFont(Font.TRUETYPE_FONT, new File("assets/font/oetztype.ttf")).deriveFont(18f);
      lgF = Font.createFont(Font.TRUETYPE_FONT, new File("assets/font/oetztype.ttf")).deriveFont(28f);
      GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

      ge.registerFont(smF);
      ge.registerFont(mdF);
      ge.registerFont(lgF);
    } catch (IOException e) {
      e.printStackTrace();
    } catch (FontFormatException e) {
      e.printStackTrace();
    }
  }

  public void initGame() throws IOException {
    gamePanel = new JPanel();
    gamePanel.setBounds(0, 0, 1100, 600);
    gamePanel.setOpaque(false);
    gamePanel.setLayout(null);

    JPanel monkeys = new JPanel();

    monkeys.setBounds(900, 0, 200, 280);
    monkeys.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
    monkeys.setOpaque(false);
    monkeys.setLayout(new GridLayout(3, 2, 10, 10));

    String[] monkeyIcons = {"dart", "ninja", "buc", "super", "village", "farm"};
    for (int i = 0; i < 6; i++) {
      BufferedImage monkeyBI = ImageIO.read(new File("assets/monkeys/" + monkeyIcons[i] + "/1.png"));
      JLabel monkeyLabel = new JLabel(new ImageIcon(monkeyBI.getScaledInstance(60, 60, Image.SCALE_FAST)));
      monkeyLabel.setBackground(new Color(139, 89, 57, 150));
      monkeyLabel.setOpaque(true);
      monkeys.add(monkeyLabel);
    }

    gamePanel.add(monkeys);

    add(gamePanel);

  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    String[] maps = {"pond", "beach"};

    BufferedImage bgBI = null;
    if (screen == Screen.MENU) {
      try {
        bgBI = ImageIO.read(new File("assets/screens/menu.png"));
      } catch (IOException e) {
        e.printStackTrace();
      }
      g.drawImage(bgBI, 0, 0, null);

    } else if (screen == Screen.MAPSELECT) {
      try {
        bgBI = ImageIO.read(new File("assets/screens/mapselect.png"));
      } catch (IOException e) {
        e.printStackTrace();
      }
      g.drawImage(bgBI, 0, 0, null);

    } else if (screen == Screen.GAME) {
      try {
        bgBI = ImageIO.read(new File("assets/screens/" + maps[currentMap] + ".png"));
      } catch (IOException e) {
        e.printStackTrace();
      }

      g.drawImage(bgBI, 0, 0, null);

      g.setColor(white);

      double fps = (Math.round(((double) frameCount / ((double) timeElapsed / 1000)) * 100)) / 100.0;
      g.drawString("FPS: " + String.format("%.2f", fps), 905, 585);
    }


  }


  public static void main(String[] args) throws IOException {

    frame = new JFrame("B");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    Main contentPanel = new Main();
    //...

    frame.add(contentPanel);
    frame.pack();
    frame.setVisible(true);

  }
}
