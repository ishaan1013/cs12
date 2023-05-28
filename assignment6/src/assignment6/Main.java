// Ishaan Dey
// May 25, 2023
// Word frequency counter

package assignment6;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import javax.swing.*;

public class Main {
  static JFrame frame;
  static JPanel contentPanel, headerPanel, mainPanel, textPanel, statsPanel;
  static Box textBox, statsBox;

  static JTextArea textContent, statsContent;
  static JLabel statsTime;

  static Color black = new Color(0x000000);
  static Color dark = new Color(0x111111);
  static Color border = new Color(0x232323);
  static Color light = new Color(0xcfcfcf);
  static Color white = new Color(0xffffff);

  static ArrayList<String> items = new ArrayList<>();

  static HashMap<String, String> contractions = new HashMap<>();
  static HashMap<Word, Integer> words = new HashMap<>();

  // initialize the header graphics
  // no params
  // void
  public static void initHeader() {
    headerPanel = new JPanel();
    headerPanel.setBackground(black);
    headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
    headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.LINE_AXIS));

    JPanel numCardsPanel = new JPanel();
    numCardsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
    JLabel numLabel = new JLabel("Book");
    numLabel.setForeground(white);

    items.add("ALICE");
    items.add("MOBY");
    JComboBox<String> cb = new JComboBox<>(new String[]{"ALICE", "MOBY"});
    JButton b1 = new JButton("Select");
    b1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String selected = cb.getItemAt(cb.getSelectedIndex());

//        textBox.removeAll();
//        statsBox.removeAll();

        String line = "";
        try {
          // read & process file contents
          words.clear();
          BufferedReader inFile = new BufferedReader(new FileReader(selected + ".txt"));

          textContent.setText("");
          textContent.read(inFile, null);

          // time it takes to process all the words
          long startTime = System.currentTimeMillis();

          inFile = new BufferedReader(new FileReader(selected + ".txt"));
          while ((line = inFile.readLine()) != null) {
            processLine(line);
          }

          long timeDiff = System.currentTimeMillis() - startTime;
          statsTime.setText(timeDiff + "ms");

          int pos = 1;
          statsContent.setText("");

          // put word hashmap into a treemap (it's not just a treemap in the first place because of an issue i had w/ duplicate keys or something)
          Map<Word, Integer> sortedWords = new TreeMap<>(words);

          for (Map.Entry<Word, Integer> entry : sortedWords.entrySet()) {
            statsContent.append(pos + ". " + entry.getKey().toString().toLowerCase() + ": " + entry.getValue() + "\n");

            pos++;
            if (pos >= 21) break;
          }

          inFile.close();
        } catch (FileNotFoundException ex) {
          System.out.println("File does not exist");
        } catch (IOException ex) {
          ex.printStackTrace();
        }

//        textBox.add(textContent);
        textBox.revalidate();

