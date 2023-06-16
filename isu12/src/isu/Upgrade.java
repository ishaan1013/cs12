package isu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

public class Upgrade {

  private String towerID;

  private boolean disabled;
  private boolean bought;
  //  private int index;
  private int cost;
  private String name;

  private int fireRateChange;
  private int damageChange;
  private int rangeChange;
  private boolean camoChange;
  private boolean leadChange;

  private MouseListener ml;

  private JPanel panel;
  private JLabel nameLabel, infoLabel;

  private Font smF;

  public Upgrade(String id, int index, boolean disabled, int cost, String name, String category, int fireRateChange, int damageChange, int rangeChange, boolean camoChange, boolean leadChange) {

    this.towerID = id;
//    this.index = index;
    this.cost = cost;
    this.name = name;
    this.fireRateChange = fireRateChange;
    this.damageChange = damageChange;
    this.rangeChange = rangeChange;
    this.camoChange = camoChange;
    this.leadChange = leadChange;

    this.disabled = disabled;
    this.bought = false;

    panel = new JPanel();
    nameLabel = new JLabel();
    infoLabel = new JLabel();

    panel.setLayout(new GridLayout(2, 1, 5, 5));
    panel.setBackground(new Color(87, 60, 43, 200));
    panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

    this.ml = new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        System.out.println("Clicked " + name + " upgrade");

        for (Tower t : Game.getTowers()) {
//            System.out.println(t.getID() + " " + towerID);
          if (t.getID().equals(towerID)) {
            System.out.println("upgrading...");
            t.upgrade();
            return;
          }
        }
        System.out.println("no matching tower found.");
      }
    };

    try {
      smF = Font.createFont(Font.TRUETYPE_FONT, new File("assets/font/oetztype.ttf")).deriveFont(12f);
      GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

      ge.registerFont(smF);
    } catch (IOException | FontFormatException e) {
      e.printStackTrace();
    }

    nameLabel.setFont(smF);
    nameLabel.setText(name);

    infoLabel.setFont(smF);
    infoLabel.setText("$" + cost + " - " + category);

    panel.add(nameLabel);
    panel.add(infoLabel);

  }

  public String getTowerID() {
    return towerID;
  }

  public boolean isDisabled() {
    return disabled;
  }

  public boolean isBought() {
    return bought;
  }

  public void enable() {
    disabled = false;
  }

  public void buy() {
    bought = true;
  }

  public int getCost() {
    return cost;
  }

  public String getName() {
    return name;
  }

  public int getFireRateChange() {
    return fireRateChange;
  }

  public int getDamageChange() {
    return damageChange;
  }

  public int getRangeChange() {
    return rangeChange;
  }

  public boolean getCamoChange() {
    return camoChange;
  }

  public boolean getLeadChange() {
    return leadChange;
  }

  public JPanel getPanel() {
    if (disabled) {
      panel.setBackground(new Color(56, 41, 31, 200));
      nameLabel.setForeground(Color.GRAY);
      infoLabel.setForeground(Color.GRAY);
      System.out.println(name + " is disabled");
    } else if (bought) {
      panel.setBackground(new Color(56, 41, 31, 200));
      nameLabel.setForeground(Color.GREEN.brighter());
      infoLabel.setForeground(Color.GRAY);
      panel.removeMouseListener(ml);

      System.out.println(name + " is bought");
    } else {
      nameLabel.setForeground(Color.WHITE);
      infoLabel.setForeground(Color.YELLOW);
      panel.setBackground(new Color(87, 60, 43, 200));

      panel.addMouseListener(ml);

      System.out.println(name + " is enabled");
    }

    return panel;
  }


}
