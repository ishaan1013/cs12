// Ishaan Dey
// April 14. 2023
// Assignment 4 Part 2 (with graphics)

package assignment4;

import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Array;
import java.util.*;
import java.io.*;
import javax.swing.*;

public class Driver extends JPanel implements ActionListener {

  Color bg = new Color(25, 25, 25);
  Color dark1 = new Color(38, 38, 38);
  Color dark2 = new Color(55, 55, 55);

  JFrame frame, popup;
  HashSet<Integer> ids = new HashSet<Integer>();

  // screen 1
  JPanel albumMenuPanel, albumListPanel;
  JLabel albumNum, albumDate, albumCapacity, albumCards, albumTotalHp;
  JButton albumAdd, albumStats, exit;

  // screen 2
  JPanel cardsMenuPanel, cardsMenuList, cardsMenuAttacks;
  JLabel albumsTitle, cardName, cardHp, cardType, cardDate, attackName, attackDesc, attackDamage;
  JButton cardAdd, albumRemove, returnToAlbums, cardSort;
  Box cardList, attackList;

  static ArrayList<Album> albums = new ArrayList<Album>();
  static ArrayList<JButton> albumButtons = new ArrayList<JButton>();
  Album selectedAlbum;
  Card selectedCard;

  // driver constructor to initialize the frame & menus
  // no params
  // no return
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

  // initializes the album menu panel with the layout etc. (global variables)
  // no params
  // void
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
    albumStats.addActionListener(this);
    albumStats.setActionCommand("stats");
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

