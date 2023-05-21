package assignment6;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

public class Main {
    static JFrame frame;
    static JPanel contentPanel, headerPanel, mainPanel, cardPanel, statsPanel;
    static Box cardBox, statsBox;

    static Color black = new Color(0x000000);
    static Color dark = new Color(0x111111);
    static Color border = new Color(0x232323);
    static Color light = new Color(0xcfcfcf);
    static Color white = new Color(0xffffff);

    public static void initHeader() {
        headerPanel = new JPanel();
        headerPanel.setBackground(black);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
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
//        b1.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                int selected = Integer.parseInt(cb.getItemAt(cb.getSelectedIndex()));
//                Stack<Integer> stack = new Stack<>();
//                for (int i = 1; i <= selected; i++) {
//                    stack.push(i);
//                }
//                LinkedList<Integer> cards = new LinkedList<>();
//                for (int i = 0; i < selected; i++) {
//                    if (cards.size() > 0) {
//                        int last = cards.removeLast();
//                        cards.addFirst(last);
//                    }
//                    cards.addFirst(stack.pop());
//                }
//
//                cardBox.removeAll();
//                for (int card : cards) {
//                    BufferedImage img;
//                    try {
//                        img = ImageIO.read(new File("images/card" + card + ".png"));
//                        JLabel imgLabel = new JLabel(new ImageIcon(img.getScaledInstance(150, 90, Image.SCALE_FAST)));
//                        cardBox.add(imgLabel);
//                    } catch (IOException ex) {
//                        System.out.println("image file error: " + ex);
//                    }
//                }
//                cardBox.revalidate();
//            }
//        });

        JButton b2 = new JButton("Add File");

        numCardsPanel.add(numLabel);
        numCardsPanel.add(cb);
        numCardsPanel.add(b1);
        numCardsPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        numCardsPanel.add(b2);
        numCardsPanel.setBackground(black);

        JButton b3 = new JButton("Exit");
        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        headerPanel.add(numCardsPanel);
        headerPanel.add(Box.createHorizontalGlue());
        headerPanel.add(b3);
    }

    public static void initMain() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.LINE_AXIS));
        mainPanel.setBackground(dark);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        mainPanel.setPreferredSize(new Dimension(frame.getWidth(), 600));

        cardBox = Box.createVerticalBox();

        cardBox.setBackground(dark);
        cardPanel = new JPanel();
        cardPanel.setLayout(new BorderLayout());
        cardPanel.add(cardBox, BorderLayout.PAGE_START);
        cardPanel.setBackground(black);


        JScrollPane cardScroll = new JScrollPane(cardPanel);
        cardScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        cardScroll.setMaximumSize(new Dimension(500, 460));
        cardScroll.setPreferredSize(new Dimension(500, 460));
        cardScroll.setBorder(BorderFactory.createMatteBorder(1,1,1,1, border));

        mainPanel.add(cardScroll);

//        JPanel testPanel = new JPanel();
//        testPanel.setPreferredSize(new Dimension(220, 460));
//        testPanel.setBackground(black);
//        mainPanel.add(testPanel);

        mainPanel.add(Box.createRigidArea(new Dimension(15, 0)));

        statsBox = Box.createVerticalBox();
        statsBox.setBackground(dark);
        statsPanel = new JPanel();
        statsPanel.setLayout(new BorderLayout());
        statsPanel.add(statsBox, BorderLayout.PAGE_START);
        statsPanel.setBackground(black);

        JScrollPane statsScroll = new JScrollPane(statsPanel);
        statsScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        statsScroll.setMaximumSize(new Dimension(500, 460));
        statsScroll.setPreferredSize(new Dimension(500, 460));
        statsScroll.setBorder(BorderFactory.createMatteBorder(1,1,1,1, border));


        mainPanel.add(statsScroll);

    }

    public static void main(String[] args) {
        frame = new JFrame("Assignment 5C");
        frame.setPreferredSize(new Dimension(900, 600));
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