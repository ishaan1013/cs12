package assignment6;

public class Word implements Comparable<Word> {

  private String w;
  private int count;

  // constructor takes word & frequency
  public Word(String s, int n) {
    w = s.trim();
    count = n;
  }

  // getter and setter methods
  public String getWord() {
    return w;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int n) {
    count += n;
  }

  // sort by frequency, then name
  public int compareTo(Word word) {
    if (word.count == this.count)
      return w.toLowerCase().compareTo(word.w.toLowerCase());
    return word.count - this.count;
  }

  public String toString() {
    return this.w;
  }

  // the equals method checks the word itself
  public boolean equals(Object o) {
    Word word = (Word) o;
    return w.equalsIgnoreCase(word.w);
  }

  public int hashCode() {
    return w.toLowerCase().hashCode();
  }
}