package assignment4;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import javax.swing.*;

public class Driver extends JPanel implements ActionListener {

  Color bg = new Color(25, 25, 25);
  Color dark1 = new Color(38, 38, 38);
  Color dark2 = new Color(55, 55, 55);

  JFrame frame, popup;

  // screen 1
  JPanel albumMenuPanel, albumListPanel;
  JLabel albumNum, albumDate, albumCapacity, albumCards, albumTotalHp;
  JButton albumAdd, albumStats, exit;

  // screen 2
  JPanel cardsMenuPanel, cardsMenuList, cardsMenuAttacks;
  JLabel albumsTitle, cardName, cardHp, cardType, cardDate, attackName, attackDesc, attackDamage;
  JButton cardAdd, albumRemove, returnToAlbums, cardSort;
  Box cardList, attackList;

  static ArrayList<Album> albums;
  Album selectedAlbum;

  public Driver() {
    frame = new JFrame("Pokemon Cards Collection");
    frame.setPreferredSize(new Dimension(800, 600));
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
    c.weightx = 0.75;
    c.gridx = 2;
    cardsMenuPanel.add(spacer, c);

    JPanel spacer2 = new JPanel();
    spacer2.setBackground(bg);
    c.fill = GridBagConstraints.HORIZONTAL;
    c.weightx = 4;
    c.gridx = 3;
    cardsMenuPanel.add(spacer2, c);

    JPanel spacer3 = new JPanel();
    spacer3.setBackground(bg);
    c.fill = GridBagConstraints.HORIZONTAL;
    c.weightx = 1;
    c.gridx = 4;
    cardsMenuPanel.add(spacer3, c);

    cardAdd = new JButton("Add Card");
    c.fill = GridBagConstraints.HORIZONTAL;
    c.weightx = 0;
    c.gridx = 5;
    cardAdd.addActionListener(this);
    cardAdd.setActionCommand("cardAdd");
    cardsMenuPanel.add(cardAdd, c);

    cardSort = new JButton("Sort Cards");
    c.fill = GridBagConstraints.HORIZONTAL;
    c.weightx = 0;
    c.gridx = 6;
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
    c.gridwidth = 7;
    c.gridy = 1;
    albumsTitle = new JLabel("Selected Album: ");
    albumsTitle.setFont(new Font("Helvetica", Font.BOLD, 24));
    albumsTitle.setForeground(Color.WHITE);
    titlePanel.add(albumsTitle);
    albumsTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
    JLabel albumsSubtitle = new JLabel("Subtitle");
    albumsSubtitle.setForeground(Color.WHITE);
    titlePanel.add(albumsSubtitle);
    albumsSubtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
    cardsMenuPanel.add(titlePanel, c);

    // COLUMN 1: CARDS
    cardsMenuList = new JPanel();
    c.fill = GridBagConstraints.BOTH;
    c.weightx = 0;
    c.gridwidth = 3;
    c.gridx = 0;
    c.gridy = 2;

    cardsMenuList.setBackground(dark1);
    cardsMenuList.setLayout(new BorderLayout());
    cardList = Box.createVerticalBox();
    cardsMenuList.add(cardList, BorderLayout.PAGE_START);

//    JPanel cardItem = new JPanel(new FlowLayout(FlowLayout.LEADING, 5, 0));
//    JLabel cardName = new JLabel("Card Name");
//    cardName.setForeground(Color.WHITE);
//    JButton cardSelect = new JButton("→");
//    cardSelect.setMargin(new Insets(2, 3, 2, 3));
//    cardSelect.addActionListener(this);
//    cardSelect.setActionCommand("cardSelect");
//    cardItem.add(cardName);
//    cardItem.add(cardSelect);
//    cardItem.setBorder(BorderFactory.createEmptyBorder(0, 0, 8, 0));
//    cardItem.setBackground(dark1);
//
//    cardList.add(cardItem);

    cardsMenuList.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));
    int cardsMenuListW = cardsMenuList.getWidth();
    JScrollPane cardsMenuListScroll = new JScrollPane(cardsMenuList);
    cardsMenuListScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    cardsMenuListScroll.setMaximumSize(new Dimension(cardsMenuListW, 300));
    cardsMenuListScroll.setPreferredSize(new Dimension(cardsMenuListW, 300));
    cardsMenuListScroll.setBorder(BorderFactory.createEmptyBorder());

    cardsMenuPanel.add(cardsMenuListScroll, c);


    // COLUMN 2: ATTACKS
    cardsMenuAttacks = new JPanel();
    cardsMenuAttacks.setBackground(dark2);
    cardsMenuAttacks.setLayout(new BorderLayout());
    attackList = Box.createVerticalBox();
    cardsMenuAttacks.add(attackList, BorderLayout.PAGE_START);