//        statsBox.add(statsContent);
        statsBox.revalidate();

      }
    });

    JButton b2 = new JButton("Add File");
    b2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String m = JOptionPane.showInputDialog("Enter file name (without .txt)");

        if (m == null || m.isEmpty()) {
          JOptionPane.showMessageDialog(null, "Input is empty.",
              "Invalid", JOptionPane.ERROR_MESSAGE);
          return;
        }

        if (m.endsWith(".txt")) {
          JOptionPane.showMessageDialog(null, "Do not include the file extension!",
              "Invalid", JOptionPane.ERROR_MESSAGE);
          return;
        }

        if (items.stream().anyMatch(m::equalsIgnoreCase)) {
          JOptionPane.showMessageDialog(null, "File already added.",
              "Invalid", JOptionPane.ERROR_MESSAGE);
          return;
        }

        File f = new File(m + ".txt");
        if (f.isFile()) {
          JOptionPane.showMessageDialog(null, "File added.",
              "Success", JOptionPane.INFORMATION_MESSAGE);
          cb.addItem(m);
          items.add(m);
        } else {
          JOptionPane.showMessageDialog(null, "File does not exist.",
              "Invalid", JOptionPane.ERROR_MESSAGE);
        }
      }
    });

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

  // initialize the main display graphics (the file contents + statistics)
  // no params
  // void
  public static void initMain() {
    mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.LINE_AXIS));
    mainPanel.setBackground(dark);
    mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
    mainPanel.setPreferredSize(new Dimension(frame.getWidth(), 600));

    textBox = Box.createVerticalBox();
    textBox.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
    textBox.setBackground(dark);

    textContent = new JTextArea();
    textContent.setEditable(false);
    textContent.setLineWrap(false);
    textContent.setWrapStyleWord(false);
    textContent.setForeground(light);
    textContent.setBackground(black);

    textBox.add(textContent);

    textPanel = new JPanel();
    textPanel.setLayout(new BorderLayout());
    textPanel.add(textBox, BorderLayout.PAGE_START);
    textPanel.setBackground(black);


    // put both parts of this section in a scroll container
    JScrollPane textScroll = new JScrollPane(textPanel);
    textScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    textScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    textScroll.setMaximumSize(new Dimension(500, 460));
    textScroll.setPreferredSize(new Dimension(500, 460));
    textScroll.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, border));

    mainPanel.add(textScroll);

    mainPanel.add(Box.createRigidArea(new Dimension(15, 0)));

    statsBox = Box.createVerticalBox();
    statsBox.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
    statsBox.setBackground(dark);

    statsTime = new JLabel();
    statsTime.setForeground(light);
    statsBox.add(statsTime);

    statsContent = new JTextArea();
    statsContent.setEditable(false);
    statsContent.setLineWrap(false);
    statsContent.setWrapStyleWord(false);
    statsContent.setForeground(light);
    statsContent.setBackground(black);

    textBox.add(Box.createRigidArea(new Dimension(0, 10)));
    statsBox.add(statsContent);

    statsPanel = new JPanel();
    statsPanel.setLayout(new BorderLayout());
    statsPanel.add(statsBox, BorderLayout.PAGE_START);
    statsPanel.setBackground(black);

    // other scroll container
    JScrollPane statsScroll = new JScrollPane(statsPanel);
    statsScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    statsScroll.setMaximumSize(new Dimension(500, 460));
    statsScroll.setPreferredSize(new Dimension(500, 460));
    statsScroll.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, border));

    mainPanel.add(statsScroll);
  }

  // takes in a line of a file being read, then processes it to get the individual words, and adds the frequency accordingly
  // parameter is the line to be processed
  // no return, just updates the map directly
  public static void processLine(String line) {
    StringTokenizer st = new StringTokenizer(line, " ()[]{};:\\\"|/?.,<>!@#$%^&*_-+=~`1234567890", false);

    while (st.hasMoreTokens()) {
      String w = st.nextToken();

      // ignore single apostrophe
      if (w.equals("'")) continue;

      // deal with quotes & possessive
      while (w.charAt(0) == '\'' || w.charAt(w.length() - 1) == '\'') {
        if (w.length() > 1 && (w.charAt(0) == '\'' || w.charAt(0) == '\"')) {
//          System.out.println("converting " + w + " to " + w.substring(1));
          w = w.substring(1);
        }
        if (w.length() > 1 && w.charAt(w.length() - 1) == '\'') {
//          System.out.println("converting " + w + " to " + w.substring(0, w.length() - 1));
          w = w.substring(0, w.length() - 1);
        }
        if (!contractions.containsKey(w)) {
          if (w.length() > 1 && w.charAt(w.length() - 2) == '\'' && w.charAt(w.length() - 1) == 's') {
//            System.out.println("converting " + w + " to " + w.substring(0, w.length() - 2));
            w = w.substring(0, w.length() - 2);
          }
        }
      }

      // separate string tokenizer for contractions since it's 2 words
      if (contractions.containsKey(w)) {
        w = contractions.get(w);
        StringTokenizer st2 = new StringTokenizer(w, " ", false);
        while (st2.hasMoreTokens()) {
          String w2 = st2.nextToken();
          Word word = new Word(w2, 1);
          if (words.containsKey(word)) {
            int newCount = words.remove(word) + 1;
            word.setCount(newCount);
            words.put(word, newCount);
          } else {
            words.put(word, 1);
          }
        }
      } else {
        Word word = new Word(w, 1);

        if (words.containsKey(word)) {
          int newCount = words.remove(word) + 1;
          word.setCount(newCount);
          words.put(word, newCount);
        } else {
          words.put(word, 1);
        }
      }
    }
  }

  // method to initialize the map of contractions
  // no params
  // void (updates global variable)
  public static void addContractions() {
    contractions.put("should've", "should have");
    contractions.put("shouldn't", "should not");
    contractions.put("could've", "could have");
    contractions.put("there'll", "there will");
    contractions.put("would've", "would have");
    contractions.put("couldn't", "could not");
    contractions.put("there're", "there are");
    contractions.put("these're", "these are");
    contractions.put("those're", "those are");
    contractions.put("wouldn't", "would not");
    contractions.put("that'll", "that will");
    contractions.put("they'll", "they will");
    contractions.put("they've", "they have");
    contractions.put("doesn't", "does not");
    contractions.put("haven't", "have not");
    contractions.put("there's", "there is");
    contractions.put("they're", "they are");
    contractions.put("weren't", "were not");
    contractions.put("she'll", "she will");
    contractions.put("you'll", "you will");
    contractions.put("aren't", "are not");
    contractions.put("didn't", "did not");
    contractions.put("hadn't", "had not");
    contractions.put("hasn't", "has not");
    contractions.put("wasn't", "was not");
    contractions.put("you're", "you are");
    contractions.put("you've", "you have");
    contractions.put("won't", "will not");
    contractions.put("he'll", "he will");
    contractions.put("it'll", "it will");
    contractions.put("we'll", "we will");
    contractions.put("we've", "we have");
    contractions.put("can't", "cannot");
    contractions.put("don't", "do not");
    contractions.put("isn't", "is not");
    contractions.put("she's", "she is");
    contractions.put("we're", "we are");
    contractions.put("I'll", "I will");
    contractions.put("I've", "I have");
    contractions.put("he's", "he is");
    contractions.put("it's", "it is");
    contractions.put("I'm", "I am");
  }

  public static void main(String[] args) {

    frame = new JFrame("Assignment 6");
    frame.setPreferredSize(new Dimension(900, 600));
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // call all initializing methods
    initHeader();
    initMain();
    addContractions();

    contentPanel = new JPanel();
    contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.PAGE_AXIS));
    contentPanel.add(headerPanel);
    contentPanel.add(mainPanel);

    frame.setContentPane(contentPanel);
    frame.pack();
    frame.setVisible(true);

  }
}