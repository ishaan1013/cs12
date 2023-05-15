package assignment5c;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

public class Main {
  static JFrame frame;
  static JPanel contentPanel, headerPanel, mainPanel, cardPanel;
  static Box cardBox;
  static JLabel lab;

  static Color black = new Color(0x000000);
  static Color dark = new Color(0x1f1f1f);
  //  static Color light = new Color(0xcfcfcf);
  static Color white = new Color(0xffffff);

  public static void initHeader() {
    headerPanel = new JPanel();
    headerPanel.setBackground(black);
    headerPanel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
    headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.LINE_AXIS));

    JPanel numCardsPanel = new JPanel();
    numCardsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
    JLabel numLabel = new JLabel("# Cards");
    numLabel.setForeground(white);
    String[] country = new String[26];
    for (int i = 0; i < 26; i++) {
      country[i] = Integer.toString(i);
    }
    JComboBox<String> cb = new JComboBox<>(country);
    JButton b1 = new JButton("Confirm");
    b1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        int selected = Integer.parseInt(cb.getItemAt(cb.getSelectedIndex()));
        Stack<Integer> stack = new Stack<>();
        for (int i = 1; i <= selected; i++) {
          stack.push(i);
        }
        LinkedList<Integer> cards = new LinkedList<>();
        for (int i = 0; i < selected; i++) {
          if (cards.size() > 0) {
            int last = cards.removeLast();
            cards.addFirst(last);
          }
          cards.addFirst(stack.pop());
        }

        cardBox.removeAll();
        for (int card : cards) {
          BufferedImage img;
          try {
            img = ImageIO.read(new File("images/card" + card + ".png"));
            JLabel imgLabel = new JLabel(new ImageIcon(img.getScaledInstance(150, 90, Image.SCALE_FAST)));
            cardBox.add(imgLabel);
          } catch (IOException ex) {
            System.out.println("image file error: " + ex);
          }
        }
        cardBox.revalidate();
      }
    });

    numCardsPanel.add(numLabel);
    numCardsPanel.add(cb);
    numCardsPanel.add(b1);
    numCardsPanel.setBackground(black);

    JButton b2 = new JButton("Exit");
    b2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.exit(0);
      }
    });

    headerPanel.add(numCardsPanel);
    headerPanel.add(Box.createHorizontalGlue());
    headerPanel.add(b2);
  }

  public static void initMain() {
    mainPanel = new JPanel();
    mainPanel.setBackground(dark);
    mainPanel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
    mainPanel.setPreferredSize(new Dimension(frame.getWidth(), 550));

    cardBox = Box.createVerticalBox();

    cardBox.setBackground(dark);
    cardPanel = new JPanel();
    cardPanel.setLayout(new BorderLayout());
    cardPanel.add(cardBox, BorderLayout.PAGE_START);
    cardPanel.setBackground(dark);


    JScrollPane cardScroll = new JScrollPane(cardPanel);
    cardScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    cardScroll.setMaximumSize(new Dimension(350, 400));
    cardScroll.setPreferredSize(new Dimension(350, 400));
    cardScroll.setBorder(BorderFactory.createEmptyBorder());

    mainPanel.add(cardScroll);

  }

  public static void main(String[] args) {
    frame = new JFrame("Assignment 5C");
    frame.setPreferredSize(new Dimension(400, 600));
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    initHeader();
    initMain();

    contentPanel = new JPanel();
    contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.PAGE_AXIS));
    contentPanel.add(headerPanel);
    contentPanel.add(mainPanel);

    frame.setContentPane(contentPanel);
    frame.pack();
    frame.setVisible(true);

  }
}