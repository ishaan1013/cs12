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

  JFrame frame, popup;
  JPanel albumMenuPanel, albumListPanel;
  JLabel albumNum, albumDate, albumCapacity, albumCards, albumTotalHp;
  JButton albumAdd, albumStats, exit;

  JPanel cardsMenuPanel;
  JButton cardAdd, albumRemove, returnToAlbums, cardSort;

  static ArrayList<Album> albums;

  MenuState state = MenuState.ALBUMS;

  public Driver() {
    frame = new JFrame("Pokemon Cards Collection");
    frame.setPreferredSize(new Dimension(800, 400));
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    initAlbumMenuPanel();
    initCardsMenuPanel();

    frame.setContentPane(albumMenuPanel);
    frame.pack();
    frame.setVisible(true);

  }

  public void initAlbumMenuPanel() {
    albumMenuPanel = new JPanel();
    albumMenuPanel.setBackground(bg);
    albumMenuPanel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

    albumMenuPanel.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();

    albumAdd = new JButton("Add");
    albumAdd.addActionListener(this);
    albumAdd.setActionCommand("albumAdd");
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 0;
    albumMenuPanel.add(albumAdd, c);

    JPanel spacer = new JPanel();
    spacer.setBackground(bg);
    c.fill = GridBagConstraints.HORIZONTAL;
    c.weightx = 1;
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


    albumListPanel = new JPanel();
    c.fill = GridBagConstraints.HORIZONTAL;
    c.weightx = 0.0;
    c.gridwidth = 5;
    c.gridx = 0;
    c.gridy = 2;

    albumListPanel.setBackground(dark1);
    albumListPanel.setLayout(new FlowLayout());
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
    albumCards = new JLabel("# Cards: ");
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
    albumMenuPanel.add(new JPanel(), c);
  }

  public void initCardsMenuPanel() {
    cardsMenuPanel = new JPanel();
    cardsMenuPanel.setBackground(bg);
    cardsMenuPanel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

    cardsMenuPanel.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();

    returnToAlbums = new JButton("< Back");
    returnToAlbums.addActionListener(this);
    returnToAlbums.setActionCommand("returnToAlbums");
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 0;
    cardsMenuPanel.add(returnToAlbums, c);

    albumRemove = new JButton("Delete Album");
    albumRemove.addActionListener(this);
    albumRemove.setActionCommand("albumRemove");
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 1;
    cardsMenuPanel.add(albumRemove, c);

    JPanel spacer = new JPanel();
    spacer.setBackground(bg);
    c.fill = GridBagConstraints.HORIZONTAL;
    c.weightx = 1;
    c.gridx = 2;
    cardsMenuPanel.add(spacer, c);

    cardAdd = new JButton("Add Card");
    c.fill = GridBagConstraints.HORIZONTAL;
    c.weightx = 0;
    c.gridx = 3;
    cardAdd.addActionListener(this);
    cardAdd.setActionCommand("cardAdd");
    cardsMenuPanel.add(cardAdd, c);

    cardSort = new JButton("Sort Cards");
    c.fill = GridBagConstraints.HORIZONTAL;
    c.weightx = 0;
    c.gridx = 4;
    exit.addActionListener(this);
    exit.setActionCommand("cardSort");
    cardsMenuPanel.add(cardSort, c);

//    JPanel albumsTitlePanel = new JPanel();
//    albumsTitlePanel.setBackground(bg);
//    albumsTitlePanel.setLayout(new BoxLayout(albumsTitlePanel, BoxLayout.PAGE_AXIS));
//    c.fill = GridBagConstraints.HORIZONTAL;
//    c.anchor = GridBagConstraints.CENTER;
//    c.insets = new Insets(5, 0, 5, 0);
//    c.gridx = 0;
//    c.gridwidth = 5;
//    c.gridy = 1;
//    JLabel albumsTitle = new JLabel("All Albums");
//    albumsTitle.setFont(new Font("Arial", Font.BOLD, 24));
//    albumsTitle.setForeground(Color.WHITE);
//    albumsTitlePanel.add(albumsTitle);
//    albumsTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
//    JLabel albumsSubtitle = new JLabel("Hover for info, click to select.");
//    albumsSubtitle.setForeground(Color.WHITE);
//    albumsTitlePanel.add(albumsSubtitle);
//    albumsSubtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
//    cardsMenuPanel.add(albumsTitlePanel, c);
//
//
//    albumListPanel = new JPanel();
//    c.fill = GridBagConstraints.HORIZONTAL;
//    c.weightx = 0.0;
//    c.gridwidth = 5;
//    c.gridx = 0;
//    c.gridy = 2;
//
//    albumListPanel.setBackground(dark1);
//    albumListPanel.setLayout(new FlowLayout());
//    cardsMenuPanel.add(albumListPanel, c);
//
//    JPanel albumInfoPanel = new JPanel();
//    c.fill = GridBagConstraints.HORIZONTAL;
//    c.weightx = 0.0;
//    c.gridwidth = 5;
//    c.gridx = 0;
//    c.gridy = 3;
//
//    albumInfoPanel.setBackground(dark2);
//    albumInfoPanel.setLayout(new BoxLayout(albumInfoPanel, BoxLayout.PAGE_AXIS));
//    albumInfoPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
//
//    albumNum = new JLabel("Album #: ");
//    albumDate = new JLabel("Date: ");
//    albumCapacity = new JLabel("Capacity: ");
//    albumCards = new JLabel("# Cards: ");
//    albumTotalHp = new JLabel("Total HP: ");
//
//    albumNum.setForeground(Color.WHITE);
//    albumDate.setForeground(Color.WHITE);
//    albumCapacity.setForeground(Color.WHITE);
//    albumCards.setForeground(Color.WHITE);
//    albumTotalHp.setForeground(Color.WHITE);
//
//    albumInfoPanel.add(albumNum);
//    albumInfoPanel.add(albumDate);
//    albumInfoPanel.add(albumCapacity);
//    albumInfoPanel.add(albumCards);
//    albumInfoPanel.add(albumTotalHp);
//
//    cardsMenuPanel.add(albumInfoPanel, c);
//
//    c.weighty = 1;
//    cardsMenuPanel.add(new JPanel(), c);
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);

  }

  public static void main(String[] args) {

    albums = new ArrayList<Album>();

    new Driver();
  }

  public void actionPerformed(ActionEvent event) {

    String eventName = event.getActionCommand();

    if (eventName.equals("exit")) {
      System.exit(0);
    } else if (eventName.equals("albumAdd")) {
      popup = new JFrame();
      String fileName = JOptionPane.showInputDialog(popup, "Input file name: ");
      try {
        int i = albums.size();
        Album added = add(fileName);
        if (added != null) {
          albums.add(added);
        }


        if (albums.size() > i) {
          System.out.println("i: " + i);
          System.out.println("albums.size(): " + albums.size());
          JButton jb = new JButton(albums.get(i).getNum() + "");
          jb.setPreferredSize(new Dimension(40, 40));
          albumListPanel.add(jb);
          final int labelNum = albums.get(i).getNum();
          final String labelDate = albums.get(i).getDate().toString();
          final int labelCapacity = albums.get(i).getCapacity();
          final int labelCards = albums.size();
          final int labelHp = albums.get(i).getHp();
          jb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
              albumNum.setText("Album #: " + labelNum);
              albumDate.setText("Date: " + labelDate);
              albumCapacity.setText("Capacity: " + labelCapacity);
              albumCards.setText("# Cards: " + labelCards);
              albumTotalHp.setText("Total HP: " + labelHp);
            }
          });

          albumListPanel.revalidate();
        }

      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  public Album add(String file) throws IOException {

    if (file == null) {
      return null;
    }
    if (file.isEmpty()) {
      return null;
    }
    try {
      BufferedReader inFile = new BufferedReader(new FileReader(file));

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
    } catch (FileNotFoundException e) {
      popup = new JFrame();
      JOptionPane.showMessageDialog(popup, "File not found.");
    }

    return null;

  }
}