//    JPanel attackItem = new JPanel(new FlowLayout(FlowLayout.LEADING, 5, 0));
//    JLabel attackName = new JLabel("Attack Name");
//    attackName.setForeground(Color.WHITE);
//    JButton attackEdit = new JButton("Edit");
//    attackEdit.setMargin(new Insets(2, 3, 2, 3));
//    attackEdit.addActionListener(this);
//    attackEdit.setActionCommand("attackEdit");
//    JButton attackSelect = new JButton("→");
//    attackSelect.setMargin(new Insets(2, 3, 2, 3));
//    attackSelect.addActionListener(this);
//    attackSelect.setActionCommand("attackSelect");
//
//    attackItem.add(attackName);
//    attackItem.add(attackEdit);
//    attackItem.add(attackSelect);
//    attackItem.setBorder(BorderFactory.createEmptyBorder(0, 0, 8, 0));
//    attackItem.setBackground(dark2);
//
//    attackList.add(attackItem);

    c.weightx = 0;
    c.gridwidth = 1;
    c.gridx = 3;

    cardsMenuAttacks.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));
    int cardsMenuAttacksW = cardsMenuAttacks.getWidth();
    JScrollPane cardsMenuAttacksScroll = new JScrollPane(cardsMenuAttacks);
    cardsMenuAttacksScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    cardsMenuAttacksScroll.setMaximumSize(new Dimension(cardsMenuAttacksW, 300));
    cardsMenuAttacksScroll.setPreferredSize(new Dimension(cardsMenuAttacksW, 300));
    cardsMenuAttacksScroll.setBorder(BorderFactory.createEmptyBorder());

    cardsMenuPanel.add(cardsMenuAttacksScroll, c);

    // COLUMN 3: INFO
    JPanel cardsMenuInfo = new JPanel();
    cardsMenuInfo.setBackground(dark1);
    cardsMenuInfo.setLayout(new BoxLayout(cardsMenuInfo, BoxLayout.PAGE_AXIS));
    cardName = new JLabel("Card: ");
    cardHp = new JLabel("Hp: ");
    cardType = new JLabel("Type: ");
    cardDate = new JLabel("Date: ");
    attackName = new JLabel("Name: ");
    attackDesc = new JLabel("Description: ");
    attackDamage = new JLabel("Damage: ");

    cardName.setFont(new Font("Helvetica", Font.PLAIN, 16));
    cardHp.setFont(new Font("Helvetica", Font.PLAIN, 16));
    cardType.setFont(new Font("Helvetica", Font.PLAIN, 16));
    cardDate.setFont(new Font("Helvetica", Font.PLAIN, 16));
    attackName.setFont(new Font("Helvetica", Font.PLAIN, 16));
    attackDesc.setFont(new Font("Helvetica", Font.PLAIN, 16));
    attackDamage.setFont(new Font("Helvetica", Font.PLAIN, 16));

    cardName.setForeground(Color.WHITE);
    cardHp.setForeground(Color.WHITE);
    cardType.setForeground(Color.WHITE);
    cardDate.setForeground(Color.WHITE);
    attackName.setForeground(Color.WHITE);
    attackDesc.setForeground(Color.WHITE);
    attackDamage.setForeground(Color.WHITE);
    c.weightx = 0;
    c.gridwidth = 3;
    c.gridx = 4;

    JLabel cardLabel = new JLabel("Selected Card:");
    cardLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 6, 0));
    cardLabel.setFont(new Font("Helvetica", Font.BOLD, 16));
    cardLabel.setForeground(Color.WHITE);

    cardsMenuInfo.add(cardLabel);
    cardsMenuInfo.add(cardName);
    cardsMenuInfo.add(cardHp);
    cardsMenuInfo.add(cardType);
    cardsMenuInfo.add(cardDate);

    JLabel attackLabel = new JLabel("Selected Attack:");
    attackLabel.setBorder(BorderFactory.createEmptyBorder(16, 0, 6, 0));
    attackLabel.setFont(new Font("Helvetica", Font.BOLD, 16));
    attackLabel.setForeground(Color.WHITE);

    cardsMenuInfo.add(attackLabel);
    cardsMenuInfo.add(attackName);
    cardsMenuInfo.add(attackDesc);
    cardsMenuInfo.add(attackDamage);

    cardsMenuInfo.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));
    int cardsMenuInfoW = cardsMenuInfo.getWidth();
    JScrollPane cardsMenuInfoScroll = new JScrollPane(cardsMenuInfo);
    cardsMenuInfoScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    cardsMenuInfoScroll.setMaximumSize(new Dimension(cardsMenuInfoW, 300));
    cardsMenuInfoScroll.setPreferredSize(new Dimension(cardsMenuInfoW, 300));
    cardsMenuInfoScroll.setBorder(BorderFactory.createEmptyBorder());

    cardsMenuPanel.add(cardsMenuInfoScroll, c);

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

      for (int i = 0; i < selectedAlbum.getCards().size(); i++) {
        JPanel cardItem = new JPanel(new FlowLayout(FlowLayout.LEADING, 5, 0));
        JLabel cardName = new JLabel(selectedAlbum.getCards().get(i).getName());
        cardName.setForeground(Color.WHITE);
        JButton cardSelect = new JButton("→");
        cardSelect.setMargin(new Insets(2, 3, 2, 3));
        cardSelect.addActionListener(this);
        cardSelect.setActionCommand("cardSelect" + i);
        cardItem.add(cardName);
        cardItem.add(cardSelect);
        cardItem.setBorder(BorderFactory.createEmptyBorder(0, 0, 8, 0));
        cardItem.setBackground(dark1);

        cardList.add(cardItem);
      }
      cardList.revalidate();
      cardsMenuList.revalidate();
    } else if (eventName.startsWith("cardSelect")) {
      int index = Integer.parseInt(eventName.substring(10));
      Card selectedCard = selectedAlbum.getCards().get(index);
      cardName.setText("Card: " + selectedCard.getName());
      cardHp.setText("Hp: " + selectedCard.getHp());
      cardType.setText("Type: " + selectedCard.getType());
      cardDate.setText("Date: " + selectedCard.getDate().toString());

      for (int i = 0; i < selectedCard.getAttacks().size(); i++) {
        System.out.println("Attack: " + selectedCard.getAttacks().get(i).getName());
        JPanel attackItem = new JPanel(new FlowLayout(FlowLayout.LEADING, 5, 0));
        JLabel attackName = new JLabel(selectedCard.getAttacks().get(i).getName());
        attackName.setForeground(Color.WHITE);
        JButton attackEdit = new JButton("Edit");
        attackEdit.setMargin(new Insets(2, 3, 2, 3));
        attackEdit.addActionListener(this);
        attackEdit.setActionCommand("attackEdit" + i);
        JButton attackSelect = new JButton("→");
        attackSelect.setMargin(new Insets(2, 3, 2, 3));
        attackSelect.addActionListener(this);
        attackSelect.setActionCommand("attackSelect" + i);

        attackItem.add(attackName);
        attackItem.add(attackEdit);
        attackItem.add(attackSelect);
        attackItem.setBorder(BorderFactory.createEmptyBorder(0, 0, 8, 0));
        attackItem.setBackground(dark2);

        attackList.add(attackItem);
      }
      attackList.revalidate();
      cardsMenuAttacks.revalidate();

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
