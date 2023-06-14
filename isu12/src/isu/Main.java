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

  Game game;

  int mouseX = -1;
  int mouseY = -1;

  JPanel controls;
  JLabel pauseL, playL, speedL, speed2L, exitL;

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
          initGame(0);
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
          initGame(1);
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

  public void initGame(int map) throws IOException {

    game = new Game(map);

    gamePanel = new JPanel();
    gamePanel.setBounds(0, 0, 1100, 600);
    gamePanel.setOpaque(false);
    gamePanel.setLayout(null);
    gamePanel.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        if (game.getPlacing() != null) {
          mouseX = e.getX();
          mouseY = e.getY();
        }

      }
    });

    JPanel monkeys = new JPanel();

    monkeys.setBounds(900, 0, 200, 280);
    monkeys.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
    monkeys.setOpaque(false);
    monkeys.setLayout(new GridLayout(2, 2, 10, 10));

    controls = new JPanel();

    controls.setBounds(900, 520, 200, 80);
    controls.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
    controls.setOpaque(false);
    controls.setLayout(new GridLayout(1, 3, 10, 10));

    String[] monkeyIcons = {"dart", "ninja", "buc", "super"};
    for (int i = 0; i < 4; i++) {
      BufferedImage monkeyBI = ImageIO.read(new File("assets/monkeys/" + monkeyIcons[i] + "/1.png"));
      JLabel monkeyLabel = new JLabel(new ImageIcon(monkeyBI.getScaledInstance(60, 60, Image.SCALE_FAST)));
      monkeyLabel.setBackground(new Color(139, 89, 57, 150));
      monkeyLabel.setOpaque(true);

      // fixes error with the mouselistener
      int finalI = i;

      monkeyLabel.addMouseListener(new MouseAdapter() {
        public void mouseClicked(MouseEvent e) {
          if (game.getPlacing() == null) game.setPlacing(monkeyIcons[finalI]);
          else game.setPlacing(null);
        }
      });
      monkeys.add(monkeyLabel);
    }

    // controls

    initControls();

    gamePanel.add(monkeys);
    gamePanel.add(controls);

    add(gamePanel);

  }

  public void initControls() throws IOException {
    BufferedImage pauseBI = ImageIO.read(new File("assets/controls/pause.png"));
    BufferedImage playBI = ImageIO.read(new File("assets/controls/play.png"));
    BufferedImage speedBI = ImageIO.read(new File("assets/controls/speed.png"));
    BufferedImage speed2BI = ImageIO.read(new File("assets/controls/speed2.png"));
    BufferedImage exitBI = ImageIO.read(new File("assets/controls/exit.png"));
    pauseL = styleControl(pauseL, pauseBI);
    playL = styleControl(playL, playBI);
    speedL = styleControl(speedL, speedBI);
    speed2L = styleControl(speed2L, speed2BI);
    exitL = styleControl(exitL, exitBI);

    pauseL.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        controls.removeAll();
        controls.add(playL);

        if (game.getSpedUp()) {
          controls.add(speed2L);
        } else {
          controls.add(speedL);
        }
        controls.add(exitL);

        controls.revalidate();

        game.pauseResume();
      }
    });
    playL.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        controls.removeAll();
        controls.add(pauseL);

        if (game.getSpedUp()) {
          controls.add(speed2L);
        } else {
          controls.add(speedL);
        }
        controls.add(exitL);

        controls.revalidate();

        game.pauseResume();
      }
    });
    speedL.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        controls.removeAll();

        if (game.getPause()) {
          controls.add(playL);
        } else {
          controls.add(pauseL);
        }

        controls.add(speed2L);
        controls.add(exitL);

        controls.revalidate();

        game.speedChange();
      }
    });
    speed2L.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        controls.removeAll();

        if (game.getPause()) {
          controls.add(playL);
        } else {
          controls.add(pauseL);
        }

        controls.add(speedL);
        controls.add(exitL);

        controls.revalidate();

        game.speedChange();
      }
    });
    exitL.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        System.out.println("exit");
      }
    });

    controls.add(pauseL);
//    controls.add(playL);
    controls.add(speedL);
//    controls.add(speed2L);
    controls.add(exitL);
  }

  public JLabel styleControl(JLabel l, BufferedImage i) {
    l = new JLabel(new ImageIcon(i.getScaledInstance(40, 40, Image.SCALE_FAST)));
    l.setBackground(new Color(87, 60, 43, 200));
    l.setOpaque(true);

    return l;
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

      for (Tower t : game.getTowers()) {
        try {
          BufferedImage towerBI = ImageIO.read(new File(t.getPathname() + "1.png"));
          g.drawImage(towerBI, t.getX() - (t.getWidth() / 2), t.getY() - (t.getHeight() / 2), null);
        } catch (IOException e) {
          e.printStackTrace();
        }

      }

      if (game.getPlacing() != null) {
//        g.drawString("X: " + mouseX, 905, 555);
//        g.drawString("Y: " + mouseY, 905, 570);
        g.drawString("Adding...", 905, 570);

        if (mouseX != -1 && mouseY != -1) {
//          System.out.println("Added monkey at " + mouseX + ", " + mouseY);
          String placing = game.getPlacing();
          if (placing.equals("dart") || placing.equals("ninja") || placing.equals("super")) {

            if (game.isCoordValid(mouseX, mouseY, 70, false)) {
              switch (placing) {
                case "dart" -> game.addTower(new DartM(mouseX, mouseY));
                case "ninja" -> game.addTower(new NinjaM(mouseX, mouseY));
                case "super" -> game.addTower(new SuperM(mouseX, mouseY));
              }
              System.out.println(placing + " - added land at " + mouseX + ", " + mouseY);
            } else {
              System.out.println(placing + " - invalid placement " + mouseX + ", " + mouseY);
            }
          } else {
            if (game.isCoordValid(mouseX, mouseY, 85, true)) {
              game.addTower(new BucM(mouseX, mouseY));
              System.out.println(placing + " - added buc at " + mouseX + ", " + mouseY);
            } else {
              System.out.println(placing + " - invalid placement " + mouseX + ", " + mouseY);
            }
          }


          game.setPlacing(null);
          mouseX = -1;
          mouseY = -1;
        }

      }
    }

  }

  public static void main(String[] args) {

    frame = new JFrame("B");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    Main contentPanel = new Main();
    //...

    frame.add(contentPanel);
    frame.pack();
    frame.setVisible(true);

  }
}
