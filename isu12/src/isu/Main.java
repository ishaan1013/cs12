package isu;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Main {

  static JFrame frame;
  static JPanel menuPanel, gamePanel;
  static JPanel contentPanel = new JPanel();

  static Font smF, mdF, lgF;

  static Color white = new Color(255, 255, 255, 255);
  static Color black = new Color(0, 0, 0, 255);

  public static void initMenu() throws IOException {

    BufferedImage bgBI = ImageIO.read(new File("assets/screens/menu.png"));
    JLabel bgLabel = new JLabel(new ImageIcon(bgBI));
    bgLabel.setBounds(0, 0, 900, 600);

    menuPanel = new JPanel();
    menuPanel.setBounds(0, 0, 900, 600);
    menuPanel.setOpaque(false);
    menuPanel.setLayout(null);

    JPanel buttonPanel = new JPanel();
    buttonPanel.setBounds(220, 440, 460, 60);
    buttonPanel.setOpaque(false);
    buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

    JButton playButton = new JButton("Map Select");
    playButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        contentPanel.removeAll();
        contentPanel.repaint();
        contentPanel.revalidate();

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
    contentPanel.add(menuPanel);

    contentPanel.add(bgLabel);


  }

  public static void initMapSelect() throws IOException {

    contentPanel.setBackground(black);

    JPanel optionsPanel = new JPanel();
    optionsPanel.setBounds(60, 120, 750, 500);
    optionsPanel.setOpaque(false);
    optionsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

    JPanel imageGroup1 = new JPanel();
    imageGroup1.setLayout(new BoxLayout(imageGroup1, BoxLayout.PAGE_AXIS));
    imageGroup1.setOpaque(false);


    BufferedImage pondMapBI = ImageIO.read(new File("assets/screens/pond.png"));
    JLabel pondMapLabel = new JLabel(new ImageIcon(pondMapBI.getScaledInstance(350, 233, Image.SCALE_FAST)));
    pondMapLabel.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        System.out.println("Pond Map");
//        contentPanel.removeAll();
//        contentPanel.repaint();
//        contentPanel.revalidate();
//
//        initGame(1);
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
    JLabel beachMapLabel = new JLabel(new ImageIcon(beachMapBI.getScaledInstance(350, 233, Image.SCALE_FAST)));
    beachMapLabel.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        System.out.println("Beach Map");
//        contentPanel.removeAll();
//        contentPanel.repaint();
//        contentPanel.revalidate();
//
//        initGame(1);
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

    contentPanel.add(optionsPanel);

  }

  public static void initFonts() {
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


  public static void main(String[] args) throws IOException {

    frame = new JFrame("B");
    frame.setPreferredSize(new Dimension(900, 600));
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    contentPanel.setLayout(null);
    contentPanel.setPreferredSize(new Dimension(900, 600));

    // call all initializing methods
    initFonts();
    initMenu();
    initMapSelect();

    frame.setContentPane(contentPanel);
    frame.pack();
    frame.setVisible(true);

  }
}
