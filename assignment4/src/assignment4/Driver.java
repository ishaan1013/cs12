package assignment4;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.io.*;
import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class Driver extends JPanel implements ActionListener {

  Color bg = new Color(25, 25, 25);
  Color dark1 = new Color(38, 38, 38);
  Color dark2 = new Color(55, 55, 55);

  JFrame frame, popup;

  // screen 2
  JPanel albumMenuPanel, albumListPanel;
  JLabel albumNum, albumDate, albumCapacity, albumCards, albumTotalHp;
  JButton albumAdd, albumStats, exit;

  // screen 2
  JPanel cardsMenuPanel, cardsMenuList;
  JButton cardAdd, albumRemove, returnToAlbums, cardSort;
  JLabel albumsTitle;

  static ArrayList<Album> albums;
  Album selectedAlbum;

  public Driver() {
    frame = new JFrame("Pokemon Cards Collection");
    frame.setPreferredSize(new Dimension(800, 600));
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    initAlbumMenuPanel();
    initCardsMenuPanel();

    frame.setContentPane(cardsMenuPanel);
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

    JPanel titlePanel = new JPanel();
    titlePanel.setBackground(bg);
    titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.PAGE_AXIS));
    c.fill = GridBagConstraints.HORIZONTAL;
    c.anchor = GridBagConstraints.CENTER;
    c.insets = new Insets(15, 0, 15, 0);
    c.gridx = 0;
    c.gridwidth = 5;
    c.gridy = 1;
    JLabel albumsTitle = new JLabel("All Albums");
    albumsTitle.setFont(new Font("Helvetica", Font.BOLD, 24));
    albumsTitle.setForeground(Color.WHITE);
    titlePanel.add(albumsTitle);
    albumsTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
    JLabel albumsSubtitle = new JLabel("Hover for info, click to select.");
    albumsSubtitle.setForeground(Color.WHITE);
    titlePanel.add(albumsSubtitle);
    albumsSubtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
    albumMenuPanel.add(titlePanel, c);


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

    albumNum.setFont(new Font("Helvetica", Font.PLAIN, 16));
    albumDate.setFont(new Font("Helvetica", Font.PLAIN, 16));
    albumCapacity.setFont(new Font("Helvetica", Font.PLAIN, 16));
    albumCards.setFont(new Font("Helvetica", Font.PLAIN, 16));
    albumTotalHp.setFont(new Font("Helvetica", Font.PLAIN, 16));

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
    c.gridy = 4;
    JPanel bottomFiller = new JPanel();
    bottomFiller.setBackground(bg);
    albumMenuPanel.add(bottomFiller, c);
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
    c.weightx = 2;
    c.gridx = 2;
    cardsMenuPanel.add(spacer, c);

    JPanel spacer2 = new JPanel();
    spacer2.setBackground(bg);
    c.fill = GridBagConstraints.HORIZONTAL;
    c.weightx = 1;
    c.gridx = 3;
    cardsMenuPanel.add(spacer2, c);

    cardAdd = new JButton("Add Card");
    c.fill = GridBagConstraints.HORIZONTAL;
    c.weightx = 0;
    c.gridx = 4;
    cardAdd.addActionListener(this);
    cardAdd.setActionCommand("cardAdd");
    cardsMenuPanel.add(cardAdd, c);

    cardSort = new JButton("Sort Cards");
    c.fill = GridBagConstraints.HORIZONTAL;
    c.weightx = 0;
    c.gridx = 5;
    exit.addActionListener(this);
    exit.setActionCommand("cardSort");
    cardsMenuPanel.add(cardSort, c);

    JPanel titlePanel = new JPanel();
    titlePanel.setBackground(bg);
    titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.PAGE_AXIS));
    c.fill = GridBagConstraints.HORIZONTAL;
    c.anchor = GridBagConstraints.CENTER;
    c.insets = new Insets(15, 0, 15, 0);
    c.gridx = 0;
    c.gridwidth = 6;
    c.gridy = 1;
    albumsTitle = new JLabel("Selected Album: ");
    albumsTitle.setFont(new Font("Helvetica", Font.BOLD, 24));
    albumsTitle.setForeground(Color.WHITE);
    titlePanel.add(albumsTitle);
    albumsTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
    JLabel albumsSubtitle = new JLabel("Hover for info, click to select.");
    albumsSubtitle.setForeground(Color.WHITE);
    titlePanel.add(albumsSubtitle);
    albumsSubtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
    cardsMenuPanel.add(titlePanel, c);


    cardsMenuList = new JPanel();
    c.fill = GridBagConstraints.BOTH;
    c.weightx = 0;
    c.gridwidth = 2;
    c.gridx = 0;
    c.gridy = 2;

    cardsMenuList.setBackground(dark1);
    cardsMenuList.setLayout(new FlowLayout());
    cardsMenuPanel.add(cardsMenuList, c);

    JPanel test1 = new JPanel();
    test1.setBackground(dark2);
    test1.setLayout(new BoxLayout(test1, BoxLayout.PAGE_AXIS));
    test1.add(new JLabel("Test"));
    test1.add(new JLabel("Test"));
    test1.add(new JLabel("Test"));
    test1.add(new JLabel("Test"));
    test1.add(new JLabel("Test"));
    test1.add(new JLabel("Test"));
    test1.add(new JLabel("Test"));
    test1.add(new JLabel("Test"));
    test1.add(new JLabel("Test"));
    test1.add(new JLabel("Test"));
    test1.add(new JLabel("Test"));
    test1.add(new JLabel("Test"));
    test1.add(new JLabel("Test"));
    test1.add(new JLabel("Test"));
    test1.add(new JLabel("Test"));
    test1.add(new JLabel("Test"));
    test1.add(new JLabel("Test"));
    test1.add(new JLabel("Test"));
    test1.add(new JLabel("Test"));
    test1.add(new JLabel("Test"));
    test1.add(new JLabel("Test"));
    test1.add(new JLabel("Test"));
    test1.add(new JLabel("Test"));
    test1.add(new JLabel("Test"));
    test1.add(new JLabel("Test"));
    test1.add(new JLabel("Test"));
    test1.add(new JLabel("Test"));
    test1.add(new JLabel("Test"));
    test1.add(new JLabel("Test"));
    test1.add(new JLabel("Test"));
    test1.add(new JLabel("Test"));
    test1.add(new JLabel("Test"));
    test1.add(new JLabel("Test"));
    c.weightx = 0;
    c.gridwidth = 1;
    c.gridx = 2;

    test1.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));
    int test1W = test1.getWidth();
    JScrollPane test1Scroll = new JScrollPane(test1);
    test1Scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    test1Scroll.setMaximumSize(new Dimension(test1W, 300));
    test1Scroll.setPreferredSize(new Dimension(test1W, 300));
    test1Scroll.setBorder(BorderFactory.createEmptyBorder());

    cardsMenuPanel.add(test1Scroll, c);

    test1Scroll.getVerticalScrollBar().setBackground(Color.BLACK);
    test1Scroll.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
      @Override
      protected void configureScrollBarColors() {
        this.thumbColor = Color.BLUE;
      }
    });

    JPanel test2 = new JPanel();
    test2.setBackground(dark1);
    test2.setLayout(new BoxLayout(test2, BoxLayout.PAGE_AXIS));
    test2.add(new JLabel("Test"));
    test2.add(new JLabel("Test"));
    test2.add(new JLabel("Test"));
    c.weightx = 0;
    c.gridwidth = 3;
    c.gridx = 3;

    test2.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));
    int test2W = test2.getWidth();
    JScrollPane test2Scroll = new JScrollPane(test2);
    test2Scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    test2Scroll.setMaximumSize(new Dimension(test2W, 300));
    test2Scroll.setPreferredSize(new Dimension(test2W, 300));
    test2Scroll.setBorder(BorderFactory.createEmptyBorder());

    cardsMenuPanel.add(test2Scroll, c);
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
          selectedAlbum = albums.get(i);
          JButton jb = new JButton(albums.get(i).getNum() + "");
          jb.setPreferredSize(new Dimension(40, 40));
          albumListPanel.add(jb);
          jb.addActionListener(this);
          jb.setActionCommand("select" + albums.get(i).getNum());
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
    } else if (eventName.equals("returnToAlbums")) {
      cardsMenuPanel.setVisible(false);
      albumMenuPanel.setVisible(true);
      frame.setContentPane(albumMenuPanel);
    } else if (eventName.startsWith("select")) {
      albumMenuPanel.setVisible(false);
      cardsMenuPanel.setVisible(true);
      frame.setContentPane(cardsMenuPanel);

      albumsTitle.setText("Selected Album: " + selectedAlbum.getNum());
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
