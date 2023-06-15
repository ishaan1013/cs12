package isu;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Upgrade {

  private int cost;
  private String name;

  private int fireRateChange;
  private int damageChange;
  private int rangeChange;
  private boolean camoChange;
  private boolean leadChange;

  private JPanel panel;

  private Font smF;

  public Upgrade(int cost, String name, String category, int fireRateChange, int damageChange, int rangeChange, boolean camoChange, boolean leadChange) {
    this.cost = cost;
    this.name = name;
    this.fireRateChange = fireRateChange;
    this.damageChange = damageChange;
    this.rangeChange = rangeChange;
    this.camoChange = camoChange;
    this.leadChange = leadChange;

    panel = new JPanel();
    JLabel nameLabel = new JLabel();
    JLabel infoLabel = new JLabel();

    panel.setLayout(new GridLayout(2, 1, 5, 5));
    panel.setBackground(new Color(87, 60, 43, 200));
    panel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

    try {
      smF = Font.createFont(Font.TRUETYPE_FONT, new File("assets/font/oetztype.ttf")).deriveFont(12f);
      GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

      ge.registerFont(smF);
    } catch (IOException | FontFormatException e) {
      e.printStackTrace();
    }

    nameLabel.setFont(smF);
    nameLabel.setForeground(Color.WHITE);
    nameLabel.setText(name);

    infoLabel.setFont(smF);
    infoLabel.setForeground(Color.YELLOW);
    infoLabel.setText("$" + cost + " - " + category);

    panel.add(nameLabel);
    panel.add(infoLabel);

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
    return panel;
  }


}