    JLabel hoveredLabel = new JLabel("Hovered Album:");
    hoveredLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 6, 0));
    hoveredLabel.setFont(new Font("Helvetica", Font.BOLD, 16));
    hoveredLabel.setForeground(Color.WHITE);

    albumInfoPanel.add(hoveredLabel);
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

  // initializes the cards menu panel with the layout etc. (global variables) (inside an album)
  // no params
  // void
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
    cardSort.addActionListener(this);
    cardSort.setActionCommand("cardSort");
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
    JLabel albumsSubtitle = new JLabel("Click to select card, then attack.");
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

    c.weighty = 1;
    c.gridy = 3;
    JPanel bottomFiller = new JPanel();
    bottomFiller.setBackground(bg);
    cardsMenuPanel.add(bottomFiller, c);

  }

  // paintcomponent
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
  }

  // main method
  public static void main(String[] args) {

    new Driver();
  }

  // handles all events (from buttons) based on the action command
  // takes in the event as a parameter
  // void
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

          JButton jb = new JButton(albums.get(i).getNum() + "");
          jb.setPreferredSize(new Dimension(40, 40));
          jb.addActionListener(this);
          jb.setActionCommand("select" + albums.get(i).getNum());
          final int labelNum = albums.get(i).getNum();
          final String labelDate = albums.get(i).getDate().toString();
          final int labelCapacity = albums.get(i).getCapacity();
          final int labelCards = albums.get(i).getNumCards();
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

          albumButtons.add(jb);

          albumListPanel.add(jb);
          albumListPanel.revalidate();
        }

      } catch (IOException e) {
        e.printStackTrace();
      }
    } else if (eventName.equals("returnToAlbums") || eventName.equals("albumRemove")) {
      cardsMenuPanel.setVisible(false);

      if (eventName.equals("albumRemove")) {
        int i = albums.indexOf(selectedAlbum);
        JButton buttonToRemove = albumButtons.get(i);

        albums.remove(selectedAlbum);
        albumButtons.remove(i);
        ids.remove(selectedAlbum.getNum());

        albumListPanel.remove(buttonToRemove);
        albumListPanel.revalidate();

      }

      albumMenuPanel.setVisible(true);
      frame.setContentPane(albumMenuPanel);

      //resetting
      cardList.removeAll();
      attackList.removeAll();
      cardName.setText("Card: ");
      cardHp.setText("Hp: ");
      cardType.setText("Type: ");
      cardDate.setText("Date: ");
      attackName.setText("Name: ");
      attackDesc.setText("Description: ");
      attackDamage.setText("Damage: ");

      // refresh the album buttons (need to update the hover listeners with new total Hp and number of cards)

      albumListPanel.removeAll();
      albumButtons.clear();

      for (Album album : albums) {
        JButton jb = new JButton(album.getNum() + "");
        jb.setPreferredSize(new Dimension(40, 40));
        jb.addActionListener(this);
        jb.setActionCommand("select" + album.getNum());
        final int labelNum = album.getNum();
        final String labelDate = album.getDate().toString();
        final int labelCapacity = album.getCapacity();
        final int labelCards = album.getNumCards();
        final int labelHp = album.getHp();
        jb.addMouseListener(new MouseAdapter() {
          public void mouseEntered(MouseEvent evt) {
            albumNum.setText("Album #: " + labelNum);
            albumDate.setText("Date: " + labelDate);
            albumCapacity.setText("Capacity: " + labelCapacity);
            albumCards.setText("# Cards: " + labelCards);
            albumTotalHp.setText("Total HP: " + labelHp);
          }
        });

        albumButtons.add(jb);

        albumListPanel.add(jb);
        albumListPanel.revalidate();
      }


    } else if (eventName.equals("stats")) {

      popup = new JFrame();
      JOptionPane.showMessageDialog(popup, "Stats.");

    } else if (eventName.startsWith("select")) {
      // reset the hovered info
      albumNum.setText("Album #: ");
      albumDate.setText("Date: ");
      albumCapacity.setText("Capacity: ");
      albumCards.setText("# Cards: ");
      albumTotalHp.setText("Total HP: ");

      int selectedNum = Integer.parseInt(eventName.substring(6));
//      System.out.println("selected album: " + selectedNum);
      selectedAlbum = albums.get(albums.indexOf(new Album(selectedNum, null, 0, null)));
      albumMenuPanel.setVisible(false);
      cardsMenuPanel.setVisible(true);
      frame.setContentPane(cardsMenuPanel);

      albumsTitle.setText("Selected Album: " + selectedAlbum.getNum());

      refreshCards();
      cardsMenuList.revalidate();
    } else if (eventName.startsWith("cardSelect")) {
      // todo change to using card name or something instead of index, bc this will cause problems with indexing once we start removing cards
      // ^ or keep using index, but then removeAll and re-add all the cards based on the updated list and indexes
      int index = Integer.parseInt(eventName.substring(10));
      selectedCard = selectedAlbum.getCards().get(index);
      cardName.setText("Card: " + selectedCard.getName());
      cardHp.setText("Hp: " + selectedCard.getHp());
      cardType.setText("Type: " + selectedCard.getType());
      cardDate.setText("Date: " + selectedCard.getDate().toString());

      attackName.setText("Name: ");
      attackDesc.setText("Description: ");
      attackDamage.setText("Damage: ");

      attackList.removeAll();

      // display all attacks of the selected card

      for (int i = 0; i < selectedCard.getAttacks().size(); i++) {
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

    } else if (eventName.startsWith("attackSelect")) {
      int index = Integer.parseInt(eventName.substring(12));
      Attack selectedAttack = selectedCard.getAttacks().get(index);
      attackName.setText("Name: " + selectedAttack.getName());
      attackDesc.setText("Description: " + selectedAttack.getDesc());
      attackDamage.setText("Damage: " + selectedAttack.getDamage());

    } else if (eventName.equals("cardSort")) {
      String[] options = {"Sort by name", "Sort by hp", "Sort by date"};
      String sortOption = (String) JOptionPane.showInputDialog(null, "Choose Sorting Option",
          null, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);
      if (sortOption != null) {
        cardList.removeAll();

        if (sortOption.equals("Sort by name")) {
          Collections.sort(selectedAlbum.getCards());
        } else if (sortOption.equals("Sort by hp")) {
          Collections.sort(selectedAlbum.getCards(), new compareByHp());
        } else if (sortOption.equals("Sort by date")) {
          Collections.sort(selectedAlbum.getCards(), new compareByDate());
        }

//        albums.set(albums.indexOf(new Album(selectedAlbum.getNum(), null, 0, null)), selectedAlbum);

        refreshCards();


      }

    } else if (eventName.equals("cardAdd")) {
      if (selectedAlbum.getCapacity() == selectedAlbum.getCards().size()) {
        popup = new JFrame();
        JOptionPane.showMessageDialog(popup, "Reached max capacity. Free up space before adding another card.", "Error", JOptionPane.ERROR_MESSAGE);
      } else {
        JTextField nameField = new JTextField(5);
        JTextField hpField = new JTextField(5);
        JTextField typeField = new JTextField(5);
        JTextField attacksField = new JTextField(5);
        JTextField dateField = new JTextField(5);
        JPanel cardAddPanel = new JPanel();

        cardAddPanel.setLayout(new BoxLayout(cardAddPanel, BoxLayout.PAGE_AXIS));
        JLabel nameLabel = new JLabel("Card Name:");
        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        cardAddPanel.add(nameLabel);
        cardAddPanel.add(nameField);
        cardAddPanel.add(Box.createVerticalStrut(10));
        JLabel hpLabel = new JLabel("Card HP:");
        hpLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        cardAddPanel.add(hpLabel);
        cardAddPanel.add(hpField);
        cardAddPanel.add(Box.createVerticalStrut(10));
        JLabel typeLabel = new JLabel("Card Type:");
        typeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        cardAddPanel.add(typeLabel);
        cardAddPanel.add(typeField);
        cardAddPanel.add(Box.createVerticalStrut(10));
        JLabel attacksLabel = new JLabel("# of Attacks:");
        attacksLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        cardAddPanel.add(attacksLabel);
        cardAddPanel.add(attacksField);
        cardAddPanel.add(Box.createVerticalStrut(10));
        JLabel dateLabel = new JLabel("Date (mm/dd/yyyy):");
        dateLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        cardAddPanel.add(dateLabel);
        cardAddPanel.add(dateField);
        cardAddPanel.add(Box.createVerticalStrut(10));


        int result = JOptionPane.showConfirmDialog(null, cardAddPanel,
            "Create A Card", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
          popup = new JFrame();
          // lots of error checking below

          if (nameField.getText().isEmpty() || hpField.getText().isEmpty() || typeField.getText().isEmpty() || attacksField.getText().isEmpty() || dateField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(popup, "Fill in all text fields.", "Error", JOptionPane.ERROR_MESSAGE);
          } else {
            try {
              int hp = Integer.parseInt(hpField.getText());
              int attacks = Integer.parseInt(attacksField.getText());
              if (hp < 0 || attacks < 0) {
                JOptionPane.showMessageDialog(popup, "Hp and attacks must be positive.", "Error", JOptionPane.ERROR_MESSAGE);
              } else if (!Date.isValid(dateField.getText())) {
                JOptionPane.showMessageDialog(popup, "Invalid Date.", "Error", JOptionPane.ERROR_MESSAGE);

              } else {

                ArrayList<Attack> attackList = new ArrayList<Attack>();
                int numAttacks = Integer.parseInt(attacksField.getText());
                boolean cancelled = false;
                for (int i = 0; i < numAttacks; i++) {
                  if (cancelled) {
                    break;
                  }
                  while (true) {
                    JTextField infoField = new JTextField(5);
                    JTextField damageField = new JTextField(5);
                    JPanel attackAddPanel = new JPanel();

                    attackAddPanel.setLayout(new BoxLayout(attackAddPanel, BoxLayout.PAGE_AXIS));
                    JLabel infoLabel = new JLabel("Attack (SEPARATE NAME & DESC WITH '-'):");
                    infoLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
                    attackAddPanel.add(infoLabel);
                    attackAddPanel.add(infoField);
                    attackAddPanel.add(Box.createVerticalStrut(10));
                    JLabel damageLabel = new JLabel("Damage:");
                    damageLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
                    attackAddPanel.add(damageLabel);
                    attackAddPanel.add(damageField);
                    attackAddPanel.add(Box.createVerticalStrut(10));

                    result = JOptionPane.showConfirmDialog(null, attackAddPanel,
                        "Create Attack #" + (i + 1) + " of " + numAttacks, JOptionPane.OK_CANCEL_OPTION);
                    if (result == JOptionPane.OK_OPTION) {
                      if (infoField.getText().isEmpty() || damageField.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(popup, "Fill in all text fields.", "Error", JOptionPane.ERROR_MESSAGE);
                      } else {
                        if (!Attack.isValid(infoField.getText())) {
                          JOptionPane.showMessageDialog(popup, "Invalid attack info.", "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                          attackList.add(new Attack(infoField.getText(), damageField.getText()));
                          break;
                        }

                      }
                    } else {
                      cancelled = true;
                      break;
                    }
                  }
                }

                if (!cancelled) {
                  Card newCard = new Card(nameField.getText(), hp, typeField.getText(), new Date(dateField.getText()), attackList);
//                System.out.println("Card added: " + newCard.getName() + " " + newCard.getHp() + " " + newCard.getType() + " " + newCard.getDate());
                  selectedAlbum.addCard(newCard);
                  refreshCards();
                }

              }
            } catch (NumberFormatException e) {
              JOptionPane.showMessageDialog(popup, "Hp and attacks must be integers.", "Error", JOptionPane.ERROR_MESSAGE);
            }
          }

        }
      }
    } else if (eventName.startsWith("cardRemove")) {
      int index = Integer.parseInt(eventName.substring(10));

      Card selectedCard = selectedAlbum.getCards().get(index);
      selectedAlbum.removeCard(selectedCard);

      // reset displayed info since card was removed (to be safe)

      cardName.setText("Card: ");
      cardHp.setText("Hp: ");
      cardType.setText("Type: ");
      cardDate.setText("Date: ");
      attackName.setText("Name: ");
      attackDesc.setText("Description: ");
      attackDamage.setText("Damage: ");

      attackList.removeAll();
      attackList.revalidate();

      refreshCards();


    } else if (eventName.startsWith("attackEdit")) {
      int index = Integer.parseInt(eventName.substring(10));
      Attack selectedAttack = selectedCard.getAttacks().get(index);

      JTextField nameField = new JTextField(selectedAttack.getName(), 5);
      JTextField descField = new JTextField(selectedAttack.getDesc(), 5);
      JTextField damageField = new JTextField(selectedAttack.getDamage(), 5);
      JPanel attackAddPanel = new JPanel();

      attackAddPanel.setLayout(new BoxLayout(attackAddPanel, BoxLayout.PAGE_AXIS));
      JLabel nameLabel = new JLabel("Name:");
      nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
      attackAddPanel.add(nameLabel);
      attackAddPanel.add(nameField);
      attackAddPanel.add(Box.createVerticalStrut(10));
      JLabel descLabel = new JLabel("Description:");
      descLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
      attackAddPanel.add(descLabel);
      attackAddPanel.add(descField);
      attackAddPanel.add(Box.createVerticalStrut(10));
      JLabel damageLabel = new JLabel("Damage:");
      damageLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
      attackAddPanel.add(damageLabel);
      attackAddPanel.add(damageField);
      attackAddPanel.add(Box.createVerticalStrut(10));

      int result = JOptionPane.showConfirmDialog(null, attackAddPanel,
          "Editing Attack", JOptionPane.OK_CANCEL_OPTION);
      if (result == JOptionPane.OK_OPTION) {
        popup = new JFrame();
        if (nameField.getText().isEmpty() || descField.getText().isEmpty() || damageField.getText().isEmpty()) {
          JOptionPane.showMessageDialog(popup, "Fill in all text fields.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
          // make all updates for the verified attack
          attackName.setText("Name: " + nameField.getText());
          attackDesc.setText("Description: " + descField.getText());
          attackDamage.setText("Damage: " + damageField.getText());

          selectedAttack.setName(nameField.getText());
          selectedAttack.setDesc(descField.getText());
          selectedAttack.setDamage(damageField.getText());

          selectedCard.getAttacks().set(index, selectedAttack);
          selectedAlbum.getCards().set(selectedAlbum.getCards().indexOf(selectedCard), selectedCard);
//          albums.set(albums.indexOf(new Album(selectedAlbum.getNum(), null, 0, null)), selectedAlbum);


        }
      }
    }
  }

  // refreshes the card list, called after adding/removing/sorting cards so indexes on listeners stay working
  // no params, uses global graphics components
  // void
  public void refreshCards() {
    cardList.removeAll();
    for (int i = 0; i < selectedAlbum.getCards().size(); i++) {
      JPanel cardItem = new JPanel(new FlowLayout(FlowLayout.LEADING, 5, 0));
      JLabel cardName = new JLabel(selectedAlbum.getCards().get(i).getName());
      cardName.setForeground(Color.WHITE);
      JButton cardSelect = new JButton("→");
      cardSelect.setMargin(new Insets(2, 3, 2, 3));
      cardSelect.addActionListener(this);
      cardSelect.setActionCommand("cardSelect" + i);
      JButton cardRemove = new JButton("x");
      cardRemove.setMargin(new Insets(2, 3, 2, 3));
      cardRemove.addActionListener(this);
      cardRemove.setActionCommand("cardRemove" + i);
      cardItem.add(cardName);
      cardItem.add(cardSelect);
      cardItem.add(cardRemove);
      cardItem.setBorder(BorderFactory.createEmptyBorder(0, 0, 8, 0));
      cardItem.setBackground(dark1);

      cardList.add(cardItem);

    }
    cardList.revalidate();
  }

  // add a new album
  // takes in the file name
  // returns the album to be added to the arraylist
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
      if (ids.contains(albumNum)) {
        popup = new JFrame();
        JOptionPane.showMessageDialog(popup, "Already added.");
        return null;
      }
      ids.add(albumNum);
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
      JOptionPane.showMessageDialog(popup, "File not found.", "Error", JOptionPane.ERROR_MESSAGE);
    }

    return null;

  }
}
