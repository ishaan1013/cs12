package assignment4;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.io.*;
import javax.swing.*;

public class Driver extends JPanel implements ActionListener {

  Color bg = new Color(25, 25, 25);
  Color dark1 = new Color(38, 38, 38);
  Color dark2 = new Color(55, 55, 55);


  JFrame frame;
  JPanel albumMenuPanel;
  JLabel albumNum, albumDate, albumCapacity, albumCards, albumTotalHp;
  JButton albumAdd, albumRemove, albumStats, exit;

  static ArrayList<Album> albums;

  MenuState state = MenuState.ALBUMS;

  public Driver() {
    frame = new JFrame("Pokemon Cards Collection");
    frame.setPreferredSize(new Dimension(800, 400));

    albumMenuPanel = new JPanel();
    albumMenuPanel.setBackground(bg);
    albumMenuPanel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

    albumMenuPanel.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();

    albumAdd = new JButton("Add");
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 0;
    albumMenuPanel.add(albumAdd, c);

    albumRemove = new JButton("Remove");
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 1;
    albumMenuPanel.add(albumRemove, c);

    JPanel spacer = new JPanel();
    spacer.setBackground(bg);
    c.fill = GridBagConstraints.HORIZONTAL;
    c.weightx = 0.5;
    c.gridx = 2;
    albumMenuPanel.add(spacer, c);

    albumStats = new JButton("Stats");
    c.fill = GridBagConstraints.HORIZONTAL;
    c.weightx = 0;
    c.gridx = 3;
    albumMenuPanel.add(albumStats, c);

    exit = new JButton("Exit");
    c.fill = GridBagConstraints.HORIZONTAL;
    c.weightx = 0;
    c.gridx = 4;
    exit.addActionListener(this);
    exit.setActionCommand("exit");
    albumMenuPanel.add(exit, c);

    JPanel albumsTitlePanel = new JPanel();
    albumsTitlePanel.setBackground(bg);
    albumsTitlePanel.setLayout(new BoxLayout(albumsTitlePanel, BoxLayout.PAGE_AXIS));
    c.fill = GridBagConstraints.HORIZONTAL;
    c.anchor = GridBagConstraints.CENTER;
    c.insets = new Insets(5, 0, 5, 0);
    c.gridx = 0;
    c.gridwidth = 5;
    c.gridy = 1;
    JLabel albumsTitle = new JLabel("All Albums");
    albumsTitle.setFont(new Font("Arial", Font.BOLD, 24));
    albumsTitle.setForeground(Color.WHITE);
    albumsTitlePanel.add(albumsTitle);
    albumsTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
    JLabel albumsSubtitle = new JLabel("Hover for info, click to select.");
    albumsSubtitle.setForeground(Color.WHITE);
    albumsTitlePanel.add(albumsSubtitle);
    albumsSubtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
    albumMenuPanel.add(albumsTitlePanel, c);


    JPanel albumListPanel = new JPanel();
    c.fill = GridBagConstraints.HORIZONTAL;
    c.weightx = 0.0;
    c.gridwidth = 5;
    c.gridx = 0;
    c.gridy = 2;

    albumListPanel.setBackground(dark1);
    albumListPanel.setLayout(new FlowLayout());

    for (int i = 0; i < albums.size(); i++) {
      JButton jb = new JButton(albums.get(i));
      jb.setPreferredSize(new Dimension(40, 40));
      albumListPanel.add(jb);
      final int finalI = i;
      jb.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseEntered(java.awt.event.MouseEvent evt) {
          albumNum.setText("Album #: " + albums.get(finalI));
        }
      });
    }

    albumMenuPanel.add(albumListPanel, c);

    JPanel albumInfoPanel = new JPanel();
    c.fill = GridBagConstraints.HORIZONTAL;
    c.weightx = 0.0;
    c.gridwidth = 5;
    c.gridx = 0;
    c.gridy = 3;

    albumInfoPanel.setBackground(dark2);
    albumInfoPanel.setLayout(new BoxLayout(albumInfoPanel, BoxLayout.PAGE_AXIS));
    albumInfoPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

    albumNum = new JLabel("Album #: ");
    albumDate = new JLabel("Date: ");
    albumCapacity = new JLabel("Capacity: ");
    albumCards = new JLabel("Cards: ");
    albumTotalHp = new JLabel("Total HP: ");

    albumNum.setForeground(Color.WHITE);
    albumDate.setForeground(Color.WHITE);
    albumCapacity.setForeground(Color.WHITE);
    albumCards.setForeground(Color.WHITE);
    albumTotalHp.setForeground(Color.WHITE);

    albumInfoPanel.add(albumNum);
    albumInfoPanel.add(albumDate);
    albumInfoPanel.add(albumCapacity);
    albumInfoPanel.add(albumCards);
    albumInfoPanel.add(albumTotalHp);

    albumMenuPanel.add(albumInfoPanel, c);

    c.weighty = 1;
    // empty panel with vertical weight to fix spacing
    albumMenuPanel.add(new JPanel(), c);


    frame.add(albumMenuPanel);
    frame.pack();
    frame.setVisible(true);

  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    if (state == MenuState.ALBUMS) {

    } else if (state == MenuState.CARDS) {
      g.setColor(Color.WHITE);
      g.drawString("Cards", 50, 50);
    }
  }

  public static void main(String[] args) throws IOException {

    albums = new ArrayList<Album>();
    albums.add(add("input.txt"));

    new Driver();
  }

  public void actionPerformed(ActionEvent event) {

    String eventName = event.getActionCommand();

    if (eventName.equals("exit")) {
      System.exit(0);
    }
  }

  public static Album add(String file) throws IOException {
    BufferedReader inFile = new BufferedReader(new FileReader("input.txt"));

    int albumNum = Integer.parseInt(inFile.readLine());
    Date date = new Date(inFile.readLine());
    int capacity = Integer.parseInt(inFile.readLine());
    int numCards = Integer.parseInt(inFile.readLine());
    ArrayList<Card> cards = new ArrayList<Card>();
    for (int i = 0; i < numCards; i++) {
      String name = inFile.readLine();
      int hp = Integer.parseInt(inFile.readLine());
      String type = inFile.readLine();
      Date cardDate = new Date(inFile.readLine());
      int numAttacks = Integer.parseInt(inFile.readLine());
      ArrayList<Attack> attacks = new ArrayList<Attack>();
      for (int j = 0; j < numAttacks; j++) {
        String attackInfo = inFile.readLine();
        String attackDamage = inFile.readLine();
        attacks.add(new Attack(attackInfo, attackDamage));
      }
      cards.add(new Card(name, hp, type, cardDate, attacks));
    }

    inFile.close();
    return new Album(albumNum, date, capacity, cards);

  }
}
