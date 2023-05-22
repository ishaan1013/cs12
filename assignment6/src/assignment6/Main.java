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
    static JPanel contentPanel, headerPanel, mainPanel, textPanel, statsPanel;
    static Box textBox, statsBox;

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
        JLabel numLabel = new JLabel("Book");
        numLabel.setForeground(white);
        JComboBox<String> cb = new JComboBox<>(new String[] {"ALICE", "MOBY"});
        JButton b1 = new JButton("Confirm");
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selected = cb.getItemAt(cb.getSelectedIndex());

                textBox.removeAll();

                String line = "";
                try {
                    Scanner inFile = new Scanner (new File (selected + ".txt"));
                    while (inFile.hasNextLine ()) {
                        line = inFile.nextLine();
                        JLabel l = new JLabel(line);
                        l.setForeground(white);
                        textBox.add(l);
                    }
                    inFile.close ();
                }
                catch (FileNotFoundException ex) {
                    System.out.println ("File does not exist");
                }

                textBox.revalidate();
            }
        });

        JButton b2 = new JButton("Add File");

        numCardsPanel.add(numLabel);
        numCardsPanel.add(cb);
        numCardsPanel.add(b1);
        numCardsPanel.add(Box.createRigidArea(new Dimension(5, 0)));
        JLabel orLabel = new JLabel("or");
        orLabel.setForeground(white);
        numCardsPanel.add(orLabel);
        numCardsPanel.add(Box.createRigidArea(new Dimension(5, 0)));
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

        textBox = Box.createVerticalBox();
        textBox.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        textBox.setBackground(dark);
        textPanel = new JPanel();
        textPanel.setLayout(new BorderLayout());
        textPanel.add(textBox, BorderLayout.PAGE_START);
        textPanel.setBackground(black);


        JScrollPane textScroll = new JScrollPane(textPanel);
        textScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        textScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        textScroll.setMaximumSize(new Dimension(500, 460));
        textScroll.setPreferredSize(new Dimension(500, 460));
        textScroll.setBorder(BorderFactory.createMatteBorder(1,1,1,1, border));

        mainPanel.add(textScroll);

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