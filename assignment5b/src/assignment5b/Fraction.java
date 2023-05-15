package assignment5b;

public class Fraction {

  private double value;

  public double getValue() {
    return value;
  }

  public Fraction(int numerator, int denominator) {
    this.value = (double) numerator / denominator;
  }


}
