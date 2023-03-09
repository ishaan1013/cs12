package recursionpractise;

public class Main {

  public static void main(String[] args) {
    double res = rec(6);
    System.out.printf("%.5f", res);
  }

  static double rec(int n) {
    if (n <= -1)
      return -1;
    else if (n == 0)
      return 0;
    else if (n == 1) {
      return n;
    } else {
      if (n % 2 == 0)
        return ((1.0 / n) + rec(n - 2));
      else {
        return ((1.0 / n) * rec(n - 2));
      }
    }
  }

}
